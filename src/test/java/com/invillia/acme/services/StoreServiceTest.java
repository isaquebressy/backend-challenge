package com.invillia.acme.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

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

import com.invillia.acme.entities.Store;
import com.invillia.acme.repositories.StoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StoreServiceTest {

	@MockBean
	private StoreRepository storeRepository;

	@Autowired
	private StoreService storeService;

	private static final String STORE_NAME = "Loja Teste";
	private static final String STORE_ADDRESS = "Rua A, 01";

	@Before
	public void setUp() {
		BDDMockito.given(this.storeRepository.findByName(Mockito.anyString(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Store>(new ArrayList<Store>()));
		BDDMockito.given(this.storeRepository.findByAddress(Mockito.anyString(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Store>(new ArrayList<Store>()));
		BDDMockito.given(this.storeRepository.findByNameAndAddress(Mockito.anyString(), Mockito.anyString()))
				.willReturn(new Store());
		BDDMockito.given(this.storeRepository.save(Mockito.any(Store.class))).willReturn(new Store());
	}

	@Test
	public void testFindStoreByName() {
		Page<Store> stores = this.storeService.findByName(STORE_NAME, PageRequest.of(0, 10));

		assertNotNull(stores);
	}

	@Test
	public void testFindStoreByAddress() {
		Page<Store> stores = this.storeService.findByAddress(STORE_ADDRESS, PageRequest.of(0, 10));

		assertNotNull(stores);
	}

	@Test
	public void testFindStoreByNameAndAddress() {
		Optional<Store> store = this.storeService.findByNameAndAddress(STORE_NAME, STORE_ADDRESS);

		assertTrue(store.isPresent());
	}

	@Test
	public void testSaveStore() {
		Store store = this.storeService.persist(new Store());

		assertNotNull(store);
	}

	@Test
	public void testUpdateStore() {
		Store store = this.storeService.persist(new Store());
		store.setName("Updated store name");

		Store updatedStore = this.storeService.update(store);
		assertNotNull(updatedStore);
	}
}
