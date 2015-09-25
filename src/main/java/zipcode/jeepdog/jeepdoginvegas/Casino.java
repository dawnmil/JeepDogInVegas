package zipcode.jeepdog.jeepdoginvegas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Casino {
    private ArrayList<Table> tables;
    private HumanPlayer humanPlayer;

    /**
     * Default constructor
     */
    public Casino() {
        this.tables = new ArrayList<Table>();
        this.humanPlayer = new HumanPlayer();
    }

    /**
     * Table constructor
     * @param tables        an array of game tables
     */
    public Casino(Table[] tables) {
        this.tables = new ArrayList<Table>(Arrays.asList(tables));
        this.humanPlayer = new HumanPlayer();
    }

    /**
     * Get the number of tables currently in this casino
     *
     * @return  the number of current tables
     */
    public int getNumberOfTables() {
        return tables.size();
    }

    /**
     * Get humanPlayer
     * @return this Casino instances humanPlayer
     */
    public HumanPlayer getHumanPlayer() {
        return this.humanPlayer;
    }

    /**
     * Prompt the user to request a table
     *
     * @param   prompt    A prompt used to request input from the user
     * @return            Return the table chosen or null if no tables are available
     */
    public Table selectTable(Prompt prompt) {
        String[] tableNames = this.getTableNames();
        String[] options;

        if(this.getTableNames() == null) {
            options = new String[1];
        }
        else {
            options = new String[tableNames.length + 1];
            System.arraycopy(tableNames, 0, options, 0, tableNames.length);
        }

        options[options.length -1] = "None of the above";

        int selectionNum = prompt.promptMenu("Please choose a table", options);

        return selectionNum < this.getNumberOfTables() ? this.tables.get(selectionNum) : null;
    }

    /**
     * Ask the user if they are ready to leave
     *
     * @param   prompt  A prompt used to request input
     * @return          A boolean indicating the users response
     */
    public boolean isReadyToQuit(Prompt prompt) {
        try {
            return prompt.promptConfirmation("Are you ready to leave the casino?");
        }
        catch(IOException e) {
            return true;
        }
    }

    /**
     * Add a table to this casino
     * @param table     the table to add
     */
    public void addTable(Table table) {
        this.tables.add(table);
    }

    /**
     * Get an array of the table's names in this casino
     *
     * @return An array of strings corresponding to each table's name
     */
    public String[] getTableNames() {
        String[] tableNames = null;
        int numberOfTables = this.getNumberOfTables();
        if(numberOfTables > 0) {
            tableNames = new String[numberOfTables];
            for(int i=0; i < numberOfTables; i++) {
                tableNames[i] = tables.get(i).getName();
            }
        }

        return tableNames;
    }

    /**
     * Main entry point for the Casino
     *
     * @param args An array of arguments passed in when starting the program
     */
    public static void main(String[] args) {
        Table[] tables = new Table[] {
                new Table()
        };

        Casino casino = new Casino(tables);

        Table table;
        Boolean leaveCasino;
        Prompt prompt = Prompt.createSystemInPrompt();
        do{
            table = casino.selectTable(prompt);
            if(table != null) {
                table.play(prompt);
            }

            leaveCasino = casino.isReadyToQuit(prompt);
        }while(! leaveCasino);
    }
}