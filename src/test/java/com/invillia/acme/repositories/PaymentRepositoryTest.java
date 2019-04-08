package com.invillia.acme.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.entities.Order;
import com.invillia.acme.entities.Payment;
import com.invillia.acme.entities.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaymentRepositoryTest {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Before
	public void setUp() {
		Store store = this.storeRepository.save(getStoreData());
		Order order = this.orderRepository.save(getOrderData(store));
		this.paymentRepository.save(getPaymentData(order));
	}

	@After
	public void tearDown() {
		this.storeRepository.deleteAll();
	}

	@Test
	public void testFindByOrderId() {
		Store store = this.storeRepository.findByName(getStoreData().getName());
		List<Order> orders = this.orderRepository.findByStoreId(store.getId());
		if (orders.size() == 1) {
			Payment payment = this.paymentRepository.findByOrderId(orders.get(0).getId());
			assertNotNull(payment);
		} else {
			fail("Número de ordens inváidas para esta loja");
		}

	}

	@Test
	public void testFindByInvalidOrderId() {
		Store store = this.storeRepository.findByName(getStoreData().getName());
		List<Order> orders = this.orderRepository.findByStoreId(store.getId());
		if (orders.size() == 1) {
			Payment payment = this.paymentRepository.findByOrderId(orders.get(0).getId() + 1L);
			assertNull(payment);
		} else {
			fail("Número de ordens inváidas para esta loja");
		}

	}
	
	private Store getStoreData() {
		Store store = new Store();
		store.setName("Loja Teste 2");
		store.setAddress("Rua B, 11");

		return store;
	}

	private Order getOrderData(Store store) {
		Order order = new Order();
		order.setAddress("Av. Esquerda, 101");
		order.setConfirmationDate(new Date());
		order.setStatus(1);
		order.setStore(store);
		return order;
	}

	private Payment getPaymentData(Order order) {
		Payment payment = new Payment();
		payment.setCreditCardNumber("1234567890");
		payment.setPaymentDate(new Date());
		payment.setStatus(1);
		payment.setOrder(order);
		return payment;
	}
}
