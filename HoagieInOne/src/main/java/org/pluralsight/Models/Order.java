package org.pluralsight.Models;

public class Order {
    Sandwich sandwich;
    public Order(Sandwich sandwich){
        this.sandwich = sandwich;

    }
    public static String orderToString(Sandwich customerSandwhich) {
        return STR.
                """
             Name:\{customerSandwhich.getName()}
             Size:\{customerSandwhich.getSize()}
             Bread:\{customerSandwhich.getBreadType()}
             Toasted:\{customerSandwhich.getIsToasted()}
             Vegetables:\{customerSandwhich.getVegetables()}
             Meat:\{customerSandwhich.getMeatType()}
             Cheeses:\{customerSandwhich.getCheeseType()}
             Price:\{customerSandwhich.getPrice()}
             Drink:\{customerSandwhich.getHasDrink()}
             Chips:\{customerSandwhich.getHasChips()}
             Sauces:\{customerSandwhich.getSauce()}
             Extra Meat:\{customerSandwhich.getExtraMeat()}
             Extra Cheese:\{customerSandwhich.getExtraCheese()}

             """;

    }
}
