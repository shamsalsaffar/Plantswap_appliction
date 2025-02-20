package com.java24.plantswap.controllers;


import com.java24.plantswap.models.Plants;
import com.java24.plantswap.models.Transaction;
import com.java24.plantswap.repositories.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")

public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/purchase")
    public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() { // bring all trasactions from BD
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions); // return transactions list
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {

        Optional<Transaction> transaction = transactionRepository.findById(id); // use findbyId instaed findby(id)

        transaction.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        return ResponseEntity.ok(transaction.get()); // return transaction if found it
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable String id, @Valid @RequestBody Transaction transaction) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        transactionOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
       return ResponseEntity.ok(transactionRepository.save(transaction));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable String id) {
        if (!transactionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found");
        }
        transactionRepository.deleteById(id);
        return ResponseEntity.noContent().build();

        }
    }




