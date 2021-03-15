package com.mlesouza.springbootminhasfinancas.service;

import com.mlesouza.springbootminhasfinancas.exception.RegraNegocioException;
import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;
import com.mlesouza.springbootminhasfinancas.model.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    UsuarioService service;

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveValidarEmail() {
        repository.deleteAll();
        service.validarEmail("usuario@email.com");
    }

    @Test
    public void deveRetornarErroQuandoExistirEmailCadastrado() {
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
        repository.save(usuario);
        Exception excecao = Assertions.assertThrows(Exception.class, () -> service.validarEmail("usuario@email.com"),
                "Já existe um usuário cadastrado com este email");

    }
}
