package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.UsuarioDTO;
import com.hotel.reservahabitaciones.Service.Impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>>getAll(){
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/buscar-usuario-por-id/{id}")
    public ResponseEntity<UsuarioDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.getById(id),HttpStatus.OK);
    }
    @GetMapping("/buscar-usuario-por-email/{email}")
    public ResponseEntity<UsuarioDTO>getByEmail(@PathVariable String email){
        return new ResponseEntity<>(usuarioService.getByEmail(email),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO usuarioDTO){
        usuarioService.save(usuarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO>update(@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.update(id,usuarioDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
