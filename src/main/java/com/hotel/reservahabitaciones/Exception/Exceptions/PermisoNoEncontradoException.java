package com.hotel.reservahabitaciones.Exception.Exceptions;

public class PermisoNoEncontradoException extends RuntimeException{
    public PermisoNoEncontradoException(){
        super("No se encontró el permiso");
    }
    public PermisoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
