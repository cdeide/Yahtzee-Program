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
import java.util.Collections;
import java.util.Scanner;

public class Scorecard {
    //Member vars for each score line
    protected ArrayList<Integer> scoreUpperArray;
    protected int scoreLineOne = 0;
    protected int scoreLineTwo = 0;
    protected int scoreLineThree = 0;
    protected int scoreLineFour = 0;
    protected int scoreLineFive = 0;
    protected int scoreLineSix = 0;
    protected int score3Kind = 0;
    protected int score4Kind = 0;
    protected int scoreFullHouse = 0;
    protected int scoreSmlStraight = 0;
    protected int scoreLrgStraight = 0;
    protected int scoreYahtzee = 0;
    protected int scoreChance = 0;


     /**
     * Method calls all methods from ScoreCard class to set score lines
     */
    public void setScoreCard(Game game, Player player) {
        Scorecard scoreCard = new Scorecard();
        scoreCard.scoreUpperSection(game, player.hand);
        scoreCard.scoreNofAKind(game, player.hand);
        scoreCard.scoreFullHouse(game, player.hand);
        scoreCard.scoreSmlStraight(game, player.hand);
        scoreCard.scoreLrgStraight(game, player.hand);
        scoreCard.scoreYahtzee(game, player.hand);
        scoreCard.scoreChance(game, player.hand);
        player.scorecard = scoreCard; //Set players scorecard
    }

    /**
     * Method displays the possible scores for the round that the user can choose from.
     * @param game
     * @param hand
     */
    public void displayPossibleScores(Game game, ArrayList<Die> hand) {
        
    }

    /**
     * Method gets the code for the line the user wants to input to the scorecard for the round
     * @return
     */
    public String getScoreCode() {
        Scanner kb = new Scanner(System.in);
        System.out.println("What scoring line do you want for this round (Input code found on scorecard)?");
        String input = kb.nextLine();
        kb.close();
        return input;
    }

    /**
     * Method scores the first six lines of scoring in Yahtzee (or the upper section)
     * @param hand
     */
    public void scoreUpperSection(Game game, ArrayList<Die> hand) {
        int count;
        scoreUpperArray = new ArrayList<Integer>(Collections.nCopies(game.dieSides, 0));
        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(hand.get(j).getSideUp() == i) {
                    count++;
                }
            }
            scoreUpperArray.add(i * count);
        }
    }

    /**
     * Method scores the two NofAKind score lines in Yahtzee (3 and 4 of a kind)
     * @param hand
     */
    public void scoreNofAKind(Game game, ArrayList<Die> hand) {
        int count = 0;
        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(hand.get(j).getSideUp() == i) {
                    count += 1;
                }
            }
            if(count == 4) { //check for 4 of a kind first
                for(int k = 0; k < game.numDie; k++) {
                    score4Kind += hand.get(k).getSideUp();
                }
            }
            else if(count == 3) { //check for 3 of a kind
                for(int k = 0; k < game.numDie; k++) {
                    score3Kind += hand.get(k).getSideUp();
                }
            }
        }
    }

    /**
     * Method scores the Full House line of Yahtzee
     * @param hand
     */
    public void scoreFullHouse(Game game, ArrayList<Die> hand) {
        int count;
        boolean TwoOfAKind = false;
        boolean ThreeOfAKind = false;

        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(hand.get(j).getSideUp() == i) {
                    count += 1;
                }
            }
            if(count == 2) {
                TwoOfAKind = true;
            }
            if(count == 3) {
                ThreeOfAKind = true;
            }
        }
        if(TwoOfAKind && ThreeOfAKind) {
            scoreFullHouse = 25;
        }
    }

    /**
     * Method scores the Small Straight line of Yahtzee
     * @param hand
     */
    public void scoreSmlStraight(Game game, ArrayList<Die> hand) {
        int count = 1;
        for(int i = 0; i < game.numDie - 1; i++) {
            if(hand.get(i).getSideUp() + 1 == hand.get(i + 1).getSideUp()) {
                count += 1;
            }
            else if(hand.get(i).getSideUp() + 1 < hand.get(i + 1).getSideUp()) {
                count = 1; // Reset count if straight is broken
            }
        }
        if(count == 4) {
            scoreSmlStraight = 30;
        }
    }

    /**
     * Method scores the Large Straight line of Yahtzee
     * @param hand
     */
    public void scoreLrgStraight(Game game, ArrayList<Die> hand) {
        int count = 1;
        for(int i = 0; i < game.numDie - 1; i++) {
            if(hand.get(i).getSideUp() + 1 == hand.get(i + 1).getSideUp()) {
                count += 1;
            }
            else if(hand.get(i).getSideUp() + 1 < hand.get(i + 1).getSideUp()){
                count = 1; // Reset count if straight is broken
            }
        }
        if(count == 5) {
            scoreLrgStraight = 40;
        }
    }

    /**
     * Method scores the Yahtzee line of Yahtzee
     * @param hand
     */
    public void scoreYahtzee(Game game, ArrayList<Die> hand) {
        int count = 0;
        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(hand.get(j).getSideUp() == i) {
                    count += 1;
                }
            }
            if(count == 5) {
                scoreYahtzee = 50;
                break;
            }
        }
    }

    /**
     * Method scores the Chance line of Yahtzee
     * @param hand
     */
    public void scoreChance(Game game, ArrayList<Die> hand) {
        for(int i = 0; i < game.numDie;  i++) {
            scoreChance += hand.get(i).getSideUp();
        }
    }
}
