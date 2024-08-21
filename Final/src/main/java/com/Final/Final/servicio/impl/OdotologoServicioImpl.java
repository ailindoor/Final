package com.Final.Final.servicio.impl;

import com.Final.Final.dao.IDAO;
import com.Final.Final.dao.impl.ImplementacionOdontologo;
import com.Final.Final.modelo.Odontologo;
import com.Final.Final.servicio.IOdontologoServicio;

import java.util.List;

public class OdotologoServicioImpl implements IOdontologoServicio {

    private IDAO<Odontologo> odontologoIDAO;

    public OdotologoServicioImpl(){
        this.odontologoIDAO = new ImplementacionOdontologo();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoIDAO.guardar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return odontologoIDAO.consultarPorId(id);
    }

    @Override
    public void actulizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoIDAO.listarTodos();
    }
}
