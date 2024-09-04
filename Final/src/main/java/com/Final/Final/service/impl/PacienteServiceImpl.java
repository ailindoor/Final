package com.Final.Final.service.impl;


import com.Final.Final.entity.Paciente;
import com.Final.Final.repository.IPacienteRepository;
import com.Final.Final.service.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteServicioImpl implements IPacienteServicio {

    @Autowired
    private IPacienteRepository iPacienteRepository;


    @Override
    public Paciente guardar(Paciente paciente) {
        return iPacienteRepository.;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return null;
    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void elimanar(Long id) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }
}
