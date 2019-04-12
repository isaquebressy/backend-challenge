package com.invillia.acme.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.invillia.acme.entities.OrderItem;
import com.invillia.acme.repositories.OrderItemRepository;

@Service
public class OrderItemService {

	private static final Logger log = LoggerFactory.getLogger(OrderItemService.class);

	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderItem persist(OrderItem orderItem) {
		log.info("Persistindo ítem: {}", orderItem);
		return this.orderItemRepository.save(orderItem);
	}
	
	public Page<OrderItem> findOrderItemsByOrderId(Long orderId, PageRequest pageRequest) {
		log.info("Buscando ítens da ordem de id {} ", orderId);
		return this.orderItemRepository.findByOrderId(orderId, pageRequest);
	}

}
