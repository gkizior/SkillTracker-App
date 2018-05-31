package com.wmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

@SpringBootApplication
// @ComponentScan
@EnableJpaAuditing
public class SkillTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillTrackerApplication.class, args);
	}
}
