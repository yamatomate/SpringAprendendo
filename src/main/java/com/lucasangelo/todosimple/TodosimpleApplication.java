package com.lucasangelo.todosimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodosimpleApplication {

	public static void main(String[] args) {
		System.out.println("oi pipoca");
		SpringApplication.run(TodosimpleApplication.class, args);
		System.out.println("tchau pipoca");
	}

}
