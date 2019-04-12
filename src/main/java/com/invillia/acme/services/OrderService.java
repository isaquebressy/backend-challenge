package com.invillia.acme.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.invillia.acme.entities.Order;
import com.invillia.acme.repositories.OrderRepository;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;

	public Order persist(Order order) {
		log.info("Persistindo ordem: {}", order);
		return this.orderRepository.save(order);
	}

	public Page<Order> findOrdersByStoreId(Long storeId, PageRequest pageRequest) {
		log.info("Buscando ordens da Loja com id () ", storeId);
		return this.orderRepository.findByStoreId(storeId, pageRequest);
	}
}
