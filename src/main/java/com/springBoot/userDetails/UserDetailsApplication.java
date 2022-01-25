package com.springBoot.userDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserDetailsApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserDetailsApplication.class, args);
	}
//THis is test for Git Push and auto Jenkins kikc off2
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
