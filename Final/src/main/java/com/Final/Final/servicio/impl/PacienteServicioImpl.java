package com.Final.Final.servicio.impl;

import com.Final.Final.dao.IDAO;
import com.Final.Final.dao.impl.ImplementacionPaciente;
import com.Final.Final.modelo.Paciente;
import com.Final.Final.servicio.IPacienteServicio;

import java.util.List;

public class PacienteServicioImpl implements IPacienteServicio {

    private IDAO<Paciente> pacienteIDAO;

    public PacienteServicioImpl(){
        this.pacienteIDAO = new ImplementacionPaciente();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteIDAO.guardar(paciente);
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return pacienteIDAO.consultarPorId(id);
    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void elimanar(Integer id) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteIDAO.listarTodos();
    }
}
