package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Historico;

@Repository
public interface HistoricoRepositorio extends JpaRepository<Historico, Long>{

}
