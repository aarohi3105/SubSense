package com.aarohi.subsense.dto;

import java.time.LocalDate;

public class UpcomingRenewalDTO {

    private Long id;
    private String name;



    private Double price;



    private String category;



    private LocalDate renewalDate;
    private long daysRemaining;

    public long getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(long daysRemaining) {
        this.daysRemaining = daysRemaining;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public UpcomingRenewalDTO() {
    }

    public UpcomingRenewalDTO(
            Long id,
            String name,
            Double price,
            String category,
            LocalDate renewalDate,
            long daysRemaining
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.renewalDate = renewalDate;
        this.daysRemaining = daysRemaining;
    }



}