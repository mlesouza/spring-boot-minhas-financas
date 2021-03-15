package com.mlesouza.springbootminhasfinancas.service;

import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;
import com.mlesouza.springbootminhasfinancas.model.repository.UsuarioRepository;
import com.mlesouza.springbootminhasfinancas.service.impl.UsuarioServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    public static String EMAIL = "usuario@email.com";
    public static String NOME  = "Usuario";
    public static String SENHA = "senha";

    UsuarioService service;
    @MockBean
    UsuarioRepository repository;

    @BeforeEach
    public void setUp() {
        service = new UsuarioServiceImpl(repository);
    }

    @Test
    public void deveAutenticarUmUsuarioComSucesso() {
        Usuario usuario = Usuario.builder().nome(NOME).email(EMAIL).senha(SENHA).id(1L).build();
        Mockito.when(repository.findByEmail(EMAIL)).thenReturn(Optional.of(usuario));

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            Usuario resultado = service.autenticar(EMAIL, SENHA);

            Assertions.assertThat(resultado).isNotNull();
        });
    }

    @Test
    public void deveValidarEmail() {
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> {
            Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
            service.validarEmail(EMAIL);
        });
    }

    @Test
    public void deveRetornarErroQuandoExistirEmailCadastrado() {
       Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> service.validarEmail(EMAIL),
                "Já existe um usuário cadastrado com este email");

    }
}
