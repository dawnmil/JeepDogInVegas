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
    Card aceOfClubs = new Card(CardSuit.CLUBS, CardValue.ACE);
    Card aceOfSpades = new Card(CardSuit.SPADES, CardValue.ACE);

    /**
     * for testGetCards method
     */
    @Before
    public void before() {
        cards = new Card[]{this.aceOfClubs, this.aceOfSpades};
        collection = new CardCollection(cards);
    }

    /**
     * checks number of cards being sent is as expected
     */
    @Test
    public void testDefaultConstructor() {
        collection = new CardCollection();
        assertEquals("There should be no cards in the collection", 0, collection.getNumberOfCards());
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
        assertEquals("There should be two cards in the collection", cards, collection.getCards());
    }

    @Test
    public void testGetCardsReturnsNullForEmptyCollection() {
        this.collection = new CardCollection();
        assertNull("There should not be any cards returned from an empty collection", collection.getCards());
    }

    @Test
    public void testToString() {
        String combinedString = cards[0].toString() + " " + cards[1].toString();
        String returned = collection.toString();
        assertEquals("toString should return space separated, concatentated toString of cards.", combinedString, returned);
    }

    @Test
    public void testRemoveCardIndexZero() {
        int initialCards = collection.getNumberOfCards();
        Card removed = collection.removeCard(0);
        assertEquals("removeCard(0) should shrink the array length by 1", initialCards - 1, collection.getNumberOfCards());
        assertSame("removeCard(0) should return the card at index 0", removed, this.aceOfClubs);
    }

    @Test
    public void testRemoveCardOtherIndex() {
        int initialCards = collection.getNumberOfCards();
        Card removed = collection.removeCard(1);
        assertEquals("removeCard(1) should shrink the array length by 1", initialCards - 1, collection.getNumberOfCards());
        assertSame("removeCard(1) should return the card at index 1", removed, this.aceOfSpades);
    }

    @Test
    public void testRemoveCardLessThanZeroFails() {
        int initialCards = collection.getNumberOfCards();
        Card removed = collection.removeCard(-1);
        assertNull("Attempting to remove a card from a negative index should return null", removed);
        assertEquals("Attempting to remove a card from a negative index should not change the array length", initialCards, collection.getNumberOfCards());
    }

    @Test
    public void testRemoveCardGreaterThanMaxFails() {
        int initialCards = collection.getNumberOfCards();
        Card removed = collection.removeCard(initialCards + 1);
        assertNull("Attempting to remove a card from a larger index than exists should return null", removed);
        assertEquals("Attempting to remove a card from a larger index than exists should not change the array length", initialCards, collection.getNumberOfCards());
    }
}