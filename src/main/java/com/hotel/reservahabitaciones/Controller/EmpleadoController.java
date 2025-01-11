package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;
import com.hotel.reservahabitaciones.Service.Impl.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoServiceImpl empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>>getAll(){
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar-empledo-por-id/{id}")
    public ResponseEntity<EmpleadoDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(empleadoService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-empleado-por-nombre/{nombre}")
    public ResponseEntity<List<EmpleadoDTO>>getByName(@PathVariable String nombre){
        return new ResponseEntity<>(empleadoService.getByName(nombre),HttpStatus.OK);
    }

    @GetMapping("/buscar-empleado-por-apellido/{apellido}")
    public ResponseEntity<List<EmpleadoDTO>>getByLastName(@PathVariable String apellido){
        return new ResponseEntity<>(empleadoService.getByLastName(apellido),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO>update(@PathVariable Long id,@RequestBody EmpleadoDTO empleadoDTO){
        return new ResponseEntity<>(empleadoService.update(id,empleadoDTO),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?>save(@RequestBody EmpleadoDTO empleadoDTO){
        empleadoService.save(empleadoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        empleadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
