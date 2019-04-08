package com.invillia.acme.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.entities.OrderItem;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "OrderItemRepository.findByOrderId", query = "SELECT oi from OrderItem oi WHERE oi.order.id = :orderId") })
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByOrderId(@Param("orderId") Long orderId);

	Page<OrderItem> findByOrderId(@Param("orderId") Long orderId, Pageable pageable);

}
