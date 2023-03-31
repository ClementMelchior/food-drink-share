package com.idovia.jus_de_bagarre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class JusDeBagarreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JusDeBagarreApplication.class, args);
	}

}
