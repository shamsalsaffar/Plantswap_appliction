package com.java24.plantswap.models;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    @DBRef (lazy = false)
    @NotNull(message = "Enter seller's name ")
    private User seller;

    @DBRef (lazy = false)
    @NotNull(message = "Enter buyer's name")
    private User buyer;

   @DBRef (lazy = false)
   @NotNull(message = "Enter plants' name ")
   private Plants plant;


@NotNull (message = "Enter total orders")
    private int totalOrders;  // stores total number of orders
    private BigDecimal totalAmount; // stores  total transaction amount


    private TransactionStatus status; // enum of transaction status
@CreatedDate // date of create transaction
    private LocalDate date;


    @Transient  // Automatically fetch and return the seller's name
    private String sellerName;


    @Transient   // Automatically fetch and return the buyer's name
    private String buyerName;

    public Transaction() {
        date = LocalDate.now();
    }

    @Transient // Automatically fetch and return plant status
    public PlantStatus getPlantStatus() {
        return (plant != null) ? plant.getPlantStatus() : null;
    }


    @Transient // Automatically fetch and return the plant
    public Plants getPlant() {
        return plant;
    }

    public void setPlant(Plants plant) {
        this.plant = plant;
    }



   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Transient
    public String getSellerName() {
        return seller != null ? seller.getName() : "Not Available";
    }

    @Transient
    public String getBuyerName() {
        return buyer != null ? buyer.getName() : "Not Available";
    }

}
