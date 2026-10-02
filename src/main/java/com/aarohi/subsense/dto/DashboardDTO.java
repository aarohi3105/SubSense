package com.aarohi.subsense.dto;

import java.util.List;
import java.util.Map;

    public class DashboardDTO {

        private SubscriptionSummaryDTO summary;
        private Map<String, Double> categoryWiseSpending;
        private List<UpcomingRenewalDTO> upcomingRenewals;

        public DashboardDTO() {
        }

        public DashboardDTO(
                SubscriptionSummaryDTO summary,
                Map<String, Double> categoryWiseSpending,
                List<UpcomingRenewalDTO> upcomingRenewals
        ) {
            this.summary = summary;
            this.categoryWiseSpending = categoryWiseSpending;
            this.upcomingRenewals = upcomingRenewals;
        }

        public SubscriptionSummaryDTO getSummary() {
            return summary;
        }

        public void setSummary(SubscriptionSummaryDTO summary) {
            this.summary = summary;
        }

        public Map<String, Double> getCategoryWiseSpending() {
            return categoryWiseSpending;
        }

        public void setCategoryWiseSpending(
                Map<String, Double> categoryWiseSpending
        ) {
            this.categoryWiseSpending = categoryWiseSpending;
        }

        public List<UpcomingRenewalDTO> getUpcomingRenewals() {
            return upcomingRenewals;
        }

        public void setUpcomingRenewals(
                List<UpcomingRenewalDTO> upcomingRenewals
        ) {
            this.upcomingRenewals = upcomingRenewals;
        }
    }

