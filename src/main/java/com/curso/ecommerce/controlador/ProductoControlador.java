package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.Producto;

import com.curso.ecommerce.modelo.Usuario;
import com.curso.ecommerce.servicios.ProductoService;
import com.curso.ecommerce.servicios.UploadFileService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {

    //Para que funcione el logger solo se importa import org.slf4j.*; y nada mas de lo contrario da error
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private UploadFileService upload;
    @Autowired
    private ProductoService productoService;

    @GetMapping("")// se invoca cuando se hace una peticion vacia por url
    public String show(Model modelo) {
        modelo.addAttribute("prod", productoService.listaProductos());
        return "Productos/show";//nombre de la carpeta donde esta show.html
    }

    @GetMapping("/create")
    public String create() {
        return "Productos/create";
    }

    @PostMapping("/guardar")      //create.html esa variable img es del campo guardar img
    public String guardar(Producto producto, @RequestParam("img") MultipartFile file) {
        LOGGER.info("Este es el objeto producto {}", producto);//solo sirve con ToStrinf en Producto
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario);

        //img
        if (producto.getIdProducto() == null) {//cuando se crea un producto
            try {
                String nombreImagen = upload.saveImg(file);
                producto.setImagen(nombreImagen);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            if (file.isEmpty()) {//editamos el producto pero no cambiamos la img
                Producto p = new Producto();
                p = productoService.buscarporID(producto.getIdProducto()).get();
                producto.setImagen(p.getImagen());
            }else{
                String nombreImagen = upload.saveImg(file);
                producto.setImagen(nombreImagen);
            }
        }
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

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        LOGGER.info("Dato a eliminar {}", id);
        productoService.eliminar(id);
        return "redirect:/productos";
    }


}
