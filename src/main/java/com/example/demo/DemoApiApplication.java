package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
	
@SpringBootApplication
@ComponentScan("com.example.demo")
public class DemoApiApplication {

	public static void main(String[] args) {
		 ApplicationContext context = SpringApplication.run(DemoApiApplication.class, args);
		 System.out.println("Contains persona_repo  "+context.
	                containsBeanDefinition("repositorio_pers"));
	}

}
