package com.pizzajdbc.application.services;

import com.pizzajdbc.infrastructure.dao.Dao;
import com.pizzajdbc.infrastructure.dao.DaoFactory;
import com.pizzajdbc.infrastructure.database.DataBase;
import com.pizzajdbc.infrastructure.models.Customer;

import java.util.List;

public class CustomerService {
    private Dao<Customer> dao = DaoFactory.createCustomerDao();

    public Customer customerRegistration(Customer customer) {
        dao.insert(customer);
        DataBase.commit();
        return  customer;
    }

    public Customer findById(String id) { return dao.findById(id); }

    public List<Customer> findAll() {
        return dao.findAll();
    }

    public Customer update(Customer customer) {
        dao.update(customer);
        DataBase.commit();
        return customer;
    }

    public void deleteById(String id) {
        dao.deleteById(id);
        DataBase.commit();
    }
}
