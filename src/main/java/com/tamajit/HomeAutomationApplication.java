package com.tamajit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.tamajit.config.CommonConfig;
import com.tamajit.config.WebsocketConfig;

@SpringBootApplication
@Import({WebsocketConfig.class,CommonConfig.class})
public class HomeAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAutomationApplication.class, args);
	}
}
