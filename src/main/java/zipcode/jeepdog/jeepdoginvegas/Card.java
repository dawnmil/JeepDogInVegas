package zipcode.jeepdog.jeepdoginvegas;

/**
 * Created by pcano on 9/22/15.
 */


public class Card {

    private CardSuit suit;

    private CardValue value;

    /**
     * The card constructor
     *
     * @param suit The suit that the card should be
     * @param value The value of the card, 2-10, J, Q, K or Ace
     */
    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * The card suit method
     *
     * @return the values of: heart, diamonds, spade, clubs
     */
    public CardSuit getSuit() {
        return suit;
    }

    /**
     * The card value method
     *
     * @return the value of: ace, two, three.... jack, queen, king
     */
    public CardValue getValue(){
        return value;
    }

    /**
     * The card String method
     * Switch parameters are the method two field variables suit and value.
     * @return the concatenated string of value and suit
     */
    public String toString(){

        String strSuit = "";
        switch(suit) {
            case SPADES:
                strSuit = "s";
                break;
            case CLUBS:
                strSuit = "c";
                break;
            case HEARTS:
                strSuit = "h";
                break;
            case DIAMONDS:
                strSuit = "d";
                break;
        }

        String strValue = "";
        switch(value){
            case ACE:
                strValue = "A";
                break;

            case TWO:
                strValue = "2";
                break;

            case THREE:
                strValue = "3";
                break;

            case FOUR:
                strValue = "4";
                break;

            case FIVE:
                strValue = "5";
                break;

            case SIX:
                strValue = "6";
                break;

            case SEVEN:
                strValue = "7";
                break;

            case EIGHT:
                strValue = "8";
                break;

            case NINE:
                strValue = "9";
                break;

            case TEN:
                strValue = "10";
                break;

            case JACK:
                strValue = "J";
                break;

            case QUEEN:
                strValue = "Q";
                break;

            case KING:
                strValue = "K";
                break;
        }

        return strValue + strSuit;
    }
}
