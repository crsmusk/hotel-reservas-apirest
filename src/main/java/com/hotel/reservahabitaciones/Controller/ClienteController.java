package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ClienteSimplificadoDto;
import com.hotel.reservahabitaciones.Service.Interface.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/hotel/clientes")
public class ClienteController {

    private final ICliente clienteService;

    public ClienteController(ICliente clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteSimplificadoDto>>obtenerTodos(){
        return new ResponseEntity<>(clienteService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-por-id/{id}")
    public ResponseEntity<ClienteSimplificadoDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(clienteService.obtenerPorId(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-por-nombre/{nombre}")
    public ResponseEntity<List<ClienteSimplificadoDto>>obtenerPorNombre(@PathVariable String nombre){
        return new ResponseEntity<>(clienteService.obtenerPorNombre(nombre),HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente-por-apellido/{apellido}")
    public ResponseEntity<List<ClienteSimplificadoDto>>obtenerPorApellido(@PathVariable String apellido){
        return new ResponseEntity<>(clienteService.obtenerPorApellido(apellido),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void>guardar(@Valid @RequestBody ClienteDto clienteDto){
        clienteService.guardar(clienteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteSimplificadoDto>actualizar(@PathVariable Long id, @Valid @RequestBody ClienteDto clienteDto){
        return new ResponseEntity<>(clienteService.actualizar(id,clienteDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

