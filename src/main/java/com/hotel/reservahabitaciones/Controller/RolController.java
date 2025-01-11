package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;
import com.hotel.reservahabitaciones.Service.Impl.RolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/roles")
public class RolController {

    @Autowired
    RolServiceImpl rolService;

    @GetMapping
    public ResponseEntity<List<RolDTO>>getAll(){
        return new ResponseEntity<>(rolService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/buscar-rol-por-id/{id}")
    public ResponseEntity<RolDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(rolService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-rol-por-nombre/{nombre}")
    public ResponseEntity<RolDTO>getByName(@PathVariable String nombre){
        return new ResponseEntity<>(rolService.getByName(nombre),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>saveRol(@RequestBody RolDTO rolDTO){
        rolService.save(rolDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO>update(@RequestBody RolDTO rolDTO,@PathVariable Long id){
        return new ResponseEntity<>(rolService.updateName(id,rolDTO),HttpStatus.OK);
    }

    @PutMapping("/actualizar-permisos/{id}")
    public ResponseEntity<RolDTO>updatePermissions(@PathVariable Long id,@RequestBody List<String>permisos){
        return new ResponseEntity<>(rolService.updatePermissions(id,permisos),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        rolService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
