package com.agenda.api.repository;

import com.agenda.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))") //Buscar o nome independente da capitalização
    List<Cliente> findByNome(String nome);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.telefone) LIKE LOWER(CONCAT('%', :telefone, '%'))")
    List<Cliente> findByTelefone(String telefone);

}
