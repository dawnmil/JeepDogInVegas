package zipcode.jeepdog.jeepdoginvegas;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by gfurlong on 9/24/15.
 */
public class Table {
    private ArrayList<Player> opponents;
    private Casino casino;

    /**
     * The default constructor for this table
     */
    public Table() {
        this.opponents = new ArrayList<Player>();
        this.casino = null;
    }

    /**
     * Get this tables name
     *
     * @return Returns the name of this table
     */
    public String getName() {
        return "Empty Table";
    }

    /**
     * Get casino
     *
     * @return the casino this table belongs to
     */
    public Casino getCasino() {
        return this.casino;
    }

    /**
     * Set casino
     *
     * @param casino    the casino this table should belong to
     */
    public void setCasino(Casino casino) {
        this.casino = casino;
    }

    /**
     * Add a player to this table
     *
     * Will ignore requests to add a player that already exists in the array
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        if(! this.opponents.contains(player)) {
            this.opponents.add(player);
        }
    }

    /**
     * Remove a player for the table
     *
     * Will validate that index is a valid index of the opponents ArrayList
     * before attempting to remove
     * @param index The index of the player to remove
     * @return the player removed from the collection
     */
    public Player removePlayer(int index) {
        if(index < 0 || index >= this.opponents.size()) {
            return null;
        }
        else {
            return this.opponents.remove(index);
        }
    }

    /**
     * Get the number of opponents currently at the table
     * @return the numbers of opponents at the table (not including human)
     */
    public int getNumberOfOpponents() {
        return this.opponents.size();
    }

    /**
     * Sit down and play at this table
     * @param    prompt Used to request input from the user
     */
    public void play(Prompt prompt) {
        System.out.println("You sat down at the " + this.getName());

        do {
            this.setupRound();
            this.playRound();
        } while(! this.isReadyToLeave(prompt));

        System.out.println("Thanks for playing at the " + this.getName());
    }

    /**
     * Play a round of whatever is done at this table
     */
    public void playRound() {
        System.out.println("<< You are sitting at the " + this.getName() + " >>");
    }

    /**
     * Ask the user if they're ready to leave the table
     * @param   prompt  Used to request input from the user
     * @return          Return a boolean indicating the users response
     */
    public boolean isReadyToLeave(Prompt prompt) {
        System.out.println("Are you ready to leave the " + this.getName() + "?");
        try {
            return prompt.promptConfirmation("Are you ready to leave the " + this.getName() + "?");
        }
        catch(Exception e) {
            return true;
        }
    }

    /**
     * Set the table back up for another round
     */
    public void setupRound() {
        System.out.println("Let me wipe the table down for you.");
    }
}
