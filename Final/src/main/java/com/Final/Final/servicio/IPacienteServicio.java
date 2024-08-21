package com.Final.Final.servicio;

import com.Final.Final.modelo.Paciente;

import java.util.List;

public interface IPacienteServicio {

    Paciente guardar(Paciente paciente);
    Paciente buscarPorId(Integer id);
    void actualizar(Paciente paciente);
    void elimanar(Integer id);
    List<Paciente> listarTodos();
}
