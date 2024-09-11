package com.Final.Final.service;

import com.Final.Final.entity.Odontologo;

import java.util.List;

public interface IOdontologoServicio {
    Odontologo guardar (Odontologo odontologo);
    Odontologo buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar (Odontologo odontologo);
    List<Odontologo> listarTodos();
}
