package com.Final.Final.dao.impl;

import com.Final.Final.dao.BD;
import com.Final.Final.dao.IDAO;
import com.Final.Final.modelo.Domicilio;
import com.Final.Final.modelo.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionPaciente implements IDAO<Paciente> {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(ImplementacionPaciente.class);

    ImplementacionDomicilio implementacionDomicilio = new ImplementacionDomicilio();
    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;

        try {
            implementacionDomicilio.guardar(paciente.getDomicilio());

            LOGGER.info("Guardando un paciente");

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO PACIENTES (" +
                            "NOMBRE, APELLIDO, DNI, FECHA_ALTA, DOMICILIO_ID) VALUES " +
                            "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaAlta()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());

            preparedStatement.execute();


            ResultSet rs = preparedStatement.getGeneratedKeys();


            while (rs.next()) {
                paciente.setId(rs.getInt(1));
                System.out.println("Se guardó el paciente con nombre " +
                        paciente.getNombre());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        LOGGER.info("Guardamos el paciente con nombre " + paciente.getNombre());
        return paciente;
    }

    @Override
    public Paciente consultarPorId(Integer id) {
       Connection connection = null;
       Paciente paciente = null;

       try {
           connection = BD.getConnection();

           PreparedStatement psBuscarPorId = connection.prepareStatement(
                   "SELECT * FROM PACIENTES WHERE ID=?"
           );

           psBuscarPorId.setInt(1, id);
           ResultSet rs = psBuscarPorId.executeQuery();

           while(rs.next()){
               Domicilio domicilio = implementacionDomicilio.consultarPorId(rs.getInt(6));

               paciente = new Paciente(rs.getInt(1), rs.getString(2),
                       rs.getString(3), rs.getString(4),
                       rs.getDate(5).toLocalDate(), domicilio);

           }

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               connection.close();
           }catch (Exception ex){
               ex.printStackTrace();
           }
       }

       return paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;

        try {
            implementacionDomicilio.guardar(paciente.getDomicilio());

            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?," +
                            "DNI=?, FECHA_ALTA=?, DOMICILIO=? WHERE ID=?"
            );

            preparedStatement.setString(1,paciente.getNombre());
            preparedStatement.setString(2,paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaAlta()));
            preparedStatement.setInt(5, paciente.getId());
            preparedStatement.setInt(6, paciente.getDomicilio().getId());

            preparedStatement.execute();

            System.out.println("Este es el nuevo nombre del paciente" + paciente.getNombre());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public void eliminarPorId(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BD.getConnection();

            preparedStatement = connection.prepareStatement("DELETE PACIENTES WHERE ID=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

            System.out.println("El paciente con id= " + id + "ha sido eliminado");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection connection = null;

        List<Paciente> pacienteList = new ArrayList<>();
        Paciente paciente = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM PACIENTES"
            );

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                Domicilio domicilio = implementacionDomicilio.consultarPorId(rs.getInt(6));

                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(), domicilio);

                pacienteList.add(paciente);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return pacienteList;
    }
}
