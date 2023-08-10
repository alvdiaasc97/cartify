package com.cartify.cartify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CertifyApplication {

	public static void main(String[] args) {

		SpringApplication.run(CertifyApplication.class, args);
	}

}
