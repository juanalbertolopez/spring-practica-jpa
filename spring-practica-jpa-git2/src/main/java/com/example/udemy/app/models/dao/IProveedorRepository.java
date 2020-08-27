package com.example.udemy.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.udemy.app.models.entity.Producto;
import com.example.udemy.app.models.entity.Proveedor;

public interface IProveedorRepository extends CrudRepository<Proveedor, String> {

}
