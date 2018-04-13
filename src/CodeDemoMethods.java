/**
 *  Contains the methods demonstrated via CodeDemoGUI.
 *
 *  @author   Robert Dobson
 *  @version  1.0
 */

import java.io.*;
import java.util.Random;

public class CodeDemoMethods {

    // Variables for branchingDemoMethod
    private static int ticketPrice = 15;

    // Variables for loopDemoMethod
    private static int halfDollars = 0;
    private static int quarters = 0;
    private static int dimes = 0;
    private static int nickels = 0;

    // Variables for arrayDemoMethod
    private static int readRandom;
    private static int randomNum;
    private static int[] userArray;
    private static int i = 0;
    private static String arrayResultOutput;

    // Variables for fileIODemoMethod
    private static String inputFile = "names.txt";
    private static String outputFile = "output.txt";
    private static String textSegment;

    // Variable for guessNumber method
    private static int numberOfGuesses = 0;

    /* Variable used by arrayDemoMethod, fileIODemoMethod, and recursionDemoMethod to
       build a string that gets displayed as a final output to JTextArea of CodeDemoGUI */
    private static String temp = "";

    public static void branchingDemoMethod(int userEntry) {

        if (userEntry < 18) {
            CodeDemoGUI.showResult.setText("You're not old enough to see this movie.");
        }
        else if (userEntry < 65) {
            CodeDemoGUI.showResult.setText("The ticket price is $" + ticketPrice);
        }
        else {
            int seniorPrice = ticketPrice - 10;
            CodeDemoGUI.showResult.setText("The ticket price is $" + seniorPrice);
        }
    }

    public static void loopDemoMethod(int userEntry) {

        if (userEntry == 0) userEntry = 1;

        while(userEntry > 49) {
            userEntry -= 50;
            ++halfDollars;
        }

        while(userEntry > 24) {
            userEntry -= 25;
            ++quarters;
        }

        while(userEntry > 9) {
            userEntry -= 10;
            ++dimes;
        }

        while(userEntry > 4) {
            userEntry -= 5;
            ++nickels;
        }
        CodeDemoGUI.showResult.setText("Your change is " + halfDollars + " half dollar(s), " +
                quarters + " quarter(s), " + dimes + " dime(s), " + nickels + " nickel(s), and " +
                userEntry  + " penn(y/ies)!");

        // Reset global variables
        halfDollars = 0;
        quarters = 0;
        dimes = 0;
        nickels = 0;
    }

    public static void arrayDemoMethod(int userEntry) {

        if (userEntry == 0) userEntry = 1;

        readRandom = userEntry;

        for(userArray = new int[userEntry]; userEntry > 0; ++i) {
            Random randGen = new Random();
            randomNum = randGen.nextInt(100) + 1;
            userArray[i] = randomNum;
            --userEntry;
        }

        temp = "This array's elements and random values are:\n\n";

        for(i = 0; i < readRandom; ++i) {
            temp = temp + "userArray[" + i + "] contains value: " + userArray[i] + "\n";
        }
        arrayResultOutput = temp;

        CodeDemoGUI.showResult.setText(arrayResultOutput);

        //Reset array and counter
        userArray = null;
        i = 0;
    }

    public static void fileIODemoMethod() {

        try {
            BufferedReader readBuffer = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(outputFile));

            temp = "\nList of names written to the file \'output.txt\':\n\n";

            while((textSegment = readBuffer.readLine()) != null) {
                temp = temp + textSegment + "\n";
                writeBuffer.write(textSegment);
                writeBuffer.newLine();
            }

            CodeDemoGUI.showResult.setText(temp);

            readBuffer.close();
            writeBuffer.close();
        }
        catch (FileNotFoundException unableToOpen) {
            CodeDemoGUI.showResult.setText("Unable to open file '" + inputFile + "'");
        }
        catch (IOException errorReading) {
            CodeDemoGUI.showResult.setText("Error reading file '" + inputFile + "'");
        }

    }

    public static void recursionDemoMethod(int userEntry) {

        // Reset global variables
        temp = "";
        numberOfGuesses = 0;

        // Call recursive function to guess number
        guessNumber(0, 100, userEntry);
    }

    private static void guessNumber(int lowValue, int highValue, int mysteryNumber) {

        int middleValue; // Midpoint between lowValue/highValue

        middleValue = (highValue + lowValue) / 2;

        numberOfGuesses = ++numberOfGuesses;

        temp = temp + "Guess number " + numberOfGuesses + ":" + " Checking if user's number" +
                " is lower," + " higher, or equal to " + middleValue + "...\n";


        if (mysteryNumber == middleValue) { // Base case: found number
            CodeDemoGUI.showResult.setText(temp + "\nIt took the computer " + numberOfGuesses + " attempts to guess" +
                    " the user's number," + " which is " + mysteryNumber + "\n");
        }
        else {       // Recursive case: split into lower OR upper half
            if (mysteryNumber < middleValue) {  // Guess in lower half
                guessNumber(lowValue, middleValue, mysteryNumber);               // Recursive call
            }
            else {                              // Guess in upper half
                guessNumber(middleValue + 1, highValue, mysteryNumber); // Recursive call
            }
        }
    }
}
