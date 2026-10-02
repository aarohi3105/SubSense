package com.aarohi.subsense.service;

import com.aarohi.subsense.dto.*;
import com.aarohi.subsense.entity.Subscription;
import com.aarohi.subsense.entity.User;
import com.aarohi.subsense.repository.SubscriptionRepository;
import com.aarohi.subsense.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            UserRepository userRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public SubscriptionResponseDTO addSubscription(Subscription subscription) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        subscription.setUser(user);

        Subscription savedSubscription =
                subscriptionRepository.save(subscription);

        UserResponseDTO userDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return new SubscriptionResponseDTO(
                savedSubscription.getId(),
                savedSubscription.getName(),
                savedSubscription.getPrice(),
                savedSubscription.getCategory(),
                savedSubscription.getRenewalDate(),
                userDTO
        );
    }



    public List<SubscriptionResponseDTO> getMySubscriptions() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        List<Subscription> subscriptions =
                subscriptionRepository.findByUserEmail(email);

        return subscriptions.stream()  //List ke objects ko ek-ek karke process karne ke liye ready karta hai streams
                .map(subscription -> {

                    User user = subscription.getUser();

                    UserResponseDTO userDTO = new UserResponseDTO(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getRole()
                    );

                    return new SubscriptionResponseDTO(
                            subscription.getId(),
                            subscription.getName(),
                            subscription.getPrice(),
                            subscription.getCategory(),
                            subscription.getRenewalDate(),
                            userDTO                             //Subscription ke andar jo associated User hai, usko retrieve kar rahe hain
                    );
                })
                .toList();
    }
    //    List<SubscriptionResponseDTO> responseList = new ArrayList<>();
//
//for (Subscription subscription : subscriptions) {
//
//        SubscriptionResponseDTO dto =
//                new SubscriptionResponseDTO(
//                        subscription.getId(),
//                        subscription.getName(),                    //ye jo upar .stream ka use kiya hai uska hi simple equivalent hai
//                        subscription.getPrice(),
//                        subscription.getCategory(),
//                        subscription.getRenewalDate(),
//                        userDTO
//                );
//
//        responseList.add(dto);
//    }
//
//return responseList;

    public SubscriptionResponseDTO updateSubscription(
            Long id,
            Subscription updatedSubscription) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Subscription existingSubscription =
                subscriptionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Subscription not found")
                        );

        if (!existingSubscription.getUser().getEmail().equals(email)) {
            throw new RuntimeException(
                    "You cannot update this subscription"
            );
        }

        existingSubscription.setName(updatedSubscription.getName());
        existingSubscription.setPrice(updatedSubscription.getPrice());
        existingSubscription.setCategory(updatedSubscription.getCategory());
        existingSubscription.setRenewalDate(
                updatedSubscription.getRenewalDate()
        );

        Subscription savedSubscription =
                subscriptionRepository.save(existingSubscription);

        User user = existingSubscription.getUser();

        UserResponseDTO userDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return new SubscriptionResponseDTO(
                savedSubscription.getId(),
                savedSubscription.getName(),
                savedSubscription.getPrice(),
                savedSubscription.getCategory(),
                savedSubscription.getRenewalDate(),
                userDTO
        );
    }

    public void deleteSubscription(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        Subscription existingSubscription =
                subscriptionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Subscription not found")
                        );

        if (!existingSubscription.getUser().getEmail().equals(email)) {
            throw new RuntimeException(
                    "You cannot delete this subscription"
            );
        }

        subscriptionRepository.delete(existingSubscription);
    }




    public SubscriptionSummaryDTO getSubscriptionSummary() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        List<Subscription> subscriptions =
                subscriptionRepository.findByUserEmail(email);

        int totalSubscriptions = subscriptions.size();

        double monthlySpending = 0;

        for (Subscription subscription : subscriptions) {
            monthlySpending += subscription.getPrice();
        }

        double yearlySpending = monthlySpending * 12;

        return new SubscriptionSummaryDTO(
                totalSubscriptions,
                monthlySpending,
                yearlySpending
        );
    }
    public List<UpcomingRenewalDTO> getUpcomingRenewals() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        LocalDate today = LocalDate.now();

        LocalDate nextSevenDays = today.plusDays(7);

        List<Subscription> subscriptions =
                subscriptionRepository
                        .findByUserEmailAndRenewalDateBetween(
                                email,
                                today,
                                nextSevenDays
                        );

        List<UpcomingRenewalDTO> responseList =
                new ArrayList<>();

        for (Subscription subscription : subscriptions) {

            long daysRemaining =
                    ChronoUnit.DAYS.between(
                            today,
                            subscription.getRenewalDate()
                    );

            UpcomingRenewalDTO renewalDTO =
                    new UpcomingRenewalDTO(
                            subscription.getId(),
                            subscription.getName(),
                            subscription.getPrice(),
                            subscription.getCategory(),
                            subscription.getRenewalDate(),
                            daysRemaining
                    );

            responseList.add(renewalDTO);
        }

        return responseList;
    }


    public Map<String, Double> getCategoryWiseSpending() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        List<Subscription> subscriptions =
                subscriptionRepository.findByUserEmail(email);

        Map<String, Double> categorySpending = new HashMap<>();

        for (Subscription subscription : subscriptions) {

            String category = subscription.getCategory();
            double price = subscription.getPrice();

            if (categorySpending.containsKey(category)) {

                double oldAmount = categorySpending.get(category);

                categorySpending.put(
                        category,
                        oldAmount + price
                );

            } else {

                categorySpending.put(category, price);
            }
        }

        return categorySpending;
    }

    public DashboardDTO getDashboard(){

        SubscriptionSummaryDTO summary =
                getSubscriptionSummary();

        Map<String, Double> categoryWiseSpending =
                getCategoryWiseSpending();

        List<UpcomingRenewalDTO> upcomingRenewals =
                getUpcomingRenewals();

        return new DashboardDTO(
                summary,
                categoryWiseSpending,
                upcomingRenewals
        );
    }


}