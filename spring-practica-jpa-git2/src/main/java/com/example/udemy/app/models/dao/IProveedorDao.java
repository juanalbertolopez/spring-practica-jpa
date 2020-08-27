package com.example.udemy.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.udemy.app.models.entity.Producto;
import com.example.udemy.app.models.entity.Proveedor;

public interface IProveedorDao extends PagingAndSortingRepository<Proveedor, Long> {
	
	@Query("select p from Proveedor p where p.nombre like %?1%")
	public List<Proveedor> findByNombre(String term);
	
	public List<Proveedor> findByNombreLikeIgnoreCase(String term);
	

}
