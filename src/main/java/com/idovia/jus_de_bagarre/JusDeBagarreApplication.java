package com.idovia.jus_de_bagarre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class JusDeBagarreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JusDeBagarreApplication.class, args);
	}

}
