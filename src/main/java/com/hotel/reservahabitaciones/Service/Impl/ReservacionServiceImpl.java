package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.*;
import com.hotel.reservahabitaciones.Mapper.ReservacionMapper;
import com.hotel.reservahabitaciones.Model.DTOs.ReservacionDTO;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import com.hotel.reservahabitaciones.Model.Entities.Reservacion;
import com.hotel.reservahabitaciones.Repository.ClienteRepository;
import com.hotel.reservahabitaciones.Repository.HabitacionRepository;
import com.hotel.reservahabitaciones.Repository.ReservaRepository;
import com.hotel.reservahabitaciones.Service.Interface.IReservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ReservacionServiceImpl implements IReservacion {

    private ReservaRepository reservaRepo;
    @Autowired
    public void setReservaRepo(ReservaRepository reservaRepo){
        this.reservaRepo=reservaRepo;
    }

    private ReservacionMapper mapper;
    @Autowired
    public void setReservacionMapper(ReservacionMapper mapper){
        this.mapper=mapper;
    }

    private HabitacionRepository habitacionRepo;
    @Autowired
    public void setHabitacionRepo(HabitacionRepository habitacionRepo){
        this.habitacionRepo=habitacionRepo;
    }

    private ClienteRepository clienteRepo;
    @Autowired
    public void setClienteRepo(ClienteRepository clienteRepo){
        this.clienteRepo=clienteRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getAll() {
        if (reservaRepo.findAll().isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else{
            return mapper.reservacionesAReservacionesDto(reservaRepo.findAll());
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ReservacionDTO getById(Long id) {
       if (reservaRepo.findById(id).isPresent()){
           return mapper.reservacionAreservacionDto(reservaRepo.findById(id).get());
       }else{
           throw new ReservacionNoEncontradaException();
       }

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
     if (reservaRepo.existsById(id)){
         reservaRepo.deleteById(id);
     }else {
         throw  new ReservacionNoEncontradaException();
     }
    }

    @Override
    @Transactional
    public void save(ReservacionDTO reservacionDTO) {
        Reservacion reservacion=new Reservacion();
        reservacion.setFechaEntrada(reservacionDTO.getFechaEntrada());
        reservacion.setFechaSalida(reservacionDTO.getFechaSalida());

        List<Habitacion>lista=new ArrayList<>();

        if (clienteRepo.existsById(reservacionDTO.getIdCliente())){
            reservacion.setCliente(clienteRepo.findById(reservacionDTO.getIdCliente()).get());
        }else{
            throw new UsuarioNoEncontradoException();
        }

        for(Long id:reservacionDTO.getIds()){
            Optional<Habitacion>room=habitacionRepo.findById(id);
            if (room.isPresent()){
                if (available(room.get(),reservacionDTO.getFechaEntrada())){
                    lista.add(room.get());
                }
            }else{
                throw new HabitacionNoEncontradaException();
            }
        }
        reservacion.setHabitacions(lista);
        reservaRepo.save(reservacion);

    }

    @Override
    public ReservacionDTO updateOutPut(Long id, LocalDate salida) {
       if (reservaRepo.existsById(id)){
           Reservacion reservacion=reservaRepo.findById(id).get();
           reservacion.setFechaSalida(salida);
           reservaRepo.save(reservacion);
           return mapper.reservacionAreservacionDto(reservacion);
       }else{
           throw new ReservacionNoEncontradaException();
       }
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getByOutPutAfterThan(LocalDate fecha) {
        if (reservaRepo.findByFechaSalidaGreaterThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else {
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaSalidaGreaterThanEqual(fecha));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getByOutPutBeforeThan(LocalDate fecha) {
        if (reservaRepo.findByFechaSalidaLessThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else {
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaSalidaLessThanEqual(fecha));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getByInPutAfterThan(LocalDate fecha) {
        if (reservaRepo.findByFechaEntradaGreaterThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else{
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaEntradaGreaterThanEqual(fecha));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getByInputBeforeThan(LocalDate fecha) {
        if (reservaRepo.findByFechaEntradaLessThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else{
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaEntradaLessThanEqual(fecha));
        }
    }

    @Override
    @Transactional
    public ReservacionDTO changeRoom(Long idReserva, Long idHabitacionActual, Long idNuevaHabitacion, LocalDate salida) {
        List<Habitacion>listaHabitaciones=new ArrayList<>();

        if (reservaRepo.existsById(idReserva)){
            Reservacion reservacion=reservaRepo.findById(idReserva).get();
            cambiarEstados(idHabitacionActual);
            //por si reservo mas de una habitacion
            for (Habitacion h:reservacion.getHabitacions()){
                Long idHabitacion=h.getId();
                if (idHabitacion.equals(idHabitacionActual)){
                    continue;
                }
                listaHabitaciones.add(h);
            }
            Optional<Habitacion>room=habitacionRepo.findById(idNuevaHabitacion);
            if (room.isPresent()){
                if (available(room.get(),LocalDate.now())){
                    listaHabitaciones.add(habitacionRepo.findById(idNuevaHabitacion).get());
                    reservacion.setHabitacions(listaHabitaciones);
                    reservaRepo.save(reservacion);
                    return mapper.reservacionAreservacionDto(reservacion);
                }else{
                    throw new ReservaNoDisponibleException();
                }

            }else{
                throw new HabitacionNoEncontradaException();
            }
        }else {
            throw new ReservacionNoEncontradaException();
        }
    }


    public Boolean available(Habitacion room, LocalDate fechaEntrada){
        if (room.isEstado()){
            room.setEstado(false);
            habitacionRepo.save(room);
            return true;

        }else  if (fechaEntrada.isAfter(room.getReservacion().getFechaSalida())) {
            return true;
        }
        return false;

    }
    //@Transactional
    public void cambiarEstados(Long id) {
        Optional<Habitacion>room=habitacionRepo.findById(id);
        if (room.isPresent()){
            Habitacion habitacion=room.get();
            habitacion.setEstado(true);
            habitacionRepo.save(habitacion);
        }else {
            throw  new HabitacionNoEncontradaException();
        }
    }

}
