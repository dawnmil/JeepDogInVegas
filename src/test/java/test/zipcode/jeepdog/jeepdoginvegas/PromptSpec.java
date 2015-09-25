package test.zipcode.jeepdog.jeepdoginvegas;

import org.junit.Test;
import org.mockito.Mockito;
import zipcode.jeepdog.jeepdoginvegas.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Created by gfurlong on 9/24/15.
 */
public class PromptSpec {
    private Prompt prompt;
    private BufferedReader bufferedReader;
    private String[] options;

    @Test
    public void testPromptMenuOnce() {
        this.bufferedReader = Mockito.spy(new BufferedReader(new StringReader("0\n")));
        this.prompt = new Prompt(bufferedReader);

        this.options = new String[] { "Lonely message" };

        int onlyChoice = prompt.promptMenu("Choose the only message", this.options);
        assertEquals("Only call to promptMenu should return 0", 0, onlyChoice);

        try {
            verify(this.bufferedReader, times(1)).readLine();
        }
        catch(IOException e) {
            fail("An exception should not be thrown when verifying number of readLine calls to spy object.");
        }
    }

    @Test
    public void testPromptMenuThrice() {
        this.bufferedReader = Mockito.spy(new BufferedReader(new StringReader("0\n0\n0\n")));
        this.prompt = new Prompt(bufferedReader);

        this.options = new String[] {"Lonely message"};

        int firstChoice = prompt.promptMenu("Choose the only message", this.options);
        int secondChoice = prompt.promptMenu("Choose the only message", this.options);
        int thirdChoice = prompt.promptMenu("Choose the only message", this.options);

        assertEquals("First call to promptMenu should return 0", 0, firstChoice);
        assertEquals("Second call to promptMenu should return 0", 0, secondChoice);
        assertEquals("Third call to promptMenu should return 0", 0, thirdChoice);

        try {
            verify(this.bufferedReader, times(3)).readLine();
        }
        catch(IOException e) {
            fail("An exception should not be thrown when verifying number of readLine calls to spy object.");
        }
    }

    @Test
    public void testPromptMenuDifferentOptions() {
        this.bufferedReader = Mockito.spy(new BufferedReader(new StringReader("0\n1\n2\n")));
        this.prompt = new Prompt(this.bufferedReader);

        this.options = new String[] {"First message", "Second message", "Third message"};

        int firstChoice = prompt.promptMenu("Choose a message", this.options);
        int secondChoice = prompt.promptMenu("Choose a message", this.options);
        int thirdChoice = prompt.promptMenu("Choose a message", this.options);

        assertEquals("First call to promptMenu should return 0", 0, firstChoice);
        assertEquals("Second call to promptMenu should return 1", 1, secondChoice);
        assertEquals("Third call to promptMenu should return 2", 2, thirdChoice);

        try {
            verify(this.bufferedReader, times(3)).readLine();
        }
        catch(IOException e) {
            fail("An exception should not be thrown when verifying number of readLine calls to spy object.");
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPromptMenuChecksInputForNull() {
        this.bufferedReader = new BufferedReader(new StringReader(""));
        this.prompt = new Prompt(this.bufferedReader);

        prompt.promptMenu("Calling with null passed as options", null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPromptMenuChecksInputForZeroLengthOptions() {
        this.bufferedReader = new BufferedReader(new StringReader(""));
        this.prompt = new Prompt(this.bufferedReader);

        prompt.promptMenu("Calling with zero length array passed as options", new String[0]);
    }

    @Test
    public void testPromptConfirmation() {
        this.bufferedReader = Mockito.spy(new BufferedReader(new StringReader("y\nn\nn\n")));
        this.prompt = new Prompt(this.bufferedReader);

        try {
            boolean firstChoice = prompt.promptConfirmation("Choose yes/no");
            boolean secondChoice = prompt.promptConfirmation("Choose yes/no");
            boolean thirdChoice = prompt.promptConfirmation("Choose yes/no");

            assertTrue(firstChoice);
            assertFalse(secondChoice);
            assertFalse(thirdChoice);
        }
        catch(IOException e) {
            fail("An IOException should not be thrown by any of the calls to promptConfirmation");
        }
    }

    @Test(expected=NullPointerException.class)
    public void testPromptConfirmationShouldThrow() throws NullPointerException, IOException {
        this.bufferedReader = Mockito.spy(new BufferedReader(new StringReader("")));
        this.prompt = new Prompt(this.bufferedReader);

        prompt.promptConfirmation("Choose yes/no");
    }

    @Test
    public void testSystemInPrompt() {
        this.prompt = Prompt.createSystemInPrompt();

        assertTrue(this.prompt instanceof Prompt);
    }
}
