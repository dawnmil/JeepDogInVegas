package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by gfurlong on 9/22/15.
 */
public class CasinoSpec {
    private Casino casino;
    private Table table;
    private Prompt prompt;

    @Before
    public void before() {
        casino = new Casino();

        table = mock(Table.class);
        doReturn("Table").when(table).getName();

        this.prompt = mock(Prompt.class);
    }

    @Test
    public void testEmptyConstructor() {
        assertEquals("casino starts without any tables for empty constructor", 0, casino.getNumberOfTables());
    }

    @Test
    public void testTablesConstructor() {
        casino = new Casino(new Table[] { table, table});
        assertEquals("casino have a constructor that can take an array of tables and properly set them", 2, casino.getNumberOfTables());
    }

    @Test
    public void testGetHumanPlayer() {
        casino = new Casino(new Table[] { table, table });
        assertNotNull("getHumanPlayer gets the Casino's humanPlayer", casino.getHumanPlayer());
    }

    @Test
    public void testCanGetNumberOfTables() {
        assertEquals("casino starts without any tables",0,casino.getNumberOfTables());
    }

    @Test
    public void testCanAddTable() {
        int initialTables = casino.getNumberOfTables();
        casino.addTable(new Table());
        assertEquals("casino can add a table", initialTables + 1, casino.getNumberOfTables());
    }

    @Test
    public void testCanAddAnotherTable() {
        int initialTables = casino.getNumberOfTables();
        casino.addTable(new Table());
        casino.addTable(new Table());
        assertEquals("casino can add more than one table", initialTables + 2, casino.getNumberOfTables());
    }

    @Test
    public void testGetTableNamesNoTables() {
        assertNull("Get table names should return null when there are no tables", casino.getTableNames());
    }

    @Test
    public void testGetTableNamesOneTable() {
        casino.addTable(table);
        assertArrayEquals("Get table names should return a single name when there is one table", new String[]{table.getName()}, casino.getTableNames());
    }

    @Test
    public void testGetTableNamesTwoTables() {
        casino.addTable(table);
        casino.addTable(table);
        assertArrayEquals("Get table names should return a single name when there is one table", new String[]{table.getName(), table.getName()}, casino.getTableNames());
    }

    @Test
    public void testSelectTableNoTables() {
        doReturn(0).when(this.prompt).promptMenu(anyString(), any(String[].class));
        assertNull("selectTable should return null with no tables", casino.selectTable(this.prompt));
    }

    @Test
    public void testSelectTableTwoTables() {
        Table otherTable = new Table();

        casino.addTable(table);
        casino.addTable(otherTable);

        doReturn(0).when(this.prompt).promptMenu(anyString(), any(String[].class));
        assertSame("selectTable should choose table 0", table, casino.selectTable(this.prompt));

        doReturn(2).when(this.prompt).promptMenu(anyString(), any(String[].class));
        assertNull("selectTable should return null if 2 is entered", casino.selectTable(this.prompt));
    }

    @Test
    public void testIsReadyToQuit() {
        try {
            doReturn(true).when(this.prompt).promptConfirmation(anyString());
            assertTrue("isReadyToQuit should return true if prompt returns true", casino.isReadyToQuit(this.prompt));

            doReturn(false).when(this.prompt).promptConfirmation(anyString());
            assertFalse("isReadyToQuit should return true if prompt returns false", casino.isReadyToQuit(this.prompt));

            doThrow(new IOException("Should not be thrown")).when(this.prompt).promptConfirmation(anyString());
            assertTrue("isReadyToQuit should return true if prompt throws an exception", casino.isReadyToQuit(this.prompt));
        }
        catch(IOException e) {
            fail("Exception should not be thrown within isReadyToQuit");
        }
    }
}