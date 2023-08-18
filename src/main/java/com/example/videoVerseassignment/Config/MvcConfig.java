package com.example.videoVerseassignment.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir= Paths.get("./photo");
        String uploadPath=uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/photo**").addResourceLocations("file:/"+uploadPath+"/");
    }


    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
