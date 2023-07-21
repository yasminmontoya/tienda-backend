package com.tienda.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "deseos")
public class Deseo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn (name = "producto_id" , nullable = false, unique = true)
	private Producto producto;
	
	public Deseo() {
		super();
	}

	public Deseo(Long id, Producto producto) {
		super();
		this.id = id;
		this.producto = producto;
	}

	public Deseo(Producto producto) {
		super();
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Deseo [id=" + id + ", producto=" + producto + "]";
	}
	
}
