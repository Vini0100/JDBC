package com.pizzajdbc.infrastructure.dao;

import com.pizzajdbc.infrastructure.dao.impl.CustomerDao;
import com.pizzajdbc.infrastructure.dao.impl.PizzaDao;
import com.pizzajdbc.infrastructure.dao.impl.RequestDao;
import com.pizzajdbc.infrastructure.database.DataBase;

public class DaoFactory {
    public static Dao createPizzaDao() {
        return new PizzaDao(DataBase.getConnection());
    }

    public static Dao createCustomerDao() { return new CustomerDao(DataBase.getConnection()); }

    public static Dao createRequestDao() {
        return new RequestDao(DataBase.getConnection());
    }
}
