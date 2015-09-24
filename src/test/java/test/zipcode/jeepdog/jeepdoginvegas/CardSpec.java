package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;
import static org.junit.Assert.*;


/**
 * Created by pcano on 9/22/15.
 */
public class CardSpec {
    private Card card;


    @Test
    public void testConstructor() {
        card = new Card(CardSuit.CLUBS, CardValue.ACE);
        assertEquals("Constructor correctly set suit", CardSuit.CLUBS, card.getSuit());
        assertEquals("Constructor correctly set value", CardValue.ACE, card.getValue());
    }

    @Test
    public void testGetSuit() {
        card = new Card(CardSuit.SPADES, CardValue.ACE);
        assertEquals("Method should return a suit", CardSuit.SPADES, card.getSuit());

        Card card2 = new Card(CardSuit.CLUBS, CardValue.ACE);
        assertEquals("Method should return a suit", CardSuit.CLUBS, card2.getSuit());

        Card card3 = new Card(CardSuit.HEARTS, CardValue.ACE);
        assertEquals("Method should return a suit", CardSuit.HEARTS, card3.getSuit());

    }

    @Test
    public void testGetValue() {
        card = new Card(CardSuit.SPADES,CardValue.ACE);
        assertEquals("Method should return a value", CardValue.ACE, card.getValue());

        Card cardx = new Card(CardSuit.SPADES,CardValue.FOUR);
        assertEquals("Method should return a value", CardValue.FOUR, cardx.getValue());
    }

    @Test
    public void testToString(){
        Card ruru = new Card(CardSuit.SPADES,CardValue.ACE);
        assertEquals("Method should return a value", "As", ruru.toString());

        Card momo = new Card(CardSuit.HEARTS,CardValue.THREE);
        assertEquals("Method should return a value", "3h", momo.toString());
    }
}
