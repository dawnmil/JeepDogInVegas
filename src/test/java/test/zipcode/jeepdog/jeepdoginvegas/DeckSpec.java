package test.zipcode.jeepdog.jeepdoginvegas;

import zipcode.jeepdog.jeepdoginvegas.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by jguevara on 9/23/15.
 */
public class DeckSpec {

    private Deck deck;

    @Before

    public void before(){

        deck = new Deck(1);
    }
    @Test
    public void testOneDeck() {
        assertEquals("Deck 1 equals 52 cards", 52, deck.getNumberOfCards());
    }

    @Test
    public void testTwoDeck(){
        deck = new Deck(2);
        assertEquals("Deck 2 equals 104 cards", 104, deck.getNumberOfCards());
    }

     @Test
    public void testThreeDeck(){
         deck = new Deck(3);
        assertEquals("Deck 3 equals 156 cards", 156, deck.getNumberOfCards());
    }
    @Test
    public void testFourDeck(){
        deck = new Deck(4);
        assertEquals("Deck equals 208 cards", 208, deck.getNumberOfCards());
    }
    @Test
    public void testDeck(){
        deck = new Deck(5);
        assertEquals("Deck equals 260 cards", 260, deck.getNumberOfCards());
    }

    /**
     * here we test that we pulled a card from deck and it returns card and that the deck amount sjrinks by 1
     */
    @Test
    public void testPullCard(){
        int startCards = deck.getNumberOfCards();
        Card card = deck.pullCard();
        assertNotNull("Returns pulled card", card);

        assertEquals("Checks deck shrinks when card is pulled",startCards - 1,deck.getNumberOfCards());
    }

    /**
     * will fail when no cards in deck
     */
    @Test
    public void testPullCardFromEmptyDeck(){
        deck = new Deck(0);

        assertNull("Should return null if there is no cards in deck",deck.pullCard());
    }

}
