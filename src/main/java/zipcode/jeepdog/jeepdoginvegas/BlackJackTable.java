package zipcode.jeepdog.jeepdoginvegas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * BlackJackTable
 *
 * @author Gregory Furlong
 */
public class BlackJackTable extends Table {
    private Deck deck;
    private BlackJackHand dealerHand;
    private ArrayList<BlackJackHand> blackJackHands;
    private HashMap<BlackJackHand,Integer> bets;

    /**
     * Get name
     * @return this table's name
     */
    public String getName() {
        return "Black Jack Table";
    }

    /**
     * Get deck
     * @return  a reference to this table's deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Get dealer hand
     * @return the dealer's hand
     */
    public BlackJackHand getDealerHand() {
        return this.dealerHand;
    }

    /**
     * Get hands
     * @return an ArrayList of the human player's current hands
     */
    public ArrayList<BlackJackHand> getHands() {
        return this.blackJackHands;
    }

    /**
     * Get bets
     *
     * @return a HashMap of the users bets with each hand as a key and the users bet on the hand as the value
     */
    public HashMap<BlackJackHand, Integer> getBets() {
        return this.bets;
    }

    /**
     * Setup the round
     *      -Create a new deck
     *      -Create a new ArrayList of player hand
     *      -Create the first player hand
     *      -Create the dealers hand
     *      -Create the bets HashMap and add an initial bet for the first hand
     */
    public void setupRound() {
        // Create and assign new instance of the deck
        this.deck = new Deck(5);

        // Create an array list to hold the user's hands, create a new hand and add it
        this.blackJackHands = new ArrayList<BlackJackHand>();
        BlackJackHand hand = new BlackJackHand(this.getCasino().getHumanPlayer());
        hand.addCard(this.deck.pullCard());
        hand.addCard(this.deck.pullCard());
        this.blackJackHands.add(hand);

        // Create the dealer's hand
        this.dealerHand = new BlackJackHand(null);
        this.dealerHand.addCard(this.deck.pullCard());
        this.dealerHand.addCard(this.deck.pullCard());

        // Create a HashMap to hold the users bets for each hand
        this.bets = new HashMap<BlackJackHand, Integer>();
        // Prompt and set bet for the initial hand
        this.bets.put(hand, this.promptInitialBet());
    }

    /**
     * Prompt the user for the initial bet
     * @return      the amount to bet
     */
    public int promptInitialBet() {
        return this.getCasino().getHumanPlayer().requestBet();
    }

    /**
     * Prompt the user if they would like to split a hand
     *
     * @return a boolean indicating if the hand was split
     */
    public boolean splitHand(BlackJackHand hand) {
        HumanPlayer human = this.getCasino().getHumanPlayer();

        int matchingBet = this.bets.get(hand);
        if(human.getChips() >= matchingBet
                && hand.canSplit()) {

            boolean response;
            try {
                response = this.getCasino().getPrompt().promptConfirmation("Would you like to spend " + matchingBet +
                        " to split your hand? (" + hand.toString() + ")");
            }
            catch(IOException e) {
                response = false;
            }

            if (response) {
                human.removeChips(matchingBet);

                BlackJackHand splitHand = hand.split();
                hand.addCard(this.deck.pullCard());
                splitHand.addCard(this.deck.pullCard());

                this.blackJackHands.add(splitHand);
                this.bets.put(splitHand, matchingBet);

                return true;
            }
        }

        return false;
    }

    /**
     * Check all hands for possibility to split and prompt user
     */
    public void handleSplits() {
        boolean loop;
        do {
            loop = false;
            for(BlackJackHand hand : this.getHands().toArray(new BlackJackHand[this.getHands().size()])) {
                loop = this.splitHand(hand) || loop;
            }
        } while(loop);
    }

    /**
     * Use simple ai to get the dealers final hand
     *
     * Works by forcing the dealer to hit until the dealer's hand has a score of greater than
     * or equal to 16 or the dealer busts
     */
    public void makeDealerDecision() {
        this.getDealerHand().calculateScore();

        do {
            if(this.getDealerHand().getScore() < 16) {
                this.getDealerHand().addCard(this.getDeck().pullCard());
                this.getDealerHand().calculateScore();
            }
        } while(this.getDealerHand().getScore() != 0 && this.getDealerHand().getScore() < 16);
    }

    /**
     * Prompt the user to make decisions on each hand until they either bust or stay
     */
    public void promptUserActions() {
        for(BlackJackHand hand : this.getHands()) {
            this.promptUserAction(hand);
        }
    }

    /**
     * Prompt the user for decisions on the passed hand until they bust or stay
     *
     * @param hand  The hand to prompt for action on
     * @return      A boolean indicating if the player busted
     */
    public boolean promptUserAction(BlackJackHand hand) {
        Prompt prompt = this.getCasino().getPrompt();

        int response;
        do {
            System.out.println("Current hand:" + hand.toString());
            response = prompt.promptMenu("Please select an action:", new String[] {
                    "Stay",
                    "Hit",
                    "Double Down"
            });

            if(response == 1) {
                this.hit(hand);
            }
            else if(response == 2){
                this.doubleDown(hand);
            }

            hand.calculateScore();
        } while(response != 0 && hand.getScore() != 0);

        return hand.getScore() != 0;
    }

    /**
     * Hit on the passed hand, adding a new card and recalculating the score accordingly
     * @param hand  The hand to hit on
     */
    public void hit(BlackJackHand hand) {
        hand.addCard(this.deck.pullCard());
        hand.calculateScore();
    }

    /**
     * Double down, doubling the players bet and hitting
     * @param hand  The hand to double down on
     */
    public void doubleDown(BlackJackHand hand) {
        int currentBet = bets.get(hand);
        if(this.getCasino().getHumanPlayer().removeChips(currentBet)) {
            bets.put(hand, currentBet * 2);
        }
        else {
            System.out.println("You do not have enough chips to double down.");
        }
        this.hit(hand);
    }

    private void distributeChips(BlackJackHand hand) {

    }

    /**
     * Finish the round
     */
    public void distributeChips() {
        for(BlackJackHand hand : this.blackJackHands) {
            hand.calculateScore();
            int bet = this.bets.get(hand);

            System.out.println(this.dealerHand.toString() + " (dealer) vs " + hand.toString() + " (you)");

            if(hand.getScore() > this.dealerHand.getScore()) {
                System.out.println("You won " + bet*2 + " chips.");
                this.getCasino().getHumanPlayer().addChips(
                        bet * 2
                );
            }
            else if(hand.getScore() == this.dealerHand.getScore()) {
                System.out.println("You broke even and had chips returned " + bet + " .");
                this.getCasino().getHumanPlayer().addChips(
                        this.bets.get(hand)
                );
            }
            else {
                System.out.println("You lost.");
            }
        }
    }

    @Override
    public void playRound() {
        this.handleSplits();
        this.promptUserActions();
        this.makeDealerDecision();
        this.distributeChips();
    }
}
