package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;
import com.hotel.reservahabitaciones.Service.Impl.HabitacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/hotel/habitaciones")
public class HabitacionController {

    @Autowired
    HabitacionServiceImpl habitacionService;

    @GetMapping
    public ResponseEntity<List<HabitacionDTO>>getAll(){
        return new ResponseEntity<>(habitacionService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HabitacionDTO>getById(@PathVariable Long id){
        return new ResponseEntity<>(habitacionService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?>save(@RequestBody HabitacionDTO habitacionDTO){
        habitacionService.save(habitacionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HabitacionDTO>update(@PathVariable Long id,@RequestBody HabitacionDTO habitacionDTO){
        return new ResponseEntity<>(habitacionService.update(id,habitacionDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        habitacionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/buscar-habitacion-por-tipo/{tipo}")
    public ResponseEntity<List<HabitacionDTO>>getByroomType(@PathVariable String tipo){
        return new ResponseEntity<>(habitacionService.getByTypeRoom(tipo),HttpStatus.OK);
    }


    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<HabitacionDTO>>getRommroomAvailable(){
        return new ResponseEntity<>(habitacionService.getByRoomAvailable(),HttpStatus.OK);
    }

    @GetMapping("/habitaciones-ocupadas")
    public ResponseEntity<List<HabitacionDTO>>getRoomOccupied(){
        return new ResponseEntity<>(habitacionService.getByRoomUnaVailable(),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-Preferencia/{preferencia}")
    public ResponseEntity<List<HabitacionDTO>>getByRoomPreference(@PathVariable String preferencia){
        return new ResponseEntity<>(habitacionService.getByPreference(preferencia),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-capacidad/{capacidad}")
    public ResponseEntity<List<HabitacionDTO>>getByRoomCapacity(@PathVariable int capacidad){
        return new ResponseEntity<>(habitacionService.getByCapacity(capacidad),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-tamaño/{tamaño}")
    public ResponseEntity<List<HabitacionDTO>>getBySizeRoom(@PathVariable int tamaño){
          return new ResponseEntity<>(habitacionService.getBySize(tamaño),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-precio-mayorQue/{precio}")
    public ResponseEntity<List<HabitacionDTO>>getByPriceGreaterThan(@PathVariable BigDecimal precio){
        return new ResponseEntity<>(habitacionService.getByPriceGreaterThan(precio),HttpStatus.OK);
    }


    @GetMapping("/buscar-por-precio-menorQue/{precio}")
    public ResponseEntity<List<HabitacionDTO>>getByPriceLessThan(@PathVariable BigDecimal precio){
        return new ResponseEntity<>(habitacionService.getByPriceLessThan(precio),HttpStatus.OK);
    }

    @GetMapping("/buscar-por-accesibilidad/{accesibilidad}")
    public ResponseEntity<List<HabitacionDTO>>getByaccessibility(@PathVariable String accesibilidad){
        return new ResponseEntity<>(habitacionService.getByAccesibility(accesibilidad),HttpStatus.OK);
    }
}
