package com.example.udemy.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.udemy.app.models.entity.Proveedor;

public interface IProveedorService {
	
	Object modelandview = null;

	public void delete(Long id);
	
	public void save ( Proveedor proveedor);

	public List<Proveedor> listProveedor();
	    
	public Page<Proveedor> findAll(Pageable pageable);
		
	public List<Proveedor> findByNombre(String term);

	public Proveedor findOne(Long id);

}
