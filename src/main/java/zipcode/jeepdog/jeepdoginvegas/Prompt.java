package zipcode.jeepdog.jeepdoginvegas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.IllegalArgumentException;

/**
 * Created by gfurlong on 9/24/15.
 */
public class Prompt {
    BufferedReader bufferedReader;

    public Prompt(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Prompt the user to select an option from the messages passed to it
     *
     * @param message The message to display
     * @param options The array of options to display
     * @return Index from options chosen
     */
    public int promptMenu(String message, String[] options) throws IllegalArgumentException {
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("The argument 'options' should not be null or a zero length array.");
        }

        System.out.println(message + ":");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + ": " + options[i]);
        }

        int selectionNum;
        do {
            System.out.println("\nPlease enter the number of your selection:");

            try {
                selectionNum = Integer.parseInt(this.bufferedReader.readLine());
            } catch (Exception e) {
                System.out.println("That is not a valid selection.");
                selectionNum = -1;
            }
        } while (selectionNum < 0 || selectionNum >= options.length);

        return selectionNum;
    }

    /**
     * Prompt the user with a yes/no question
     *
     * Defaults to true if an option other than no is selected
     * @param message The message to display before requesting user input
     * @return Return a boolean indicating the users response
     */
    public boolean promptConfirmation(String message) throws IOException {
            System.out.println(message);

            System.out.println("Please select (Y/n):");
            String response = this.bufferedReader.readLine();

            // return true unless some variation of no was chosen
            return response.length() > 0 && !(response.charAt(0) == 'n' || response.charAt(0) == 'N');
    }
}