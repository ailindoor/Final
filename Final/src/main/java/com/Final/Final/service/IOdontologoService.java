package com.Final.Final.service;

import com.Final.Final.entity.Odontologo;


import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);
    Odontologo buscarPorId(Long id);
    void actulizar(Odontologo odontologo);
    void eliminar(Long id);
    List<Odontologo> listarTodos();

    Odontologo buscarPorMatricula(String matricula);
}
