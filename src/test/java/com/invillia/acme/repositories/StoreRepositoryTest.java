package com.invillia.acme.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.entities.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StoreRepositoryTest {

	private static final String STORE_ADDRESS = "Rua A, 10";
	private static final String STORE_NAME = "Loja Teste";
	@Autowired
	private StoreRepository storeRepository;

	@Before
	public void setUp() throws Exception {
		Store store = new Store();
		store.setName(STORE_NAME);
		store.setAddress(STORE_ADDRESS);

		this.storeRepository.save(store);
	}

	@After
	public final void tearDown() {
		this.storeRepository.deleteAll();
	}

	@Test
	public void testFindByName() {
		Store store = this.storeRepository.findByName(STORE_NAME);

		assertEquals(STORE_NAME, store.getName());
	}
	
	@Test
	public void testFindByAddress() {
		Store store = this.storeRepository.findByAddress(STORE_ADDRESS);
		
		assertEquals(STORE_ADDRESS, store.getAddress());
	}
	
	@Test
	public void testFindByInvalidName() {
		Store store = this.storeRepository.findByName("Invalid Store Name");
		
		assertNull(store);
	}
	
	@Test
	public void testFindByInvalidAddress() {
		Store store = this.storeRepository.findByAddress("Invalid Store Address");
		
		assertNull(store);
	}

}
