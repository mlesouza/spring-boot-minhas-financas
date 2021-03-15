package com.mlesouza.springbootminhasfinancas.service.impl;

import com.mlesouza.springbootminhasfinancas.exception.RegraNegocioException;
import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;
import com.mlesouza.springbootminhasfinancas.model.repository.UsuarioRepository;
import com.mlesouza.springbootminhasfinancas.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

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
       boolean existe = repository.existsByEmail(email);
       if (existe) {
           throw new RegraNegocioException("Já existe um usuário cadastrado com este email");
       }
    }
}
