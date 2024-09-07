package com.Final.Final.service.impl;

import com.Final.Final.entity.Turno;
import com.Final.Final.repository.ITurnoRepository;
import com.Final.Final.service.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TurnoServiceImpl implements ITurnoServicio {
    @Autowired
    private ITurnoRepository iTurnoRepository;
    @Override
    public Turno guardar(Turno turno) {
        return iTurnoRepository.save(turno);
    }
    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoBuscado =  iTurnoRepository.findById(id);
        return turnoBuscado.orElse(null);
    }
    @Override
    public void eliminar(Long id) {
        iTurnoRepository.deleteById(id);
    }
    @Override
    public List<Turno> listarTodos() {
        return iTurnoRepository.findAll();
    }
}
