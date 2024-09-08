package com.Final.Final.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turno {

    @Id
    private Long id;

    @ManyToOne
    private Paciente paciente;

    private LocalDate fecha;
}
