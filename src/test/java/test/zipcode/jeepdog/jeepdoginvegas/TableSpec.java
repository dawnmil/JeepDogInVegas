package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

/**
 * Created by gfurlong on 9/24/15.
 */
public class TableSpec {
    private Table table;
    private Player bob = new Player("Bob");
    private Player sarah = new Player("Sarah");
    private Prompt prompt;

    @Before
    public void before() {
        this.table = new Table();
        this.bob = new Player("Bob");
        this.sarah = new Player("Sarah");

        this.prompt = mock(Prompt.class);
    }

    @Test
    public void testConstructor() {
        assertNotNull("Default constructor should create a table object.", this.table);
    }

    @Test
    public void testGetCasinoShouldInitiallyReturnNull() {
        assertNull("Get casino should initally return null", this.table.getCasino());
    }

    @Test
    public void testSetCasino() {
        Casino casino = new Casino();
        this.table.setCasino(casino);
        assertSame("Get casino should return a reference to the same casino set with setCasino", casino, this.table.getCasino());
    }

    @Test
    public void testGetNumberOfPlayersShouldInitiallyBeZero() {
        assertEquals("Table should initially have zero players", 0, this.table.getNumberOfOpponents());
    }

    @Test
    public void testAddPlayerShouldIncreaseTheNumberOfPlayers() {
        int initialPlayers = this.table.getNumberOfOpponents();
        this.table.addPlayer(bob);
        assertEquals("Adding a player should increase the number of players by 1", initialPlayers + 1, this.table.getNumberOfOpponents());
        this.table.addPlayer(sarah);
        assertEquals("Adding a player should increase the number of players by 1", initialPlayers + 2, this.table.getNumberOfOpponents());
        this.table.addPlayer(sarah);
        assertEquals("You should not be able to add the same player more than once", initialPlayers + 2, this.table.getNumberOfOpponents());
    }

    @Test
    public void testRemovePlayer() {
        this.table.addPlayer(bob);
        this.table.addPlayer(sarah);

        int initialPlayers = this.table.getNumberOfOpponents();
        Player removed = this.table.removePlayer(0);
        assertEquals("removePlayer should decrease the size of players collection by 1", initialPlayers - 1, this.table.getNumberOfOpponents());
        assertSame("removePlayer should return a reference to bob", bob, removed);
    }

    @Test
    public void testRemovePlayerShouldReturnNullOnInvalidIndices() {
        this.table.addPlayer(bob);
        this.table.addPlayer(sarah);

        int initialPlayers = this.table.getNumberOfOpponents();
        Player removed = this.table.removePlayer(-1);

        assertEquals("removePlayer should not change the size of the collection for negative indices", initialPlayers, this.table.getNumberOfOpponents());
        assertNull("Should return null for negative indices", removed);

        removed = this.table.removePlayer(initialPlayers + 1);
        assertEquals("removePlayer should not change the size of the collection for indices greater than size of array", initialPlayers, this.table.getNumberOfOpponents());
        assertNull("Should return null for indices large than collection size", removed);
    }

    @Test
    public void testIsReadyToLeave() {
        try {
            doReturn(true).when(this.prompt).promptConfirmation(anyString());
            assertTrue("isReadyToLeave should return true if prompt returns true", this.table.isReadyToLeave(this.prompt));

            doReturn(false).when(this.prompt).promptConfirmation(anyString());
            assertFalse("isReadyToLeave should return true if prompt returns false", this.table.isReadyToLeave(this.prompt));

            doThrow(new IOException("Should not be thrown")).when(this.prompt).promptConfirmation(anyString());
            assertTrue("isReadyToLeave should return true if prompt throws an exception", this.table.isReadyToLeave(this.prompt));
        }
        catch(IOException e) {
            fail("Exception should not be thrown within isReadyToLeave");
        }
    }

    @Test
    public void testPlayLoopsOnce() {
        BufferedReader mockReader = Mockito.spy(new BufferedReader(new StringReader("y\n")));
        this.prompt = new Prompt(mockReader);
        table.play(prompt);

        try {
            verify(mockReader, times(1)).readLine();
        }
        catch(IOException e) {
            fail("IOException should not be thrown within play.");
        }
    }

    @Test
    public void testPlayLoopsThrice() {
        BufferedReader mockReader = Mockito.spy(new BufferedReader(new StringReader("n\nn\ny\n")));
        this.prompt = new Prompt(mockReader);
        table.play(prompt);

        try{
            verify(mockReader, times(3)).readLine();
        }
        catch(IOException e) {
            fail("IOException should not be thrown within play.");
        }
    }
}
