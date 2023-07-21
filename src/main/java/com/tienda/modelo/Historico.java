package com.tienda.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historico")
public class Historico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha", length = 25, nullable = false)
	private String fecha;
	
	@Column(name = "accion", length = 10, nullable = false)
	private String accion;
	
	@ManyToOne
	@JoinColumn (name = "producto_id" , nullable = false)
	private Producto producto;

	public Historico() {
		super();
	}
	
	public Historico(Long id, String fecha, Producto producto) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.producto = producto;
	}

	public Historico(String fecha, String accion, Producto producto) {
		super();
		this.fecha = fecha;
		this.accion = accion;
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", fecha=" + fecha + ", accion=" + accion + ", producto=" + producto + "]";
	}

}
