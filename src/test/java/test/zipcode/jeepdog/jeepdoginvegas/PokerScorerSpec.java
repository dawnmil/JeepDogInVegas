package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import static org.junit.Assert.*;

/**
 * Specification and Unit Tests for the PokerScorer class
 * @author Gregory Furlong
 */
public class PokerScorerSpec {
    PokerScorer pokerScorer;
    Player player;

    @Before
    public void before() {
        pokerScorer = new PokerScorer();
        Player player = new Player();
    }

    @Test
    public void testIsStraightFlush() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a straight flush", pokerScorer.isStraightFlush());
    }

    @Test
    public void testIsNotStraightFlush() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be a straight flush", pokerScorer.isStraightFlush());
    }

    @Test
    public void testIsFourOfAKind() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.SPADES, CardValue.ACE));
        hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be four of a kind", pokerScorer.isFourOfAKind());
    }

    @Test
    public void testIsNotFourOfAKind() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be four of a kind", pokerScorer.isFourOfAKind());
    }

    @Test
    public void testIsFullHouse() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a full house", pokerScorer.isFullHouse());
    }

    @Test
    public void testIsNotFullHouse() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be a full house", pokerScorer.isFullHouse());
    }

    @Test
    public void testIsFlush() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a flush", pokerScorer.isFlush());
    }

    @Test
    public void testIsNotFlush() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.SPADES, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be a flush", pokerScorer.isFlush());

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be a flush", pokerScorer.isFlush());
    }

    @Test
    public void testIsStraight() {
        // build the hand (high-ace straight)
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.SPADES, CardValue.TEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a straight", pokerScorer.isStraight());

        // build the hand (low-ace straight)
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.TWO));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.SPADES, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a straight", pokerScorer.isStraight());

        // build the hand (mid straight)
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.SPADES, CardValue.TEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a straight", pokerScorer.isStraight());
    }

    @Test
    public void testIsNotStraight() {
        // build the hand (high-ace straight)
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        hand.addCard(new Card(CardSuit.SPADES, CardValue.TEN));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be a straight", pokerScorer.isStraight());
    }

    @Test
    public void testIsThreeOfAKind() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be three of a kind", pokerScorer.isThreeOfAKind());
    }

    @Test
    public void testIsNotThreeOfAKind() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be three of a kind", pokerScorer.isThreeOfAKind());
    }

    @Test
    public void testIsTwoPair() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be two pair", pokerScorer.isTwoPair());
    }

    @Test
    public void testIsNotTwoPair() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be two pair", pokerScorer.isTwoPair());
    }

    @Test
    public void testIsPair() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);

        assertTrue("This hand should be a pair", pokerScorer.isPair());
    }

    @Test
    public void testIsNotPair() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);

        assertFalse("This hand should not be a pair", pokerScorer.isPair());
    }

    @Test
    public void testTwoFullHouses() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);

        int firstScore = pokerScorer.getScore();

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);
        int secondScore = pokerScorer.getScore();

        assertTrue("This second full house should win", secondScore > firstScore);
    }

    @Test
    public void testTwoFourOfAKind() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.QUEEN));

        pokerScorer.setHand(hand);

        int firstScore = pokerScorer.getScore();

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));

        pokerScorer.setHand(hand);
        int secondScore = pokerScorer.getScore();

        assertTrue("This first four of a kind should win", firstScore > secondScore);
    }

    @Test
    public void testTwoStraights() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));

        pokerScorer.setHand(hand);

        int firstScore = pokerScorer.getScore();

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.THREE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);
        int secondScore = pokerScorer.getScore();

        assertTrue("This first straight should win", firstScore > secondScore);
    }

    @Test
    public void testLowAceStraightComparison() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.SIX));

        pokerScorer.setHand(hand);

        int firstScore = pokerScorer.getScore();

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.TWO));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);
        int secondScore = pokerScorer.getScore();

        assertTrue("This first straight should win", firstScore > secondScore);
    }

    @Test
    public void testHighCard() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.NINE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));

        pokerScorer.setHand(hand);

        int firstScore = pokerScorer.getScore();

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.KING));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.FOUR));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);
        int secondScore = pokerScorer.getScore();

        assertTrue("The high card ace should win", firstScore > secondScore);
    }

    @Test
    public void testHighCardKicker() {
        // build the hand
        PokerHand hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.NINE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));

        pokerScorer.setHand(hand);

        int firstScore = pokerScorer.getScore();

        // build the hand
        hand = new PokerHand(player);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.NINE));
        hand.addCard(new Card(CardSuit.HEARTS, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        pokerScorer.setHand(hand);
        int secondScore = pokerScorer.getScore();

        assertTrue("The kicker of six should beat 5", firstScore > secondScore);
        assertTrue("They should only differ in score by one", firstScore == secondScore + 1);
    }
}
