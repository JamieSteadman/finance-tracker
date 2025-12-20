package com.jamie.financetracker.controller;

import com.jamie.financetracker.model.Transaction;
import com.jamie.financetracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Post endpoint for transaction objects
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Returns a list of JSON arrays, each containing a transaction tuple
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Returns corresponding transaction object in JSON format based on id
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        //Returns 200 ok if found and 404 not found if missing
        return transactionService.getTransactionById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletes a transaction tuple by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}
