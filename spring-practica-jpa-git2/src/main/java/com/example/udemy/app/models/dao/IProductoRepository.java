package com.example.udemy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.udemy.app.models.entity.Producto;


public interface IProductoRepository extends CrudRepository<Producto, String>  {
	
	
}
