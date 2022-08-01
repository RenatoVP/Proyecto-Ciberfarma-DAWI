package com.cibertec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/producto/imagen/**").addResourceLocations("file:C:/Mis Archivos/Java_Proyects/Spring/CiberfarmaApp_Proyect/Files/images/");
	}
}
