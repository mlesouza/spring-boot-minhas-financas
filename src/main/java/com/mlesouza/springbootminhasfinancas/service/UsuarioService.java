package com.mlesouza.springbootminhasfinancas.service;

import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;

public interface UsuarioService {
    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

}
