package com.tienda;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.tienda.modelo.Producto;
import com.tienda.repositorio.ProductoRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class ProductoTest {
	
	@Autowired
	private ProductoRepositorio repositorio;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testGuardarProducto() {
		Producto producto = new Producto("Lapiceros", 3.45f, 40, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2020-10-14at1.39.03PM_300x300.png");
		Producto productoGuardado = repositorio.save(producto);
		
		//Producto producto = new Producto("Cuadernos", 4.59f, 30, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2022-08-13at3.21.52PM_1024x1024@2x.png");
		//Producto productoGuardado = repositorio.save(producto);
		
		assertNotNull(productoGuardado);		
	}
	
	@Test
	@Order(2)
	public void testBuscarProductoPorNombre() {
		String nombreProducto = "Lapiceros";
		Producto producto = repositorio.findByNombre(nombreProducto);
		assertThat(producto.getNombre().equals(nombreProducto));		
	}
	
	@Test
	@Order(3)
	public void testBuscarProductoPorNombreNoExistente() {
		String nombreProducto = "Marcadores";
		Producto producto = repositorio.findByNombre(nombreProducto);
		
		assertNull(producto);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	public void testActualizarProducto() {
		String nombreProducto = "Cuadernos";
		Producto producto = new Producto(nombreProducto, 4.59f, 30, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2022-08-13at3.21.52PM_1024x1024@2x.png");
		producto.setId(1L);
		repositorio.save(producto);
		
		Producto productoActualizado = repositorio.findByNombre(nombreProducto);
		assertNotNull(productoActualizado.getNombre().equals(nombreProducto));		
	}
	
	@Test
	@Order(5)
	public void testListarProductos() {
		List<Producto> productos = repositorio.findAll();
		
		for (Producto producto: productos) {
			System.out.println(producto);
		}
		
		assertThat(productos.size()>0);
	}
	
	@Test
	@Rollback(false)
	@Order(6)
	public void testEliminarProducto() {
		Long id = 1L;
		
		boolean esExistenteAntesDeEliminar = repositorio.findById(id).isPresent();
		repositorio.deleteById(id);
		boolean esExistenteDespuesDeEliminar = repositorio.findById(id).isPresent();
		
		assertTrue(esExistenteAntesDeEliminar);
		assertFalse(esExistenteDespuesDeEliminar);
	}

}
