package com.hotel.reservahabitaciones.Exception.Exceptions;

// TODO: Documentar y utilizar esta excepción en ReservacionServiceImpl para validar rangos de fecha
public class RangoDeFechaNoDisponible extends RuntimeException{
    public RangoDeFechaNoDisponible(String message){
        super(message);
    }
}
