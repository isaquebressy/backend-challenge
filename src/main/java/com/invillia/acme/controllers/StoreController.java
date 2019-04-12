package com.invillia.acme.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.dtos.StoreDto;
import com.invillia.acme.entities.Store;
import com.invillia.acme.response.Response;
import com.invillia.acme.services.StoreService;

@RestController
@RequestMapping("/store")
@CrossOrigin(origins = "*")
public class StoreController {

	private Logger log = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private StoreService storeService;

	public StoreController() {
	}

	@PostMapping
	public ResponseEntity<Response<StoreDto>> create(@Valid @RequestBody StoreDto storeDto, BindingResult result) {

		log.info("Cadastrando loja: {}", storeDto);
		Response<StoreDto> response = new Response<>();
		this.validateData(storeDto, result);

		Store store = this.convertDtoToStore(storeDto);
		if (result.hasErrors()) {
			log.error("Erro validando dados da loja: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.storeService.persist(store);

		response.setData(this.convertStoreToDto(store));
		return ResponseEntity.ok(response);

	}

	@PutMapping("/{storeId}")
	public ResponseEntity<Response<StoreDto>> update(@PathVariable Long storeId, @Valid @RequestBody StoreDto storeDto,
			BindingResult result) {
		log.info("Atualizando loja de id {}", storeId);
		Response<StoreDto> response = new Response<>();

		Optional<Store> store = this.storeService.findById(storeId);
		if (!store.isPresent()) {
			response.getErrors().add("Loja com o id " + storeId + " não encontrada");
		}

		Store newStore = this.convertDtoToStore(storeDto);
		if (result.hasErrors()) {
			log.error("Erro validando dados da loja: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		newStore.setId(store.get().getId());
		this.storeService.persist(newStore);

		response.setData(this.convertStoreToDto(newStore));
		return ResponseEntity.ok(response);

	}

	@GetMapping
	public ResponseEntity<Response<StoreDto>> findStoreByNameAndAddress(String name, String address) {

		log.info("Buscando loja com o nome {} e endereço {}", name, address);

		Response<StoreDto> response = new Response<>();
		Optional<Store> store = storeService.findByNameAndAddress(name, address);

		if (!store.isPresent()) {
			log.info("Loja com o nome {} e endereço {} não encontrada", name, address);
			response.getErrors().add("Loja com o nome " + name + " e endereço " + address + " não encontrada");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertStoreToDto(store.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{storeId}")
	public ResponseEntity<Response<StoreDto>> findStoreById(@PathVariable Long storeId) {

		log.info("Buscando loja com o id {}", storeId);

		Response<StoreDto> response = new Response<>();
		Optional<Store> store = storeService.findById(storeId);

		if (!store.isPresent()) {
			log.info("Loja com o id {} não encontrada", storeId);
			response.getErrors().add("Loja com o id " + storeId + " não encontrada");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.convertStoreToDto(store.get()));
		return ResponseEntity.ok(response);
	}

	private void validateData(StoreDto storeDto, BindingResult result) {
		this.storeService.findByNameAndAddress(storeDto.getName(), storeDto.getAddress())
				.ifPresent(store -> result.addError(new ObjectError("store", "loja já existente")));
	}

	private Store convertDtoToStore(StoreDto storeDto) {
		Store store = new Store();
		store.setName(storeDto.getName());
		store.setAddress(storeDto.getAddress());
		return store;
	}

	private StoreDto convertStoreToDto(Store store) {
		StoreDto storeDto = new StoreDto();
		storeDto.setName(store.getName());
		storeDto.setAddress(store.getAddress());
		return storeDto;
	}
}
