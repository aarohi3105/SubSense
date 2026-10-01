package com.aarohi.subsense.dto;

public class SubscriptionSummaryDTO {

    private int totalSubscriptions;
    private double monthlySpending;
    private double yearlySpending;

    public SubscriptionSummaryDTO() {
    }

    public SubscriptionSummaryDTO(
            int totalSubscriptions,
            double monthlySpending,
            double yearlySpending
    ) {
        this.totalSubscriptions = totalSubscriptions;
        this.monthlySpending = monthlySpending;
        this.yearlySpending = yearlySpending;
    }

    public int getTotalSubscriptions() {
        return totalSubscriptions;
    }

    public void setTotalSubscriptions(int totalSubscriptions) {
        this.totalSubscriptions = totalSubscriptions;
    }

    public double getMonthlySpending() {
        return monthlySpending;
    }

    public void setMonthlySpending(double monthlySpending) {
        this.monthlySpending = monthlySpending;
    }

    public double getYearlySpending() {
        return yearlySpending;
    }

    public void setYearlySpending(double yearlySpending) {
        this.yearlySpending = yearlySpending;
    }
}