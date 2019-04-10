package com.invillia.acme.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.entities.Payment;
import com.invillia.acme.repositories.PaymentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaymentServiceTest {

	@MockBean
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentService paymentService;

	@Before
	public void setUp() {
		BDDMockito.given(this.paymentRepository.save(Mockito.any(Payment.class))).willReturn(new Payment());
		BDDMockito.given(this.paymentRepository.findByOrderId(Mockito.anyLong())).willReturn(new Payment());
	}

	@Test
	public void testSavePayment() {
		Payment payment = this.paymentService.persist(new Payment());

		assertNotNull(payment);
	}

	@Test
	public void testFindPaymentByOrderId() {
		Payment payment = this.paymentRepository.findByOrderId(1L);

		assertNotNull(payment);
	}

}
