package com.agenda.api.repository;

import com.agenda.api.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    @Query("SELECT b FROM Barbeiro b WHERE LOWER(b.nome) LIKE LOWER(CONCAT('%', :nome, '%'))") //Buscar o nome independente da capitalização
    List<Barbeiro> findByNome(String nome);

}
