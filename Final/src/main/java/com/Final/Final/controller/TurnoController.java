package com.Final.Final.controller;

import com.Final.Final.entity.Odontologo;
import com.Final.Final.entity.Paciente;
import com.Final.Final.entity.Turno;
import com.Final.Final.exeption.InvalidDateException;
import com.Final.Final.service.IOdontologoServicio;
import com.Final.Final.service.IPacienteServicio;
import com.Final.Final.service.ITurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private ITurnoServicio iTurnoServicio;
    @Autowired
    private IOdontologoServicio iOdontologoService;
    @Autowired
    private IPacienteServicio iPacienteService;

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(iTurnoServicio.guardar(turno));
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(iTurnoServicio.listarTodos());
    }
    @DeleteMapping("eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        iTurnoServicio.eliminar(id);
        return "ok";
    }
    @GetMapping("/nuevo")
    public Map<String, Object> obtenerDatosParaTurno() {
        List<Odontologo> odontologos = iOdontologoService.listarTodos();
        List<Paciente> pacientes = iPacienteService.listarTodos();

        Map<String, Object> response = new HashMap<>();
        response.put("pacientes", pacientes);
        response.put("odontologos", odontologos);

        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {

        //TODO que pasa si el id del paciente o del odontologo
        //que recibe el turno no existen
        return ResponseEntity.ok(iTurnoServicio.buscarPorId(id));
    }
}
