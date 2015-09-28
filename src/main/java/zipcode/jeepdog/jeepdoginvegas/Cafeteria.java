package zipcode.jeepdog.jeepdoginvegas;

import java.util.Scanner;

/**
 * Cafeteria class extends Table
 * This class builds the menu and ask for users to pick their choice of food
 * Created by pcano on 9/28/15.
 */

public class Cafeteria extends Table {

    public String getName(){
        return "Casino Cafeteria";
    }

    public void playRound() {

        FoodDrawings pictures = new FoodDrawings();
        Scanner keyboard = new Scanner(System.in);

        /**
         * Here we display the menu
         */
        System.out.println();
        System.out.println("Welcome to the Jeep Dog Casino Cafeteria");
        System.out.println();
        System.out.println("We know gambling makes you hungry. So here are some " +
                "of our delicacies:");
        System.out.println();
        System.out.println("- Pizza (per slice)");
        System.out.println();
        System.out.println("- Sandwich and drink set");
        System.out.println();
        System.out.println("- Soup du jour");
        System.out.println();
        System.out.println("- Decadent slice of cake");
        System.out.println();


        /**
         * Here we ask user to choose meal
         */
        System.out.println();
        System.out.println("Please make your selection by typing either Pizza, Sandwich, Soup, or Cake");
        String decision = keyboard.next();


        /**
         * Here are the if statements that create the meal class and displays the order that the user chose
         */

        if(decision.toLowerCase().equals("pizza")){

            System.out.println("How many slices would you like? Type a number");
            int amount = keyboard.nextInt();

            Meal meal1 = new Meal("Pepperoni pizza", 15.00, amount);
            System.out.println("You chose "+ amount+ " "+ meal1.getName()+" for the amount of: $"+meal1.getTotal()
                    + " at the price of $"+meal1.getPrice()+" each.");
            System.out.println("Do you accept this request? Please type Yes or No");
            String decision2 = keyboard.next();

            if(decision2.toLowerCase().equals("yes")){
                pictures.pizzaDrawing();
            }else{
                System.out.println("Sorry, we charged you anyways");
                pictures.pizzaDrawing();
            }
        }

        else if(decision.toLowerCase().equals("sandwich")){

            System.out.println("How many sets would you like? Type a number");
            int amount = keyboard.nextInt();

            Meal meal1 = new Meal("Ham and cheese sandwich", 25.00, amount);
            System.out.println("You chose "+ amount+ " "+ meal1.getName()+" for the amount of: $"+meal1.getTotal()
                    + " at the price of $"+meal1.getPrice()+" each.");
            System.out.println("Do you accept this request? Please type Yes or No");
            String decision2 = keyboard.next();

            if(decision2.toLowerCase().equals("yes")){
                pictures.sandwichDrawing();
            }else{
                System.out.println("Sorry, we charged you anyways");
                pictures.sandwichDrawing();
            }
        }

        else if(decision.toLowerCase().equals("soup")){

            System.out.println("How many bowls would you like? Type a number");
            int amount = keyboard.nextInt();

            Meal meal1 = new Meal("Tomato soup", 20.00, amount);
            System.out.println("You chose "+ amount+ " "+ meal1.getName()+" for the amount of: $"+meal1.getTotal()
                    + " at the price of $"+meal1.getPrice()+" each.");
            System.out.println("Do you accept this request? Please type Yes or No");
            String decision2 = keyboard.next();

            if(decision2.toLowerCase().equals("yes")){
                pictures.soupDrawing();
            }else{
                System.out.println("Sorry, we charged you anyways");
                pictures.soupDrawing();
            }
        }

        else if(decision.toLowerCase().equals("cake")){

            System.out.println("How many slices would you like? Type a number");
            int amount = keyboard.nextInt();

            Meal meal1 = new Meal("Chocolate cake", 8.00, amount);
            System.out.println("You chose "+ amount+ " "+ meal1.getName()+" for the amount of: $"+meal1.getTotal()
                    + " at the price of $"+meal1.getPrice()+" each.");
            System.out.println("Do you accept this request? Please type Yes or No");
            String decision2 = keyboard.next();

            if(decision2.toLowerCase().equals("yes")){
                pictures.cakeDrawing();
            }else{
                System.out.println("Sorry, we charged you anyways");
                pictures.cakeDrawing();
            }
        }
    }
}
