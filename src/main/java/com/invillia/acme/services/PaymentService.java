package com.invillia.acme.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.entities.Payment;
import com.invillia.acme.repositories.PaymentRepository;

@Service
public class PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment persist(Payment payment) {
		log.info("Persistindo pagamento: {}", payment);
		return this.paymentRepository.save(payment);
	}

	public Optional<Payment> findPaymentByOrderId(Long orderId) {
		log.info("Buscando pagamento da ordem de id {} ", orderId);
		return Optional.ofNullable(this.paymentRepository.findByOrderId(orderId));
	}
}