package com.hotel.reservahabitaciones.Exception.Exceptions;

public class HabitacionNoEncontradaException extends RuntimeException{
    public HabitacionNoEncontradaException(){
        super("no se encontraron habitaciones ");
    }
}
