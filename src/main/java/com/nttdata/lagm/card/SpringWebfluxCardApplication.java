package com.nttdata.lagm.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringWebfluxCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxCardApplication.class, args);
	}

}
