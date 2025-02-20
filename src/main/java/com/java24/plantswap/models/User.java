package com.java24.plantswap.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "user")
public class User {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotBlank(message = ("email cannot be empty" ))
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "That's not a valid email.")
    private String email;

@NotBlank(message = ("password cannot be empty"))
@Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters long")
@Pattern( regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,}$",
        message = "Password must contain at least one uppercase letter, one number, and one special character."
)
    private String password;

@NotBlank(message = ("address cannot be empty"))
@Size(max = 350)
@Pattern(regexp = "^[\\p{L}0-9\\s,.-/\\\\]+$",message = "Address contains invalid characters")
    private String address;

    @Pattern(regexp = "^\\+\\d{1,3}\\d{9}$", message = "That's not a valid phone number.")
    private String phone;



    @DBRef
    private List <Plants> plants; // list of plants that the user ca select

    private int totalOrders =0;  // stores total number of orders
    private BigDecimal totalAmount;  // stores total transaction amount

    @DBRef
    private List<Transaction> transactions;  // list of all transactions associated with the user
    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Plants> getPlants() {
        return plants;
    }

    public void setPlants(List<Plants> plants) {
        this.plants = plants;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
