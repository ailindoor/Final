package com.Final.Final.dao.implementacion;

import com.Final.Final.dao.BD;
import com.Final.Final.dao.IDAO;
import com.Final.Final.modelo.Domicilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionDomicilio implements IDAO<Domicilio> {
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO DOMICILIOS (" +
                            "CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES " +
                            "(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()){
                domicilio.setId(1);
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
        return domicilio;
    }

    @Override
    public Domicilio consultarPorId(Integer id) {
        Connection connection = null;
        Domicilio domicilio = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM DOMICILIOS WHERE ID=?"
            );
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                domicilio = new Domicilio();
                domicilio.setId(rs.getInt(1));
                domicilio.setCalle(rs.getString(2));
                domicilio.setNumero(rs.getInt(3));
                domicilio.setLocalidad(rs.getString(4));
                domicilio.setProvincia(rs.getString(5));
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
        return domicilio;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        return null;
    }

    @Override
    public void eliminarPorId(Integer id) {

    }

    @Override
    public List<Domicilio> listarTodos() {
        Connection connection = null;

        List<Domicilio> domicilioList = new ArrayList<>();
        Domicilio domicilio = null;

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM DOMICILIOS"
            );

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                domicilio = new Domicilio(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getString(4),rs.getString(5));
                domicilioList.add(domicilio);
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

        return domicilioList;
    }
}
