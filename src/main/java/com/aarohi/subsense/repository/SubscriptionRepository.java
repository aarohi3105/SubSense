package com.aarohi.subsense.repository;

import com.aarohi.subsense.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository
        extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserEmail(String email);


    List<Subscription> findByUserEmailAndRenewalDateBetween(
            String email,
            LocalDate startDate,
            LocalDate endDate
    );


}