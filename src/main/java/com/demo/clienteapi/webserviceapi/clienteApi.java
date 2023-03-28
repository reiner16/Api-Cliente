package com.demo.clienteapi.webserviceapi;

import com.demo.clienteapi.dto.ClienteDto;
import com.demo.clienteapi.service.Clienteservice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/cliente")
@Slf4j
public class clienteApi {
    @Autowired

    private Clienteservice clienteservice;

    @GetMapping("/{id}")
    public ClienteDto BuscarCliente(@PathVariable int id) {
        log.info("Busqueda de cliente: {}", id );
        return  clienteservice.obtenerClienteReport(id);
    }

    @PostMapping
    public void guardarCliente(@Valid @RequestBody ClienteDto clienteDto){
        log.info("Se inserta el cliente; {}", clienteDto);
        clienteservice.insertarCliente(clienteDto);

    }

    @PutMapping
    public void actualizarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("Se actualiza el cliente; {}", clienteDto);
        clienteservice.actualizarCliente(clienteDto);
    }

    @PutMapping
    public void desactivarCliente(@RequestBody int clienteId){
        log.info("Desactivar Cliente; {}", clienteId);
        //clienteservice.actualizarCliente(clienteId)
        //clienteService.actualizarCliente(clienteId);
    }


}
