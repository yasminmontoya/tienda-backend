package com.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Producto;
import com.tienda.repositorio.ProductoRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio repositorio;

	//este metodo sirve para listar todos los productos
	@GetMapping("/productos")
	public List<Producto> listarTodosLosProductos() {
		return repositorio.findAll();
	}
	
	//este metodo sirve para buscar un producto
	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id){
		Producto producto = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));
		return ResponseEntity.ok(producto);
	}
}
