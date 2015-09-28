package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import static org.junit.Assert.*;

/**
 * Specification and unit tests for class Pot
 * @author Gregory Furlong
 */
public class PotSpec {
    private Pot pot;
    private Scoreable[] scoreables;

    @Before
    public void before(){
        this.scoreables = new Scoreable[] {
                new BlackJackHand(new Player()),
                new BlackJackHand(new Player()),
                new BlackJackHand(new Player()),
                new BlackJackHand(new Player())
        };

        this.pot = new Pot(scoreables);
    }

    @Test
    public void testGetChips() {
        assertEquals("Pot should start without any chips", 0, this.pot.getChips());
    }

    @Test
    public void testAddChips() {
        int toAdd = 500;
        this.pot.addChips(toAdd);
        assertEquals("Pot should now have the number of chips added", toAdd, this.pot.getChips());

        int secondAdd = 1000;
        this.pot.addChips(secondAdd);
        assertEquals("Pot should have a further 1000 chips added", toAdd + secondAdd, this.pot.getChips());
    }

    @Test
    public void testGetScoreables() {
        for(Scoreable scoreable : this.pot.getScoreables()) {
            assertTrue("The scoreable should be in the pot's getScoreables", this.pot.getScoreables().contains(scoreable));
        }
    }

    @Test
    public void testAddScoreable() {
        Scoreable scoreable = new BlackJackHand(new Player());
        this.pot.addScoreable(scoreable);

        assertTrue("The new scoreable should now be in the arraylist of scoreables", this.pot.getScoreables().contains(scoreable));
    }

    @Test
    public void testRemoveScoreable() {
        Scoreable toRemove = this.scoreables[1];
        assertTrue("The scoreable toRemove started in the pot's scoreables", this.pot.getScoreables().contains(toRemove));
        this.pot.removeScoreable(toRemove);
        assertFalse("The scoreable toRemove is not longer in the pot's scoreables", this.pot.getScoreables().contains(toRemove));
    }

    @Test
    public void testSpitPotOneWinner() {
        int winningChips = 1000;

        Scoreable toWin = this.scoreables[1];
        Player winner = toWin.getPlayer();

        toWin.setScore(20);
        this.pot.addChips(winningChips);

        int initialChips = winner.getChips();
        this.pot.splitPot();
        assertEquals("Winning player should have 1000 more chips", initialChips + winningChips, winner.getChips());
    }

    @Test
    public void testSplitPotSharedWinnings() {
        int winningChips = 1000;

        Scoreable winningHand1 = this.scoreables[1];
        Player winner1 = winningHand1.getPlayer();

        Scoreable winningHand2 = this.scoreables[2];
        Player winner2 = winningHand2.getPlayer();

        winningHand1.setScore(20);
        winningHand2.setScore(20);

        int winningsShare = winningChips / 2;
        this.pot.addChips(winningChips);

        int initialChips1 = winner1.getChips();
        int initialChips2 = winner2.getChips();
        this.pot.splitPot();

        assertEquals("Winning player should have 500 more chips", initialChips1 + winningsShare, winner1.getChips());
        assertEquals("Winning player should have 500 more chips", initialChips2 + winningsShare, winner2.getChips());
    }
}
