package com.trungdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookWorldShopR {

	public static void main(String[] args) {
		SpringApplication.run(BookWorldShopR.class, args);
	}

}
