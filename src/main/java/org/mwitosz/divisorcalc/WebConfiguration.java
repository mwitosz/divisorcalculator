/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author mwito
 */
@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
           .addResourceHandler("/*.html")
           .addResourceLocations("classpath:/static/");
        
        registry
           .addResourceHandler("/js/**")
           .addResourceLocations("classpath:/static/js/");
        
        registry
           .addResourceHandler("/css/**")
           .addResourceLocations("classpath:/static/css/");
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
           .addViewController("/")
           .setViewName("forward:/index.html");
    }
}
