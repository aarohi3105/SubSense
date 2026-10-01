package com.aarohi.subsense.controller;

import com.aarohi.subsense.dto.SubscriptionResponseDTO;
import com.aarohi.subsense.dto.SubscriptionSummaryDTO;
import com.aarohi.subsense.dto.UpcomingRenewalDTO;
import com.aarohi.subsense.entity.Subscription;
import com.aarohi.subsense.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public SubscriptionResponseDTO addSubscription(
            @Valid @RequestBody Subscription subscription) {

        return subscriptionService.addSubscription(subscription);
    }

    @GetMapping
    public List<SubscriptionResponseDTO> getMySubscriptions() {
        return subscriptionService.getMySubscriptions();
    }
    @PutMapping("/{id}")
    public SubscriptionResponseDTO updateSubscription(
            @PathVariable Long id,
          @Valid @RequestBody Subscription subscription) {

        return subscriptionService.updateSubscription(
                id,
                subscription
        );
    }
    @DeleteMapping("/{id}")
    public String deleteSubscription(@PathVariable Long id) {

        subscriptionService.deleteSubscription(id);

        return "Subscription deleted successfully";
    }
    @GetMapping("/summary")
    public SubscriptionSummaryDTO getSubscriptionSummary() {

        return subscriptionService.getSubscriptionSummary();
    }
    @GetMapping("/upcoming")
    public List<UpcomingRenewalDTO> getUpcomingRenewals() {

        return subscriptionService.getUpcomingRenewals();
    }
    @GetMapping("/category-wise")
    public Map<String, Double> getCategoryWiseSpending() {

        return subscriptionService.getCategoryWiseSpending();
    }
}