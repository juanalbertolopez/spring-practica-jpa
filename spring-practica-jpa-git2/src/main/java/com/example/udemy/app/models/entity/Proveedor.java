package com.example.udemy.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "proveedores")
public class Proveedor  implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String direccion;
	@NotEmpty
	private String nif;
	@NotEmpty
	private String telefono;
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
	
	@OneToMany(mappedBy = "proveedor",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Compra> compras;
	
	
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getNif() {
		return nif;
	}
	public String getTelefono() {
		return telefono;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	public void addCompra(Compra compra) {
		compras.add(compra);
	}
	
	@Override
	public String toString() {
		return "Proveedor id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", nif=" + nif
				+ ", telefono=" + telefono + ", createAt=" + createAt + "";
	}

}
