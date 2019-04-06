package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
