package com.mlesouza.springbootminhasfinancas.exception;

public class ErroAutenticacao extends RuntimeException{
    public ErroAutenticacao(String mensagem) {
        super(mensagem);
    }
}