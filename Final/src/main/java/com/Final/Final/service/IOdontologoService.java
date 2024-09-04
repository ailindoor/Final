package com.Final.Final.service;

import com.Final.Final.entity.Odontologo;
import com.Final.Final.modelo.Odontologo;

import java.util.List;

public interface IOdontologoServicio {
    Odontologo guardar (Odontologo odontologo);
    Odontologo buscarPorId(Long id);
    void actulizar(Odontologo odontologo);
    void eliminar(Long id);
    List<Odontologo> listarTodos();

    Odontologo buscarPorMatricula(String matricula);
}
