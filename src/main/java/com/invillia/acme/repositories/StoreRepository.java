package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.entities.Store;

@Transactional(readOnly = true)
public interface StoreRepository extends JpaRepository<Store, Long> {

	Store findByName(String storeName);
	Store findByAddress(String address);

}
