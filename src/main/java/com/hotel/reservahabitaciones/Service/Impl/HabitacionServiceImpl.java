package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.HabitacionNoEncontradaException;
import com.hotel.reservahabitaciones.Exception.Exceptions.HabitacionYaExisteException;
import com.hotel.reservahabitaciones.Mapper.HabitacionMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.HabitacionDto;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import com.hotel.reservahabitaciones.Repository.HabitacionRepository;
import com.hotel.reservahabitaciones.Service.Interface.IHabitacion;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements IHabitacion {


    private HabitacionRepository habitacionRepo;
    private HabitacionMapper mapper;

    public HabitacionServiceImpl(HabitacionRepository habitacionRepo, HabitacionMapper mapper) {
        this.habitacionRepo = habitacionRepo;
        this.mapper = mapper;
    }

    @Override
    public List<HabitacionDto> obtenerTodos() {
        List<Habitacion>habitaciones=habitacionRepo.findAll();
        if(habitaciones.isEmpty()){
           return List.of();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public HabitacionDto obtenerPorId(Long id) {
        Optional<Habitacion>habitacion=habitacionRepo.findById(id);
        if (habitacion.isPresent()){
            return mapper.habitacionAHabitacionDto(habitacion.get());
        }else{
            throw new HabitacionNoEncontradaException();
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorTipoHabitacion(String tipo) {
        List<Habitacion>habitaciones=habitacionRepo.findByTipoHabitacionContainingIgnoreCase(tipo);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorPreferencia(String preferencia) {
        List<Habitacion>habitaciones=habitacionRepo.findByPreferenciaContainingIgnoreCase(preferencia);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorCapacidad(int capacidad) {
        List<Habitacion>habitaciones=habitacionRepo.findByCapacidadGreaterThanEqual(capacidad);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorTamano(int tamano) {
        List<Habitacion>habitaciones=habitacionRepo.findByTamanoGreaterThanEqual(tamano);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorPrecioMenorQue(BigDecimal precio) {
        List<Habitacion>habitaciones=habitacionRepo.findByPrecioNocheLessThanEqual(precio);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else {
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorPrecioMayorQue(BigDecimal precio) {
        List<Habitacion>habitaciones=habitacionRepo.findByPrecioNocheGreaterThanEqual(precio);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerHabitacionesDisponibles() {
       List<Habitacion>habitaciones=habitacionRepo.findByEstadoTrue();
       if (habitaciones.isEmpty()){
           throw new HabitacionNoEncontradaException();
       }else{
           return mapper.habitacionesAHabitacionesDto(habitaciones);
       }
    }

    @Override
    public List<HabitacionDto> obtenerHabitacionesNoDisponibles() {
        List<Habitacion>habitaciones=habitacionRepo.findByEstadoFalse();
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else {
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDto> obtenerPorAccesibilidad(String accesibilidad) {
        List<Habitacion>listaHabitaciones=habitacionRepo.findByAccesibilidadContainingIgnoreCase(accesibilidad);
        if (listaHabitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(listaHabitaciones);
        }
    }

    @Override
    public void guardar(HabitacionDto HabitacionDto) {
        if (habitacionRepo.existsByNumeroHabitacion(HabitacionDto.getNumeroHabitacion())) {
            throw new HabitacionNoEncontradaException();
        } 
        Habitacion habitacion= new Habitacion();
        habitacion.setEstado(HabitacionDto.isEstado());
        habitacion.setTipoHabitacion(HabitacionDto.getTipoHabitacion());
        habitacion.setNumeroHabitacion(HabitacionDto.getNumeroHabitacion());
        habitacion.setAccesibilidad(HabitacionDto.getAccesibilidad());
        habitacion.setCapacidad(HabitacionDto.getCapacidad());
        habitacion.setDescripcion(HabitacionDto.getDescripcion());
        habitacion.setPrecioNoche(HabitacionDto.getPrecioNoche());
        habitacion.setPreferencia(HabitacionDto.getPreferencia());
        habitacion.setTamano(HabitacionDto.getTamaño());

        habitacionRepo.save(habitacion);
    }

    @Override
    public HabitacionDto actualizar(Long id, HabitacionDto HabitacionDto) {
        Optional<Habitacion> habitacion=habitacionRepo.findById(id);
        if (habitacion.isPresent()){
            habitacion.get().setEstado(HabitacionDto.isEstado());
            habitacion.get().setTipoHabitacion(HabitacionDto.getTipoHabitacion());
            habitacion.get().setNumeroHabitacion(HabitacionDto.getNumeroHabitacion());
            habitacion.get().setAccesibilidad(HabitacionDto.getAccesibilidad());
            habitacion.get().setCapacidad(HabitacionDto.getCapacidad());
            habitacion.get().setDescripcion(HabitacionDto.getDescripcion());
            habitacion.get().setPrecioNoche(HabitacionDto.getPrecioNoche());
            habitacion.get().setPreferencia(HabitacionDto.getPreferencia());
            habitacion.get().setTamano(HabitacionDto.getTamaño());
            habitacionRepo.save(habitacion.get());
            return mapper.habitacionAHabitacionDto(habitacion.get());
        }else {
            throw new HabitacionNoEncontradaException();
        }

    }

    @Override
    public void eliminar(Long id) {
      if (habitacionRepo.existsById(id)){
          habitacionRepo.deleteById(id);
      }else{
          throw new HabitacionNoEncontradaException();
      }
    }

}

