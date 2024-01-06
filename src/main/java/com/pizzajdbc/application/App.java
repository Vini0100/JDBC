package com.pizzajdbc.application;

import com.pizzajdbc.infrastructure.models.Pizza;

public class App {
    public static void main(String[] args) {
        Pizza model1 = new Pizza();
        model1.setName("Banana");
        model1.setPrice(200.00);
        System.out.println(model1);

        Pizza model2 = new Pizza("Oi", 200.00);
        System.out.println(model2);
    }
}
