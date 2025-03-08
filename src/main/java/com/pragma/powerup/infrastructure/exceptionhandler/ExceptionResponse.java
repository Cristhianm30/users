package com.pragma.powerup.infrastructure.exceptionhandler;

public enum ExceptionResponse {

    NO_DATA_FOUND("No data found for the requested petition"),

    BAD_EMAIL("No se encotró el Email"),

    INVALID_AGE("El propietario deber tener minimo 18 años"),

    INVALID_DOCUMENTNUMBER("Numero de documento"),

    INVALID_CELLPHONE("Numero de telefono incorrecto"),

    INVALID_USER_FIELDS("Campos del usuario incorrectos");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}