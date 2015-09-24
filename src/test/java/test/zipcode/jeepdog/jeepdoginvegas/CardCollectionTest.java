package test.zipcode.jeepdog.jeepdoginvegas;

/**
 * Created by dmilnamow on 9/21/15.
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

public class CardCollectionTest {
    CardCollection collection;
    Card[] cards;

    /**
     * for testGetCards method
     */
    @Before
    public void before() {
        cards = new Card[]{new Card(CardSuit.CLUBS, CardValue.ACE), new Card(CardSuit.CLUBS, CardValue.ACE)};
        collection = new CardCollection(cards);
    }

    /**
     * checks number of cards being sent is as expected
     */
    @Test
    public void testCardArrayConstructor() {
        collection = new CardCollection(cards);
        assertEquals("There should be two cards in the collection", 2, collection.getNumberOfCards());
    }


    /**
     * This test ensure that cardArray grows by one and that the new card
     * gets inserted at the right chronological position
     */
    @Test
    public void testAddCardIncreasesSize() {
        int initialCards = collection.getNumberOfCards();
        collection.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        assertEquals("addCard should add a new card to the array", initialCards + 1, collection.getNumberOfCards());
    }

    @Test
    public void testAddCardAddsToLastIndex() {
        Card card = new Card(CardSuit.CLUBS, CardValue.ACE);
        collection.addCard(card);

        Card[] cards = collection.getCards();
        assertSame("addCard should add a new card to the end of the array", card, cards[cards.length - 1]);
    }

    @Test
    public void testGetCards() {
        assertSame("There should be two cards in the collection", cards, collection.getCards());
    }

    @Test
    public void testToString() {
        String combinedString = cards[0].toString() + " " + cards[1].toString();
        String returned = collection.toString();
        assertEquals("toString should return space separated, concatentated toString of cards.", combinedString, returned);
    }
}