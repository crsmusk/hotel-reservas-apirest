package com.hotel.reservahabitaciones.Exception.Exceptions;

public class RolNoEncontradoException extends RuntimeException{
    public RolNoEncontradoException(){
        super("No se econtro el rol");
    }
}
