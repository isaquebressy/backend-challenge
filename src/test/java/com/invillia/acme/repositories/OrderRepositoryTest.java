package com.invillia.acme.repositories;

import static org.junit.Assert.assertEquals;

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
import com.invillia.acme.entities.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Before
	public void setUp() {
		Store store = this.storeRepository.save(getStoreData());
		this.orderRepository.save(getOrderData(store));
	}

	@After
	public void tearDown() {
		this.storeRepository.deleteAll();
	}

	@Test
	public void testFindByStoreId() {
		Store store = this.storeRepository.findByName(getStoreData().getName());
		List<Order> orders = this.orderRepository.findByStoreId(store.getId());

		assertEquals(1, orders.size());
	}

	@Test
	public void testFindByInvalidStoreId() {
		Store store = this.storeRepository.findByName(getStoreData().getName());
		List<Order> orders = this.orderRepository.findByStoreId(store.getId() + 1L);

		assertEquals(0, orders.size());
	}

	private Store getStoreData() {
		Store store = new Store();
		store.setName("Loja Teste");
		store.setAddress("Rua A, 10");

		return store;
	}

	private Order getOrderData(Store store) {
		Order order = new Order();
		order.setAddress("Av. Direita, 100");
		order.setConfirmationDate(new Date());
		order.setStatus(1);
		order.setStore(store);
		return order;
	}
}
