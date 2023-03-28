package com.demo.clienteapi.repository;

import com.demo.clienteapi.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> , JpaSpecificationExecutor<Cliente>   {

    public List<Cliente> findClienteByIdentificacion(String identificacion);

    @Query(value = "select c.* from Cliente",
            nativeQuery = true)
    List<Tuple> buscarTodosLosClientes();

}
