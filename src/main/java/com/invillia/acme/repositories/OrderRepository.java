package com.invillia.acme.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.entities.Order;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "OrderRepository.findByStoreId", query = "SELECT o from Order o WHERE o.store.id = :storeId") })
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByStoreId(@Param("storeId") Long storeId);

	Page<Order> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}
