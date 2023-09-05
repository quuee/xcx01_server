package com.xxx.xcx01_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.xcx01_server.common.utils.WXUtil;
import com.xxx.xcx01_server.security.*;
import com.xxx.xcx01_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
// 开启注解设置权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthExceptionEntryPoint myAuthExceptionEntryPoint;

    @Autowired
    private WXAppletAuthenticationManager wxAppletAuthenticationManager;

    @Autowired
    private WXAuthenticationSuccessHandler wxAuthenticationSuccessHandler;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private WXUtil wxUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests()
                .antMatchers("/index","/static/**","/images/**","/goods_swiper/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(myAuthExceptionEntryPoint);
        http.addFilterAt(wxAppletAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationTokenFilter(),WXAppletAuthenticationFilter.class);

    }

    @Bean
    public WXAppletAuthenticationFilter wxAppletAuthenticationFilter(){
        WXAppletAuthenticationFilter wxAppletAuthenticationFilter = new WXAppletAuthenticationFilter("/user/login");
        wxAppletAuthenticationFilter.setJsonMapper(jsonMapper);
        wxAppletAuthenticationFilter.setWxUtil(wxUtil);
        wxAppletAuthenticationFilter.setAuthenticationManager(wxAppletAuthenticationManager);
        wxAppletAuthenticationFilter.setAuthenticationSuccessHandler(wxAuthenticationSuccessHandler);
        return wxAppletAuthenticationFilter;
    }

    @Bean
    public JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JWTAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setUserService(userService);
        jwtAuthenticationTokenFilter.setHandlerExceptionResolver(handlerExceptionResolver);
        jwtAuthenticationTokenFilter.setJsonMapper(jsonMapper);
        return jwtAuthenticationTokenFilter;
    }
}
