package test.zipcode.jeepdog.jeepdoginvegas;

import zipcode.jeepdog.jeepdoginvegas.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Class specification and test cases for the base Player class
 *
 * @author Joel Guevara
 */
public class PlayerSpec {
    private Player snoopy;

    @Before
    public void before() {

        snoopy = new Player("snoopy");
    }

    @Test
    public void testGetChips() {

        assertEquals("Your chips equal zero", 0, snoopy.getChips());
    }

    @Test
    public void testAddChips(){
        int g = snoopy.getChips();
        int c = 3;
        snoopy.addChips(c);
        assertEquals("Make sure chips equal 0", g + c, snoopy.getChips());
    }
    @Test
    public void testRemoveChips(){
        assertFalse("You dont have enough Chips!", snoopy.removeChips(2));
        snoopy.addChips(2);

        int g = snoopy.getChips();
        int c = 1;
        assertTrue("Removed amount requested", snoopy.removeChips(c));
        assertEquals("Make sure removed chips are equal to chips", g - c, snoopy.getChips());
    }

    @Test
    public void testSelectOption(){
        String[] s = new String []{ "Play BlackJack",  "PLay Poker", "Quit"};
        int returnedInt = snoopy.selectOption(s);
        assertTrue("What option do you wish to choose?", returnedInt < s.length);
        assertTrue("What option do you wish to choose?", returnedInt >= 0);
    }

    @Test
    public void testRequestBet(){
        int bet = snoopy.requestBet();
        assertTrue("Bet should be less than 10", bet <= 10);
        assertTrue("Bet should be greater than or equal to 0", bet >= 0);
    }
}
