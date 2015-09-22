package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import zipcode.jeepdog.jeepdoginvegas.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by gfurlong on 9/22/15.
 */
public class CasinoSpec {
    private Casino casino;
    private Table table;

    @Before
    public void before() {
        casino = new Casino();

        table = mock(Table.class);
        doReturn("Table").when(table).getName();
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
        assertEquals("Get table names should return a single name when there is one table", new String[]{table.getName()}, casino.getTableNames());
    }

    @Test
    public void testGetTableNamesTwoTables() {
        casino.addTable(table);
        casino.addTable(table);
        assertEquals("Get table names should return a single name when there is one table", new String[]{table.getName(), table.getName()}, casino.getTableNames());
    }

    @Test
    public void testSelectTable() {
        casino.addTable(table);
        casino.addTable(table);
        assertEquals("Get table names should return a single name when there is one table", new String[]{table.getName(), table.getName()}, casino.getTableNames());
    }

    @Test
    public void testSelectTableNoTables() {
        casino = new Casino();
        assertEquals("selectTable should return null with no tables",null,casino.selectTable(new BufferedReader(new StringReader("0"))));
    }

    @Test
    public void testSelectTableTwoTables() {
        Table otherTable = new Table();
        casino = new Casino(new Table[]{ table, otherTable});
        assertSame("selectTable should choose table 0", table, casino.selectTable(new BufferedReader(new StringReader("0"))));
        assertSame("selectTable should choose table 1", otherTable, casino.selectTable(new BufferedReader(new StringReader("1"))));
        assertSame("selectTable should choose table 0 after initially failing", table, casino.selectTable(new BufferedReader(new StringReader("a\n0"))));
        assertNull("selectTable should return null if 2 is entered", casino.selectTable(new BufferedReader(new StringReader("2"))));
    }

    @Test
    public void testIsReadyToQuit() {
        assertTrue("isReadyToQuit should return true 'Yes'", casino.isReadyToQuit(new BufferedReader(new StringReader("Yes\n"))));
        assertTrue("isReadyToQuit should return true from 'yes'", casino.isReadyToQuit(new BufferedReader(new StringReader("y\n"))));
        assertFalse("isReadyToQuit should return false from 'no'", casino.isReadyToQuit(new BufferedReader(new StringReader("no\n"))));
        assertFalse("isReadyToQuit should return false from 'No'", casino.isReadyToQuit(new BufferedReader(new StringReader("N\n"))));
        assertFalse("isReadyToQuit should return false", casino.isReadyToQuit(new BufferedReader(new StringReader("\n"))));
    }
}