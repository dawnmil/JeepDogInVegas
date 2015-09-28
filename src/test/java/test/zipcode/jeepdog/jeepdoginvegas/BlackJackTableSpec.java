package test.zipcode.jeepdog.jeepdoginvegas;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Class specification and test cases for BlackJackTable
 *
 * @author Gregory Furlong
 */
public class BlackJackTableSpec {
    // fixed value that the user will return when prompted for a bet during these tests
    private final static int playerBet = 5;

    // system under test
    private BlackJackTable blackJackTable;
    // mock casino
    private Casino casino;
    // configure the casino mock to return this human player
    private HumanPlayer humanPlayer;
    // configure the casino mock to return this prompt
    private Prompt prompt;

    @Before
    public void before() {
        // create mock casino
        this.casino = mock(Casino.class);

        // configure humanPlayer mock and set the casino to return it with getHumanPlayer()
        this.humanPlayer = mock(HumanPlayer.class);
        doReturn(this.humanPlayer).when(this.casino).getHumanPlayer();
        doReturn(BlackJackTableSpec.playerBet).when(this.humanPlayer).requestBet();

        // set casino to the casino mock
        this.blackJackTable = spy(new BlackJackTable());
        this.blackJackTable.setCasino(this.casino);

        // create a mock prompt and configure the casino to return it with getPrompt
        this.prompt = mock(Prompt.class);
        doReturn(this.prompt).when(this.casino).getPrompt();
    }

    @Test
    public void testGetDeck() {
        assertNull("Get deck should return null on a new BlackJackTable", this.blackJackTable.getDeck());
        this.blackJackTable.setupRound();
        assertNotNull("Get deck should return a deck if one is assigned to the deck field", this.blackJackTable.getDeck());
    }

    @Test
    public void testGetDealerHand() {
        assertNull("getDealerHand should return null on a new BlackJackTable", this.blackJackTable.getDealerHand());
        this.blackJackTable.setupRound();
        assertNotNull("getDealerHand should return a hand if one is assigned to the dealerHand field", this.blackJackTable.getDealerHand());
    }

    @Test
    public void testGetHands() {
        assertNull("getHands should return null on a new BlackJackTable", this.blackJackTable.getDealerHand());
        this.blackJackTable.setupRound();
        assertNotNull("getHands should return a hand if one is assigned to the dealerHand field", this.blackJackTable.getDealerHand());
    }

    @Test
    public void testGetBets() {
        assertNull("getDealerHand should return null on a new BlackJackTable", this.blackJackTable.getDealerHand());
        this.blackJackTable.setupRound();
        assertNotNull("getDealerHand should return a hand if one is assigned to the dealerHand field", this.blackJackTable.getDealerHand());
    }

    @Test
    public void testPromptInitialBet() {
        assertEquals("promptInitialBet should return 5", 5, this.blackJackTable.promptInitialBet());
    }

    @Test
    public void testSetupRound() {
        this.blackJackTable.setupRound();

        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        assertEquals("There is one BlackJack hand", 1, this.blackJackTable.getHands().size());
        assertEquals("The initial hand has two cards", 2, hand.getNumberOfCards());

        assertNotNull("There is dealer has a hand", this.blackJackTable.getDealerHand());
        assertEquals("The dealer's hand has two cards", 2, this.blackJackTable.getDealerHand().getNumberOfCards());

        assertEquals("There is one bet in the bets HashMap", 1, this.blackJackTable.getBets().size());
        assertEquals("There should be a bet of 5 in the bets HashMap for the initial hand", 5, (int) this.blackJackTable.getBets().get(hand));

        assertEquals("Deck should have 256 cards remaining", 256, this.blackJackTable.getDeck().getNumberOfCards());
    }

    @Test
    public void testHandleSplits() {
        this.blackJackTable.setupRound();

        // Force it to loop twice because a hand is "split" the first time
        doReturn(true)
                .doReturn(false)
                .when(this.blackJackTable)
                .splitHand(any(BlackJackHand.class));

        this.blackJackTable.handleSplits();

        // the hand isn't actually split (as splitHand isn't actually called), so there should be two
        // total calls to splitHand
        verify(this.blackJackTable, times(2)).splitHand(any(BlackJackHand.class));
    }

    @Test
    public void testMakeDealerDecisionStarting16() {
        // return our mock deck when getDeck is called
        Deck deck = mock(Deck.class);
        doReturn(deck).when(this.blackJackTable).getDeck();

        this.blackJackTable.setupRound();
        BlackJackHand hand = this.blackJackTable.getDealerHand();

        // empty the hand
        while(hand.getNumberOfCards() > 0) {
            hand.removeCard(0);
        }

        // start with 16 points
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.SIX));

        this.blackJackTable.makeDealerDecision();
        assertEquals("Should not change the score ", 16, hand.getScore());

        // check that no cards were pulled
        verify(deck, never()).pullCard();
    }

    @Test
    public void testMakeDealerDecisionStarting21() {
        // return our mock deck when getDeck is called
        Deck deck = mock(Deck.class);
        doReturn(deck).when(this.blackJackTable).getDeck();

        this.blackJackTable.setupRound();
        BlackJackHand hand = this.blackJackTable.getDealerHand();

        while(hand.getNumberOfCards() > 0) {
            hand.removeCard(0);
        }

        // start with 21 points, shouldn't pull
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.ACE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));

        this.blackJackTable.makeDealerDecision();
        assertEquals("Should not change the score ", 21, hand.getScore());

        // check that no cards were pulled
        verify(deck, never()).pullCard();

    }

    @Test
    public void testMakeDealerDecisionBecomes18() {
        // return our mock deck when getDeck is called
        Deck deck = mock(Deck.class);
        doReturn(deck).when(this.blackJackTable).getDeck();

        this.blackJackTable.setupRound();
        BlackJackHand hand = this.blackJackTable.getDealerHand();

        // empty the hand
        while(hand.getNumberOfCards() > 0) {
            hand.removeCard(0);
        }

        // start with thirteen points
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));

        // return 5 pts, bringing the total to 18
        doReturn(new Card(CardSuit.CLUBS, CardValue.FIVE))
                .when(deck)
                .pullCard();

        this.blackJackTable.makeDealerDecision();
        assertEquals("Should end at 18 points (after 1 pull)", 18, hand.getScore());
    }

    @Test
    public void testMakeDealerDecisionBusts() {
        // return our mock deck when getDeck is called
        Deck deck = mock(Deck.class);
        doReturn(deck).when(this.blackJackTable).getDeck();

        this.blackJackTable.setupRound();
        BlackJackHand hand = this.blackJackTable.getDealerHand();

        // empty the hand
        while(hand.getNumberOfCards() > 0) {
            hand.removeCard(0);
        }

        // the player should start with thirteen points
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.THREE));

        // pullCard should return a ten, brining the total to 23 and causing the player to bust
        doReturn(new Card(CardSuit.CLUBS, CardValue.TEN))
                .when(deck)
                .pullCard();

        this.blackJackTable.makeDealerDecision();
        assertEquals("Should bust", 0, hand.getScore());
    }

    @Test
    public void testMakeDealerDecisionMultiplePulls() {
        // return our mock deck when getDeck is called
        Deck deck = mock(Deck.class);
        doReturn(deck).when(this.blackJackTable).getDeck();

        this.blackJackTable.setupRound();
        BlackJackHand hand = this.blackJackTable.getDealerHand();

        // empty the hand
        while(hand.getNumberOfCards() > 0) {
            hand.removeCard(0);
        }

        // start with 10 points
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.FIVE));

        // first three pulls will each be worth 2 pts
        doReturn(new Card(CardSuit.CLUBS, CardValue.TWO))
                .doReturn(new Card(CardSuit.CLUBS, CardValue.TWO))
                .doReturn(new Card(CardSuit.CLUBS, CardValue.TWO))
                .when(deck)
                .pullCard();


        this.blackJackTable.makeDealerDecision();
        assertEquals("Should stop at 16 points (after 3 calls to pullCard)", 16, hand.getScore());

        // check that three cards were pulled
        verify(deck, times(3)).pullCard();
    }

    @Test
    public void testHit() {
        this.blackJackTable.setupRound();
        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        hand.calculateScore();

        // don't need to set the score as it shouldn't be possible for the user to

        int initialScore = hand.getScore();
        this.blackJackTable.hit(hand);

        hand.calculateScore();

        assertNotEquals("The hand's score should have changed", initialScore, hand.getScore());
    }

    @Test
    public void testDoubleDownNotEnoughMoney() {
        this.blackJackTable.setupRound();

        // should return false to indicate player doesn't have enough
        doReturn(false).when(this.humanPlayer).removeChips(anyInt());

        // first hand
        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        int initialBet = this.blackJackTable.getBets().get(hand);

        this.blackJackTable.doubleDown(hand);

        assertEquals("The player shouldn't be able to double down, so the bet should be the same", (int) initialBet, (int) this.blackJackTable.getBets().get(hand));
    }

    @Test
    public void testDoubleDownEnoughMoney() {
        this.blackJackTable.setupRound();

        // should return true inidicating player has enough
        doReturn(true).when(this.humanPlayer).removeChips(anyInt());

        // first hand
        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        int initialBet = this.blackJackTable.getBets().get(hand);

        this.blackJackTable.doubleDown(hand);

        assertEquals("The player should be able to double down, so their bet should double", (int) initialBet + BlackJackTableSpec.playerBet, (int) this.blackJackTable.getBets().get(hand));
    }

    @Test
    public void testPromptUserAction() {
        this.blackJackTable.setupRound();

        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        hand.removeCard(1);
        hand.removeCard(0);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));

        // hit, then double down, then stay
        doReturn(1).doReturn(2).doReturn(0).when(this.prompt).promptMenu(anyString(), any(String[].class));

        this.blackJackTable.promptUserAction(hand);

        verify(this.blackJackTable, times(2)).hit(any(BlackJackHand.class));
        verify(this.blackJackTable, times(1)).doubleDown(any(BlackJackHand.class));
    }

    @Test
    public void testPromptUserActions() {
        this.blackJackTable.setupRound();
        this.blackJackTable.getHands().add(new BlackJackHand(this.humanPlayer));

        // we're testing that promptUserAction is called the correct number of times, so we do not
        // need to actually need the methods to perform an action
        doReturn(false).when(this.blackJackTable).promptUserAction(any(BlackJackHand.class));

        this.blackJackTable.promptUserActions();

        // make sure its called once for each hand
        verify(this.blackJackTable, times(2)).promptUserAction(any(BlackJackHand.class));
    }

    @Test
    public void testDistributeChipsPlayerWon() {
        this.blackJackTable.setupRound();

        // guarantee player's score is ten
        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        hand.removeCard(1);
        hand.removeCard(0);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        hand.calculateScore();

        // guarantee dealer's score is two
        BlackJackHand dealerHand = this.blackJackTable.getDealerHand();
        dealerHand.removeCard(1);
        dealerHand.removeCard(0);
        dealerHand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        dealerHand.calculateScore();

        this.blackJackTable.distributeChips();
        // player should get double their bet in winnings
        verify(this.humanPlayer, times(1)).addChips(BlackJackTableSpec.playerBet * 2);
    }

    @Test
    public void testDistributeChipsPlayerTied() {
        this.blackJackTable.setupRound();

        // guarantee player's score is two
        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        hand.removeCard(1);
        hand.removeCard(0);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        hand.calculateScore();

        // guarantee dealer's score is two
        BlackJackHand dealerHand = this.blackJackTable.getDealerHand();
        dealerHand.removeCard(1);
        dealerHand.removeCard(0);
        dealerHand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        dealerHand.calculateScore();

        this.blackJackTable.distributeChips();
        // player should get their bet back on a tie
        verify(this.humanPlayer, times(1)).addChips(BlackJackTableSpec.playerBet);
    }

    @Test
    public void testDistributeChipsPlayerLost() {
        this.blackJackTable.setupRound();

        // guarantee player's score is two
        BlackJackHand hand = this.blackJackTable.getHands().get(0);
        hand.removeCard(1);
        hand.removeCard(0);
        hand.addCard(new Card(CardSuit.CLUBS, CardValue.TWO));
        hand.calculateScore();

        // guarantee dealer's score is ten
        BlackJackHand dealerHand = this.blackJackTable.getDealerHand();
        dealerHand.removeCard(1);
        dealerHand.removeCard(0);
        dealerHand.addCard(new Card(CardSuit.CLUBS, CardValue.TEN));
        dealerHand.calculateScore();

        this.blackJackTable.distributeChips();
        // player should not receive winnings
        verify(this.humanPlayer, never()).addChips(anyInt());
    }
}
