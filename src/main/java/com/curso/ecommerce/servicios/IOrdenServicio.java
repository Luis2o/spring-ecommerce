package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.Orden;

import java.util.List;

public interface IOrdenServicio {
    Orden guardar(Orden orden);

    List<Orden> listaOrden();
    String numeroOrden();
}
