package com.pizzajdbc.infrastructure.dao.impl;
import com.pizzajdbc.infrastructure.dao.Dao;
import com.pizzajdbc.infrastructure.database.DataBase;
import com.pizzajdbc.infrastructure.database.DataBaseException;
import com.pizzajdbc.infrastructure.database.DataBaseIntegrityException;
import com.pizzajdbc.infrastructure.models.Pizza;
import com.pizzajdbc.infrastructure.models.Request;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PizzaDao implements Dao<Pizza> {
    private final Connection connection;

    public PizzaDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Pizza pizza) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO tb_pizza(name, price) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1,pizza.getName());
            preparedStatement.setDouble(2, pizza.getPrice());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    pizza.setId(resultSet.getLong(1));
                }
            }
            else {
                throw new DataBaseException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Pizza pizza) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE tb_pizza SET name = ?, price = ? WHERE id = ?"
            );
            preparedStatement.setString(1, pizza.getName());
            preparedStatement.setDouble(2,pizza.getPrice());
            preparedStatement.setLong(3, pizza.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM tb_pizza WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DataBaseIntegrityException(e.getMessage());
        }
        finally {
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public Pizza findById(Long id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT id, name, price FROM tb_pizza WHERE id = ?"
            );
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Pizza pizza = new Pizza();
                pizza.setId(resultSet.getLong("id"));
                pizza.setName(resultSet.getString("name"));
                pizza.setPrice(resultSet.getDouble("price"));
                return pizza;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeResultSet(resultSet);
            DataBase.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Pizza> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT id, name, price FROM tb_pizza ORDER BY name");
            resultSet = preparedStatement.executeQuery();
            List<Pizza> list = new ArrayList<>();
            while (resultSet.next()) {
                Pizza pizza = new Pizza();
                pizza.setId(resultSet.getLong("id"));
                pizza.setName(resultSet.getString("name"));
                pizza.setPrice(resultSet.getDouble("price"));
                list.add(pizza);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DataBase.closeResultSet(resultSet);
            DataBase.closeStatement(preparedStatement);
        }
    }
}
