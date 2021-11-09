package com.back.websters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebstersApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebstersApplication.class, args);
	}

}
