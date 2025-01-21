package com.hotel.reservahabitaciones.Exception.Exceptions;

public class ReservacionNoEncontradaException  extends  RuntimeException{

    public ReservacionNoEncontradaException(){
        super("No se encontro la reservacion");
    }
}
