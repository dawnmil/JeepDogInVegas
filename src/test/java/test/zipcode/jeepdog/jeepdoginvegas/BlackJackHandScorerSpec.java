package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;
import static org.junit.Assert.*;



/**
 * Created by dmilnamow on 9/24/15.
 */
public class BlackJackHandScorerSpec {

    private BlackJackHandScorer blackJackHandScorer = new BlackJackHandScorer();

    private Hand hand;

    @Before
    public void beforeTests(){

        this.hand = new Hand(new Player("Bob"));
    }

    @Test
    public void emptyHandScoreTest(){

        assertEquals("Should return a score of 0", 0, blackJackHandScorer.playerScore(hand));

    }

    @Test
    public void twoCardHandScoreTest() {

        this.hand.addCard(new Card(CardSuit.CLUBS, CardValue.QUEEN));
        this.hand.addCard(new Card(CardSuit.SPADES, CardValue.TEN));

        assertEquals("Should return score of 20", 20, blackJackHandScorer.playerScore(hand));

    }

    @Test
    public void handWithOneAceScoreTest() {

        this.hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.HEARTS, CardValue.KING));

        assertEquals("Should return score of 21", 21, blackJackHandScorer.playerScore(hand));

    }

    @Test
    public void handWithTwoAcesScoreTest() {

        this.hand.addCard(new Card(CardSuit.SPADES, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.HEARTS, CardValue.ACE));

        assertEquals("Should return score of 12", 12, blackJackHandScorer.playerScore(hand));

    }

    @Test
    public void bustedHandScoreTest1() {

        this.hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.EIGHT));
        this.hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.FIVE));
        this.hand.addCard(new Card(CardSuit.CLUBS, CardValue.JACK));

        assertEquals("Should return score of 0", 0, blackJackHandScorer.playerScore(hand));

    }

    @Test
    public void bustedHandScoreTest2() {

        this.hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.SPADES, CardValue.SIX));
        this.hand.addCard(new Card(CardSuit.HEARTS, CardValue.JACK));
        this.hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.EIGHT));

        assertEquals("Should return score of 0", 0, blackJackHandScorer.playerScore(hand));

    }

    @Test
    public void bustedHandScoreTest3() {

        this.hand.addCard(new Card(CardSuit.SPADES, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.HEARTS, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.ACE));
        this.hand.addCard(new Card(CardSuit.DIAMONDS, CardValue.KING));
        this.hand.addCard(new Card(CardSuit.SPADES, CardValue.KING));

        assertEquals("Should return score of 0", 0, blackJackHandScorer.playerScore(hand));

    }

}
