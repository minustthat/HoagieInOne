package org.pluralsight.Models;

import org.pluralsight.Enums.Meats;

public class Meat {

  Meats meat;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    double price;

    public Meat(Meats meat, double price) {

        this.meat = meat;
        this.price = price;

    }




}

