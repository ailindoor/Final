package com.Final.Final.service.impl;

import com.Final.Final.entity.Odontologo;
import com.Final.Final.repository.IOdontologoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OdontologoServicioImplTest {

    @Mock
    private IOdontologoRepository iOdontologoRepository;

    @InjectMocks
    private OdontologoServicioImpl odontologoServicoImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        // Arrange
        Odontologo juan = new Odontologo(1L, "Juan", "wer", "1212");
        when(iOdontologoRepository.save(juan)).thenReturn(juan);

        // Act
        Odontologo result = odontologoServicoImpl.guardar(juan);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testListarTodos() {
        // Arrange
        Odontologo juan = new Odontologo(1L, "Juan", "wer", "121");
        Odontologo andre = new Odontologo(2L, "Andre", "iso", "121");
        when(iOdontologoRepository.findAll()).thenReturn(Arrays.asList(juan, andre));

        // Act
        List<Odontologo> result = odontologoServicoImpl.listarTodos();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(juan));
        assertTrue(result.contains(andre));
    }

}