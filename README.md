# Plantswap_appliction

Description
PlantSwap is a plant exchange and sales application built using Spring Boot. It allows users to register, list plants for sale or exchange, and manage transactions. The API connects to a database MongoDB for storing user , plant and transactions data.  

## Database Plantswap
Database Structure
The PlantSwap application uses a database that contains the following main entities:

1. Users: Represents the users of the platform.<br> Collection Name: users
3. Plants:Represents the plants available for sale or exchange on the platform and other plant information as age , size etc...<br>
   Collection Name: plants

4. Transactions:Represents the transactions between users for plant exchanges or sales.<br>
   Collection Name: transactions

   # API Specification
The REST API for PlantSwap handles the following resources and operations:

#### Plants
. POST /plants: Add a new plant for exchange/sale.<br>
. GET /plants: Retrieve a list of all available plants.<br>
. GET /plants/{id}: Retrieve information about a specific plant.<br>
. PUT /plants/{id}: Update plant information.<br>
. DELETE /plants/{id}: Remove a plant listing.<br>
. GET /plants/available: Retrieve only available plants.<br>   
#### Users
. POST /users: Register a new user.<br>
. GET /users: Retrieve a list of all users.<br>
. GET /users/{id}: Retrieve a specific user’s information.<br>
. PUT /users/{id}: Update user information.<br>
. DELETE /users/{id}: Delete a user.<br>
. GET /users/{id}/plants: Retrieve all plants belonging to a specific user.<br>
#### Transactions
. POST /transactions: Register a new transaction (exchange or purchase).<br>
. GET /transactions: Retrieve all transactions.<br>
. GET /transactions/{id}: Retrieve a specific transaction.<br>
. PUT /transactions/{id}: Update a transaction.<br>
. GET /transactions/user/{userId}: Retrieve all transactions for a specific user.<br>

## Business Rules
##### . A user cannot have more than 10 active listings at the same time.

 ```java
 
 @PostMapping("/user/{id}") // to add plant to user
    public ResponseEntity<Plants> createPlantsForUser(@RequestBody @Valid Plants plants, @PathVariable String id) {
        User user = userRepository.findById(id)  // find user
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Check if the user already has 10 active listings
        long activeCount = plantsRepository.countByUserIdAndPlantStatus(user.getId(), PlantStatus.AVAILABLE);
        if (activeCount >= 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannot have more than 10 active listings");
        }

        List<Plants> userPlants = user.getPlants(); // create list to store user's plants
        if (userPlants == null) {
            userPlants = new ArrayList<>();
        }
```
##### . Plants marked for exchange can only be exchanged for other plants, not sold.<br>

 ```java 

@PutMapping("/{transactionId}/EXCHANGED") // Plants marked for exchange can only be exchanged for other plants
    public ResponseEntity<Transaction> markTransactionAsExchanged(@PathVariable String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));

        transaction.setStatus(TransactionStatus.EXCHANGED);

        if (transaction.getPlants() == null) {
            transaction.setPlants(new ArrayList<>());
        }

        for (Plants plant : transaction.getPlants()) {
            plant.setPlantStatus(PlantStatus.EXCHANGED);
            plantsRepository.save(plant);
        }

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(updatedTransaction);
    }
 ```
    
##### . Both parties must agree to the exchange before it is executed.<br>
##### . Priced plants must have a fixed price between 50 and 1000 SEK.<br>
         when use [@] Vaild in Trasaction control can prove vaildation in this code below  


 ```java 

 @DecimalMin(value = "50.00", message = "price must be at least 50 kr ")
    @DecimalMax(value = "1000.00", message = "Price must be at most 1000 kr")
    private BigDecimal price; 
  ```
  
