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

import com.invillia.acme.entities.OrderItem;
import com.invillia.acme.repositories.OrderItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderItemServiceTest {

	@MockBean
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderItemService orderItemService;

	@Before
	public void setUp() {
		BDDMockito.given(this.orderItemRepository.findByOrderId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<OrderItem>(new ArrayList<OrderItem>()));
		BDDMockito.given(this.orderItemRepository.save(Mockito.any(OrderItem.class))).willReturn(new OrderItem());
	}

	@Test
	public void testFindOrderItemsByOrderId() {
		Page<OrderItem> orderItems = this.orderItemService.findOrderItemsByOrderId(1L, PageRequest.of(0, 10));

		assertNotNull(orderItems);
	}

	@Test
	public void testOrderItemSave() {
		OrderItem orderItem = this.orderItemService.persist(new OrderItem());

		assertNotNull(orderItem);
	}

}
