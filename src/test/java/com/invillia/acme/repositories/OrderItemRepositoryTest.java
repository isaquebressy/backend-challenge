package com.invillia.acme.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
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
import com.invillia.acme.entities.OrderItem;
import com.invillia.acme.entities.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderItemRepositoryTest {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Before
	public void setUp() {
		Store store = this.storeRepository.save(getStoreData());
		Order order = this.orderRepository.save(getOrderData(store));
		this.orderItemRepository.save(getOrderItemData(order));
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
			List<OrderItem> orderItems = this.orderItemRepository.findByOrderId(orders.get(0).getId());
			assertEquals(1, orderItems.size());
		} else {
			fail("Número de ordens inváidas para esta loja");
		}

	}

	@Test
	public void testFindByInvalidOrderId() {
		Store store = this.storeRepository.findByName(getStoreData().getName());
		List<Order> orders = this.orderRepository.findByStoreId(store.getId());
		if (orders.size() == 1) {
			List<OrderItem> orderItems = this.orderItemRepository.findByOrderId(orders.get(0).getId() + 1L);
			assertEquals(0, orderItems.size());
		} else {
			fail("Número de ordens inváidas para esta loja");
		}

	}

	private Store getStoreData() {
		Store store = new Store();
		store.setName("Loja Teste 3");
		store.setAddress("Rua C, 12");

		return store;
	}

	private Order getOrderData(Store store) {
		Order order = new Order();
		order.setAddress("Av. Centro, 99");
		order.setConfirmationDate(new Date());
		order.setStatus(1);
		order.setStore(store);
		return order;
	}

	private OrderItem getOrderItemData(Order order) {
		OrderItem orderItem = new OrderItem();
		orderItem.setDescription("Item 1");
		orderItem.setQuantity(Long.valueOf(3));
		orderItem.setUnitPrice(BigDecimal.valueOf(30));
		orderItem.setOrder(order);
		return orderItem;
	}

}
