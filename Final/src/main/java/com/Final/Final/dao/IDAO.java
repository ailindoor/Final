package com.Final.Final.dao;

import java.util.List;

public interface IDAO<T> {
    //creacion de los metodos

    //GUARDAR
    T guardar (T t);

    //CONSULTAR POR ID
    T consultarPorId (Integer id);

    //ACTUALIZAR
    T actualizar (T t);

    //ELIMINAR
    void eliminarPorId (Integer id);

    //LISTAR TODOS O CONSULTARLOS
    List<T> listarTodos();


}
