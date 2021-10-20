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
        ArrayList<Die> hand;
        hand = rollAll(game);
        for(int turn = 1; turn <= game.numRolls; turn++) {
            for(int j = 0; j < userInput.length(); j++) {
                if(userInput.charAt(j) == 'n') {
                    hand.set(j, rollOne(game));
                }
            }
            if(turn < game.numRolls) {
                System.out.println("Your roll was:");
                outputHand(hand);
                userInput = userKeeps();
            }
        }
        // Output the sorted hand
        sortHand(hand);
        System.out.println("Your final hand is:");
        outputHand(hand);
        // Assign the hand to the player
        this.hand = hand;
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
        for(int i = 0; i < hand.size(); i++) {
            System.out.print(hand.get(i).getSideUp() + " ");
        }
        System.out.println();
    }

    /**
     * Method calls all methods from ScoreCard class to output the scores
     */
    public void getScoreCard(Game game) {
        Scorecard scoreCard = new Scorecard();
        scoreCard.scoreUpperSection(game, hand);
        scoreCard.scoreNofAKind(game, hand);
        scoreCard.scoreFullHouse(game, hand);
        scoreCard.scoreSmlStraight(game, hand);
        scoreCard.scoreLrgStraight(game, hand);
        scoreCard.scoreYahtzee(game, hand);
        scoreCard.scoreChance(game, hand);
    }

    /**
     * Method gets user input for which dice to keep and which to re-roll
     * @return a String to be used in makeHand
     */
    public String userKeeps() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter dice you would like to keep (y or n):");
        String userInput = kb.nextLine();
        return userInput;
    }
}