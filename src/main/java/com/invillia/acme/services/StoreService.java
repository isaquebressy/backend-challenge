package com.invillia.acme.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.entities.Store;
import com.invillia.acme.repositories.StoreRepository;

@Service
public class StoreService {

	private static final Logger log = LoggerFactory.getLogger(StoreService.class);

	@Autowired
	private StoreRepository storeRepository;

	public Optional<Store> findByName(String name) {
		log.info("Buscando uma loja para o nome {}", name);
		return Optional.ofNullable(this.storeRepository.findByName(name));
	}

	public Optional<Store> findByAddress(String address) {
		log.info("Buscando uma loja para o endereço {}", address);
		return Optional.ofNullable(this.storeRepository.findByAddress(address));
	}

	public Store persist(Store store) {
		log.info("Persistindo loja: {}", store);
		return this.storeRepository.save(store);
	}
	
	public Store update(Store store) {
		log.info("Atualizando loja {} ", store);
		return this.storeRepository.save(store);
	}
}
