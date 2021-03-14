package com.mlesouza.springbootminhasfinancas.model.repository;

import com.mlesouza.springbootminhasfinancas.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
