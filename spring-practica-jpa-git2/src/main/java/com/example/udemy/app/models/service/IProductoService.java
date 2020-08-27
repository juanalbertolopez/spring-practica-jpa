package com.example.udemy.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.udemy.app.models.entity.Cliente;
import com.example.udemy.app.models.entity.Producto;

public interface IProductoService {
	
	Object modelandview = null;

	public void delete(Long id);
	
	public void save (Producto producto);

	public List<Producto> findAll();
	    
	public Page<Producto> findAll(Pageable pageable);
		
	public List<Producto> findByNombre(String term);

	public Producto findOne(Long id);
	
	public void saveAll(Iterable<Producto> productos);
	
	public Producto findProductoById(Long id);

	}
