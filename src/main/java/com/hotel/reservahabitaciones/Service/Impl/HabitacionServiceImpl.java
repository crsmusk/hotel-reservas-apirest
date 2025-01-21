package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.HabitacionNoEncontradaException;
import com.hotel.reservahabitaciones.Mapper.HabitacionMapper;
import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import com.hotel.reservahabitaciones.Repository.HabitacionRepository;
import com.hotel.reservahabitaciones.Service.Interface.IHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HabitacionServiceImpl implements IHabitacion {


    private HabitacionRepository habitacionRepo;
    @Autowired
    public void setHabitacionRepo(HabitacionRepository habitacionRepo){
        this.habitacionRepo=habitacionRepo;
    }

    private HabitacionMapper mapper;
    @Autowired
    public void setMapper(HabitacionMapper mapper){
        this.mapper=mapper;
    }

    @Override
    public List<HabitacionDTO> getAll() {
        if(habitacionRepo.findAll().isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findAll());
        }
    }

    @Override
    public HabitacionDTO getById(Long id) {
        if (habitacionRepo.existsById(id)){
            return mapper.habitacionAHabitacionDto(habitacionRepo.findById(id).get());
        }else{
            throw new HabitacionNoEncontradaException();
        }
    }

    @Override
    public List<HabitacionDTO> getByTypeRoom(String tipo) {
        List<Habitacion>habitaciones=habitacionRepo.findByTipoHabitacionContainingIgnoreCase(tipo);
        if (habitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitaciones);
        }
    }

    @Override
    public List<HabitacionDTO> getByPreference(String preferencia) {
        if (habitacionRepo.findByPreferenciaContainingIgnoreCase(preferencia).isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByPreferenciaContainingIgnoreCase(preferencia));
        }
    }

    @Override
    public List<HabitacionDTO> getByCapacity(int capacidad) {
        if (habitacionRepo.findByCapacidadGreaterThanEqual(capacidad).isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByCapacidadGreaterThanEqual(capacidad));
        }
    }

    @Override
    public List<HabitacionDTO> getBySize(int tamano) {
        if (habitacionRepo.findByTamanoGreaterThanEqual(tamano).isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByTamanoGreaterThanEqual(tamano));
        }
    }

    @Override
    public List<HabitacionDTO> getByPriceLessThan(BigDecimal precio) {
        if (habitacionRepo.findByPrecioNocheLessThanEqual(precio).isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else {
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByPrecioNocheLessThanEqual(precio));
        }
    }

    @Override
    public List<HabitacionDTO> getByPriceGreaterThan(BigDecimal precio) {
        if (habitacionRepo.findByPrecioNocheGreaterThanEqual(precio).isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByPrecioNocheGreaterThanEqual(precio));
        }
    }

    @Override
    public List<HabitacionDTO> getByRoomAvailable() {
       if (habitacionRepo.findByEstadoTrue().isEmpty()){
           throw new HabitacionNoEncontradaException();
       }else{
           return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByEstadoTrue());
       }
    }

    @Override
    public List<HabitacionDTO> getByRoomUnaVailable() {
        if (habitacionRepo.findByEstadoFalse().isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else {
            return mapper.habitacionesAHabitacionesDto(habitacionRepo.findByEstadoFalse());
        }
    }

    @Override
    public List<HabitacionDTO> getByAccesibility(String accesibilidad) {
        List<Habitacion>listaHabitaciones=habitacionRepo.findByAccesibilidadContainingIgnoreCase(accesibilidad);
        if (listaHabitaciones.isEmpty()){
            throw new HabitacionNoEncontradaException();
        }else{
            return mapper.habitacionesAHabitacionesDto(listaHabitaciones);
        }
    }

    @Override
    public void save(HabitacionDTO habitacionDTO) {
        Habitacion habitacion=new Habitacion();
        habitacion.setEstado(habitacionDTO.isEstado());
        habitacion.setTipoHabitacion(habitacionDTO.getTipoHabitacion());
        habitacion.setAccesibilidad(habitacionDTO.getAccesibilidad());
        habitacion.setCapacidad(habitacionDTO.getCapacidad());
        habitacion.setDescripcion(habitacionDTO.getDescripcion());
        habitacion.setPrecioNoche(habitacionDTO.getPrecioNoche());
        habitacion.setPreferencia(habitacionDTO.getPreferencia());
        habitacion.setTamano(habitacionDTO.getTamaño());
        habitacionRepo.save(habitacion);
    }

    @Override
    public HabitacionDTO update(Long id, HabitacionDTO habitacionDTO) {
        if (habitacionRepo.existsById(id)){
            Habitacion habitacion=habitacionRepo.findById(id).get();
            habitacion.setEstado(habitacionDTO.isEstado());
            habitacion.setTipoHabitacion(habitacionDTO.getTipoHabitacion());
            habitacion.setAccesibilidad(habitacionDTO.getAccesibilidad());
            habitacion.setCapacidad(habitacionDTO.getCapacidad());
            habitacion.setDescripcion(habitacionDTO.getDescripcion());
            habitacion.setPrecioNoche(habitacionDTO.getPrecioNoche());
            habitacion.setPreferencia(habitacionDTO.getPreferencia());
            habitacion.setTamano(habitacionDTO.getTamaño());
            habitacionRepo.save(habitacion);
            return mapper.habitacionAHabitacionDto(habitacion);
        }else {
            throw new HabitacionNoEncontradaException();
        }

    }

    @Override
    public void delete(Long id) {
      if (habitacionRepo.existsById(id)){
          habitacionRepo.deleteById(id);
      }else{
          throw new HabitacionNoEncontradaException();
      }
    }

}
