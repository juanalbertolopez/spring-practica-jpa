package com.example.udemy.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Proveedor proveedor;

	@NotEmpty
	private String recibo_compra;
	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "create_at")
	private Date createAt;
	
	@JsonIgnore
	@OneToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="compra_id")
	private List<ComprasItems> items;
	
	public Compra() {
		this.items = new ArrayList<ComprasItems>();
	}
	

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public String getRecibo_compra() {
		return recibo_compra;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRecibo_compra(String recibo_compra) {
		this.recibo_compra = recibo_compra;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public void addComprasItems(ComprasItems item) {
		this.items.add(item);
	}

	public List<ComprasItems> getItems() {
		return items;
	}

	public void setItems(List<ComprasItems> items) {
		this.items = items;
	}
	
	public Double getTotal() {
		Double total= 0.0;
		
		int size= items.size();
		
		for(int i=0;i<size;i++) {
			total+= items.get(i).calcularImporte();
		}
		return total;
	}
	

}
