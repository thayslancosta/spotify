package br.com.minispotify.Exceptions;

public class EmailInvalidoException extends Exception {

    public EmailInvalidoException() {
        super("Email inválido! Digite um email com '@'.");
    }

    public EmailInvalidoException(String message) {
        super(message);
    }
}
