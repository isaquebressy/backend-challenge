package com.invillia.acme.repositories;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.entities.Payment;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "PaymentRepository.findByOrderId", query = "SELECT p from Payment p WHERE p.order.id = :orderId") })
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByOrderId(@Param("orderId") Long orderId);

}
