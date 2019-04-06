package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
