package zipcode.jeepdog.jeepdoginvegas;

/**
 * A Black Jack hand
 *
 * Extends hand to include Black Jack specific implementations of
 * calculateScore and getRelativeScore
 *
 * @author Gregory Furlong
 */
public class BlackJackHand extends Hand {
    static BlackJackHandScorer scorer = new BlackJackHandScorer();

    public BlackJackHand(Player player) {
        super(player);
    }

    /**
     * Get a relative representation of the score
     *
     * With 11 or less, it's impossible to bust by hitting, so it shouldn't
     * be considered part of the valid scoring range for computer players.
     * @return a score from 0 to 100, showing the relative strength of the hand
     */
    public int getRelativeScore() {
        if(this.getScore() <= 11) {
            return 0;
        }
        else {
            return (this.getScore() - 11) * 10;
        }
    }

    /**
     * Recalculate and set this hand's score
     */
    public void calculateScore() {
        this.setScore(BlackJackHand.scorer.playerScore(this));
    }

    /**
     * Test if the value of the passed CardValue is ten in BlackJack
     * @param value     The CardValue to test
     * @return          True if the value is ten, otherwise false
     */
    private boolean hasValueTen(CardValue value) {
        switch(value) {
            case KING:
                return true;
            case QUEEN:
                return true;
            case JACK:
                return true;
            case TEN:
                return true;
            default:
                return false;
        }
    }

    /**
     * Test if this hand can be split according to the rules of black jack
     *
     * Hands can be split if they it is comprised of two cards and the cards share a value.
     * As face cards all have a value of ten, two different faces (or a ten) still satisfy
     * this condition.
     * @return      the result of the test
     */
    public boolean canSplit() {
        if(this.getNumberOfCards() != 2) {
            return false;
        }

        Card[] cards = this.getCards();
        // the cards have the same CardValue
        return cards[0].getValue().equals(cards[1].getValue()) ||
                // or they are both have a value in BlackJack of ten
                this.hasValueTen(cards[0].getValue()) && this.hasValueTen(cards[1].getValue());
    }

    /**
     * Split a hand into two and return the new hand
     * @return      the new BlackJackHand containing one of the cards from the original hand
     */
    public BlackJackHand split() {
        Card card = this.removeCard(1);

        BlackJackHand splitHand = new BlackJackHand(this.getPlayer());
        splitHand.addCard(card);

        return splitHand;
    }
}
