package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Deseo;

@Repository
public interface DeseoRepositorio extends JpaRepository<Deseo, Long>{

}
