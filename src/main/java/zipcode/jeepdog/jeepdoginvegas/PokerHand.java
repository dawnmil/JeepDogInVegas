package zipcode.jeepdog.jeepdoginvegas;

/**
 * A hand sub-class for use in 5 card poker games
 *
 * Can calculate a poker specific score and can fill itself when
 * provided with a deck
 * @author Gregory Furlong
 */
public class PokerHand extends Hand {
    public PokerHand(Player player) {
        super(player);
    }

    /**
     * Get the relative score for this hand
     * @return The relative score of this hand (0 to 100)
     */
    public int getRelativeScore() {
        return (int) ((this.getScore() / ((float) PokerScorer.MINIMUM_PAIR * 9)) * 100);
    }

    /**
     * Calculate this hand's score
     */
    public void calculateScore() {
        PokerScorer scorer = new PokerScorer();
        scorer.setHand(this);
        this.setScore(scorer.getScore());
    }

    /**
     * Pull cards from the deck until this hand is full
     * @param deck      A deck to pull cards from
     */
    public void fillHand(Deck deck) {
        while(this.getNumberOfCards() < 5) {
            this.addCard(deck.pullCard());
        }
    }
}
