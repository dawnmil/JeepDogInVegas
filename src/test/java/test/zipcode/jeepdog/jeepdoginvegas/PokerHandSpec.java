package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Specification and unit tests for the Poker Hand class
 * @author Gregory Furlong
 */
public class PokerHandSpec {
    PokerHand pokerHand;
    Deck deck;

    @Before
    public void before() {
        this.pokerHand = new PokerHand(new Player());
        this.pokerHand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        this.pokerHand.addCard(new Card(CardSuit.HEARTS, CardValue.FIVE));
        this.pokerHand.addCard(new Card(CardSuit.DIAMONDS, CardValue.EIGHT));
        this.pokerHand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));
        this.pokerHand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));

        this.deck = spy(new Deck(1));
    }

    @Test
    public void testGetScore() {
        this.pokerHand.calculateScore();
        int initialScore = this.pokerHand.getScore();

        this.pokerHand.removeCard(4);
        this.pokerHand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        this.pokerHand.calculateScore();

        assertTrue("The value of the hand should have changed", initialScore != this.pokerHand.getScore());
    }

    @Test
    public void testGetRelativeScore() {
        this.pokerHand.calculateScore();
        int initialScore = this.pokerHand.getRelativeScore();

        this.pokerHand.removeCard(4);
        this.pokerHand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        this.pokerHand.calculateScore();

        assertTrue("The relative score should be between 0 and 100", initialScore <= 100 && initialScore >= 0);
        assertTrue("The relative score should be between 0 and 100", this.pokerHand.getRelativeScore() <= 100 && this.pokerHand.getRelativeScore() >= 0);
        assertTrue("The relative score of the hand should have changed", initialScore != this.pokerHand.getRelativeScore());
    }

    @Test
    public void testFillHand() {
        this.pokerHand = new PokerHand(new Player());
        this.pokerHand.fillHand(this.deck);

        assertEquals("Hand should have 5 cards", 5, this.pokerHand.getNumberOfCards());
        verify(this.deck, times(5)).pullCard();
    }

    @Test
    public void testFillHandAlreadyFull() {
        this.pokerHand.fillHand(this.deck);

        assertEquals("Hand should have 5 cards", 5, this.pokerHand.getNumberOfCards());
        verify(this.deck, never()).pullCard();
    }
}
