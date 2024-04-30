package com.curso.ecommerce.repositorio;

import com.curso.ecommerce.modelo.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepositorio extends JpaRepository<DetalleOrden, Integer> {
}
