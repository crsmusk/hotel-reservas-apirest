package com.hotel.reservahabitaciones.Exception.Exceptions;

// TODO: Documentar y utilizar esta excepción en HabitacionServiceImpl.obtenerPorAccesibilidad()
public class AccesibilidadNoEncontrada extends RuntimeException{
    public AccesibilidadNoEncontrada(String message) {
        super(message);
    }
}
