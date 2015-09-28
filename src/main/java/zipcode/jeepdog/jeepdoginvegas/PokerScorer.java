package zipcode.jeepdog.jeepdoginvegas;

import org.mockito.cglib.core.CollectionUtils;

import java.util.*;

/**
 * A hand scorer used to score 5 card poker hands
 *
 * This is not even remotely optimized.
 * @author Gregory Furlong
 */
public class PokerScorer {
    /*
        Separate card ranks into ranges where each range is 14^5

        Most values in a given range can not actually be reached, but
        this would allow the same scoring method to be used for comparing
        the same type of hand easily
     */
    public final static int MINIMUM_STRAIGHT_FLUSH = 4302592;
    public final static int MINIMUM_FOUR_OF_A_KIND = 3764768;
    public final static int MINIMUM_FULL_HOUSE = 3226944;
    public final static int MINIMUM_FLUSH = 2689120;
    public final static int MINIMUM_STRAIGHT = 2151296;
    public final static int MINIMUM_THREE_OF_A_KIND = 1613472;
    public final static int MINIMUM_TWO_PAIR = 1075648;
    public final static int MINIMUM_PAIR = 537824;


    PokerHand hand;
    private boolean aceIsLow;

    HashMap<CardValue, Integer> values;
    HashMap<CardSuit, Integer> suits;

    public PokerScorer() {
        this.values = new HashMap<CardValue, Integer>();
        this.suits = new HashMap<CardSuit, Integer>();
    }

    /**
     * A comparator for returning two card values
     */
    public class CardValueComparator implements Comparator<CardValue> {
        /**
         * Score a card value
         * @param cardValue The card value of this card
         * @return          An integer value for the card
         */
        private int scoreCardValue(CardValue cardValue) {
            switch(cardValue) {
                case ACE:
                    return PokerScorer.this.aceIsLow ? 1 : 14;
                case KING:
                    return 13;
                case QUEEN:
                    return 12;
                case JACK:
                    return 11;
                case TEN:
                    return 10;
                case NINE:
                    return 9;
                case EIGHT:
                    return 8;
                case SEVEN:
                    return 7;
                case SIX:
                    return 6;
                case FIVE:
                    return 5;
                case FOUR:
                    return 4;
                case THREE:
                    return 3;
                case TWO:
                    return 2;
                default:
                    // should never be reached
                    return 0;
            }
        }

        /**
         * Compare two card values, returning their difference
         * @param o1    The first CardValue
         * @param o2    The second CardValue
         * @return      The difference in value between the two cards
         */
        public int compare(CardValue o1, CardValue o2) {
            return this.scoreCardValue(o2) - this.scoreCardValue(o1);
        }
    }

    /**
     * Test if this hand is four of a kind
     * @return  if the hand is four of a kind
     */
    public boolean isFourOfAKind() {
        for(CardValue value : this.values.keySet()) {
            if(this.values.get(value) == 4) {
                return true;
            }
        }

        return false;
    }

    /**
     * Is straight flush
     * @return  if this hand is a straight flush
     */
    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    /**
     * Is full house
     * @return  if the hand is a full house
     */
    public boolean isFullHouse() {
        boolean hasTrips = false;
        boolean hasPair = false;

        for(CardValue value : this.values.keySet()) {
                if(this.values.get(value) == 3) {
                    hasTrips = true;
                }
                else if(this.values.get(value) == 2) {
                    hasPair = true;
                }
        }
        return hasTrips && hasPair;
    }

    /**
     * Is straight
     * @return  if the hand is straight
     */
    public boolean isStraight() {
        boolean hasAce = this.values.containsKey(CardValue.ACE);
        int count = 0;
        for(CardValue value : CardValue.values()) {
            // ace needs to be handled differently as it can match either end
            if (value.equals(CardValue.ACE)) continue;

            // increment count
            if(this.values.containsKey(value)) {
                count++;
                // if the count of consecutive cards is now 5, we found a straight
                if(count == 5) {
                    return true;
                }
                // if the count of consecutive cards is 4, this card would be the highest non-ace
                // in a straight involving an ace and we have an ace, we've also found a straight
                else if(count == 4 && hasAce) {
                    if (value.equals(CardValue.FIVE)) {
                        // we'll need to know it was a low ace for same hand type comparisons
                        aceIsLow = true;
                        return true;
                    } else if (value.equals(CardValue.KING)) {
                        return true;
                    }
                }
            }
            // there is apparently a gap, so no straight is present
            else if (count > 0){
                return false;
            }
        }

        // fallback that should never be reached
        return false;
    }

    /**
     * Is flush
     * @return  if the hand is a flush
     */
    public boolean isFlush() {
        for(CardSuit suit : this.suits.keySet()) {
            if(this.suits.get(suit) == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is three of a kind
     * @return  if the hand is three of a kind
     */
    public boolean isThreeOfAKind() {
        for(CardValue value : this.values.keySet()) {
            if(this.values.get(value) == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is two pair
     * @return if the hand is two pair
     */
    public boolean isTwoPair() {
        boolean onePair = false;
        for(CardValue value : this.values.keySet()) {
            if(this.values.get(value) == 2) {
                if(onePair) {
                    return true;
                }
                else {
                    onePair = true;
                }
            }
        }
        return false;
    }

    /**
     * Is pair
     * @return  if the hand is a pair
     */
    public boolean isPair() {
        for(CardValue value : this.values.keySet()) {
            if(this.values.get(value) == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Score a hand of 5 cards.
     *
     * This scoring mechanism is independent of the type of hand and is only used to differentiate
     * hands that are of the same type.
     *
     * Result should range from 15 (A,2,3,4,5) to 499408 (A,A,A,A,K), so adding this value to
     * the minimum for the identified hand classification should not push the total value into
     * the next classification's range.
     * @return      Total score of this collection of cards
     */
    private int scoreCards() {
        ArrayList<CardValue> keys = new ArrayList<CardValue>(this.values.keySet());
        CardValueComparator comparator = new CardValueComparator();
        Collections.sort(keys, comparator);

        // Next card value added to cv will be multiplied by 14^exponent
        int exponent = 4;
        // Running total
        int total = 0;

        // 4 of a kind
        if(this.values.values().contains(4)) {
            for(CardValue cardValue: keys) {
                if(this.values.get(cardValue).equals(4)) {
                    total += Math.pow(14, exponent) * comparator.scoreCardValue(cardValue);
                    exponent -= 4;
                    break;
                }
            }
        }
        // 3 of a kind
        else if(this.values.values().contains(3)) {
            for(CardValue cardValue: keys) {
                if(this.values.get(cardValue).equals(3)) {
                    total += Math.pow(14, exponent) * comparator.scoreCardValue(cardValue);
                    exponent -= 3;
                    break;
                }
            }
        }

        // evaluate pairs (keys are already sorted by card value, so higher valued card should)
        // be added first
        if(this.values.values().contains(2)) {
            for(CardValue cardValue: keys) {
                if(this.values.get(cardValue).equals(2)) {
                    total += Math.pow(14, exponent) * comparator.scoreCardValue(cardValue);
                    exponent -= 2;
                }
            }
        }

        // Evaluate remaining unpaired cards
        for(CardValue cardValue: keys) {
            if(this.values.get(cardValue).equals(1)) {
                total += Math.pow(14, exponent) * comparator.scoreCardValue(cardValue);
                exponent -= 1;
            }
        }

        return total;
    }

    /**
     * Return a score based on this hand's classification
     * @return      A score based on this hand's minimum classification
     */
    private int scoreHand() {
        if(isStraightFlush()) return PokerScorer.MINIMUM_STRAIGHT_FLUSH;
        if(isFourOfAKind()) return PokerScorer.MINIMUM_FOUR_OF_A_KIND;
        if(isFullHouse()) return PokerScorer.MINIMUM_FULL_HOUSE;
        if(isFlush()) return PokerScorer.MINIMUM_FLUSH;
        if(isStraight()) return PokerScorer.MINIMUM_STRAIGHT;
        if (isThreeOfAKind()) return PokerScorer.MINIMUM_THREE_OF_A_KIND;
        if(isTwoPair()) return PokerScorer.MINIMUM_TWO_PAIR;
        if(isPair()) return PokerScorer.MINIMUM_PAIR;
        return 0;
    }

    /**
     * Reset the state of this scorer
     */
    private void reset() {
        this.aceIsLow = false;
        this.values = new HashMap<CardValue, Integer>();
        this.suits = new HashMap<CardSuit, Integer>();
    }

    /**
     * Build HashMaps for counting how many cards are of a given value and suit
     */
    private void mapHand() {
        for(Card card : this.hand.getCards()) {
            CardValue value = card.getValue();
            CardSuit suit = card.getSuit();

            int valueCount = values.containsKey(value) ? values.get(value) + 1 : 1;
            values.put(value, valueCount);

            int suitCount = this.suits.containsKey(suit) ? this.suits.get(suit) + 1 : 1;
            this.suits.put(suit, suitCount);
        }
    }

    /**
     * Score the current hand
     * @return      The calculated score
     */
    public int getScore() {
        return this.scoreHand() + this.scoreCards();
    }

    public void setHand(PokerHand hand) {
        this.hand = hand;
        this.reset();

        this.mapHand();
    }
}
