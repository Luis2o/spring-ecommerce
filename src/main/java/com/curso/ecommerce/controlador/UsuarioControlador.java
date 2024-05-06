package com.curso.ecommerce.controlador;

import com.curso.ecommerce.servicios.IUsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private IUsuarioServicios iUsuarioServicios;

    //vamos a acceder a esteme metodo como /usuario/registro para crear registrar usuarios
    @GetMapping("/registrar")
    public String crear(){
        return "usuario/registro";//usuario es la carpeta donde esta registro.html que es lo que tine el retur
    }
}
