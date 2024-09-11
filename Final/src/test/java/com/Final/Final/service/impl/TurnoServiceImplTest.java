package com.Final.Final.service.impl;

import com.Final.Final.entity.Domicilio;
import com.Final.Final.entity.Odontologo;
import com.Final.Final.entity.Paciente;
import com.Final.Final.entity.Turno;
import com.Final.Final.repository.ITurnoRepository;
import com.Final.Final.service.ITurnoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TurnoServiceImplTest {

    @Mock
    private ITurnoRepository iTurnoRepository;

    @InjectMocks
    private TurnoServiceImpl turnoServiceImpl;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar(){
        Odontologo camilo = new Odontologo(1L, "CAMILO", "CARRILLO","12121");
        Domicilio domicilio = new Domicilio(1L,"romano",2,"laRosa","Arma");
        Paciente juan = new Paciente(1L, "Juan", "Dos","123", LocalDate.parse("2024-12-23"),domicilio,new HashSet<>(1));
        Turno turno = new Turno(1L,juan,camilo,LocalDate.parse("2024-12-23"));
        when(iTurnoRepository.save(turno)).thenReturn(turno);

        // Act
        Turno result = turnoServiceImpl.guardar(turno);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

}