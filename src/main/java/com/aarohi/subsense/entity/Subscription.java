package com.aarohi.subsense.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name="subscriptions")
public class Subscription {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
 private Long id;

@NotBlank(message = "Subscription name is required")
private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;


    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Renewal date is required")
    private LocalDate renewalDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) //means the subscriptions table stores the foreign key of the user.
    private User user;

    public Subscription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

