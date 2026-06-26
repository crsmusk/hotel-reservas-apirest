package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.HabitacionDto;
import com.hotel.reservahabitaciones.Service.Interface.IHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/hotel/habitaciones")
public class HabitacionController {


    private final IHabitacion habitacionService;
    
    public HabitacionController(IHabitacion habitacionService){
        this.habitacionService=habitacionService;
    }

    @GetMapping
    public ResponseEntity<List<HabitacionDto>>obtenerTodos(){
        return new ResponseEntity<>(habitacionService.obtenerTodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(habitacionService.obtenerPorId(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void>guardar(@RequestBody HabitacionDto HabitacionDto){
        habitacionService.guardar(HabitacionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDto>actualizar(@PathVariable Long id,@RequestBody HabitacionDto HabitacionDto){
        return new ResponseEntity<>(habitacionService.actualizar(id,HabitacionDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        habitacionService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/buscar-habitacion-por-tipo/{tipo}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorTipoHabitacion(@PathVariable String tipo){
        return new ResponseEntity<>(habitacionService.obtenerPorTipoHabitacion(tipo),HttpStatus.OK);
    }


    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<HabitacionDto>>obtenerHabitacionesDisponibles(){
        return new ResponseEntity<>(habitacionService.obtenerHabitacionesDisponibles(),HttpStatus.OK);
    }

    @GetMapping("/habitaciones-ocupadas")
    public ResponseEntity<List<HabitacionDto>>obtenerHabitacionesOcupadas(){
        return new ResponseEntity<>(habitacionService.obtenerHabitacionesNoDisponibles(),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-Preferencia/{preferencia}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorPreferenciaHabitacion(@PathVariable String preferencia){
        return new ResponseEntity<>(habitacionService.obtenerPorPreferencia(preferencia),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-capacidad/{capacidad}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorCapacidadHabitacion(@PathVariable int capacidad){
        return new ResponseEntity<>(habitacionService.obtenerPorCapacidad(capacidad),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-tamaño/{tamano}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorTamanoHabitacion(@PathVariable int tamano){
          return new ResponseEntity<>(habitacionService.obtenerPorTamano(tamano),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-precio-mayorQue/{precio}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorPrecioMayorQue(@PathVariable BigDecimal precio){
        return new ResponseEntity<>(habitacionService.obtenerPorPrecioMayorQue(precio),HttpStatus.OK);
    }


    @GetMapping("/buscar-por-precio-menorQue/{precio}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorPrecioMenorQue(@PathVariable BigDecimal precio){
        return new ResponseEntity<>(habitacionService.obtenerPorPrecioMenorQue(precio),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-accesibilidad/{accesibilidad}")
    public ResponseEntity<List<HabitacionDto>>obtenerPorAccesibilidad(@PathVariable String accesibilidad){
        return new ResponseEntity<>(habitacionService.obtenerPorAccesibilidad(accesibilidad),HttpStatus.OK);
    }
}

