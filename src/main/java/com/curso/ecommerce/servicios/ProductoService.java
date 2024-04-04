package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.Producto;

import java.util.Optional;

public interface ProductoService {
    public Producto guardar(Producto producto);
    public Optional<Producto> buscarporID(Integer id);
    public void actualiza(Producto producto);
    public void eliminar(Integer id);
}
