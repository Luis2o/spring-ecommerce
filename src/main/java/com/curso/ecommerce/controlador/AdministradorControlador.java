package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.Producto;
import com.curso.ecommerce.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {

    @Autowired
    ProductoService productoService;

    @GetMapping("")
    public String home(Model modelo) {
        List<Producto> listaProductos = productoService.listaProductos();
        modelo.addAttribute("listaProductos", listaProductos);
        return "administrador/home";
    }


}

