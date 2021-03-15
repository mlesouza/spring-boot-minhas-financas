package com.mlesouza.springbootminhasfinancas.service;

import com.mlesouza.springbootminhasfinancas.model.repository.UsuarioRepository;
import com.mlesouza.springbootminhasfinancas.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    UsuarioService service;
    UsuarioRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);
    }

    @Test
    public void deveValidarEmail() {
        Assertions.assertDoesNotThrow(() -> {
            Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
            service.validarEmail("usuario@email.com");
        });
    }

    @Test
    public void deveRetornarErroQuandoExistirEmailCadastrado() {
       Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
       Assertions.assertThrows(Exception.class, () -> service.validarEmail("usuario@email.com"),
                "Já existe um usuário cadastrado com este email");

    }
}
