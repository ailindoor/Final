package com.Final.Final.service;

import com.Final.Final.entity.Turno;

import java.util.List;

public interface ITurnoServicio {
    Turno guardar(Turno turno);
    Turno buscarPorId(Long id);
    void eliminar(Long id);
    List<Turno> listarTodos();
}
