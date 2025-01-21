package com.hotel.reservahabitaciones.Exception.Exceptions;

public class ReservaNoDisponibleException extends RuntimeException{
    public ReservaNoDisponibleException(){
        super("No se pudo hacer la reservacion");
    }
}
