package zipcode.jeepdog.jeepdoginvegas;

/**
 * Scoreable interface
 *
 * Declares methods used to rate hands, dice, or other
 * devices used in gambling.
 *
 * @author Gregory Furlong
 */
public interface Scoreable {
    /**
     * Get player
     *
     * @return The player who owns this scoreable
     */
    public Player getPlayer();

    /**
     * Get score
     *
     * @return The last calculated score of this scoreable
     */
    public int getScore();

    /**
     * Set score
     *
     * @param score The score to set this scoreable to
     */
    public void setScore(int score);

    /**
     * Get relative score
     *
     * Gets a score between 0 and 100 based on the default score of this scoreable.
     * The common score should not simply be a scaled version of the raw score, but should
     * instead be calculated in such a way to reflect the relative strength of the raw
     * score.
     *
     * @return Returns a score reflecting the relative strength of the base score
     */
    public int getRelativeScore();
}
