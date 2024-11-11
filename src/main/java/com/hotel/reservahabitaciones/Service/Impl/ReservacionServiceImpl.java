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

@Service
public class ReservacionServiceImpl implements IReservacion {

    @Autowired
    ReservaRepository reservaRepo;
    @Autowired
    ReservacionMapper mapper;
    @Autowired
    HabitacionRepository habitacionRepo;
    @Autowired
    ClienteRepository clienteRepo;

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getAll() {
        if (reservaRepo.findAll().isEmpty()){
            throw new ReservacionNoEncontradaException("no hay reservaciones registradas");
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
           throw new ReservacionNoEncontradaException("no se encontro la reservacion con el id "+id);
       }

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) {
     if (reservaRepo.existsById(id)){
         reservaRepo.deleteById(id);
     }else {
         throw  new ReservacionNoEncontradaException("no se encontro la reservacion con el id "+id);
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
            throw new UsuarioNoEncontradoException("no se encontro el cliente con el id "+reservacionDTO.getIdCliente());
        }

        for(Long id:reservacionDTO.getIds()){
            if (habitacionRepo.findById(id).isPresent()){
                if (disponible(id,reservacionDTO.getFechaEntrada())){
                    lista.add(habitacionRepo.findById(id).get());
                }
            }else{
                throw new HabitacionNoEncontradaException("no se encontro la habitacion con el id "+id);
            }
        }
        reservacion.setHabitacions(lista);
        reservaRepo.save(reservacion);

    }

    @Override
    public ReservacionDTO updateSalida(Long id, LocalDate salida) {
       if (reservaRepo.existsById(id)){
           Reservacion reservacion=reservaRepo.findById(id).get();
           reservacion.setFechaSalida(salida);
           reservaRepo.save(reservacion);
           return mapper.reservacionAreservacionDto(reservacion);
       }else{
           throw new ReservacionNoEncontradaException("no se encotro la reservacion con el id "+id);
       }
    }


    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getBySalidaDespuesDe(LocalDate fecha) {
        if (reservaRepo.findByFechaSalidaGreaterThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException("no hay reservas dentro de ese rango de tiempo");
        }else {
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaSalidaGreaterThanEqual(fecha));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getBySalidaAntesDe(LocalDate fecha) {
        if (reservaRepo.findByFechaSalidaLessThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException("no hay reservas dentro de ese rango de tiempo");
        }else {
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaSalidaLessThanEqual(fecha));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getByEntradaDespuesDe(LocalDate fecha) {
        if (reservaRepo.findByFechaEntradaGreaterThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException("no hay reservas dentro de ese rango de tiempo");
        }else{
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaEntradaGreaterThanEqual(fecha));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservacionDTO> getByEntradaAntesDe(LocalDate fecha) {
        if (reservaRepo.findByFechaEntradaLessThanEqual(fecha).isEmpty()){
            throw new ReservacionNoEncontradaException("no hay reservas dentro de ese rango de tiempo");
        }else{
            return mapper.reservacionesAReservacionesDto(reservaRepo.findByFechaEntradaLessThanEqual(fecha));
        }
    }

    @Override
    @Transactional
    public ReservacionDTO cambiarHabitacion(Long idReserva, Long idHabitacionActual, Long idNuevaHabitacion, LocalDate salida) {
        List<Habitacion>listaHabitaciones=new ArrayList<>();

        if (reservaRepo.existsById(idReserva)){
            Reservacion reservacion=reservaRepo.findById(idReserva).get();
            cambiarEstados(idHabitacionActual);
            //por si reservo mas de una habitacion
            for (Habitacion h:reservacion.getHabitacions()){
                if (h.getId()==idHabitacionActual){
                    continue;
                }
                listaHabitaciones.add(h);
            }
            if (disponible(idNuevaHabitacion,LocalDate.now())){
                listaHabitaciones.add(habitacionRepo.findById(idNuevaHabitacion).get());
                reservacion.setHabitacions(listaHabitaciones);
                reservaRepo.save(reservacion);
                return mapper.reservacionAreservacionDto(reservacion);
            }else{
                throw new HabitacionNoDisponibleException("la habitacion no esta disponible ");
            }
        }else {
            throw new ReservacionNoEncontradaException("no se encontro la reservacion con el id "+idReserva);
        }
    }


    @Transactional
    public Boolean disponible(Long id,LocalDate fechaEntrada){
        Habitacion habitacion=habitacionRepo.findById(id).orElseThrow(()->new HabitacionNoEncontradaException("no se econtro la habitacion con el id "+id));
        if (habitacion.isEstado()){

            habitacion.setEstado(false);
            habitacionRepo.save(habitacion);
            return true;

        }else{
            if (fechaEntrada.isAfter(habitacion.getReservacion().getFechaSalida())){
                habitacion.setEstado(false);
                habitacionRepo.save(habitacion);
                return true;
            }else {
                throw new ReservaNoDisponibleException("no se puede hacer la reservacion ala habitacion con el id "+id);
            }
        }
    }
    //@Transactional
    public void cambiarEstados(Long id) {
        if (habitacionRepo.existsById(id)){
            Habitacion habitacion=habitacionRepo.findById(id).get();
            habitacion.setEstado(true);
            habitacionRepo.save(habitacion);
        }else {
            throw  new HabitacionNoEncontradaException("no se encontro la habitacion con el id "+id);
        }
    }

}
