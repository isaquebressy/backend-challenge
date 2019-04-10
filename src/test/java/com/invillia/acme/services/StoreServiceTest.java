package com.invillia.acme.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
		BDDMockito.given(this.storeRepository.findByName(Mockito.anyString())).willReturn(new Store());
		BDDMockito.given(this.storeRepository.findByAddress(Mockito.anyString())).willReturn(new Store());
		BDDMockito.given(this.storeRepository.save(Mockito.any(Store.class))).willReturn(new Store());
	}

	@Test
	public void testFindStoreByName() {
		Optional<Store> store = this.storeService.findByName(STORE_NAME);

		assertTrue(store.isPresent());
	}

	@Test
	public void testFindStoreByAddress() {
		Optional<Store> store = this.storeService.findByAddress(STORE_ADDRESS);

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
