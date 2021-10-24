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

public class Scorecard {
    //Member vars for each score line
    protected ArrayList<Boolean> checkScoreLines;
    protected ArrayList<Integer> scoreUpperArray;
    protected int score3Kind = 0;
    protected int score4Kind = 0;
    protected int scoreFullHouse = 0;
    protected int scoreSmlStraight = 0;
    protected int scoreLrgStraight = 0;
    protected int scoreYahtzee = 0;
    protected int scoreChance = 0;

    /**
     * Method sets the scorecard by adding 'dieSide' elements with a value of zero to the ArrayList scoreUpperArray
     * and setting the ArrayList checkScoreLines by creating an array of true values for however many lines there are.
     * @param game
     */
    public void setScoreCard(Game game) {
        scoreUpperArray = new ArrayList<Integer>();
        for(int i = 0; i < game.dieSides; ++i) {
            scoreUpperArray.add(0);
        }
        checkScoreLines = new ArrayList<Boolean>();
        for(int i = 0; i < (scoreUpperArray.size() + 7); ++i) {
            checkScoreLines.add(true);
        }
    }

    /**
     * Method resets all fields back to zero unless they have been picked previously or were picked during the current round
     * @param scoreCode
     */
    public void resetScoreLines() {
        //Reset upper section
        for(int i = 1; i <= scoreUpperArray.size(); ++i) {
            if(checkScoreLines.get(i - 1)) {
                scoreUpperArray.set(i - 1, 0);
            }
        }
        //Reset lower section
        int line = scoreUpperArray.size();
        if(checkScoreLines.get(line))
            score3Kind = 0;
        line++;
        if(checkScoreLines.get(line))
            score4Kind = 0;
        line++;
        if(checkScoreLines.get(line))
            scoreFullHouse = 0;
        line++;
        if(checkScoreLines.get(line))
            scoreSmlStraight = 0;
        line++;
        if(checkScoreLines.get(line))
            scoreLrgStraight = 0;
        line++;
        if(checkScoreLines.get(line))
            scoreYahtzee = 0;
        line++;
        if(checkScoreLines.get(line)) {
            scoreChance = 0;
        }
    }

    /**
     * Method displays the possible scores for the round that the user can choose from,
     * and gets the line code from the user
     * @param game
     * @param hand
     */
    // Need to make this output the values without assigning to any member fields
    public void getPlayerScoreLine(Game game, ArrayList<Die> hand) {
        updateScoreCard(game, hand);
        //Output for the upper section
        for(int i = 1; i <= scoreUpperArray.size(); ++i) {
            if(checkScoreLines.get(i - 1)) {
                System.out.println("Score is " + scoreUpperArray.get(i - 1) + " if you choose the " + i + " line.");
            }
        }
        //Output for the lower section
        int line = scoreUpperArray.size();
        if(checkScoreLines.get(line))
            System.out.println("Score is " + score3Kind + " if you choose the 3K line.");
        line++;
        if(checkScoreLines.get(line))
            System.out.println("Score is " + score4Kind + " if you choose the 4K line.");
        line++;
        if(checkScoreLines.get(line))
            System.out.println("Score is " + scoreFullHouse + " if you choose the FH line.");
        line++;
        if(checkScoreLines.get(line))
            System.out.println("Score is " + scoreSmlStraight + " if you choose the SS line.");
        line++;
        if(checkScoreLines.get(line))
            System.out.println("Score is " + scoreLrgStraight + " if you choose the LS line.");
        line++;
        if(checkScoreLines.get(line))
            System.out.println("Score is " + scoreYahtzee + " if you choose the Y line.");
        line++;
        if(checkScoreLines.get(line)) {
            System.out.println("Score is " + scoreChance + " if you choose the C line.");
        }
        System.out.println();
        getScoreCode();
    }

    /**
     * Method gets the code for the line the user wants to input to the scorecard for the round
     * and sets the corresponding element to false in the ArrayList checkScoreLines
     * @return
     */
    public void getScoreCode() {
        Scanner kb = new Scanner(System.in);
        System.out.println("What scoring line do you want for this round (Input code found on scorecard)?");
        String input = kb.nextLine();
        int i;
        for(i = 0; i < scoreUpperArray.size(); ++i) {
            if(input.equals(Integer.toString(i + 1))) {
                checkScoreLines.set(i, false);
                return;
            }
        }
        if(input.equals("3K")) {
            checkScoreLines.set(i, false);
            return;
        }
        i++;
        if(input.equals("4K")) {
            checkScoreLines.set(i, false);
            return;
        }
        i++;
        if(input.equals("FH")) {
            checkScoreLines.set(i, false);
            return;
        } 
        i++;
        if(input.equals("SS")) {
            checkScoreLines.set(i, false);
            return;
        }
        i++;
        if(input.equals("LS")) {
            checkScoreLines.set(i, false);
            return;
        }
        i++;
        if(input.equals("Y")) {
            checkScoreLines.set(i, false);
            return;
        }
        i++;
        if(input.equals("C")) {
            checkScoreLines.set(i, false);
            return;
        }
    }

    /**
     * Method calls methods from ScoreCard class to set score line fields of the class if they are available
     */
    public void updateScoreCard(Game game, ArrayList<Die> hand) {
        scoreUpperSection(game, hand);
        scoreNofAKind(game, hand);
        if(checkScoreLines.get(scoreUpperArray.size() + 2)) {
            scoreFullHouse(game, hand);
        }
        if(checkScoreLines.get(scoreUpperArray.size() + 3)) {
            scoreSmlStraight(game, hand);
        }
        if(checkScoreLines.get(scoreUpperArray.size() + 4)) {
            scoreLrgStraight(game, hand);
        }
        if(checkScoreLines.get(scoreUpperArray.size() + 5)) {
            scoreYahtzee(game, hand);
        }
        if(checkScoreLines.get(scoreUpperArray.size() + 6)) {
            scoreChance(game, hand);
        }
    }

    /**
     * Method scores the first six lines of scoring in Yahtzee (or the upper section)
     * @param hand
     */
    public void scoreUpperSection(Game game, ArrayList<Die> hand) {
        int count;
        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            if(checkScoreLines.get(i - 1)) {
                for(int j = 0; j < game.numDie; j++) {
                    if(hand.get(j).getSideUp() == i) {
                        count++;
                    }
                }
                scoreUpperArray.set(i - 1, i * count);
            }
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
            if(count == 4 && checkScoreLines.get(scoreUpperArray.size() + 1)) { //check for 4 of a kind first
                for(int k = 0; k < game.numDie; k++) {
                    score4Kind += hand.get(k).getSideUp();
                }
            }
            else if(count == 3 && checkScoreLines.get(scoreUpperArray.size())) { //check for 3 of a kind
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
        if(count >= 4) {
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