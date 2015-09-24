package zipcode.jeepdog.jeepdoginvegas;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dmilnamow on 9/21/15.
 */
public class CardCollection {
    private ArrayList<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<Card>();
    }

    public CardCollection(Card[] cards) {
        if(cards == null) {
            this.cards = new ArrayList<Card>();
        }
        else {
            this.cards = new ArrayList<Card>(Arrays.asList(cards));
        }
    }

    /**
     * The addCard method adds a card to the card collection
     * @param card  The card to add to this collection
     */
    public void addCard(Card card){
        this.cards.add(card);
    }

    /**
     * Get this collection's array of cards
     * @return  an array of this collection's cards
     */
    public Card[] getCards(){
        if(this.cards.size() == 0) {
            return null;
        }
        else {
            return this.cards.toArray(new Card[this.cards.size()]);
        }
    }

    /**
     *  Remove a card from this card collection
     * @param index The index of the card to remove
     * @return      Return the card that was removed from the deck
     */
    public Card removeCard(int index) {
        // passed index is outside the range of allowed indices
        if(index < 0 || index >= this.cards.size()) {
            return null;
        }
        // passed index is a valid index of cards
        else {
            return this.cards.remove(index);
        }
    }

    /**
     * Get the number of cards in this collection
     * @return The number of cards in this collection
     */
    public int getNumberOfCards() {
        return this.cards.size();
    }

    /**
     * Return the cards in this collection as a space separated string
     * @return Return a string representation of this card collection
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if(this.cards.size() > 0) {
            for(Card c : this.cards) {
                sb.append(c.toString()).append(" ");
            }
        }

        return sb.toString().trim();
    }
}
