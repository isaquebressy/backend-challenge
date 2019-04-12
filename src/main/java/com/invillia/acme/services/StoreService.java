package com.invillia.acme.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.invillia.acme.entities.Store;
import com.invillia.acme.repositories.StoreRepository;

@Service
public class StoreService {

	private static final Logger log = LoggerFactory.getLogger(StoreService.class);

	@Autowired
	private StoreRepository storeRepository;

	public Page<Store> findByName(String name, PageRequest pageRequest) {
		log.info("Buscando uma loja para o nome {}", name);
		return this.storeRepository.findByName(name, pageRequest);
	}

	public Page<Store> findByAddress(String address, PageRequest pageRequest) {
		log.info("Buscando uma loja para o endereço {}", address);
		return this.storeRepository.findByAddress(address, pageRequest);
	}

	public Optional<Store> findByNameAndAddress(String name, String address) {
		log.info("Buscando uma loja para o nome {} e endereço {}", name, address);
		return Optional.ofNullable(this.storeRepository.findByNameAndAddress(name, address));
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
