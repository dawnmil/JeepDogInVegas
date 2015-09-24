package zipcode.jeepdog.jeepdoginvegas;



/**
 * Created by jguevara on 9/23/15.
 */
public class Deck extends CardCollection {

    private int numDecks;

    public Deck(int d) {
        super();

        if (d > 0) {
            for (CardSuit cs : CardSuit.values()) {
                for (CardValue cv : CardValue.values()) {
                    for (int i = 0; i < d; i++) {
                        this.addCard(new Card(cs, cv));
                    }
                }
            }
        }
    }

    public Card pullCard(){
        if(this.getNumberOfCards() == 0){
            return null;
        }
        int random = (int) (Math.random() *  this.getNumberOfCards());
        return this.removeCard(random);

    }
}
