package com.mlesouza.springbootminhasfinancas.model.repository;

import com.mlesouza.springbootminhasfinancas.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    public static String EMAIL = "usuario@email.com";
    public static String NOME  = "Usuario";
    public static String SENHA = "senha";

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);
        boolean resultado = repository.existsByEmail(usuario.getEmail());
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
        boolean resultado = repository.existsByEmail(EMAIL);
        Assertions.assertThat(resultado).isFalse();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        Usuario usuario = criarUsuario();
        Usuario usuarioSalvo = repository.save(usuario);
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);
        Optional<Usuario> resultado = repository.findByEmail(EMAIL);
        Assertions.assertThat(resultado.isPresent()).isTrue();
    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
        Optional<Usuario> resultado = repository.findByEmail(EMAIL);
        Assertions.assertThat(resultado.isPresent()).isFalse();
    }

    public static Usuario criarUsuario() {
        return Usuario.builder()
                .nome(NOME)
                .email(EMAIL)
                .senha(SENHA)
                .build();
    }
}
