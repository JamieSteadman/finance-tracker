package com.jamie.financetracker.cli;

import com.jamie.financetracker.model.Transaction;
import com.jamie.financetracker.model.TransactionType;
import com.jamie.financetracker.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class FinanceTrackerCLI implements CommandLineRunner {
    private final TransactionService transactionService;

    public FinanceTrackerCLI(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Personal Finance Tracker ===");
            System.out.println("1. Add transaction");
            System.out.println("2. View all transactions");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTransaction(scanner);
                case 2 -> viewTransactions();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    private void addTransaction(Scanner scanner) {
        System.out.println("Amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Type (INCOME/EXPENSE): ");
        TransactionType type = TransactionType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Category: ");
        String category = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        Transaction transaction = new Transaction(amount, type, category, description, LocalDate.now());
        transactionService.createTransaction(transaction);
        System.out.println("Transaction saved!");
    }
    private void viewTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        for (Transaction t : transactions) {
            System.out.println(t.getId() + " | " + t.getDate() + " | " + t.getType() + " | " +
                    t.getAmount() + " | " +
                    t.getCategory());
        }
    }
}