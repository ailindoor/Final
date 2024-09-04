package com.Final.Final.repository;

import com.Final.Final.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRespository extends JpaRepository<Turno, Long> {
}
