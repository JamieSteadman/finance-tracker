package com.jamie.financetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jamie.financetracker.cli.FinanceTrackerCLI;
import com.jamie.financetracker.service.TransactionService;

@SpringBootApplication
public class FinanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceTrackerApplication.class, args);
	}
}
