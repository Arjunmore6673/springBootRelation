package com.realtion.human;

import com.realtion.human.model.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.relation.human.model")
@EnableJpaRepositories(basePackages = {"com.relation.human.repository"})
@ComponentScan(basePackages = {"com.relation.*"})
@SpringBootApplication
public class HumanApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanApplication.class, args);
    }


    @Bean
    Response response() {
		return new Response();
    }
}
