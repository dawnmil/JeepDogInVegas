package zipcode.jeepdog.jeepdoginvegas;

/**
 * the implementation of a blackjack Player
 * Created by jguevara on 9/21/15.
 */
public class Player {
    /**
     * Player name, and amount of chips player has
     */
    private String name;

    private int chips;

    public Player(String name){

        this.name = name;
    }

    /**
     * @return value of chips
     */
    public int getChips(){
        return chips;

    }

    /**
     * @param selectAmount of chips to add to pot
     */
    public void addChips(int selectAmount){

        chips += selectAmount;
    }

    /**
     * @param amount that can be removed from person pot of chips
     * @throws Exception
     */
    public boolean removeChips(int amount) {
        if(chips > amount){
            chips -= amount;
            return true;
        }else {
            return false;
        }

    }

    /**
     * @param option that can be picked during play by computer
     * @return
     */
    public int selectOption(String[] option){

        return (int) (Math.random() * option.length);
    }

    /**
     *
     * @return request  bet
     */
    public int requestBet(){
        return (int) (Math.random() * 10);
    }
}
