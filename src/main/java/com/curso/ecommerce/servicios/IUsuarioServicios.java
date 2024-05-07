package com.curso.ecommerce.servicios;

import com.curso.ecommerce.modelo.Usuario;

import java.util.Optional;

public interface IUsuarioServicios {
    Optional<Usuario> buscatId(Integer id);

    Usuario guardar(Usuario u);

    Optional<Usuario> findByEmail(String email);
}
