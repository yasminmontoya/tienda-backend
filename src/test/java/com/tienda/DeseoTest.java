package com.tienda;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Deseo;
import com.tienda.modelo.Producto;
import com.tienda.repositorio.DeseoRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class DeseoTest {
	
	@Autowired
	private DeseoRepositorio repositorio;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testGuardarDeseo() {
		Producto producto = new Producto(1L,"Lapiceros", 3.45f, 40, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2020-10-14at1.39.03PM_300x300.png");
		Deseo deseo = new Deseo(producto); 
		Deseo deseoGuardado = repositorio.save(deseo);
		
		assertNotNull(deseoGuardado);		
	}
	
	@Test
	@Order(2)
	public void testBuscarDeseoPorId() {
		Long id = 1L;
		Deseo deseo = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el deseo con el ID : " + id));
		assertThat(deseo.getId().equals(id));		
	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void testActualizarDeseo() {
		Long id = 1L;
		String nombreProducto = "Cuadernos";
		Producto producto = new Producto(2L, nombreProducto, 4.59f, 30, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2022-08-13at3.21.52PM_1024x1024@2x.png");
		Deseo deseo = new Deseo(1L,producto);
		repositorio.save(deseo);
		
		Deseo deseoActualizado = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el deseo con el ID : " + id));
		assertNotNull(deseoActualizado.getProducto().getNombre().equals(nombreProducto));		
	}
	
	@Test
	@Order(4)
	public void testListarDeseos() {
		List<Deseo> deseos = repositorio.findAll();
		
		for (Deseo deseo: deseos) {
			System.out.println(deseo);
		}
		
		assertThat(deseos.size()>0);
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testEliminarDeseo() {
		Long id = 1L;
		
		boolean esExistenteAntesDeEliminar = repositorio.findById(id).isPresent();
		repositorio.deleteById(id);
		boolean esExistenteDespuesDeEliminar = repositorio.findById(id).isPresent();
		
		assertTrue(esExistenteAntesDeEliminar);
		assertFalse(esExistenteDespuesDeEliminar);
	}
}
