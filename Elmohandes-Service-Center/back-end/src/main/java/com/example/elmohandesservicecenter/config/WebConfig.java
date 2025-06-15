package com.example.elmohandesservicecenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String currentDir = new File("").getAbsolutePath(); // This is back-end

        String uploadDir = currentDir + File.separator + "Elmohandes-Service-Center" + File.separator + "upload" + File.separator;


        System.out.println("Upload Dir (post files): " + uploadDir);


        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:C:/Users/User/ElmohandesServiceCenter/Elmohandes-Service-Center/back-end/ElmohandesServiceCenter/upload/")
                .setCachePeriod(0);


    }
}
