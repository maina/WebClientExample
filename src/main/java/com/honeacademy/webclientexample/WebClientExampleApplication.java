package com.honeacademy.webclientexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.honeacademy.fieldconverter")
public class WebClientExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClientExampleApplication.class, args);
	}

}
