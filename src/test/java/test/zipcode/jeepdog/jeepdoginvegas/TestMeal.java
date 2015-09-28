package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Test;
import static org.junit.Assert.*;
import zipcode.jeepdog.jeepdoginvegas.*;

/**
 * Created by pcano on 9/28/15.
 */
public class TestMeal {

    @Test
    public void TestConstructor(){

        Meal meal = new Meal("Pizza", 1.00, 3);
        System.out.println("Checking out constructor ");
        assertEquals("Pizza", meal.getName());
        assertEquals(1.00, meal.getPrice(),0.1);
        assertEquals(3, meal.getNumItems());
    }

    @Test
    public void TestGetName(){
        Meal meal = new Meal("Soup", 12.00, 3);
        Meal meal1 = new Meal("bread", 12.00, 3);
        System.out.println("Checking out getName ");
        assertEquals("Soup", meal.getName());
        assertEquals("bread", meal1.getName());
    }
    @Test
    public void TestGetPrice(){
        Meal meal = new Meal("Soup", 24.00, 3);
        Meal meal1 = new Meal("Soup", 40.55, 3);
        System.out.println("Checking out getPrice ");
        assertEquals(24.00, meal.getPrice(), 0.1);
        assertEquals(40.55, meal1.getPrice(), 0.1);
    }

    @Test
    public void testGetItems(){
        Meal meal = new Meal("Soup", 12.00, 7);
        Meal meal1 = new Meal("Soup", 12.00, 10);
        System.out.println("Checking out get number of Items ");
        assertEquals(7, meal.getNumItems());
        assertEquals(10, meal1.getNumItems());
    }

    @Test
    public void testGetTotal(){
        Meal meal = new Meal("Soup", 12.00, 2);
        Meal meal1 = new Meal("Soup", 30.00, 3);
        System.out.println("Checking out total amount ");
        assertEquals(24.00, meal.getTotal(), 0.1);
        assertEquals(90.00, meal1.getTotal(), 0.1);

    }
}
