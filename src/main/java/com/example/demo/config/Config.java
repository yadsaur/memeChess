package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class Config {
	
	 @Bean
	 public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		 return new PropertySourcesPlaceholderConfigurer();
	 }

}
