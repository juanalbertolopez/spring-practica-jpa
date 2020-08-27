package com.example.udemy.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.udemy.app.models.entity.Compra;
import com.example.udemy.app.models.entity.Producto;

public interface ICompraService {
	
	Object modelandview = null;

	public void delete(Long id);
	
	public void save(Compra compra);

	public List<Compra> findAll();
	
	public Compra findOne(Long id);
	
	public Compra findCompraById(Long id);
	
	public Page<Compra> findAll(Pageable pageable);
	
	
	

}
