package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.RolDto;
import com.hotel.reservahabitaciones.Service.Interface.IRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/roles")
public class RolController {


    private final IRol rolService;
    
    public RolController(IRol rolService){
        this.rolService=rolService;
    }

    @GetMapping
    public ResponseEntity<List<RolDto>>obtenerTodos(){
        return new ResponseEntity<>(rolService.obtenerTodos(), HttpStatus.OK);
    }
    @GetMapping("/buscar-rol-por-id/{id}")
    public ResponseEntity<RolDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(rolService.obtenerPorId(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-rol-por-nombre/{nombre}")
    public ResponseEntity<RolDto>obtenerPorNombre(@PathVariable String nombre){
        return new ResponseEntity<>(rolService.obtenerPorNombre(nombre),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void>guardarRol(@RequestBody RolDto RolDto){
        rolService.guardar(RolDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto>actualizar(@RequestBody RolDto RolDto,@PathVariable Long id){
        return new ResponseEntity<>(rolService.actualizarNombre(id,RolDto),HttpStatus.OK);
    }

    @PutMapping("/actualizar-permisos/{id}")
    public ResponseEntity<RolDto>actualizarPermisos(@PathVariable Long id,@RequestBody List<String>permisos){
        return new ResponseEntity<>(rolService.actualizarPermisos(id,permisos),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        rolService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

