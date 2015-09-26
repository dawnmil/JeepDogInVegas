package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

/**
 * Class specificationa nd test cases for the HumanPlayer class
 *
 * @author Gregory Furlong
 */
public class HumanPlayerSpec {
    private HumanPlayer humanPlayer;
    private Prompt prompt;

    @Before
    public void before() {
        this.prompt = mock(Prompt.class);
        this.humanPlayer = new HumanPlayer(prompt);
    }

    @Test
    public void testRequestBet() {
        this.humanPlayer.addChips(10);
        int initialChips = this.humanPlayer.getChips();
        doReturn(10).when(prompt).promptInteger(0, 10);
        int bet = this.humanPlayer.requestBet();
        assertEquals("requestBet should call prompt.promptInteger to determine how much to return", 10, bet);
        assertEquals("requestBet should decrease chips by amount of bet", initialChips - bet, this.humanPlayer.getChips());
    }
}
