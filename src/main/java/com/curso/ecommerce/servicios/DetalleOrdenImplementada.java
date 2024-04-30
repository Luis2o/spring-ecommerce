package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.DetalleOrden;
import com.curso.ecommerce.modelo.Orden;
import com.curso.ecommerce.repositorio.DetalleOrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetalleOrdenImplementada implements IDetalleOrdenServicio{
    @Autowired
    private DetalleOrdenRepositorio detalleOrdenRepositorio;

    @Override
    public DetalleOrden guardar(DetalleOrden detalleOrden) {
        return detalleOrdenRepositorio.save(detalleOrden);
    }
}
