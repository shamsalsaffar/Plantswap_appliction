package com.java24.plantswap.repositories;


import com.java24.plantswap.models.PlantStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.java24.plantswap.models.Plants;

import java.util.List;
import java.util.Optional;

public interface PlantsRepository extends MongoRepository<Plants,String> {



    List<Plants> findByPlantStatus(PlantStatus plantstatus); // To search in plants Status
}


