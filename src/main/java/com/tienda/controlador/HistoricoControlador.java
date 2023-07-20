package com.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.modelo.Historico;
import com.tienda.repositorio.HistoricoRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricoControlador {
	
	@Autowired
	private HistoricoRepositorio repositorio;

	//este metodo sirve para listar el historico
	@GetMapping("/historico")
	public List<Historico> listarElHistorico() {
		return repositorio.findAll();
	}
	
	//este metodo sirve para guardar un historico
	@PostMapping("/historico")
	public Historico guardarHistorico(@RequestBody Historico historico) {
		return repositorio.save(historico);
	}

}
