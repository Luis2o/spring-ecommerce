package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.Producto;
import com.curso.ecommerce.servicios.ProductoService;
import org.slf4j.Logger;//para que funcione logger
import org.slf4j.LoggerFactory;//para que funcione logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")//apunta a la raiz
public class HomeControlador {

    @Autowired
    private ProductoService productoService;//para poder mostrar los productos en la raiz

    private final Logger log = LoggerFactory.getLogger(HomeControlador.class);

    @GetMapping("")
    public String home(Model modelo) {
        modelo.addAttribute("listaProducto", productoService.listaProductos());
        return "usuario/home";
    }

    @GetMapping("/productoHome/{id}")
    public String verProducto(@PathVariable Integer id, Model modelo) {
        log.info("id del producto enviado por parametro {}", id);
        Producto p = new Producto();
        Optional<Producto> productoOptional = productoService.buscarporID(id);
        p = productoOptional.get();
        log.info("El id:" + id + " trae los siguientes datos de producto {}:", p);
        modelo.addAttribute("listaProducto", p);
        return "usuario/productohome";
    }
}
