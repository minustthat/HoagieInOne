package org.pluralsight.Models;

import org.pluralsight.Enums.Veggies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vegetables { ;

    String veggie;
    double price;

    public String getVeggie() {
        return veggie;
    }

    public void setVeggie(String veggie) {
        this.veggie = veggie;
    }

    public Vegetables(String veggie){
        this.veggie = veggie;
        this.price = price;
        price = 0;
    }

    @Override
    public String toString(){
        return veggie;
    }


}
