/**
 * This program plays a simple version of Yahtzee
 * CPSC 224, Fall 2021
 * Homework 3
 * No sources to site
 * @ConnorDeide
 * @Version 3.0 10/24/2021
 */
package edu.gonzaga;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player class deals with a players hand and input from the player
 */
public class Player {
    protected ArrayList<Die> hand;
    protected Scorecard scorecard;

    //Constructor
    public Player() {
        scorecard = new Scorecard();
    }

    /**
     * Method plays a round of the game: rolls hand, gets which die the user wants to keep, re-rolls the ones the user doesnt want to keep
     * and repeats for however turns there are. Then tells user the possible score for each line, gets which one they want to choose for the round
     * and updates the users scorecard.
     */
    public void playRound(Game game) {
        String keepAll = "";
        Boolean displayedScore = false;
        hand = rollAll(game);
        //Create string with length of numDie full of y's to check if user wants to keep all
        for(int i = 0; i < game.numDie; ++i) {
            keepAll += "y";
        }
        System.out.println();
        System.out.println("Your roll was:");
        outputHand(hand);
        System.out.println();

        int turn = 2; //Turn equals two because 
        while(turn <= game.numRolls) {
            String userInput = userKeeps(displayedScore);
            if(userInput.equals(keepAll)) { //No need to re-roll if user wants to keep all
                break;
            }
            else if(userInput.equals("S") && !displayedScore) { //Can only display scorecard once per-round
                displayPlayerScoreCard(game);
                displayedScore = true;
            }
            else {
                for(int j = 0; j < userInput.length(); j++) {
                    if(userInput.charAt(j) == 'n') {
                        hand.set(j, rollOne(game));
                    }
                }
                System.out.println();
                System.out.println("Your roll was:");
                outputHand(hand);
                System.out.println();
                turn++;
            }
        }
        //Output the sorted hand
        sortHand(hand);
        System.out.println("Your final hand is:");
        outputHand(hand);
        System.out.println();
        scorecard.getPlayerScoreLine(game, hand);
        scorecard.resetScoreLines();
    }

    /**
     * Method rolls five die to fill players hand at beginning of game
     * @return returns an array list of 5 die with random sides up
     */
    private ArrayList<Die> rollAll (Game game) {
        ArrayList<Die> firstHand = new ArrayList<>();
        for(int i = 0; i < game.numDie; i++) {
            Die newDie = new Die(game.dieSides);
            newDie.roll();
            firstHand.add(newDie);
        }
        return firstHand;
    }

    /**
     * Method rolls just one die
     * @return return the newly rolled die
     */
    private Die rollOne (Game game) {
        Die newDie = new Die(game.dieSides);
        newDie.roll();
        return newDie;
    }

    /**
     * Method implements a selection sort on the given array list
     * @param hand
     */
    private void sortHand(ArrayList<Die> hand) {
        for(int i = 0; i < hand.size() - 1; i++) {
            int idx = i;
            for(int j = i + 1; j < hand.size(); j++) {
                if(hand.get(j).getSideUp() < hand.get(idx).getSideUp()) {
                    idx = j;
                }
            }
            Die tmp = hand.get(idx);
            hand.set(idx, hand.get(i));
            hand.set(i, tmp);
        }
    }

    /**
     * Method outputs the side up value of each of the die in the array list
     * @param hand
     */
    private void outputHand(ArrayList<Die> hand) {
        System.out.print("[ ");
        for(int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i).getSideUp() + " ");
        }
        System.out.print("]");
        System.out.println();
    }

    /**
     * Method displays the scorecard to the system when user inputs 'S'
     */
    public void displayPlayerScoreCard(Game game) {
        System.out.println("Line          Score");
        System.out.println("-------------------");
        int upperSum = 0;
        for(int i = 1; i <= game.dieSides; ++i) {
            System.out.println(i + "                " + scorecard.scoreUpperArray.get(i - 1));
            upperSum += scorecard.scoreUpperArray.get(i - 1);
        }
        System.out.println("-------------------");
        int bonus = 0;
        if(upperSum >= 63)
            bonus = 35;
        int upperTotal = upperSum + bonus;
        System.out.println("Sub Total        " + upperSum);
        System.out.println("Bonus            " + bonus);
        System.out.println("-------------------");
        System.out.println("Upper Total      " + upperTotal);
        System.out.println();
        System.out.println("3Kind            " + scorecard.score3Kind);
        System.out.println("4Kind            " + scorecard.score4Kind);
        System.out.println("FullHouse        " + scorecard.scoreFullHouse);
        System.out.println("SmlStrt          " + scorecard.scoreSmlStraight);
        System.out.println("LrgStrt          " + scorecard.scoreLrgStraight);
        System.out.println("Yahtzee          " + scorecard.scoreYahtzee);
        System.out.println("Chance           " + scorecard.scoreChance);
        System.out.println("-------------------");
        int lowerSum = scorecard.score3Kind + scorecard.score4Kind + scorecard.scoreFullHouse + scorecard.scoreSmlStraight + scorecard.scoreLrgStraight + scorecard.scoreYahtzee + scorecard.scoreChance;
        System.out.println("Lower Total      " + lowerSum);
        System.out.println("-------------------");
        System.out.println("Grand Total      " + (upperSum + lowerSum));
        System.out.println();
        outputHand(hand);
    }

    /**
     * Method gets user input for which dice to keep and which to re-roll
     * @return a String to be used in makeHand
     */
    public String userKeeps(Boolean displayedScore) {
        if(!displayedScore) {
            System.out.println("Enter dice you would like to keep (y or n) or 'S' to see your Scorecard:");
        }
        else {
            System.out.println("Enter dice you would like to keep (y or n)");
        }
        Scanner kb = new Scanner(System.in);
        String userInput = kb.nextLine();
        return userInput;
    }
}