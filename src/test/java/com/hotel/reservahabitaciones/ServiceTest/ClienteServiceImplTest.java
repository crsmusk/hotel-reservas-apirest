package com.hotel.reservahabitaciones.ServiceTest;



import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Service.Impl.ClienteServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_clientes_srvc.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class ClienteServiceImplTest {
//se cambiara la base de datos para hacer los test de los servicios y se pondra en create-drop

 @Autowired
 private ClienteServiceImpl service;
 
 

 private  ClienteDTO clienteDTO=new ClienteDTO(1L,"Juan","Pérez","123456789","5551234567","cliente@example.com","password_encriptado");

 
  
 @Test
  void testGetAll() {
  List<ClienteDTO>obtenido=service.getAll();
  List<ClienteDTO>esperado=List.of(clienteDTO);
  assertEquals(esperado.getFirst().getNombre(),obtenido.getFirst().getNombre());


 }
 @Test
 void testGetById(){
  ClienteDTO obtnenido=service.getById(1L);
  assertNotNull(obtnenido);
  assertEquals(clienteDTO,obtnenido);
 }

 @Test
 void testGetByName(){
  List<ClienteDTO>obtenido=service.getByName("Juan");
  List<ClienteDTO>esperado=List.of(clienteDTO);
  assertNotNull(obtenido);
  assertEquals(esperado,obtenido);
 }

 @Test
 void testGetByLastName(){
  List<ClienteDTO>obtenido=service.getByLastName("Pérez");
  List<ClienteDTO>esperado=List.of(clienteDTO);
  assertNotNull(obtenido);
  assertEquals(esperado,obtenido);
 }

 @Test
 void testUpdate(){
  clienteDTO.setNombre("marco");
  ClienteDTO optenido=service.update(1L,clienteDTO);
  assertNotNull(optenido);
  assertEquals(clienteDTO,optenido);
 }


 @Test
  void  testUsuarioNoEncontradoException(){

  assertThrows(UsuarioNoEncontradoException.class, ()->{
    service.getById(44L);
  } );
 }





}
