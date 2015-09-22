package zipcode.jeepdog.jeepdoginvegas;

import java.io.BufferedReader;
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
     * Choose a table based on input from the passed Scanner
     *
     * @param   br    A BufferedReader to read the decision from
     * @return        Return the table chosen or null if no tables are available
     */
    public Table selectTable(BufferedReader br) {
        int numberOfTables = this.getNumberOfTables();
        String[] tableNames = this.getTableNames();

        System.out.println("Tables:");
        if(numberOfTables > 0) {
            for(int i=0; i < tableNames.length; i++) {
                System.out.println(i + ": " + tableNames[i]);
            }
        }

        System.out.println(numberOfTables + ": none of the above");

        int selectionNum;
        do {
            System.out.println("Please enter the number of your selection:");

            try{
                selectionNum = Integer.parseInt(br.readLine());
            }
            catch(Exception e) {
                System.out.println("That is not a valid selection.");
                selectionNum = -1;
            }
        } while(selectionNum < 0 || selectionNum > numberOfTables);

        return selectionNum < numberOfTables ? this.tables.get(selectionNum) : null;
    }

    /**
     * Read the first character from scanner to determine if the user is ready to quit
     *
     * @param   br      A BufferedReader to read the input from
     * @return          A boolean indicating if the user is ready to leave
     */
    public boolean isReadyToQuit(BufferedReader br) {
        System.out.println("Are you ready to leave the casino?");
        try {
            String response = br.readLine();

            return response.length() > 0 && (response.charAt(0) == 'y' || response.charAt(0) == 'Y');
        }
        catch(Exception e) {
            return false;
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
                new BlackJackTable(),
                new PokerTable()
        };

        Casino casino = new Casino(tables);

        Table table;
        Boolean leaveCasino;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            table = casino.selectTable(br);
            if(table != null) {
                table.play();
            }

            leaveCasino = casino.isReadyToQuit(br);
        }while(! leaveCasino);
    }
}