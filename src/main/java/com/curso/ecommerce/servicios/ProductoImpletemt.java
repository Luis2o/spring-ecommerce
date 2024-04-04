package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.Producto;
import com.curso.ecommerce.repositorio.ProductoRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoImpletemt implements ProductoService {
    @Autowired
    ProductoRepositorie productoRepositorie;

    @Override
    public Producto guardar(Producto producto) {
        return productoRepositorie.save(producto);
    }

    @Override
    public Optional<Producto> buscarporID(Integer id) {
        return productoRepositorie.findById(id);
    }

    @Override
    public void actualiza(Producto producto) {
        productoRepositorie.save(producto);
    }

    @Override
    public void eliminar(Integer id) {
        productoRepositorie.deleteById(id);
    }
}
