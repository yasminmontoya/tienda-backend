package com.tienda;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.tienda.modelo.Historico;
import com.tienda.modelo.Producto;
import com.tienda.repositorio.HistoricoRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class HistoricoTest {

	@Autowired
	private HistoricoRepositorio repositorio;
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testGuardarHistorico() {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		Producto producto = new Producto(1L,"Lapiceros", 3.45f, 40, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2020-10-14at1.39.03PM_300x300.png");
		Historico historico = new Historico(date,"Agregar",producto); 
		Historico historicoGuardado = repositorio.save(historico);
		
		assertNotNull(historicoGuardado);		
	}
	
	@Test
	@Order(2)
	public void testBuscarHistoricoPorId() {
		Long id = 1L;
		Historico historico = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el historico con el ID : " + id));
		assertThat(historico.getId().equals(id));		
	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void testActualizarHistorico() {
		Long id = 1L;
		String nombreProducto = "Cuadernos";
		String accionHistorico = "Eliminar";
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String date = dateFormat.format(new Date());
		Producto producto = new Producto(2L, nombreProducto, 4.59f, 30, "https://www.hanjigifts.com/cdn/shop/products/ScreenShot2022-08-13at3.21.52PM_1024x1024@2x.png");
		Historico historico = new Historico(date, accionHistorico, producto);
		historico.setId(id);
		repositorio.save(historico);
		
		Historico historicoActualizado = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el historico con el ID : " + id));
		assertNotNull(historicoActualizado.getProducto().getNombre().equals(nombreProducto));
	}
	
	@Test
	@Order(4)
	public void testListarHistorico() {
		List<Historico> historicos = repositorio.findAll();
		
		for (Historico historico: historicos) {
			System.out.println(historico);
		}
		
		assertThat(historicos.size()>0);
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testEliminarHistorico() {
		Long id = 1L;
		
		boolean esExistenteAntesDeEliminar = repositorio.findById(id).isPresent();
		repositorio.deleteById(id);
		boolean esExistenteDespuesDeEliminar = repositorio.findById(id).isPresent();
		
		assertTrue(esExistenteAntesDeEliminar);
		assertFalse(esExistenteDespuesDeEliminar);
	}
}
