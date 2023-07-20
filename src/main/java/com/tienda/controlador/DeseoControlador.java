package com.tienda.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.excepciones.ResourceNotFoundException;
import com.tienda.modelo.Deseo;
import com.tienda.repositorio.DeseoRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class DeseoControlador {

	@Autowired
	private DeseoRepositorio repositorio;

	//este metodo sirve para listar todos los deseos
	@GetMapping("/deseos")
	public List<Deseo> listarTodosLosDeseos() {
		return repositorio.findAll();
	}
	
	//este metodo sirve para buscar un producto
	@GetMapping("/deseos/{id}")
	public ResponseEntity<Deseo> obtenerDeseoPorId(@PathVariable Long id){
		Deseo deseo = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el deseo con el ID : " + id));
		return ResponseEntity.ok(deseo);
	}
	
	//este metodo sirve para guardar un deseo
	@PostMapping("/deseos")
	public Deseo guardarDeseo(@RequestBody Deseo deseo) {
		return repositorio.save(deseo);
	}
	
	//este metodo sirve para eliminar un deseo
	@DeleteMapping("/deseos/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarDeseo(@PathVariable Long id){
		Deseo deseo = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el deseo con el ID : " + id));
		
		repositorio.delete(deseo);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
