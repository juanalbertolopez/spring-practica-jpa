package com.example.udemy.app.models.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="compras_items")
public class ComprasItems implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	private int compra_id;
	
	private Double precio_compra;
	
	
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;
	

	public Long getId() {
		return id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getCompra_id() {
		return compra_id;
	}

	public Double getPrecio_compra() {
		return precio_compra;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setCompra_id(int compra_id) {
		this.compra_id = compra_id;
	}

	public void setPrecio_compra(Double precio_compra) {
		this.precio_compra = precio_compra;
	}
	
	public Double calcularImporte() {
		return cantidad.doubleValue()* getPrecio_compra();
	}
	
	
	
	
	

}
