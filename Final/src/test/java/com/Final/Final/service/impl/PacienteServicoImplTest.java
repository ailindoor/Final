package com.Final.Final.service.impl;

import com.Final.Final.entity.Domicilio;
import com.Final.Final.entity.Odontologo;
import com.Final.Final.entity.Paciente;
import com.Final.Final.entity.Turno;
import com.Final.Final.repository.IOdontologoRepository;
import com.Final.Final.repository.IPacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PacienteServicoImplTest {

    @Mock
    private IPacienteRepository iPacienteRepository;

    @InjectMocks
    private PacienteServicoImpl pacienteServicoImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        // Arrange
        Domicilio domicilio = new Domicilio(1L,"romano",2,"laRosa","Arma");
        Paciente juan = new Paciente(1L, "Juan", "Dos","123", LocalDate.parse("2024-02-23"),domicilio,new HashSet<>(1));
        when(iPacienteRepository.save(juan)).thenReturn(juan);

        // Act
        Paciente result = pacienteServicoImpl.guardar(juan);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testListarTodos() {
        // Arrange
        Domicilio domicilio = new Domicilio(1L,"romano",2,"laRosa","Arma");
        Paciente juan = new Paciente(1L, "Juan", "Dos","123", LocalDate.parse("2024-02-23"),domicilio,new HashSet<>(1));
        Domicilio domicilio2 = new Domicilio(1L,"romano",2,"laRosa","Arma");
        Paciente andre = new Paciente(1L, "andre", "Dos","123", LocalDate.parse("2024-02-23"),domicilio2,new HashSet<>(1));
        when(iPacienteRepository.findAll()).thenReturn(Arrays.asList(juan, andre));

        // Act
        List<Paciente> result = pacienteServicoImpl.listarTodos();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(juan));
        assertTrue(result.contains(andre));
    }
}