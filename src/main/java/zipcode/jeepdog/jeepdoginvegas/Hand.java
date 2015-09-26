package zipcode.jeepdog.jeepdoginvegas;

/**
 * Class hand
 *
 * Represents a players hand while playing a card game.  Abstract class, it
 * should be extended by game specific sub-classes to implement the appropriate
 * getRelativeScore and calculateScore methods.
 *
 * @author Pablo Cano
 */
abstract public class Hand extends CardCollection implements Scoreable {

    private Player player;
    private  int score;

    /**
     * Hand constructor
     */
    public Hand(Player player){
        super(null);
        this.player = player;
        score = 0;
    }

    /**
     * getPlayer method
     * @return the player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * setScore method
     * Sets the score with an int value passed on to it.
     * @param value the value of the score
     */
    public void setScore(int value){
        score  = value;
    }

    /**
     * getScore method
     * @return score
     */
    public int getScore(){
        return score;
    }
}
