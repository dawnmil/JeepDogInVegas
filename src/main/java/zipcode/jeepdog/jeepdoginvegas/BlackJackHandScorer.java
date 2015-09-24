package zipcode.jeepdog.jeepdoginvegas;

/**
 * Created by dmilnamow on 9/24/15.
 */
public class BlackJackHandScorer {


    public int playerScore(Hand hand) {


       if(hand.getNumberOfCards() == 0){ return 0; }

        boolean hasAce = false;
        int scoreCounter = 0;

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
            }
        }

        if(hasAce = true && scoreCounter < 12) {
            scoreCounter += 10;
        }


            return scoreCounter > 21? 0 : scoreCounter;
    }


}
