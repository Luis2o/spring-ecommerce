package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.Producto;

import com.curso.ecommerce.modelo.Usuario;
import com.curso.ecommerce.servicios.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

    //Para que funcione el logger solo se importa import org.slf4j.*; y nada mas de lo contrario da error
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoControlador.class);
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model modelo) {
        modelo.addAttribute("prod", productoService.listaProductos());
        return "Productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "Productos/create";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto) {
        LOGGER.info("Este es el objeto producto {}", producto);//solo sirve con ToStrinf en Producto
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario);
        productoService.guardar(producto);
        return "redirect:/productos";//redicreccionamos al metodo show somo recibe vaci no se le pone un nombre
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable Integer id, Model modelo) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.buscarporID(id);
        producto = optionalProducto.get();
        LOGGER.info("producto buscado: {}", producto);

        modelo.addAttribute("productoEditar", producto);// declaramos esa variable "producto" para poder hacer el llamdo
        return "productos/edit";
    }

    @PostMapping("/actualizar")
    public String actualizar(Producto producto) {
        LOGGER.info("que datos lleva el actualizar {}", producto);
        productoService.actualiza(producto);
        return "redirect:/productos";
    }
}
