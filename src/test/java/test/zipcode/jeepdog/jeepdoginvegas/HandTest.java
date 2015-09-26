package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;
import static org.junit.Assert.*;


/**
 * Class specification and test cases for abstract class Hand
 *
 * @author Pablo Cano
 */
public class HandTest {

    private Hand hand;
    private Player player;

    private class SimpleHand extends Hand {
        public SimpleHand(Player player) {
            super(player);
        }

        public void calculateScore() {}

        public int getRelativeScore() {
            return 0;
        }
    }

    @Test
    public void testConstructor(){
        Player player = new Player("Marla");
        hand = new SimpleHand(player);
        assertNotNull("Constructor returns a hand", hand);
        assertEquals("Constructor sets Player", player, hand.getPlayer());
        assertEquals("Constructor sets score", 0, hand.getScore());


    }

    @Test
    public void testGetPlayer(){
        hand = new SimpleHand(player);
        assertNull("Method should return a hand player", hand.getPlayer());

        Player player2 = new Player("Luke");
        Hand hand2 = new SimpleHand(player2);

        assertSame("Testing we are getting the player", player2, hand2.getPlayer());


    }

    @Test
    public void testSetScore(){
        Hand hand2 = new SimpleHand(player);
        hand2.setScore(4);
        assertEquals("getScore should return what setScore sets score to", 4, hand2.getScore());
        hand2.setScore(11);
        assertEquals("getScore should return what setScore sets score to", 11, hand2.getScore());
    }

    @Test
    public void testGetScore(){
        Hand hand3 = new SimpleHand(player);
        assertEquals("Returns the score", 0 ,hand3.getScore());

    }

}
