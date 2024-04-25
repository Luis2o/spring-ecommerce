package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.DetalleOrden;
import com.curso.ecommerce.modelo.Orden;
import com.curso.ecommerce.modelo.Producto;
import com.curso.ecommerce.servicios.ProductoService;
import org.slf4j.Logger;//para que funcione logger
import org.slf4j.LoggerFactory;//para que funcione logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")//apunta a la raiz
public class HomeControlador {

    @Autowired
    private ProductoService productoService;//para poder mostrar los productos en la raiz
    private final Logger log = LoggerFactory.getLogger(HomeControlador.class);
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();  //Para almacenas los detalles de la orden
    Orden orden = new Orden();//datos de la orden

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

    @PostMapping("/cart")
    public String anadirCarrito(@RequestParam Integer idProd, @RequestParam Integer cantidadProducto,Model modelo) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto p = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.buscarporID(idProd);
        log.info("Producto anadidos: {}",optionalProducto.get());
        log.info("Cantidad: {}",cantidadProducto);

        p =optionalProducto.get();
        detalleOrden.setCantidad(cantidadProducto);
        detalleOrden.setPrecion(p.getPrecio());
        detalleOrden.setNombre(p.getNombre());
        detalleOrden.setTotal(p.getPrecio()*cantidadProducto);
        detalleOrden.setProducto(p);
        detalles.add(detalleOrden);
        sumaTotal=detalles.stream().mapToDouble(dt-> dt.getTotal()).sum(); //suma todo los totales de esa lista

        orden.setTotal(sumaTotal);//guardo el total de la compra
        modelo.addAttribute("Carrito",detalles);
        //con esto puedo mostrar el total de la compra
        modelo.addAttribute("mostrarTotal",orden);//solo mando a llamar mostrarTotal
        return "usuario/carrito";
    }
}
