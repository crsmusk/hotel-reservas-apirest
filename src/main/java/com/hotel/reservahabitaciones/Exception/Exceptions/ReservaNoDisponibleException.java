package com.hotel.reservahabitaciones.Exception.Exceptions;

public class ReservaNoDisponibleException extends RuntimeException{
    public ReservaNoDisponibleException(Long habitacion){
        super("No se pudo hacer la reservacion de la habitacion con el id "+habitacion);
    }
}
