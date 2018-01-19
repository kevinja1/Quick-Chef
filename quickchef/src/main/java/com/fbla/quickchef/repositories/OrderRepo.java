package com.fbla.quickchef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.quickchef.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
