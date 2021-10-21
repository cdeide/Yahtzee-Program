/**
 * This program plays a simple version of Yahtzee
 * CPSC 224, Fall 2021
 * Homework 2
 * No sources to site
 * @ConnorDeide
 * @Version 2.0 10/3/2021
 */
package edu.gonzaga;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Player class deals with a players hand and input from the player
 */
public class Player {
    protected ArrayList<Die> hand;
    protected Scorecard scorecard = new Scorecard();

    /**
     * Method returns the value of hand, a field of class Player
     * @return ArrayList of type die called hand
     */
    public ArrayList<Die> getHand() {
        return hand;
    }

    /**
     * Method rolls a hand for the user than gets their input for re-rolls until user is out of turns.
     * Then sorts their hand and assigns it to the class member variable hand
     */
    public void makeHand(Game game) {
        String userInput = " ";
        Boolean displayedScore = false;
        //ArrayList<Die> hand;
        hand = rollAll(game);
        scorecard.setScoreCard(game, this);
        for(int turn = 1; turn <= game.numRolls; turn++) {
            if(userInput.equals("S") && !displayedScore) {
                displayPlayerScoreCard(game);
                displayedScore = true;
            }
            else {
                for(int j = 0; j < userInput.length(); j++) {
                    if(userInput.charAt(j) == 'n') {
                        hand.set(j, rollOne(game));
                    }
                }
            }
            if(turn < game.numRolls) {
                System.out.println("Your roll was:");
                outputHand(hand);
                scorecard.setScoreCard(game, this);
                userInput = userKeeps(displayedScore);
            }
        }
        // Output the sorted hand
        sortHand(hand);
        System.out.println("Your final hand is:");
        outputHand(hand);
        // Assign the hand to the player
        //this.hand = hand;
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
        System.out.println("Line      Score");
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
    }

    /**
     * Method gets user input for which dice to keep and which to re-roll
     * @return a String to be used in makeHand
     */
    public String userKeeps(Boolean displayedScore) {
        Scanner kb = new Scanner(System.in);
        if(!displayedScore) {
            System.out.println("Enter dice you would like to keep (y or n) or 'S' to see your Scorecard:");
        }
        else {
            System.out.println("Enter dice you would like to keep (y or n)");
        }
        String userInput = kb.nextLine();
        kb.close();
        return userInput;
    }
}