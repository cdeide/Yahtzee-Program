/**
 * This program plays a simple version of Yahtzee
 * CPSC 224, Fall 2021
 * Homework 3
 * No sources to site
 * @ConnorDeide
 * @Version 3.0 10/24/2021
 */
package edu.gonzaga;

/**
*  This is the main class for the Yahtzee project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/

import java.util.Scanner;

/** Main program class for launching Yahtzee program. */
public class YahtzeeDriver {
    public static void main(String[] args) {
        String userInput = "y";
        //Game loop
        while(userInput.equals("y")) {
            Game newGame = new Game();
            System.out.println("Welcome to YAHTZEE");
            newGame.playGame(newGame);
            userInput = playAgain();
        }
    }

    /**
     * Method gets user input to see if the game should restart or end
     * @return a String to be used in play
     */
    public static String playAgain() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter 'y' to play again:");
        String userInput = kb.nextLine();
        return userInput;
    }
}