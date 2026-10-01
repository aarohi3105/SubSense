package com.aarohi.subsense.dto;

import java.time.LocalDate;

public class SubscriptionResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private String category;
    private LocalDate renewalDate;
    private UserResponseDTO user;

    public SubscriptionResponseDTO() {
    }

    public SubscriptionResponseDTO(
            Long id,
            String name,
            Double price,
            String category,
            LocalDate renewalDate,
            UserResponseDTO user
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.renewalDate = renewalDate;
        this.user = user;
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

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}