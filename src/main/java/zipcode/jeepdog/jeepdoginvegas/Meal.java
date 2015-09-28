package zipcode.jeepdog.jeepdoginvegas;

/**
 * Meal class called by Cafeteria
 * Created by pcano on 9/28/15.
 */
public class Meal {

    private String name;
    private double price;
    private int numItems;

    /**
     * Meal constructor with 3 parameters
     * @param name of object being created
     * @param price price per unit
     * @param numItems how many were ordered
     */

    public Meal(String name, double price, int numItems){
        this.name = name;
        this.price = price;
        this.numItems = numItems;
    }

    /**
     * Get price method returns the price of a single meal
     * @return price of item
     */
    public double getPrice(){
        return price;
    }

    /**
     * Get name method returns the name of the meal
     * @return name of meal
     */
    public String getName(){
        return name;
    }

    /**
     * Get number of items method
     * @return how many items were chosen
     */
    public int getNumItems(){
        return numItems;
    }

    /**
     * Get total method returns the price of the meal multiplied by amount ordered
     * @return total amount
     */
    public double getTotal(){
        return price*numItems;
    }
}
