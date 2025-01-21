package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Service.Impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/hotel/clientes")
public class ClienteController {

    @Autowired
    ClienteServiceImpl clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>>getAll(){
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-por-id/{id}")
    public ResponseEntity<ClienteDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(clienteService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-por-nombre/{nombre}")
    public ResponseEntity<List<ClienteDTO>>getByName(@PathVariable String nombre){
        return new ResponseEntity<>(clienteService.getByName(nombre),HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-por-apellido/{apellido}")
    public ResponseEntity<List<ClienteDTO>>getByLastName(@PathVariable String apellido){
        return new ResponseEntity<>(clienteService.getByLastName(apellido),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void>save(@RequestBody ClienteDTO clienteDTO){
        clienteService.save(clienteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO>update(@PathVariable Long id,@RequestBody ClienteDTO clienteDTO){
        return new ResponseEntity<>(clienteService.update(id,clienteDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
