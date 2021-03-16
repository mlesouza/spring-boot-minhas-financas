package com.mlesouza.springbootminhasfinancas.service.impl;

import com.mlesouza.springbootminhasfinancas.exception.ErroAutenticacao;
import com.mlesouza.springbootminhasfinancas.exception.RegraNegocioException;
import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;
import com.mlesouza.springbootminhasfinancas.model.repository.UsuarioRepository;
import com.mlesouza.springbootminhasfinancas.service.UsuarioService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        if (!usuario.isPresent()) {
            throw new ErroAutenticacao("Usuario não encontrado.");
        }
        if (!usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticacao("Senha inválida.");
        }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
        }
    }
}
