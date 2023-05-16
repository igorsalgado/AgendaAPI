package com.agenda.api.repository;

import com.agenda.api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("SELECT a FROM Agendamento a WHERE LOWER(a.cliente.nome) LIKE LOWER(CONCAT('%', :nomeCliente, '%'))") //Buscar o nome independente da capitalização
    List<Agendamento> findByClienteNomeIgnoreCase(String nomeCliente);


    @Query("SELECT a FROM Agendamento a WHERE LOWER(a.barbeiro.nome) LIKE LOWER(CONCAT('%', :nomeBarbeiro, '%'))") //Buscar o nome independente da capitalização
    List<Agendamento> findByBarbeiroNomeIgnoreCase(String nomeBarbeiro);
}
