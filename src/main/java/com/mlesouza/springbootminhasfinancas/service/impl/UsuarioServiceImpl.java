package com.mlesouza.springbootminhasfinancas.service.impl;

import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;
import com.mlesouza.springbootminhasfinancas.model.repository.UsuarioRepository;
import com.mlesouza.springbootminhasfinancas.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {

    }
}
