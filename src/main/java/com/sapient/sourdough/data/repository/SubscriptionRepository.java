package com.sapient.sourdough.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.sourdough.data.entity.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
	List<SubscriptionEntity> findByHourAndMinute(String hour, String minute);
}
