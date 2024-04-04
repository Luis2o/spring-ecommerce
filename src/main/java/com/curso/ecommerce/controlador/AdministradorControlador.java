package com.curso.ecommerce.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorControlador {

    @GetMapping("")
    public String home() {
        return "administrador/home";
    }
}
