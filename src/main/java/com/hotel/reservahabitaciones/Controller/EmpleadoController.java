package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.EmpleadoDto;
import com.hotel.reservahabitaciones.Service.Interface.IEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/empleados")
public class EmpleadoController {


    private final IEmpleado empleadoService;
    
    public EmpleadoController(IEmpleado empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDto>>obtenerTodos(){
        return new ResponseEntity<>(empleadoService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/buscar-empleado-por-id/{id}")
    public ResponseEntity<EmpleadoDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(empleadoService.obtenerPorId(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-empleado-por-nombre/{nombre}")
    public ResponseEntity<List<EmpleadoDto>>obtenerPorNombre(@PathVariable String nombre){
        return new ResponseEntity<>(empleadoService.obtenerPorNombre(nombre),HttpStatus.OK);
    }

    @GetMapping("/buscar-empleado-por-apellido/{apellido}")
    public ResponseEntity<List<EmpleadoDto>>obtenerPorApellido(@PathVariable String apellido){
        return new ResponseEntity<>(empleadoService.obtenerPorApellido(apellido),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto>actualizar(@PathVariable Long id,@RequestBody EmpleadoDto EmpleadoDto){
        return new ResponseEntity<>(empleadoService.actualizar(id,EmpleadoDto),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Void>guardar(@RequestBody EmpleadoDto EmpleadoDto){
        empleadoService.guardar(EmpleadoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        empleadoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cambiar-puesto/{id}")
    public ResponseEntity<Void> cambiarPuesto(@PathVariable Long id, @RequestBody String puesto){
        empleadoService.cambiarPuesto(id, puesto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

