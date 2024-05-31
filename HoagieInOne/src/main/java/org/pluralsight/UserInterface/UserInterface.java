package org.pluralsight.UserInterface;

import org.pluralsight.Enums.BreadType;
import org.pluralsight.Enums.Cheeses;
import org.pluralsight.Enums.Meats;
import org.pluralsight.Enums.Veggies;
import org.pluralsight.Models.*;
import org.pluralsight.Services.Receipt;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    static List<Vegetables> veggiesList = new ArrayList<>();
    static List<Meat> meatList = new ArrayList<>();
    static List<Cheese> cheeseList = new ArrayList<>();

    private static int choiceScreen(Scanner scanner) {
        System.out.println(
                """
                        Please select an option:
                        1. Add Sandwich
                        2. Add Extras (Extra toppings, Drink, Sauce, and/or Chips.) 
                        3. Checkout
                        4. Cancel Order
                                        
                          """
        );

        int orderChoice = Integer.parseInt(scanner.nextLine());
        return orderChoice;
    }


    private static void addExtras(Scanner scanner, Sandwich customerSandwhich) {
        System.out.println("Would you like to add Chips, a Drink, or extra toppings ? ");
        String extraChoice = scanner.nextLine();
        if (extraChoice.equalsIgnoreCase("Chips")) {
            customerSandwhich.addChips();
            customerSandwhich.setHasChips(true);
            System.out.println("Chips added to your order. ");
        }
        if (extraChoice.equalsIgnoreCase("Drink")) {
            customerSandwhich.addDrink();
            customerSandwhich.setHasDrink(true);
            System.out.println("Drink added to your order. ");
        }

        if (extraChoice.equalsIgnoreCase("Extra Toppings")) {
            System.out.println("Meats \n -------------------------- \n");
            EnumSet.allOf(Meats.class).forEach(System.out::println);

            String meatChoice = String.valueOf(Meats.valueOf(scanner.nextLine().toUpperCase().strip()));
            Meat meat = new Meat(Meats.valueOf(meatChoice),
                    customerSandwhich.getSize() == 4? .50 : customerSandwhich.getSize() == 8 ? 1.00 :
                            customerSandwhich.getSize() == 12 ? 1.50 : 0)  ;
            customerSandwhich.setExtraMeat(meat);
            extraCheese(customerSandwhich);

        } else {
            System.out.println("Order Saved. ");
        }

        if(extraChoice.equalsIgnoreCase("all")){
            customerSandwhich.addChips();
            customerSandwhich.setHasChips(true);
            System.out.println("Chips added to your order. ");
            customerSandwhich.addDrink();
            customerSandwhich.setHasDrink(true);
            System.out.println("Drink added to your order. ");System.out.println("Meats \n -------------------------- \n");
            EnumSet.allOf(Meats.class).forEach(System.out::println);

            String meatChoice = String.valueOf(Meats.valueOf(scanner.nextLine().toUpperCase().strip()));
            Meat meat = new Meat(Meats.valueOf(meatChoice),
                    customerSandwhich.getSize() == 4? .50 : customerSandwhich.getSize() == 8 ? 1.00 :
                            customerSandwhich.getSize() == 12 ? 1.50 : 0)  ;
            customerSandwhich.setExtraMeat(meat);

            System.out.println("Cheeses");
            System.out.println("------------------------");
            EnumSet.allOf(Cheeses.class).forEach(System.out::println);

            Cheeses cheeseChoice = Cheeses.valueOf(scanner.nextLine().toUpperCase().strip());
            Cheese cheese = new Cheese(cheeseChoice, customerSandwhich.getSize() == 4 ? .75 :
                    customerSandwhich.getSize() == 8 ? 1.50 : customerSandwhich.getSize() == 12 ? 2.25 : 0);
            customerSandwhich.setExtraCheese(cheese);
            System.out.println(customerSandwhich.getPrice());
            // only one cheese is needed !
        }

    }

    private static void selectVegetables(Scanner scanner, Sandwich customerSandwhich) {

        EnumSet.allOf(Veggies.class).forEach(System.out::println);
        // just lists the veggies
        scanner.nextLine();
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().toUpperCase();
            for (Veggies veg : Veggies.values()) {

                if (line.equalsIgnoreCase(String.valueOf(veg).toUpperCase().strip())) {
//                    String vegetables = String.valueOf(Veggies.valueOf(line)).toUpperCase().strip();
//                    Vegetables vegetable = new Vegetables(Veggies.valueOf(vegetables));
                    Vegetables vegetable = new Vegetables(line);
                    veggiesList.add(vegetable);
                    customerSandwhich.addVegetable(vegetable);
//                    customerSandwhich.setVegetables(veggiesList);
//                     ADDING VEGETABLES
                }

            }
            if (line.isEmpty()) {
                break;
            }
        }

        System.out.println(STR."Items added \{customerSandwhich.getVegetables()}");
    }

    private static void nameSizeBreadType(Scanner scanner, Sandwich customerSandwhich) {
        System.out.println("What is your name? ");
        String name = scanner.nextLine();

        customerSandwhich.setName(name);
        // set name
        System.out.println(STR."Okay \{name}, Please select a size \n 4) 4 inch \n 8) 8 inch \n 12) 12 inch");
    int size = Integer.parseInt(scanner.nextLine());
    customerSandwhich.setSize(size);

        System.out.println(STR."Current Price : \{customerSandwhich.getPrice()}");
        // SIZE
        System.out.println("""
               Please choice your choice of bread
               White, Rye, Wrap, or Wheat?
                """);
        String breadChoice = String.valueOf(BreadType.valueOf(scanner.nextLine().toUpperCase().strip()));
        customerSandwhich.setBreadType(BreadType.valueOf(breadChoice));
        // BREADTYPE
    }


    private static void selectMeats(Scanner scanner, Sandwich customerSandwhich) {
        System.out.println("Meats \n -------------------------- \n");
        EnumSet.allOf(Meats.class).forEach(System.out::println);

        String meatChoice = String.valueOf(Meats.valueOf(scanner.nextLine().toUpperCase().strip()));
        Meat meat = new Meat(Meats.valueOf(meatChoice),
                customerSandwhich.getSize() == 4? 1.00 : customerSandwhich.getSize() == 8 ? 2.00 :
                        customerSandwhich.getSize() == 12 ? 3.00 : 0)  ;
        customerSandwhich.setMeatType(meat);



        System.out.println(STR."\{customerSandwhich.getPrice()}");
        // Only one meat is needed.
    }


    private static void selectCheese(Scanner scanner, Sandwich customerSandwhich) {
        System.out.println("Cheeses");
        System.out.println("------------------------");
        EnumSet.allOf(Cheeses.class).forEach(System.out::println);

        Cheeses cheeseChoice = Cheeses.valueOf(scanner.nextLine().toUpperCase().strip());
        Cheese cheese = new Cheese(cheeseChoice, customerSandwhich.getSize() == 4 ? .75 :
                customerSandwhich.getSize() == 8 ? 1.50 : customerSandwhich.getSize() == 12 ? 2.25 : 0);
        customerSandwhich.setCheeseType(cheese);
        System.out.println(customerSandwhich.getPrice());
        // only one cheese is needed !
    }

    public static void extraMeat(Sandwich customerSandwhich) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Meats \n -------------------------- \n");
        EnumSet.allOf(Meats.class).forEach(System.out::println);
        // lists cheese and meats
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            for (Meats meat : Meats.values()) {
                if (line.equalsIgnoreCase(String.valueOf(meat).toUpperCase())) {
                    Meat meats = new Meat(Meats.valueOf(line), customerSandwhich.getSize() == 4 ? .50 :
                            customerSandwhich.getSize() == 8 ? 1.00 : customerSandwhich.getSize() == 12 ? 1.50 : 0);
                    customerSandwhich.setExtraMeat(meats);
                }

            }
        }
        System.out.println(customerSandwhich.getExtraMeat());
        // go back to previous screen.
    }

    public static void extraCheese(Sandwich customerSandwhich) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cheeses");
        System.out.println("------------------------");
        EnumSet.allOf(Cheeses.class).forEach(System.out::println);
        // lists cheese and meats
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            for (Cheeses cheese : Cheeses.values()) {
                if (line.equalsIgnoreCase(String.valueOf(cheese).toUpperCase())) {
                    Cheese cheeses = new Cheese(Cheeses.valueOf(line), customerSandwhich.getSize() == 4 ? .30 :
                            customerSandwhich.getSize() == 8 ? .60 : customerSandwhich.getSize() == 12 ? .90 : 0);
                    cheeseList.add(cheeses);
                    customerSandwhich.setCheeses(cheeseList);
                }

            }

            // add all vegetables we obtain in the loop, and set them as the toppings list
            // for the sandwich.
            // when veggies for the sandwich are set, they must be set as a list.
        }
        System.out.println(customerSandwhich.getPrice());
        // go back to previous screen.
    }
    private static Scanner orderScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                      .--..--..--..--..--..--.
                    .' \\  (`._   (_)     _   \\
                  .'    |  '._)         (_)  |
                  \\ _.')\\      .----..---.   /
                  |(_.'  |    /    .-\\-.  \\  |
                  \\     0|    |   ( O| O) | o|
                   |  _  |  .--.____.'._.-.  |
                   \\ (_) | o         -` .-`  |
                    |    \\   |`-._ _ _ _ _\\ /
                    \\    |   |  `. |_||_|   |
                    | o  |    \\_      \\     |     -.   .-.
                    |.-.  \\     `--..-'   O |     `.`-' .'
                  _.'  .' |     `-.-'      /-.__   ' .-'
                .' `-.` '.|='=.='=.='=.='=|._/_ `-'.'
                `-._  `.  |________/\\_____|    `-.'
                   .'   ).| '=' '='\\/ '=' |
                   `._.`  '---------------'
                           //___\\   //___\\
                             ||       ||
                             ||_.-.   ||_.-.
                            (_.--__) (_.--__) 
                Welcome to Hoagie in one! How may I help you today?     
                1. Place new order
                2. Exit Application
                """);
        return scanner;
    }

    private static int addToppings(Scanner scanner) {
        System.out.println("Would you like toppings? \n 1. Yes  \n 2. No \n DISCLAIMER \n CHEESE AND " +
                "MEAT ARE PREMIUM " +
                "TOPPINGS:)");
        int toppingChoice = scanner.nextInt();
        String veggies;

        return toppingChoice;
    }


    private static void addSandwich(int secondChoice, Scanner scanner, Sandwich customerSandwhich) {
        switch (secondChoice) {

            case 1:
                nameSizeBreadType(scanner, customerSandwhich);

                int toppingChoice = addToppings(scanner);
                switch (toppingChoice) {

                    case 1:
                        selectVegetables(scanner, customerSandwhich);
                        selectMeats(scanner, customerSandwhich);
                        selectCheese(scanner, customerSandwhich);
                        System.out.println("Would you like the sandwich to be toasted? ");
                        String toast = scanner.nextLine();
                        if(toast.equalsIgnoreCase("yes")){
                            customerSandwhich.setIsToasted();
                        }
                        System.out.println("Would you like to add Sauce");
                        String sauce = scanner.nextLine();
                        if(sauce.equalsIgnoreCase("Yes")){
                            String sauceChoice = scanner.nextLine();
                            customerSandwhich.setSauce(sauceChoice);
                            choiceScreen(scanner);
                        } else {
                            customerSandwhich.setSauce("None");
                        }
                        // YES, I WANT TOPPINGS
                        break;
                    case 2:
                        choiceScreen(scanner);
                        break;
                    // NO, I DO NOT WANT ANY TOPPINGS -> RETURN BACK TO ORDER SCREEN

                    default:
                        System.out.println("This road leads nowhere .... ");

                }
                break;

        }

    }
    public static void run() {
        Scanner scanner = orderScreen();
    int orderChoice = Integer.parseInt(scanner.nextLine());
    Sandwich customerSandwhich = new Sandwich();
    Order newOrder = new Order(customerSandwhich);
    switch (orderChoice) {
        case 1:

            int sandChoice = choiceScreen(scanner);
            Order order = new Order(customerSandwhich);
            switch (sandChoice) {
                case 1:
                addSandwich(orderChoice, scanner, customerSandwhich);
                addExtras(scanner, customerSandwhich);
                Receipt.saveReceipt(order, customerSandwhich);
                break;
                case 2:
                    addExtras(scanner, customerSandwhich);
                    break;
                case 3:
                    Receipt.saveReceipt(order, customerSandwhich);
                    break;
                default:
                    //<editor-fold desc="Invalid Spongebob">
                    System.out.println("""
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬛⬜⬜⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬛⬜⬛⬜⬜⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬛⬜⬛⬜⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬜⬛⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬜⬛⬜⬜⬛⬛⬛⬛⬛⬛⬛⬜⬛⬛⬛⬛⬜⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬛🟨🟨🟨⬛⬜⬜⬜⬜⬛🟨🟨🟫🟫🟨🟨⬛🟨🟫🟨🟨⬛🟨⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬛⬛⬛🟨🟫🟫🟫🟨⬛⬛⬜⬛⬛⬛🟨🟨🟨🟨🟨🟨🟫🟫🟫🟨⬛⬛🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬛🟨🟨🟨🟨🟫🟫🟨🟨⬛⬛⬛🟦⬛🟨🟨🟫🟫🟫🟨🟨🟨🟨🟨⬛🟨🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬛⬛🟨🟨⬛⬛🟨🟨🟨⬛⬛🟦🟦🟦🟦⬛🟨🟫🟫⬛⬛🟨🟨🟨⬛⬛🟨🟨🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬛🟨⬛⬛🟨🟨⬛⬛⬛🟦🟦🟦🟦🟦⬛🟨⬛⬛⬛🟨🟨⬛⬛⬛⬛🟨🟨🟨🟫🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛🟦🟦🟦⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟫🟫🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬛🟨🟨⬛🟨⬛🟨🟨🟨⬛⬛⬛🟨🟨🟨🟨🟨🟨⬛🟨⬛🟨🟨🟨⬛🟨🟫🟫🟨⬛⬜⬜⬜⬜⬜⬜⬜⬛⬛⬜⬜⬜⬜
                            ⬜⬛🟨⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨⬛⬛⬛⬛🟨🟨🟨⬛🟨🟫🟫🟨⬛⬜⬜⬜⬜⬜⬜⬛🟨🟨⬛⬜⬜⬜
                            ⬜⬜⬛⬜⬜⬜⬜⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛⬜⬜⬜⬜⬛🟨🟨⬛🟨🟨🟨🟨🟨⬛⬜⬜⬜⬜⬛🟨⬛🟨⬛⬜⬜⬜
                            ⬜⬛⬜🟦🟦⬜⬜⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛⬜⬜⬜⬜🟦⬜⬛🟨⬛🟨🟨🟫🟨🟨⬛⬜⬛⬛⬛🟨⬛⬛🟨⬛⬜⬜⬜
                            ⬜⬛🟦⬛🟦⬜⬜⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛⬜⬜⬜🟦⬛🟦⬛🟨🟨⬛🟨🟫🟨🟨⬛⬛⬜⬜⬜⬛⬜⬛🟨⬛⬛⬜⬜
                            ⬜⬛⬜🟦🟦⬜⬜⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛⬜⬜⬜🟦🟦🟦⬛🟨🟨⬛🟨🟨🟨🟨⬛⬜⬜⬜⬜⬛⬜⬛🟨⬛🟨⬛⬜
                            ⬜⬜⬛⬜⬜⬜⬛🟨⬛🟨⬛🟨🟨🟨🟨🟨⬛⬜⬜⬜⬜⬜⬛🟨🟨🟨⬛🟨🟨🟨🟨⬛⬜⬜⬜⬜⬛⬜⬛🟨⬛⬛🟨⬛
                            ⬜⬜⬜⬛⬛⬛🟨⬛🟨🟨⬛🟨🟨🟨🟨🟨🟨⬛⬛⬛⬛⬛🟨🟨🟨🟨⬛🟨🟨🟨🟫⬛⬜⬜⬜⬛⬛⬛🟨🟨⬛🟨⬛⬜
                            ⬜⬜⬜⬜⬛🟨⬛🟨🟨⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟨🟫⬛⬛⬛⬛🟫🟫⬛🟨⬛🟨⬛⬜⬜
                            ⬜⬜⬜⬜⬛⬛🟨🟨⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟫🟫🟨🟨🟨⬛⬛⬛🟨🟨⬛⬜⬜⬜
                            ⬜⬜⬜⬛🟨🟨🟨⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟨🟨🟨🟨🟨⬛⬜⬛🟨⬛⬛⬛⬜⬜
                            ⬜⬜⬜⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨⬛⬛⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟨🟨🟨🟫🟨⬛⬜⬜⬛🟫⬛🟫⬛⬜
                            ⬜⬜⬜⬜⬜⬛🟨🟨🟨🟨🟨🟨🟨⬛🟫🟫🟫🟫🟫🟫⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟨🟨🟨🟨🟨⬛⬜⬛🟫⬛🟫⬛⬜
                            ⬜⬜⬜⬜⬜⬛🟨🟨🟨🟨🟨🟨⬛🟫⬛⬛⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛🟨🟨🟨🟨⬛⬜⬜⬛🟫🟫⬛⬜⬜
                            ⬜⬜⬜⬜⬜⬛🟨🟨🟨🟨🟨⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨⬛🟨🟨🟨🟨🟨🟨🟨🟨⬛⬛⬛🟨🟨⬛⬜⬜⬛🟫⬛⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬛🟨🟨🟨⬛⬛🟨🟨🟨⬛⬛⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛⬛⬛🟨⬛⬜⬛⬛⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬛🟨🟨⬛⬜⬛⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛⬛⬛⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬛🟨⬛⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨🟨⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬛🟨⬛⬜⬜⬜⬜⬜⬛🟫⬛🟫🟫⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛⬛🟫🟫🟫⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬛⬜⬜⬜⬜⬜⬜⬜⬛🟨⬛🟫⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟨🟫🟫⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟨⬛⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟨🟫⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬜⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬜⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛🟨⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬜⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜
                            ⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬛⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜
                                      INVALID OPTION 
                            """);
//</editor-fold>
            }
                break;
        case 2:
            //<editor-fold desc="No Squidward">
            System.out.println("""
                                               .--'''''''''--.
                                             .'      .---.      '.
                                            /    .-----------.    \\\\
                                           /        .-----.        \\\\
                                           |       .-.   .-.       |
                                           |      /   \\\\ /   \\\\      |
                                            \\\\    | .-. | .-. |    /
                                             '-._| | | | | | |_.-'
                                                 | '-' | '-' |
                                                  \\\\___/ \\\\___/
                                               _.-'  /   \\\\  `-._
                                             .' _.--|     |--._ '.
                                             ' _...-|     |-..._ '
                                                    |     |
                                                    '.___.'
                                                      | |
                                                     _| |_
                                                    /\\\\( )/\\\\
                                                   /  ` '  \\\\
                                                  | |     | |
                                                  '-'     '-'
                                                  | |     | |
                                                  | |     | |
                                                  | |-----| |
                                               .`/  |     | |/`.
                                               |    |     |    |
                                               '._.'| .-. |'._.'
                                                     \\\\ | /
                                                     | | |
                                                     | | |
                                                     | | |
                                                    /| | |\\\\
                                                  .'_| | |_`.
                                                 `. | | | .'
                                               .    /  |  \\\\    .
                                              /o`.-'  / \\\\  `-.`o\\\\
                                             /o  o\\\\ .'   `. /o  o\\\\
                                             `.___.'       `.___.'
                                            WAIT WHY ARE YOU LEAVING
                    """);
            //</editor-fold>
           return;


    }




    }
}
