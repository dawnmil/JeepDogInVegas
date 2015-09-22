package zipcode.jeepdog.jeepdoginvegas;

/**
 * Created by dmilnamow on 9/21/15.
 */
public class CardCollection {
    private Card[] cardArray;

    public CardCollection(Card[] cards) {
        this.cardArray = cards;
    }

    /**
     * The addCard method adds a card to the card collection
     * @param card  The card to add to this collection
     */
    public void addCard(Card card){
        if (cardArray == null){
            cardArray = new Card[1];
            cardArray[0] = card;
        }
        else {
            Card[] tempArray = new Card[cardArray.length + 1];
            for(int i=0; i<cardArray.length; i++){
                tempArray[i] = cardArray[i];
            }
            tempArray[tempArray.length -1] = card;
            cardArray = tempArray;
        }
    }

    /**
     * Get this collection's array of cards
     * @return
     */
    public Card[] getCards(){
        return cardArray;
    }

    /**
     * Get the number of cards in this collection
     * @return The number of cards in this collection
     */
    public int getNumberOfCards() {
        return cardArray == null ? 0 : cardArray.length;
    }

    /**
     * Return the cards in this collection as a space separated string
     * @return
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(cardArray != null) {
            for(Card c : cardArray) {
                sb.append(c.toString()).append(" ");
            }
        }

        return sb.toString().trim();
    }
}
