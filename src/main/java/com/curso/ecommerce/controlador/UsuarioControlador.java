package com.curso.ecommerce.controlador;

import com.curso.ecommerce.modelo.Usuario;
import com.curso.ecommerce.servicios.IUsuarioServicios;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private IUsuarioServicios iUsuarioServicios;
    private final Logger log = LoggerFactory.getLogger(HomeControlador.class);

    //vamos a acceder a esteme metodo como /usuario/registro para crear registrar usuarios
    @GetMapping("/registrar")
    public String crear() {
        return "usuario/registro";//usuario es la carpeta donde esta registro.html que es lo que tine el retur
    }

    @PostMapping("/guardar")
    public String guardar(Usuario u) {
        log.info("Usuario registro: {}",u);
        //tipos de usuarios
        u.setTipo("Usuario");
        iUsuarioServicios.guardar(u);
        return "redirect:/";//redireccionamos a la raiz osea a la home que en realidad es index
    }

    //login
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(Usuario u, HttpSession session){
        log.info("datos de inicio de sesion: {}",u);
        Optional<Usuario> user = iUsuarioServicios.findByEmail(u.getEmail());
        log.info("Usuario obtenido por el email: {}",user.get());
        if(user.isPresent()){//verificamos si el usuario esta presente en el optiona
            //asi podre tener el id global
            session.setAttribute("idusuario",user.get().getIdUsuario());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }else{
            log.info("Usuario no existe");
        }
        return "redirect:/";//nos redireccionamos a la home
    }
}
