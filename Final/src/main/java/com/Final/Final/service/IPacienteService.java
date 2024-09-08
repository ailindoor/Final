package com.Final.Final.service;


import com.Final.Final.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    Paciente guardar(Paciente paciente);
    Paciente buscarPorId(Long id);
    void actualizar(Paciente paciente);
    void elimanar(Long id);
    List<Paciente> listarTodos();
}
