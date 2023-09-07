package com.xxx.xcx01_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // registry.addResourceHandler("/p/**").addResourceLocations("file:" + "F:/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:C:/Users/x/Pictures/");
        registry.addResourceHandler("/goods_swiper/**").addResourceLocations("file:C:/Users/x/Pictures/goods_swiper/");
    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .allowedOriginPatterns("*")
//                .allowCredentials(true)
//                .maxAge(7200);
//    }


}