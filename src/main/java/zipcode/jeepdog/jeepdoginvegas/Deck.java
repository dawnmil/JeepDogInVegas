package zipcode.jeepdog.jeepdoginvegas;



/**
 * Created by jguevara on 9/23/15.
 */
public class Deck extends CardCollection {
    /**
     * takes number of decks and values of cards and suit of cards
     * @param numDecks
     */
    public Deck(int numDecks) {
        super();

        if (numDecks > 0) {
            for (CardSuit cardSuit : CardSuit.values()) {
                for (CardValue cardValue : CardValue.values()) {
                    for (int i = 0; i < numDecks; i++) {
                        this.addCard(new Card(cardSuit, cardValue));
                    }
                }
            }
        }
    }

    /**
     *  gives you random card and removes one card from deck
     * @return
     */
    public Card pullCard(){
        if(this.getNumberOfCards() == 0){
            return null;
        }
        int random = (int) (Math.random() *  this.getNumberOfCards());
        return this.removeCard(random);

    }
}
