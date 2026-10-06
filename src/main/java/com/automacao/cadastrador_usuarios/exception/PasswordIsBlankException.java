package com.automacao.cadastrador_usuarios.exception;

public class PasswordIsBlankException extends RuntimeException {
    public PasswordIsBlankException(String message) {
        super(message);
    }
}
