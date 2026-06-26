package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.*;
import com.hotel.reservahabitaciones.Mapper.ReservacionMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.ReservacionDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ReservacionSimplificadoDto;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import com.hotel.reservahabitaciones.Model.Entities.Reservacion;
import com.hotel.reservahabitaciones.Repository.ClienteRepository;
import com.hotel.reservahabitaciones.Repository.HabitacionRepository;
import com.hotel.reservahabitaciones.Repository.ReservaRepository;
import com.hotel.reservahabitaciones.Service.Interface.IReservacion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ReservacionServiceImpl implements IReservacion {

    private ReservaRepository reservaRepo;
    private ReservacionMapper mapper;
    private HabitacionRepository habitacionRepo;
    private ClienteRepository clienteRepo;

    public ReservacionServiceImpl(ReservaRepository reservaRepo, ReservacionMapper mapper, HabitacionRepository habitacionRepo, ClienteRepository clienteRepo) {
        this.reservaRepo = reservaRepo;
        this.mapper = mapper;
        this.habitacionRepo = habitacionRepo;
        this.clienteRepo = clienteRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionSimplificadoDto> obtenerTodos() {
        List<Reservacion>reservaciones=reservaRepo.findAll();
        if (reservaciones.isEmpty()) {
            return List.of();
        }else{

            return mapper.reservacionesAReservacionesDto(reservaciones);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ReservacionSimplificadoDto obtenerPorId(Long id) {
        Optional<Reservacion>reservacion=reservaRepo.findById(id);
       if (reservacion.isPresent()){
           return mapper.reservacionAReservacionDto(reservacion.get());
       }else{
           throw new ReservacionNoEncontradaException();
       }

    }

    @Override
    @Transactional
    public void eliminar(Long id) {
     if (reservaRepo.existsById(id)){
         reservaRepo.deleteById(id);
     }else {
         throw  new ReservacionNoEncontradaException();
     }
    }

    @Override
    @Transactional
    public void guardar(ReservacionDto ReservacionDto) {
        Reservacion reservacion = new Reservacion();
        reservacion.setFechaEntrada(ReservacionDto.fechaEntrada());
        reservacion.setFechaSalida(ReservacionDto.fechaSalida());

        Optional<Cliente> cliente = clienteRepo.findById(ReservacionDto.idCliente());
        if (cliente.isPresent()){
            reservacion.setCliente(cliente.get());
        } else {
            throw new UsuarioNoEncontradoException();
        }

        List<Habitacion> lista = new ArrayList<>();
        List<Habitacion> habitaciones = habitacionRepo.findByIdInWithLock(ReservacionDto.habitacionIds());

        for (Habitacion habitacion : habitaciones) {
            if (estaDisponible(habitacion, ReservacionDto.fechaEntrada())) {
                habitacion.setEstado(false);
                habitacionRepo.save(habitacion);
                lista.add(habitacion);
            } else {
                throw new ReservaNoDisponibleException(habitacion.getId());
            }
        }

        reservacion.setHabitacionesActivas(lista);
        reservaRepo.save(reservacion);
    }


    @Override
    @Transactional
    public ReservacionSimplificadoDto actualizarFechaSalida(Long id, Long idHabitacion, LocalDate salida) {
        Optional<Reservacion> reservacion = reservaRepo.findById(id);
        if (reservacion.isPresent()) {
            Optional<Reservacion> reservacionProxima = reservaRepo.findFirstByHabitacionesActivasIdAndFechaEntradaGreaterThanOrderByFechaEntradaAsc(idHabitacion, reservacion.get().getFechaSalida());
            if (reservacionProxima.isPresent() && salida.isAfter(reservacionProxima.get().getFechaEntrada())) {
                throw new HabitacionNoDisponibleException("la habitacion ya tiene una reserva para esa hora");
            }
            reservacion.get().setFechaSalida(salida);
            reservaRepo.save(reservacion.get());
            return mapper.reservacionAReservacionDto(reservacion.get());
        } else {
            throw new ReservacionNoEncontradaException();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservacionSimplificadoDto> obtenerPorSalidaDespuesDe(LocalDate fecha) {
        List<Reservacion>reservaciones=reservaRepo.findByFechaSalidaGreaterThanEqual(fecha);
        if (reservaciones.isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else {
            return mapper.reservacionesAReservacionesDto(reservaciones);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionSimplificadoDto> obtenerPorSalidaAntesDe(LocalDate fecha) {
        List<Reservacion>reservaciones=reservaRepo.findByFechaSalidaLessThanEqual(fecha);
        if (reservaciones.isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else {
            return mapper.reservacionesAReservacionesDto(reservaciones);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionSimplificadoDto> obtenerPorEntradaDespuesDe(LocalDate fecha) {
        List<Reservacion>reservaciones=reservaRepo.findByFechaEntradaGreaterThanEqual(fecha);
        if (reservaciones.isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else{
            return mapper.reservacionesAReservacionesDto(reservaciones);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionSimplificadoDto> obtenerPorEntradaAntesDe(LocalDate fecha) {
        List<Reservacion>reservaciones=reservaRepo.findByFechaEntradaLessThanEqual(fecha);
        if (reservaciones.isEmpty()){
            throw new ReservacionNoEncontradaException();
        }else{
            return mapper.reservacionesAReservacionesDto(reservaciones);
        }
    }

    @Override
    @Transactional
    public ReservacionSimplificadoDto cambiarHabitacion(Long idReserva, Long idHabitacionActual, Long idNuevaHabitacion, LocalDate salida) {

        Optional<Reservacion> reservacion = reservaRepo.findById(idReserva);
        if (!reservacion.isPresent()) {
            throw new ReservacionNoEncontradaException();
        }
        Habitacion habitacionActual = null;
        for (Habitacion i : reservacion.get().getHabitacionesActivas()) {
            if (i.getId().equals(idHabitacionActual)) {
                habitacionActual = i;
                break;
            }
        }
        if (habitacionActual == null) {
            throw new HabitacionNoEncontradaException();
        }
        reservacion.get().getHabitacionesAnteriores().add(habitacionActual);
        reservacion.get().getHabitacionesActivas().remove(habitacionActual);
        habitacionActual.setEstado(true);
        habitacionRepo.save(habitacionActual);
        reservaRepo.save(reservacion.get());

        Optional<Habitacion> nuevaHabitacion = habitacionRepo.findByIdWithLock(idNuevaHabitacion);
        if (!nuevaHabitacion.isPresent()) {
            throw new HabitacionNoEncontradaException();
        }
        if (estaDisponible(nuevaHabitacion.get(), reservacion.get().getFechaEntrada())) {
            nuevaHabitacion.get().setEstado(false);
            habitacionRepo.save(nuevaHabitacion.get());
            reservacion.get().getHabitacionesActivas().add(nuevaHabitacion.get());
            reservacion.get().setFechaSalida(salida);
            reservaRepo.save(reservacion.get());
            return mapper.reservacionAReservacionDto(reservacion.get());
        } else {
            throw new HabitacionNoDisponibleException("la habitacion a cambiar no esta disponible");
        }
    }


    public Boolean estaDisponible(Habitacion room, LocalDate fechaEntrada){
        if (room.isEstado()){
            return true;
        }
        Optional<Reservacion> reservaActual = reservaRepo.findOverlappingReservation(room.getId(), fechaEntrada);
        return reservaActual.isEmpty();
    }
    //@Transactional
    public void cambiarEstados(Long id) {
        Optional<Habitacion> room = habitacionRepo.findById(id);
        if (room.isPresent()) {
            Habitacion habitacion = room.get();
            habitacion.setEstado(true);
            habitacionRepo.save(habitacion);
        } else {
            throw new HabitacionNoEncontradaException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ReservacionSimplificadoDto obtenerProximaReservacionPorHabitacion(Long idHabitacion, LocalDate fecha) {
        Optional<Reservacion> reservacion = reservaRepo.findFirstByHabitacionesActivasIdAndFechaEntradaGreaterThanOrderByFechaEntradaAsc(idHabitacion, fecha);
        if (reservacion.isPresent()) {
            return mapper.reservacionAReservacionDto(reservacion.get());
        } else {
            throw new ReservacionNoEncontradaException();
        }
    }

}


