package com.wmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan // Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
public class SkillTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillTrackerApplication.class, args);
	}
}
