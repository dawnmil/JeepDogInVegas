package zipcode.jeepdog.jeepdoginvegas;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class pot
 *
 * Tracks a number of scoreables that have a share in the winnings of the pot as well
 * as a current amount of chips in the pot.
 */
public class Pot {
    private int chips;
    private ArrayList<Scoreable> scoreables;

    /**
     * Default constructor
     * @param scoreables
     */
    public Pot(Scoreable[] scoreables) {
        if(scoreables == null) {
            this.scoreables = new ArrayList<Scoreable>();
        }
        else {
            this.scoreables = new ArrayList<Scoreable>(Arrays.asList(scoreables));
        }

        this.chips = 0;
    }

    /**
     * Add chips
     * @param chips     how many chips to add to the pot
     */
    public void addChips(int chips) {
        this.chips += chips;
    }

    /**
     * Get chips
     * @return          the current number of chips in the pot
     */
    public int getChips() {
        return this.chips;
    }

    /**
     * Get scoreables
     * @return          get an ArrayList of current scoreables with a share in the pot
     */
    public ArrayList<Scoreable> getScoreables() {
        return this.scoreables;
    }

    /**
     * Add scoreable
     * @param scoreable     Add a scoreable with a share in the pot
     */
    public void addScoreable(Scoreable scoreable) {
        this.scoreables.add(scoreable);
    }

    /**
     * Remove a scoreable from having a share in the pot
     * @param scoreable
     */
    public void removeScoreable(Scoreable scoreable) {
        this.scoreables.remove(scoreable);
    }

    /**
     *  Split this pot's winnings among the owners of the scoreable's
     *  with the best score
     */
    public void splitPot() {
        // find the high score
        int highScore = 0;
        for(Scoreable scoreable : this.getScoreables()) {
            if(scoreable.getScore() > highScore) {
                highScore = scoreable.getScore();
            }
        }

        // remove every scoreable that doesn't have that score
        for(Scoreable scoreable : this.scoreables.toArray(new Scoreable[this.scoreables.size()])) {
            if(scoreable.getScore() != highScore) {
                this.scoreables.remove(scoreable);
            }
        }

        // find out the per winning winnings share and add it to each winner
        int winnings = this.chips / this.scoreables.size();
        for(Scoreable scoreable : this.scoreables) {
            scoreable.getPlayer().addChips(winnings);
        }
    }
}
