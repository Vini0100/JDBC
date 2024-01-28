package com.pizzajdbc.application;

import com.pizzajdbc.Config;
import com.pizzajdbc.application.services.CustomerService;
import com.pizzajdbc.application.services.PizzaService;
import com.pizzajdbc.application.services.RequestService;
import com.pizzajdbc.infrastructure.database.DataBase;
import com.pizzajdbc.infrastructure.models.Customer;
import com.pizzajdbc.infrastructure.models.Pizza;
import com.pizzajdbc.infrastructure.models.Request;

import java.util.List;

public class App {
    public static void main (String [] args) {
        Config.createTables(DataBase.getConnection());

        PizzaService pizzaService = new PizzaService();
        Pizza pizza = new Pizza("Muzzarela", 30.50);
        pizzaService.pizzaRegistration(pizza);

        CustomerService customerService = new CustomerService();
        Customer customer = new Customer("11999999999", "Marcos", "Av. Paulista, 1578");
        customerService.customerRegistration(customer);

        RequestService requestService = new RequestService();
        Request request = new Request(2, pizza.getId(), customer.getTelephone(), pizza.getPrice());
        requestService.requestRegistration(request);

        List<Pizza> pizzass = pizzaService.findAll();
        pizzass.forEach(System.out::println);

        pizza.setPrice(33.89);
        pizzaService.update(pizza);

        pizza = new Pizza("Calabreza", 30.50);
        pizzaService.pizzaRegistration(pizza);
        pizzaService.deleteById(pizza.getId());

        List<Pizza> pizzas = pizzaService.findAll();
        pizzas.forEach(System.out::println);

        List<Customer> customers = customerService.findAll();
        customers.forEach(System.out::println);

        List<Request> requests = requestService.findAll();
        requests.forEach(System.out::println);

        Config.dropTables(DataBase.getConnection());
    }
}
