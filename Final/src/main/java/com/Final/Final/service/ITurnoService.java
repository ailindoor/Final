package com.Final.Final.service;

import com.Final.Final.entity.Turno;

import java.util.List;

public interface ITurnoService {

    Turno guardar(Turno turno);
    Turno buscarPorId(Long id);
    List<Turno> listarTodos();
    
}
