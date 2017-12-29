package com.kennexcorp;

import com.kennexcorp.model.Question;
import com.kennexcorp.model.User;
import com.kennexcorp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuestionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/questions_api/*")
						.allowedOrigins("http://localhost:8081");
				registry.addMapping("/login_api")
						.allowedOrigins("http://localhost:8081");
			}
		};
	}
}
