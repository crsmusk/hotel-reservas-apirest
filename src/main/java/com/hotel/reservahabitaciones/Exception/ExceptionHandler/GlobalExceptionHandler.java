package com.hotel.reservahabitaciones.Exception.ExceptionHandler;

import com.hotel.reservahabitaciones.Exception.Error.Error;
import com.hotel.reservahabitaciones.Exception.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HabitacionNoEncontradaException.class)
    public ResponseEntity<Error>manejarHabitacionNoEncontradaException(HabitacionNoEncontradaException ex){
        Error error=new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
                );
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HabitacionYaExisteException.class)
    public ResponseEntity<Error>manejarHabitacionYaExisteException(HabitacionYaExisteException ex){
        Error error=new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HabitacionNoDisponibleException.class)
    public ResponseEntity<Error>manejarHabitacionNoDisponibleException(HabitacionNoDisponibleException ex){
        Error error=new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RangoDeFechaNoDisponible.class)
    public  ResponseEntity<Error>manejarRangoDeFechaNoDisponible(RangoDeFechaNoDisponible ex){
        Error error=new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservaNoDisponibleException.class)
    public ResponseEntity<Error>manejarReservaNoDisponibleException(ReservaNoDisponibleException ex){
        Error error=new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ReservacionNoEncontradaException.class)
    public ResponseEntity<Error>manejarReservacionNoEncontradaException(ReservacionNoEncontradaException ex){
        Error error=new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RolNoEncontradoException.class)
    public ResponseEntity<Error>manejarRolNoEncontradoException(RolNoEncontradoException ex){
        Error error=new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PermisoNoEncontradoException.class)
    public ResponseEntity<Error>manejarPermisoNoEncontradoException(PermisoNoEncontradoException ex){
        Error error=new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Error>manejarUsuarioNoEncontradoException(UsuarioNoEncontradoException ex){
        Error error=new Error(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<Error>manejarDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex){
        Error error=new Error(
                HttpStatus.CONFLICT.value(),
                "Ya existe un registro con ese valor único. Verifique los datos e intente de nuevo.",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
