package org.pluralsight.Models;

import org.pluralsight.Enums.*;

import java.util.ArrayList;
import java.util.List;



public class Sandwich implements AddExtras {
    private String name;
    private int size;
    private List<Vegetables> vegetables = new ArrayList<>();// sandwich class vegetables
    private BreadType breadType;
    private double price;
    private boolean hasDrink;
    private boolean hasChips;
    private List<Cheese> cheeses = new ArrayList<>();
    private Meat meatType;
    private Cheese extraCheese;
    private Cheese cheeseType;
    private boolean isToasted;
    private String sauce;
    private Meat extraMeat;


    public Sandwich(int size, BreadType breadType){
        this.size = size;
        this.breadType = breadType;
    }

    public Sandwich(){

    }

    //<editor-fold desc="Getters and Setters">
    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
        if(size == 4){
            price += 5.50;
        }
        if(size == 8){
            price += 7.00;
        }

        if(size == 12){
            price += 8.50;
        }
    }

    public List<Vegetables> getVegetables() {
return vegetables;
    }

    public void setVegetables(List<Vegetables> veggies) {
        this.vegetables = vegetables;


    }

    public void addVegetable(Vegetables veggie)
    {
        this.vegetables.add(veggie);
    }





    public  BreadType getBreadType() {
        return breadType;
    }

    public  void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public  void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
        for(Cheese cheese: cheeses){
            price += cheese.price;
        }
    }
    public String getHasDrink() {
        if (hasDrink){
            return "Yes";
        }
        return "No";
    }

    public String getHasChips() {
        if(hasChips){
            return "Yes";
        }
        return "No";
    }

    public void setIsToasted(){
        this.isToasted = isToasted;
    }

    public String getIsToasted(){
        if(isToasted){
            return "Yes";
        }
        return "No";
    }
    public void setHasChips(boolean hasChips) {
        this.hasChips = hasChips;

    }

    public void setHasDrink(boolean hasDrink) {
        this.hasDrink = hasDrink;


    }

    //</editor-fold>

    public void setExtraMeat(Meat extraMeat){
        this.extraMeat = extraMeat;
        price += extraMeat.price;

    }
    public String getExtraMeat() {
        if(meatType == null){
            return "none";
        }
        return Meats.valueOf(String.valueOf(extraMeat.meat)).toString();
    }

    public void setExtraCheese(Cheese extraCheese){
        this.extraCheese = extraCheese;
        price += extraCheese.price;

    }
    public String getExtraCheese() {
        if(cheeseType == null){
            return "none";
        }
        return Cheeses.valueOf(String.valueOf(extraCheese.cheese)).toString();
    }


    public String getCheeseType() {
        if(cheeseType == null){
            return "none";
        }
        return Cheeses.valueOf(String.valueOf(cheeseType.cheese)).toString();
    }

    public void setCheeseType(Cheese cheeseType) {
        this.cheeseType = cheeseType;
        price += cheeseType.price;



    }

    public String getMeatType() {
        if(meatType == null){
            return "none";
        }
        return Meats.valueOf(String.valueOf(meatType.meat)).toString();
    }

    public void setMeatType(Meat meatType) {
        this.meatType = meatType;
        price += meatType.price;


    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }
    @Override
    public void addDrink() {
        if(size ==4){price += 2.00;}
        if(size == 8){price += 2.50;}
        if(size == 12){ price += 3.00;}
    }

    @Override
    public void addChips() {
        price += 1.50;
    }

}



