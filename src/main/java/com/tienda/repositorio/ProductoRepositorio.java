package com.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.modelo.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

}
