package com.Final.Final.controller;

import com.Final.Final.entity.Paciente;
import com.Final.Final.service.IPacienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private IPacienteServicio iPacienteServicio;

    @GetMapping("buscar/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(iPacienteServicio.buscarPorId(id));
        //return "redirect:/pacientes";
    }
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(iPacienteServicio.guardar(paciente));
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(iPacienteServicio.listarTodos());
    }
    @PutMapping("actualizar/{id}")
    public String actualizar(@PathVariable Long id, @RequestBody Paciente paciente){
        paciente.setId(id);
        iPacienteServicio.actualizar(paciente);
        return "ok";
    }
    @DeleteMapping("eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        iPacienteServicio.eliminar(id);
        return "ok";
    }
}
