package org.pluralsight.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vegetables {
static List<Vegetables> freeToppings = Arrays.asList(
        new Vegetables("Lettuce"),
        new Vegetables("Peppers"),
        new Vegetables("Onions"),
        new Vegetables("Tomatoes"),
        new Vegetables("Jalapenos"),
        new Vegetables("Cucumbers"),
        new Vegetables("Pickles"),
        new Vegetables("Guacamole"),
        new Vegetables("Mushrooms")
);

    String name;
    double price;

    public Vegetables(String name){
        this.name = name;
        this.price = price;
    }



}
