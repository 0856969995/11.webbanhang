package com.example.webbanhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.webbanhang")
public class WebbanhangApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebbanhangApplication.class, args);
	}
}
