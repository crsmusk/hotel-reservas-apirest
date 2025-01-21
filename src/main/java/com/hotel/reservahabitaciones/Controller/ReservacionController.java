package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.ReservacionDTO;
import com.hotel.reservahabitaciones.Service.Impl.ReservacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotel/reservacion")
public class ReservacionController {


    private ReservacionServiceImpl reservacionService;
    @Autowired
    public void setReservacionService(ReservacionServiceImpl reservacionService){
        this.reservacionService=reservacionService;
    }

    @GetMapping
    public ResponseEntity<List<ReservacionDTO>>getAll(){
        return new ResponseEntity<>(reservacionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(reservacionService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-entradas-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByInputBeforeThan(@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.getByInputBeforeThan(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-entradas-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>> getByInPutAfterThan(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.getByInPutAfterThan(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByOutPutAfterThan(@PathVariable LocalDate fecha){
      return new ResponseEntity<>(reservacionService.getByOutPutAfterThan(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByOutPutBeforeThan(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.getByOutPutBeforeThan(fecha),HttpStatus.OK);
    }

    @PutMapping("/Cambiar-habitacion/{id}/{idHabitacionActual}/{idNuevaHabitacion}/{salida}")
    public ResponseEntity<ReservacionDTO>changeRoom(@PathVariable Long id,
                                                    @PathVariable Long idHabitacionActual,
                                                    @PathVariable Long idNuevaHabitacion,
                                                    @PathVariable LocalDate salida){
        return new ResponseEntity<>(reservacionService.changeRoom(id,idHabitacionActual,idNuevaHabitacion,salida),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void>save(@RequestBody ReservacionDTO reservacionDTO){
        reservacionService.save(reservacionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-salida/{fecha}")
    public ResponseEntity<ReservacionDTO>changeOutPut(@PathVariable Long id,@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.updateOutPut(id,fecha),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        reservacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
