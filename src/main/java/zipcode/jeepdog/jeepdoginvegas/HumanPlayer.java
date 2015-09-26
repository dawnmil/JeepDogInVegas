package zipcode.jeepdog.jeepdoginvegas;

/**
 * HumanPlayer
 *
 * This class represents the user of the program.  It stores there chips
 * and can prompt the user to request a bet or to choose other game related
 * options.
 *
 * @author Gregory Furlong
 */
public class HumanPlayer extends Player {
    private Prompt prompt;

    public HumanPlayer() {
        super("JeepDog");

        this.prompt = Prompt.createSystemInPrompt();
    }

    public HumanPlayer(Prompt prompt) {
        super("JeepDog");

        this.prompt = prompt;
    }

    /**
     * Is this player human?
     * @return Return true if this player is human
     */
    public boolean isHuman() {
        return true;
    }

    /**
     * Request a bet amount from the player
     *
     * @return The requested bet
     */
    @Override
    public int requestBet() {
        System.out.println("How much would you like to bet?");

        int bet = this.prompt.promptInteger(0, this.getChips());

        this.removeChips(bet);

        return bet;
    }
}
