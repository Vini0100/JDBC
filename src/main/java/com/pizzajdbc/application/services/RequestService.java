package com.pizzajdbc.application.services;


import com.pizzajdbc.infrastructure.dao.Dao;
import com.pizzajdbc.infrastructure.dao.DaoFactory;
import com.pizzajdbc.infrastructure.database.DataBase;
import com.pizzajdbc.infrastructure.models.Request;

import java.util.List;
/*
    Esta classe representa os serviços disponíveis para o pedido.
 */
public class RequestService {
    private Dao<Request> dao = DaoFactory.createRequestDao();

    public Request requestRegistration(Request request) {
        dao.insert(request);
        DataBase.commit();
        return  request;
    }

    public Request findById(Long id) { return dao.findById(id); }

    public List<Request> findAll() {
        return dao.findAll();
    }

    public Request update(Request request) {
        dao.update(request);
        DataBase.commit();
        return request;
    }

    public void deleteById(Long id) {
        dao.deleteById(id);
        DataBase.commit();
    }
}
