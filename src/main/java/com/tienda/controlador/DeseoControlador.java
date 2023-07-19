package com.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
