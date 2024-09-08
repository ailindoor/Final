package com.Final.Final.repository;

import com.Final.Final.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {
    Odontologo findByMatricula(String matricula);
}
