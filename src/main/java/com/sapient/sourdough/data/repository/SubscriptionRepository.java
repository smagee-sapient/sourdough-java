package com.sapient.sourdough.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.sourdough.data.entity.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
	// default implmentation only
}
