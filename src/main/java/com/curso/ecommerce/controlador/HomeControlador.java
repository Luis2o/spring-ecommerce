package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.DetalleOrden;
import com.curso.ecommerce.modelo.Orden;
import com.curso.ecommerce.modelo.Producto;
import com.curso.ecommerce.modelo.Usuario;
import com.curso.ecommerce.servicios.IDetalleOrdenServicio;
import com.curso.ecommerce.servicios.IOrdenServicio;
import com.curso.ecommerce.servicios.IUsuarioServicios;
import com.curso.ecommerce.servicios.ProductoService;
import org.slf4j.Logger;//para que funcione logger
import org.slf4j.LoggerFactory;//para que funcione logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private IUsuarioServicios usuarioServicios;
    @Autowired
    private IOrdenServicio ordenServicio;
    @Autowired
    private IDetalleOrdenServicio iDetalleOrdenServicio;

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
    public String anadirCarrito(@RequestParam Integer idProd, @RequestParam Integer cantidadProducto, Model modelo) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto p = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.buscarporID(idProd);
        log.info("Producto anadidos: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidadProducto);

        p = optionalProducto.get();
        detalleOrden.setCantidad(cantidadProducto);
        detalleOrden.setPrecion(p.getPrecio());
        detalleOrden.setNombre(p.getNombre());
        detalleOrden.setTotal(p.getPrecio() * cantidadProducto);
        detalleOrden.setProducto(p);

        //validar que el producto no se anada 2veces  sino que se sume si se quiere otro del mismo
        Integer id = p.getIdProducto();
        //verificamos si el idProducto que traemos por id ya esta en la tabla
        boolean ingresado = detalles.stream().anyMatch(produ -> produ.getProducto().getIdProducto() == id);
        if (!ingresado) {
            detalles.add(detalleOrden);
        }
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); //suma todo los totales de esa lista

        orden.setTotal(sumaTotal);//guardo el total de la compra
        modelo.addAttribute("Carrito", detalles);
        //con esto puedo mostrar el total de la compra
        modelo.addAttribute("mostrarTotal", orden);//solo mando a llamar mostrarTotal
        return "usuario/carrito";
    }

    @GetMapping("/quitar/{id}")
    public String quitarProductoCarrito(@PathVariable Integer id, Model modelo) {
        //lista nueva de productos
        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

        //obtenemos los detalles osea los productos
        for (DetalleOrden d : detalles) {
            //y lo que hacemos que si el getIdProducto es diferente del id que traemos que lo agrege
            //pero si es igual no lo agregara
            if (d.getProducto().getIdProducto() != id) {
                ordenesNuevas.add(d);// se van agregando solo los id diferentes del que traemos por parametro
            }
        }
        //una ves que llenamos la ordeNueva los pasamos a la lista origial=detalles
        detalles = ordenesNuevas;

        //recalcular el total
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); //suma todo los totales de esa lista
        orden.setTotal(sumaTotal);//guardo el total de la compra
        modelo.addAttribute("Carrito", detalles);
        //con esto puedo mostrar el total de la compra
        modelo.addAttribute("mostrarTotal", orden);//solo mando a llamar mostrarTotal
        return "usuario/carrito";
    }

    @GetMapping("/irAlCarrito")
    public String irCarrito(Model modelo) {
        modelo.addAttribute("Carrito", detalles);
        //con esto puedo mostrar el total de la compra
        modelo.addAttribute("mostrarTotal", orden);//solo mando a llamar mostrarTotal
        return "usuario/carrito";
    }

    @GetMapping("/resumenOrden")
    public String resumenOrden(Model modelo) {
        Usuario usuario = usuarioServicios.buscatId(1).get();
        modelo.addAttribute("CarritoResumen", detalles);
        //con esto puedo mostrar el total de la compra
        modelo.addAttribute("mostrarResumenTotal", orden);//solo mando a llamar mostrarTotal
        modelo.addAttribute("enviarUsuario",usuario);//enviamos los datos de usuario
        return "usuario/resumenorden";
    }

    //guarda el detalle de la  orden
    @GetMapping("/guardarOrden")
    public String guardarOrden(){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenServicio.numeroOrden());
        Usuario usuario = usuarioServicios.buscatId(1).get();
        orden.setUsuario(usuario);
        ordenServicio.guardar(orden);

        for(DetalleOrden dt : detalles){
            dt.setOrden(orden);
            iDetalleOrdenServicio.guardar(dt);
        }

        //limpiamos los valo para que no se duplique
        orden = new Orden();
        detalles.clear();
        return "redirect:/";// redirigimos al la raiz
    }
}
