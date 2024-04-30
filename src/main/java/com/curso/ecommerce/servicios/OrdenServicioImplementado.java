package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.Orden;
import com.curso.ecommerce.repositorio.OrdenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServicioImplementado implements IOrdenServicio {
    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Override
    public Orden guardar(Orden orden) {
        return ordenRepositorio.save(orden);
    }

    @Override
    public List<Orden> listaOrden() {
        return ordenRepositorio.findAll();
    }

    public String numeroOrden() {
        int numero = 0;//para incrementar el numero de orden
        String numeroConcatenado = "";
        List<Orden> listaOrdenes = listaOrden();//obtenemos todas las ordenes
        List<Integer> numeros = new ArrayList<>();//pasamos de String a entero

        //pasamos la lista que trae numero pero String pasamos a numeros
        listaOrdenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
        if (listaOrdenes.isEmpty()) {
            numero = 1;
        } else {
            //sacamos el numero mayor de la lista que traemos
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }
//        if (numero < 10) {
//            numeroConcatenado = "000000000" + String.valueOf(numero);
//        } else if (numero < 100) {
//            numeroConcatenado = "00000000" + String.valueOf(numero);
//        } else if (numero < 1000) {
//            numeroConcatenado = "0000000" + String.valueOf(numero);
//        } enves de hacer tantos if solo se formatea
        numeroConcatenado = String.format("%010d", numero);
        return numeroConcatenado;
    }
}
