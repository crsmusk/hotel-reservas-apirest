package com.hotel.reservahabitaciones.ServiceTest;



import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ClienteSimplificadoDto;
import com.hotel.reservahabitaciones.Service.Impl.ClienteServiceImpl;

@Sql(scripts = "/scripts/inicializar_datos_para_clientes_srvc.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class ClienteServiceImplTest {
//se cambiara la base de datos para hacer los test de los servicios y se pondra en create-drop

 @Autowired
 private ClienteServiceImpl service;
 

 private  ClienteSimplificadoDto clienteEsperado=new ClienteSimplificadoDto(1L,"Juan","Pérez","cliente@example.com","5551234567");
 private  ClienteDto ClienteDto=new ClienteDto(1L,"Juan","Pérez","123456789","5551234567","cliente@example.com","password_encriptado");

 
  
 @Test
  void testGetAll() {
  List<ClienteSimplificadoDto>obtenido=service.obtenerTodos();
  List<ClienteSimplificadoDto>esperado=List.of(clienteEsperado);
  assertEquals(esperado.getFirst().getNombre(),obtenido.getFirst().getNombre());


 }
 @Test
 void testGetById(){
  ClienteSimplificadoDto obtnenido=service.obtenerPorId(1L);
  assertNotNull(obtnenido);
  assertEquals(clienteEsperado,obtnenido);
 }

 @Test
 void testGetByName(){
  List<ClienteSimplificadoDto>obtenido=service.obtenerPorNombre("Juan");
  List<ClienteSimplificadoDto>esperado=List.of(clienteEsperado);
  assertNotNull(obtenido);
  assertEquals(esperado,obtenido);
 }

 @Test
 void testGetByLastName(){
  List<ClienteSimplificadoDto>obtenido=service.obtenerPorApellido("Pérez");
  List<ClienteSimplificadoDto>esperado=List.of(clienteEsperado);
  assertNotNull(obtenido);
  assertEquals(esperado,obtenido);
 }

 @Test
 void testUpdate(){
  ClienteDto clienteActualizado = new ClienteDto(
      1L,
      "marco",
      "Pérez",
      "123456789",
      "5551234567",
      "cliente@example.com",
      "password_encriptado"
  );
  ClienteSimplificadoDto optenido = service.actualizar(1L, clienteActualizado);
  assertNotNull(optenido);
  assertEquals("marco", optenido.getNombre());
 }


 @Test
  void  testUsuarioNoEncontradoException(){

  assertThrows(UsuarioNoEncontradoException.class, ()->{
    service.obtenerPorId(44L);
  } );
 }




}

