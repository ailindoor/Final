package com.Final.Final.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    private static final Logger LOGGER = Logger.getLogger(BD.class);

    private static final String DROP_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE " +
            "ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR(100) NOT NULL," +
            "MATRICULA VARCHAR(100) NOT NULL)";

    private static final String DROP_CREATE_PACIENTES = "DROP TABLE IF EXISTS PACIENTES; CREATE TABLE " +
            "PACIENTES (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR(100) NOT NULL," +
            "DOMICILIO VARCHAR(100) NOT NULL," +
            "DNI VARCHAR(100) NOT NULL," +
            "FECHA_ALTA DATE NOT NULL)";




    public static void createTable() {
        Connection connetion = null;
        try {
            connetion = getConnection();

            Statement statement = connetion.createStatement();
            statement.execute(DROP_CREATE_ODONTOLOGOS);
            LOGGER.info("se crea la tabla odontologos");
            statement.execute(DROP_CREATE_PACIENTES);
            LOGGER.info("creacin de la tabla pacientes");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws Exception {
        Class.forName("or.h2.Driver");
        return DriverManager.getConnection("dbc:h2:./","sa","sa");
    }
}
