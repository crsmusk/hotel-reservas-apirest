package com.hotel.reservahabitaciones.Exception.Error;

public class Error {
    private int estado;
    private String mensaje;
    private Long timestamp;


    public Error() {
    }


    public Error(int estado, String mensaje, Long timestamp) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }


    public int getEstado() {
        return estado;
    }


    public void setEstado(int estado) {
        this.estado = estado;
    }


    public String getMensaje() {
        return mensaje;
    }


    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public Long getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
