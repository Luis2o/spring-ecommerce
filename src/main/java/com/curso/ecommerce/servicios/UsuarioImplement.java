package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.Usuario;
import com.curso.ecommerce.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioImplement implements IUsuarioServicios{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public Optional<Usuario> buscatId(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Usuario guardar(Usuario u) {
        return usuarioRepositorio.save(u);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }
}
