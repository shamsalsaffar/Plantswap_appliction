package com.java24.plantswap.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Transaction extends MongoRepository<Transaction, String> {

}
