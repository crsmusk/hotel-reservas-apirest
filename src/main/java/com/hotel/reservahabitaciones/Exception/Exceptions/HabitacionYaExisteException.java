package com.hotel.reservahabitaciones.Exception.Exceptions;

public class HabitacionYaExisteException extends RuntimeException {
    public HabitacionYaExisteException(String message) {
        super(message);
    }
}
