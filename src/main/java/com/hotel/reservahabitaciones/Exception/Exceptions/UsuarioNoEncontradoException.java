package com.hotel.reservahabitaciones.Exception.Exceptions;

public class UsuarioNoEncontradoException extends  RuntimeException{

    public UsuarioNoEncontradoException(){
        super("no se encontro ala persona");
    }
}
