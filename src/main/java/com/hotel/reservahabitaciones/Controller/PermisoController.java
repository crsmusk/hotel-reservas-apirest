package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.PermisoDto;
import com.hotel.reservahabitaciones.Service.Interface.IPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/permisos")
public class PermisoController {


    private final IPermiso permisoService;
    
    public PermisoController(IPermiso permisoService){
        this.permisoService=permisoService;
    }

    @GetMapping
    public ResponseEntity<List<PermisoDto>>obtenerTodos(){
        return new ResponseEntity<>(permisoService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<PermisoDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(permisoService.obtenerPorId(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-nombre/{nombre}")
    public ResponseEntity<PermisoDto>obtenerPorNombre(@PathVariable String nombre){
        return new ResponseEntity<>(permisoService.obtenerPorNombre(nombre),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PermisoDto>guardar(@RequestBody PermisoDto permisoDto){
        PermisoDto creado = permisoService.guardar(permisoDto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public  ResponseEntity<PermisoDto>actualizar(@PathVariable Long id,@RequestBody PermisoDto permisoDto){
        return new ResponseEntity<>(permisoService.actualizar(id,permisoDto),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        permisoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

