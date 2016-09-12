package com.tamajit.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CommonConfig {
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

}
