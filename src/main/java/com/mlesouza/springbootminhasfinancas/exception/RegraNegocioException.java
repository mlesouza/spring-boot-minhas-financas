package com.mlesouza.springbootminhasfinancas.exception;

public class RegraNegocioException extends RuntimeException{
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
