package com.invillia.acme.services;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.entities.Order;
import com.invillia.acme.repositories.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {

	@MockBean
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@Before
	public void setUp() {
		BDDMockito.given(this.orderRepository.findByStoreId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Order>(new ArrayList<Order>()));
		BDDMockito.given(this.orderRepository.save(Mockito.any(Order.class))).willReturn(new Order());
	}

	@Test
	public void testFindOrdersByStoreId() {
		Page<Order> orders = this.orderService.findOrdersByStoreId(1L, PageRequest.of(0, 10));

		assertNotNull(orders);
	}

	@Test
	public void testSaveOrder() {
		Order order = this.orderService.persist(new Order());

		assertNotNull(order);
	}

}
