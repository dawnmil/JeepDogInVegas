package zipcode.jeepdog.jeepdoginvegas;

/**
 * The base Player class.
 *
 * Has methods to manipulate the players chip total, request a bet and
 * randomly choose from a simple list of options.
 *
 * @author Joel Guevara
 */
public class Player {
    /**
     * Player name, and amount of chips player has
     */
    private String name;

    private int chips;

    public Player() {
        this.name = "Player";
    }

    public Player(String name){
        this.name = name;
    }

    /**
     * Is this player human
     * @return Return false if this player should be computer controlled
     */
    public boolean isHuman() {
        return false;
    }

    /**
     * Get chips
     *
     * @return value of chips
     */
    public int getChips(){
        return chips;

    }

    /**
     * Add chips
     * @param selectAmount of chips to add to pot
     */
    public void addChips(int selectAmount){

        chips += selectAmount;
    }

    /**
     * Remove chips
     *
     * @param amount that can be removed from person pot of chips
     */
    public boolean removeChips(int amount) {
        if(chips >= amount){
            chips -= amount;
            return true;
        }else {
            return false;
        }

    }

    /**
     * Select an option from the passed array
     *
     * @param option Array of options to choose from
     * @return       Return the index of the selected option
     */
    public int selectOption(String[] option){

        return (int) (Math.random() * option.length);
    }

    /**
     * Request a bet from the player
     *
     * Removes the bet from the player
     * @return request  The bet decided upon
     */
    public int requestBet(){
        int bet = (int) (Math.random() * 10);

        this.removeChips(bet);
        return bet;
    }
}
