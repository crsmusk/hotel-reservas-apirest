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

    @Autowired
    ReservacionServiceImpl reservacionService;

    @GetMapping
    public ResponseEntity<List<ReservacionDTO>>getAll(){
        return new ResponseEntity<>(reservacionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(reservacionService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-entradas-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByFechaEntradaMenoGreaterThan(@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.getByEntradaAntesDe(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-entradas-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByFechaEntradaLessThan(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.getByEntradaDespuesDe(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByFechaSalidaGreaterThan(@PathVariable LocalDate fecha){
      return new ResponseEntity<>(reservacionService.getBySalidaDespuesDe(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionDTO>>getByFechaSalidaLessThan(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.getBySalidaAntesDe(fecha),HttpStatus.OK);
    }

    @PutMapping("/Cambiar-habitacion/{id}/{idHabitacionActual}/{idNuevaHabitacion}/{salida}")
    public ResponseEntity<ReservacionDTO>changeRoom(@PathVariable Long id,
                                                    @PathVariable Long idHabitacionActual,
                                                    @PathVariable Long idNuevaHabitacion,
                                                    @PathVariable LocalDate salida){
        return new ResponseEntity<>(reservacionService.cambiarHabitacion(id,idHabitacionActual,idNuevaHabitacion,salida),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>save(@RequestBody ReservacionDTO reservacionDTO){
        reservacionService.save(reservacionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-salida/{fecha}")
    public ResponseEntity<ReservacionDTO>ChangeOutPut(@PathVariable Long id,@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.updateSalida(id,fecha),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        reservacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
