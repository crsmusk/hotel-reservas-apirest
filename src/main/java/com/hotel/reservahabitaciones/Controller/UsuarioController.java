package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.UsuarioDto;
import com.hotel.reservahabitaciones.Service.Interface.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/usuarios")
public class UsuarioController {


    private final IUsuario usuarioService;
    
    public UsuarioController(IUsuario usuarioService){
        this.usuarioService=usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>>obtenerTodos(){
        return new ResponseEntity<>(usuarioService.obtenerTodos(), HttpStatus.OK);
    }
    @GetMapping("/buscar-usuario-por-id/{id}")
    public ResponseEntity<UsuarioDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.obtenerPorId(id),HttpStatus.OK);
    }
    @GetMapping("/buscar-usuario-por-email/{email}")
    public ResponseEntity<UsuarioDto>obtenerPorEmail(@PathVariable String email){
        return new ResponseEntity<>(usuarioService.obtenerPorEmail(email),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> guardarUsuario(@RequestBody UsuarioDto UsuarioDto){
        usuarioService.guardar(UsuarioDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto>actualizar(@PathVariable Long id,@RequestBody UsuarioDto UsuarioDto){
        return new ResponseEntity<>(usuarioService.actualizar(id,UsuarioDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/actualizar-contrasena/{email}")
    public ResponseEntity<Void> actualizarContrasena(@PathVariable String email, @RequestBody String contrasenaNueva){
        usuarioService.actualizarContrasena(email, contrasenaNueva);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/actualizar-roles/{id}")
    public ResponseEntity<Void> actualizarRoles(@PathVariable Long id, @RequestBody List<String> roles){
        usuarioService.actualizarRoles(id, roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

