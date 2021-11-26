package com.empresajr.fatec.services.exceptions;

public class InsertQueryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsertQueryException(String msg){
        super(msg);
    }
}
