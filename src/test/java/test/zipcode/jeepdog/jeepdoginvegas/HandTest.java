package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;
import static org.junit.Assert.*;


/**
 * Created by pcano on 9/24/15.
 */
public class HandTest {

    private Hand hand;
    private Player player;

    @Test
    public void testConstructor(){
        Player player = new Player("Marla");
        hand = new Hand(player);
        assertNotNull("Constructor returns a hand", hand);
        assertEquals("Constructor sets Player", player, hand.getPlayer());
        assertEquals("Constructor sets score", 0, hand.getScore());


    }

    @Test
    public void testGetPlayer(){
        hand = new Hand(player);
        assertNull("Method should return a hand player", hand.getPlayer());

        Player player2 = new Player("Luke");
        Hand hand2 = new Hand(player2);

        assertSame("Testing we are getting the player", player2, hand2.getPlayer());


    }

    @Test
    public void testSetScore(){
        Hand hand2 = new Hand(player);
        hand2.setScore(4);
        assertEquals("getScore should return what setScore sets score to", 4, hand2.getScore());
        hand2.setScore(11);
        assertEquals("getScore should return what setScore sets score to", 11, hand2.getScore());
    }

    @Test
    public void testGetScore(){
        Hand hand3 = new Hand(player);
        assertEquals("Returns the score", 0 ,hand3.getScore());

    }

}
