package com.example.udemy.app.models.dao;



import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.udemy.app.models.entity.Cliente;


public interface IClienteDao extends PagingAndSortingRepository<Cliente,Long> {
	
public List<Cliente> findByEdad (int edad);
	
}
