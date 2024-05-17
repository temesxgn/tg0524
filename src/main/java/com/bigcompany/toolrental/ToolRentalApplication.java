package com.bigcompany.toolrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ToolRentalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ToolRentalApplication.class, args);
		ConsoleReader consoleReader = context.getBean(ConsoleReader.class);
		consoleReader.readInput();
	}

}
