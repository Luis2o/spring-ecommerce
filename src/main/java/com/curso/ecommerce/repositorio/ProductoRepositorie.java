package com.curso.ecommerce.repositorio;

import com.curso.ecommerce.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorie extends JpaRepository<Producto, Integer> {
}
