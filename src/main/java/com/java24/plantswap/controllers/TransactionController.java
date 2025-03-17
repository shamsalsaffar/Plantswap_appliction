package com.java24.plantswap.controllers;


import com.java24.plantswap.models.PlantStatus;
import com.java24.plantswap.models.Plants;
import com.java24.plantswap.models.Transaction;
import com.java24.plantswap.models.TransactionStatus;
import com.java24.plantswap.repositories.PlantsRepository;
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

    // lägger till för att kunna göra metoderna för godkännande av transaktion
    @Autowired
    private PlantsRepository plantsRepository;

    // lägger till nya endpoints för godkännande från båda parter

    // du skulle nog behöva använda setter med för alla gamla fält i metoderna så att dom inte
    // körs över och blir null... men typ nåt sånt här skulle lösa ditt problem :)

    // metod som låter en seller godkänna trnasaktionen
    @PutMapping("/{id}/approve/seller")
    public ResponseEntity<Transaction> approveTransactionSeller(@PathVariable String id) {
        // först måste vi hitta den existerande transaktionen som ska godkännas
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        // sätter status till true, att den är accepterad av seller
        transaction.setSellerApproved(true);

        // kontrollera om båda har godkänt och uppdatera status om så är fallet
        if (transaction.isSellerApproved() && transaction.isBuyerApproved()) {
            transaction.setStatus(TransactionStatus.COMPLETED);

            // uppdatera plantans status om det är ett byte
            Plants plant = transaction.getPlant();
            if (plant != null) {
                plant.setPlantStatus(PlantStatus.EXCHANGED);
                plantsRepository.save(plant);
            }
        }

        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    // metod som låter en buyer godkänna transaktionen
    @PutMapping("/{id}/approve/buyer")
    public ResponseEntity<Transaction> approveTransactionBuyer(@PathVariable String id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        transaction.setBuyerApproved(true);

        // kontrollera om båda har godkänt och uppdatera status om så är fallet
        if (transaction.isSellerApproved() && transaction.isBuyerApproved()) {
            transaction.setStatus(TransactionStatus.COMPLETED);

            // uppdatera plantans status om det är ett byte
            Plants plant = transaction.getPlant();
            if (plant != null) {
                plant.setPlantStatus(PlantStatus.EXCHANGED);
                plantsRepository.save(plant);
            }
        }

        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

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




