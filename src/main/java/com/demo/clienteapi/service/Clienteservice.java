package com.demo.clienteapi.service;

import com.demo.clienteapi.dto.ClienteDto;
import com.demo.clienteapi.model.Cliente;
import com.demo.clienteapi.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class Clienteservice {

    ClienteRepository clienteRepository;


    public void insertarCliente (ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccion(clienteDto.getDireccion());
        clienteRepository.save(cliente);

    }

    public ClienteDto obtenerClienteReport (int idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).
                orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});

        ClienteDto clienteDto= new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setIdentificacion(cliente.getIdentificacion());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setTelefono(cliente.getTelefono());
        clienteDto.setPaisNacimiento(cliente.getPaisNacimiento());
        clienteDto.setPaisResidencia(cliente.getPaisResidencia());
        clienteDto.setDireccion(cliente.getDireccion());
        return clienteDto;
    }

    public List<ClienteDto> listarTodosLosClientes(){
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        clienteRepository
                .findAll()
                .stream()
                .map(cliente -> {

                    clienteDtoList.add(fromClienteToDto(cliente));
                    return cliente;
                }).collect(Collectors.toList());
        return clienteDtoList;
    }

    private ClienteDto fromClienteToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }

    public ClienteDto actualizarCliente(ClienteDto clienteDto){
        Cliente cliente = clienteRepository.findById(clienteDto.getId()).
                orElseThrow(() -> {throw new RuntimeException("Cliente No Existe");});
        cliente.setId(clienteDto.getId());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccion(clienteDto.getDireccion());
        clienteRepository.save(cliente);

        return clienteDto;
    };

    public void eliminarCliente(int clienteId){
        clienteRepository.deleteById(clienteId);
    };

}
