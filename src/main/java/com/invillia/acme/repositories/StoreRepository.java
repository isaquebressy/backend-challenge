package com.invillia.acme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
