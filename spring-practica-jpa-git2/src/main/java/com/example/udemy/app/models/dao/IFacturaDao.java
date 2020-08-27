package com.example.udemy.app.models.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.udemy.app.models.entity.Cliente;
import com.example.udemy.app.models.entity.Factura;
import com.example.udemy.app.models.entity.Producto;

public interface IFacturaDao extends CrudRepository<Factura,Long> {
	
	//public List<Factura> findByItem (Producto producto);

}
