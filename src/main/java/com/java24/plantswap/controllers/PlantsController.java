package com.java24.plantswap.controllers;

import com.java24.plantswap.models.PlantStatus;
import com.java24.plantswap.models.Plants;
import com.java24.plantswap.models.User;
import com.java24.plantswap.repositories.PlantsRepository;
import com.java24.plantswap.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/plants")

public class PlantsController {
    @Autowired
    private PlantsRepository plantsRepository;

    @Autowired
    private UserRepository userRepository;

    // HTTP post METOD`
    @PostMapping()
    public ResponseEntity<Plants> createPlants(@RequestBody @Valid Plants plants) {

        Plants createdPlants = plantsRepository.save(plants); // Pass the 'plants' object to the save method
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlants); // Return the created plant
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Plants> createPlantsForUser(@RequestBody @Valid Plants plants, @PathVariable String userId) {
        // Save the plant first
        Plants savedPlant = plantsRepository.save(plants);

        // Find the user by userId
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("Plant not found with id: " + userId));
        // Add the new plant to the user's list of plants
        List<Plants> userPlants = user.getPlants();
        if (userPlants == null) {
            userPlants = new ArrayList<>();
        }
        userPlants.add(savedPlant);

        // Update the user's plants list
        user.setPlants(userPlants);

        if (plants.getId() != null && !plants.getId().equals(savedPlant.getId())) {
            savedPlant.setId(plants.getId());
        }
        if (plants.getName() != null && !plants.getName().isEmpty()) {
            savedPlant.setName(plants.getName());
        }
        if (plants.getTrivialName() != null && !plants.getTrivialName().isEmpty()) {
            savedPlant.setTrivialName(plants.getTrivialName());
        }
        if (plants.getAge() > 0) {
            savedPlant.setAge(plants.getAge());
        }
        if (plants.getPlantSize() != null) {
            savedPlant.setPlantSize(plants.getPlantSize());
        }
        if (plants.getPlantType() != null) {
            savedPlant.setPlantType(plants.getPlantType());
        }
        if (plants.getWaterRequirement() != null) {
            savedPlant.setWaterRequirement(plants.getWaterRequirement());
        }
        if (plants.getLightRequirement() != null) {
            savedPlant.setLightRequirement(plants.getLightRequirement());
        }
        if (plants.getDifficultyLevel() != null) {
            savedPlant.setDifficultyLevel(plants.getDifficultyLevel());
        }
        if (plants.getPhoto() != null && !plants.getPhoto().isEmpty()) {
            savedPlant.setPhoto(plants.getPhoto());
        }
        if (plants.getPlantStatus() != null) {
            savedPlant.setPlantStatus(plants.getPlantStatus());
        }
        if (plants.getPayMethod() != null) {
            savedPlant.setPayMethod(plants.getPayMethod());
        }
        if (plants.getPrice() != null && plants.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            savedPlant.setPrice(plants.getPrice());
        }

        // Save the updated user
        userRepository.save(user);


        // Return the created plant response
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);
    }


    @GetMapping
    public ResponseEntity<List<Plants>>getAllPlants(){
        List<Plants> plants = plantsRepository.findAll();

        return ResponseEntity.ok(plants);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Plants>getPlantsById(@PathVariable String id) {
        Plants plants = (Plants) plantsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return ResponseEntity.ok(plants);

    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Plants>> getPlantbystatus(@PathVariable PlantStatus status  ) {
        List<Plants> plants = plantsRepository.findByPlantStatus(status);
        return ResponseEntity.ok(plants);
    }
@PutMapping("/{id}")
    public ResponseEntity<Plants> updatePlants(@PathVariable String id, @RequestBody Plants plantsDetails ){
        Plants existingPlants = (Plants) plantsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));

        existingPlants.setName(plantsDetails.getName());
        existingPlants.setPlantStatus(plantsDetails.getPlantStatus());
        existingPlants.setPayMethod(plantsDetails.getPayMethod());
        return ResponseEntity.ok(plantsRepository.save(existingPlants));
}
@DeleteMapping("/{id}")
public ResponseEntity<Void>deleteplant(@PathVariable String id) {
       if (!plantsRepository.existsById(id)) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plants ot found");
       }

       plantsRepository.deleteById(id);

        return ResponseEntity.noContent().build();
}

    @DeleteMapping("/{userId}")
    public ResponseEntity<Plants> deletePlants(@PathVariable String id) {
        if (!plantsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");

        }
        plantsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }




}
