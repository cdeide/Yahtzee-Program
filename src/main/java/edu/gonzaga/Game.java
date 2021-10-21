/**
 * This program plays a simple version of Yahtzee
 * CPSC 224, Fall 2021
 * Homework 2
 * No sources to site
 * @ConnorDeide
 * @Version 2.0 10/3/2021
 */
package edu.gonzaga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Class used for overall game flow as well as reading, writing, and outputting game configuration to/from file
 */
public class Game {
    protected int dieSides;
    protected int numDie;
    protected int numRolls;

    /**
     * Method deals with the general game flow of one game
     * @param game
     */
    public void playGame(Game game) {
        Player player = new Player();
        game.getGameConfig(); //Read original configuration from file
        outputGameConfig();
        System.out.println("If you would like to play with a different configuration enter 'y':");
        String input = getInput();
        if(input.equals("y")) {
            game.setGameConfig(); //Write new configuration to file
            game.getGameConfig(); //Read new configuration from file
            outputGameConfig();
        }
        // make and get the players hand
        player.makeHand(game);
        player.hand = player.getHand();
        //Output the scores given the players hand
        System.out.println();
        player.scorecard.setScoreCard(game, player);
        player.displayPlayerScoreCard(game);
        System.out.println();
    }

    /**
     * Method gets input from user and writes it to file
     */
    public void setGameConfig() {
        try {
            //Write user's input to yahtzeeConfig.txt
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("./yahtzeeConfig.txt"));
            System.out.println("Enter the number of sides on each die:");
            String dieSides = getInput();
            bw.write(dieSides + "\n");
            System.out.println("Enter the number of dice in play:");
            String numDie = getInput();
            bw.write(numDie + "\n");
            System.out.println("Enter the number of rolls per hand:");
            String numRolls = getInput();
            bw.write(numRolls + "\n");
            bw.close();
        }
        catch(Exception ex) {
            System.out.println("Error writing to yahtzeeConfig.txt");
            return;
        }
    }

    /**
     * Method reads configuration values from file and initializes game fields
     */
    public void getGameConfig() {
        //Read values from yahtzeeConfig.txt
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("./yahtzeeConfig.txt"));
            String string;
            for(int lineNum = 1; lineNum <= 3; lineNum++) {
                if(lineNum == 1) {
                    string = br.readLine();
                    dieSides = Integer.parseInt(string);
                }
                if(lineNum == 2) {
                    string = br.readLine();
                    numDie = Integer.parseInt(string);
                }
                if(lineNum == 3) {
                    string = br.readLine();
                    numRolls = Integer.parseInt(string);
                }
            }
            br.close();
        }
        catch(Exception ex) {
            System.out.println("Error reading from yahtzeeConfig.txt");
            return;
        }
    }

    /**
     * Method outputs the game configuration
     */
    public void outputGameConfig() {
        System.out.println("Game configuration is set for " + numDie + " " + dieSides + "-sided dice.");
        System.out.println("There are " + numRolls + " rolls per-hand.");
    }

    /**
     * Method gets input from the user
     * @return
     */
    public static String getInput() {
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();
        kb.close();
        return input;
    }
}
