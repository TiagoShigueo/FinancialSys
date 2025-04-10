package br.com.financialsys.backend.exception;

public class BankAlreadyExistsException extends RuntimeException {
    public BankAlreadyExistsException(String message) {
        super(message);
    }
}
