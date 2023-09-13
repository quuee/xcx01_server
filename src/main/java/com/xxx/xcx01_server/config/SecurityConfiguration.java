package com.xxx.xcx01_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxx.xcx01_server.common.utils.WXUtil;
import com.xxx.xcx01_server.security.*;
import com.xxx.xcx01_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfiguration {

    @Autowired
    private MyAuthExceptionEntryPoint myAuthExceptionEntryPoint;

    @Autowired
    private WXAppletAuthenticationManager wxAppletAuthenticationManager;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private WXUtil wxUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private WXAuthenticationSuccessHandler wxAuthenticationSuccessHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .requestMatchers("/static/**", "/xcx01-images/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement((sessionManagement) -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        httpSecurity.authorizeHttpRequests((authorizeRequests) -> {
            authorizeRequests.anyRequest().authenticated();

        }).exceptionHandling((exceptionHandling) -> {
            exceptionHandling.authenticationEntryPoint(myAuthExceptionEntryPoint);
        });

        httpSecurity.addFilterAt(wxAppletAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationTokenFilter(), WXAppletAuthenticationFilter.class);
//        httpSecurity.authenticationManager(wxAppletAuthenticationManager);

        return httpSecurity.build();
    }

    @Bean
    public WXAppletAuthenticationFilter wxAppletAuthenticationFilter() {
        WXAppletAuthenticationFilter wxAppletAuthenticationFilter = new WXAppletAuthenticationFilter();
        wxAppletAuthenticationFilter.setJsonMapper(jsonMapper);
        wxAppletAuthenticationFilter.setWxUtil(wxUtil);
        wxAppletAuthenticationFilter.setAuthenticationManager(wxAppletAuthenticationManager);
        wxAppletAuthenticationFilter.setAuthenticationSuccessHandler(wxAuthenticationSuccessHandler);
        return wxAppletAuthenticationFilter;
    }

    @Bean
    public JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JWTAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setUserService(userService);
        jwtAuthenticationTokenFilter.setHandlerExceptionResolver(handlerExceptionResolver);
        jwtAuthenticationTokenFilter.setJsonMapper(jsonMapper);
        return jwtAuthenticationTokenFilter;
    }


}
