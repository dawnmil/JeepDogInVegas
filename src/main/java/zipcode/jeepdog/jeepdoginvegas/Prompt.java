package zipcode.jeepdog.jeepdoginvegas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.IllegalArgumentException;

/**
 * Prompt class
 *
 * Includes methods to simplify prompting the user for certain types of input, including
 * choosing a selection from a list of strings, choosing an integer within a specified range
 * and answer yes/no qeuestions.
 *
 * @author Gregory Furlong
 */
public class Prompt {
    BufferedReader bufferedReader;

    public Prompt(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Create a new prompt using System.in as the input source
     * @return A new Prompt that takes input from System.in
     */
    public static Prompt createSystemInPrompt() {
        return new Prompt(new BufferedReader(new InputStreamReader(System.in)));
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

        return this.promptInteger(0, options.length - 1);
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

    /**
     * Prompt the user for an integer between the specified min and max
     *
     * @param min   The minimum allowed input
     * @param max   The maximum allowed input
     * @return      The chosen integer
     */
    public int promptInteger(int min, int max) {
        int selectionNum = 0;
        boolean loop = true;
        do {
            loop = false;
            System.out.println("\nPlease select a number (" + min + "-" + max + "):");

            try {
                selectionNum = Integer.parseInt(this.bufferedReader.readLine());
            } catch (Exception e) {
                loop = true;
                System.out.println("That is not a valid selection.");
            }
        } while (loop || selectionNum < min || selectionNum > max);

        return selectionNum;
    }
}