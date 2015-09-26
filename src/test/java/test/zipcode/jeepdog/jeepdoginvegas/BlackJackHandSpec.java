package test.zipcode.jeepdog.jeepdoginvegas;

import zipcode.jeepdog.jeepdoginvegas.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gfurlong on 9/26/15.
 */
public class BlackJackHandSpec {
    private Player player;
    private BlackJackHand blackJackHand;

    @Before
    public void before() {
        this.player = new Player("Player");
        this.blackJackHand = new BlackJackHand(this.player);
    }

    @Test
    public void testCanSplitFailsIfNotQuantityTwo() {
        assertFalse("canSplit should fail with no cards", this.blackJackHand.canSplit());
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        assertFalse("canSplit should fail with one cards", this.blackJackHand.canSplit());
        this.blackJackHand.addCard(new Card(CardSuit.DIAMONDS, CardValue.ACE));
        this.blackJackHand.addCard(new Card(CardSuit.SPADES, CardValue.ACE));
        assertFalse("canSplit should fail with three cards", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitFailsWithDifferentValue1() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));
        assertFalse("canSplit should fail with different values", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitFailsWithDifferentValue2() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.FOUR));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.EIGHT));
        assertFalse("canSplit should fail with different values", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitFailsWithDifferentValue3() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.SEVEN));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        assertFalse("canSplit should fail with different values", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitFailsWithDifferentValue4() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        assertFalse("canSplit should fail with different values", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitPassesWithSameValue1() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        assertTrue("canSplit should return true with same values", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitPassesWithSameValue2() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));
        assertTrue("canSplit should return true with same values", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitPassesWithTwoFaces1() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        assertTrue("canSplit should return true with two faces", this.blackJackHand.canSplit());
    }

    @Test
    public void testCanSplitPassesWithTwoFaces2() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        assertTrue("canSplit should return true with two faces", this.blackJackHand.canSplit());
    }

    @Test
    public void testSplitHand() {
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        this.blackJackHand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        assertTrue("canSplit should return true with two faces", this.blackJackHand.canSplit());

        BlackJackHand splitHand = this.blackJackHand.split();

        assertEquals("Initial hand should have one card", 1, this.blackJackHand.getNumberOfCards());
        assertEquals("Initial hand should have a JACK", CardValue.JACK, this.blackJackHand.getCards()[0].getValue());

        assertEquals("Split hand should have one card", 1, splitHand.getNumberOfCards());
        assertEquals("Initial hand should have a JACK", CardValue.KING, splitHand.getCards()[0].getValue());
    }
}
