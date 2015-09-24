package zipcode.jeepdog.jeepdoginvegas;

/**
 * Created by dmilnamow on 9/24/15.
 */
public class BlackJackHandScorer {

    /**
     * playerScore constructor
     * @param hand  takes a hand to be scored based on the card score values in the hand
     * @return if score is greater than 21, bust score is 0
     */
    public int playerScore(Hand hand) {

        /**
         * If hand has no cards, return score of 0
         */
       if(hand.getNumberOfCards() == 0){ return 0; }

        /**
         * Ask if hand has an Ace to determine how much it adds to the score (1 or 11)
         * scoreCounter starts at 0 and adds # of points based on worth of cards in the hand
         */
        boolean hasAce = false;
        int scoreCounter = 0;

        /**
         *scoreCounter values to be added to total score as per card added to the hand
         */
        for(Card card : hand.getCards()){
            switch (card.getValue()){
                case ACE: hasAce = true;
                    scoreCounter += 1;
                    break;
                case TWO: scoreCounter += 2;
                    break;
                case THREE: scoreCounter+= 3;
                    break;
                case FOUR: scoreCounter += 4;
                    break;
                case FIVE: scoreCounter += 5;
                    break;
                case SIX: scoreCounter += 6;
                    break;
                case SEVEN: scoreCounter += 7;
                    break;
                case EIGHT: scoreCounter += 8;
                    break;
                case NINE: scoreCounter += 9;
                    break;
                case TEN: scoreCounter += 10;
                    break;
                case JACK: scoreCounter += 10;
                    break;
                case QUEEN: scoreCounter += 10;
                    break;
                case KING: scoreCounter += 10;
                    break;
                default:
                    break;
            }
        }

        /**
         * If the score is less than 12 when an Ace is present in the hand, 10 points will be added
         * and Ace value is thus responsible for 11 points toward the hand's score
         */
        if(hasAce && scoreCounter < 12) {
            scoreCounter += 10;
        }

        /**
         * Bust happens after hand score surpasses 21, returns a score of 0
         */
            return scoreCounter > 21? 0 : scoreCounter;
    }


}
