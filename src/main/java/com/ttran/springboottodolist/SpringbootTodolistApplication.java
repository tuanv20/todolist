package com.ttran.springboottodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.ttran.springboottodolist.*")
@SpringBootApplication
@EntityScan("com.ttran.springboottodolist.*")
public class SpringbootTodolistApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTodolistApplication.class, args);
	}
}
