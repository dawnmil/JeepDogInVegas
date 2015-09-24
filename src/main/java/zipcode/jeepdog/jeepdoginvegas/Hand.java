package zipcode.jeepdog.jeepdoginvegas;

/**
 * Created by pcano on 9/24/15.
 */
public class Hand extends CardCollection{

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
