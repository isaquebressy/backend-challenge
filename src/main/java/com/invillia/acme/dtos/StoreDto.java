package com.invillia.acme.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class StoreDto {

	private String name;
	private String address;

	public StoreDto() {
	}

	@NotEmpty(message = "O Nome da loja náo pode ser vazio.")
	@Length(min = 3, max = 100, message = "O nome da loja deve ter o tamanho entre 3 e 100 caracteres")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "O endereço da loja náo pode ser vazio.")
	@Length(min = 5, max = 200, message = "O endereço deve conter de 3 a 200 caracteres")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StoreDto [name=" + name + ", address=" + address + "]";
	}

}
