package com.agenda.api.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;


    @Column(name = "telefone", nullable = false, length = 100)
    @NotBlank(message = "O campo telefone é obrigatório")
    private String telefone;
}
