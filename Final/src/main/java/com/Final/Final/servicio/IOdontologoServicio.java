package com.Final.Final.servicio;

import com.Final.Final.modelo.Odontologo;

import java.util.List;

public interface IOdontologoServicio {
    Odontologo guardar (Odontologo odontologo);
    Odontologo buscarPorId(Integer id);
    void actulizar(Odontologo odontologo);
    void eliminar(Integer id);
    List<Odontologo> listarTodos();
}
