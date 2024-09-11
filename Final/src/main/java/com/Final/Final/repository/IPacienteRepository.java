package com.Final.Final.repository;

import com.Final.Final.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
