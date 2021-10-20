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

public class Scorecard {

    /**
     * Method scores the first six lines of scoring in Yahtzee (or the upper section)
     * @param finalHand
     */
    public void scoreUpperSection(Game game, ArrayList<Die> finalHand) {
        int count;
        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(finalHand.get(j).getSideUp() == i) {
                    count++;
                }
            }
            System.out.println("Score " + i * count + " on the " + i + " line.");
        }
    }

    /**
     * Method scores the two NofAKind score lines in Yahtzee (3 and 4 of a kind)
     * @param finalHand
     */
    public void scoreNofAKind(Game game, ArrayList<Die> finalHand) {
        int score;
        int count = 0;
        boolean ThreeofAKind = false;
        boolean FourofAKind = false;
        for(int i = 1; i <= game.dieSides; i++) {
            score = 0;
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(finalHand.get(j).getSideUp() == i) {
                    count += 1;
                }
            }
            if(count == 4) { //check for 4 of a kind first
                FourofAKind = true;
                for(int k = 0; k < game.numDie; k++) {
                    score += finalHand.get(k).getSideUp();
                }
                System.out.println("Score " + score + " on the 4 of a kind line.");
            }
            else if(count == 3) { //check for 3 of a kind
                ThreeofAKind = true;
                for(int k = 0; k < game.numDie; k++) {
                    score += finalHand.get(k).getSideUp();
                }
                System.out.println("Score " + score + " on the 3 of a kind line.");
            }
        }
        if(!ThreeofAKind) {
            System.out.println("Score 0 on the 3 of a kind line.");
        }
        if(!FourofAKind) {
            System.out.println("Score 0 on the 4 of a kind line.");
        }
    }

    /**
     * Method scores the Full House line of Yahtzee
     * @param finalHand
     */
    public void scoreFullHouse(Game game, ArrayList<Die> finalHand) {
        int count;
        boolean TwoOfAKind = false;
        boolean ThreeOfAKind = false;

        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(finalHand.get(j).getSideUp() == i) {
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
            System.out.println("Score 25 on the Full House line.");
        }
        else {
            System.out.println("Score 0 on the Full House line");
        }
    }

    /**
     * Method scores the Small Straight line of Yahtzee
     * @param finalHand
     */
    public void scoreSmlStraight(Game game, ArrayList<Die> finalHand) {
        int count = 1;
        for(int i = 0; i < game.numDie - 1; i++) {
            if(finalHand.get(i).getSideUp() + 1 == finalHand.get(i + 1).getSideUp()) {
                count += 1;
            }
            else if(finalHand.get(i).getSideUp() + 1 < finalHand.get(i + 1).getSideUp()) {
                count = 1; // Reset count if straight is broken
            }
        }
        if(count == 4) {
            System.out.println("Score 30 on the Small Straight line");
        }
        else {
            System.out.println("Score 0 on the Small Straight line");
        }
    }

    /**
     * Method scores the Large Straight line of Yahtzee
     * @param finalHand
     */
    public void scoreLrgStraight(Game game, ArrayList<Die> finalHand) {
        int count = 1;
        for(int i = 0; i < game.numDie - 1; i++) {
            if(finalHand.get(i).getSideUp() + 1 == finalHand.get(i + 1).getSideUp()) {
                count += 1;
            }
            else if(finalHand.get(i).getSideUp() + 1 < finalHand.get(i + 1).getSideUp()){
                count = 1; // Reset count if straight is broken
            }
        }
        if(count == 5) {
            System.out.println("Score 40 on the Large Straight line.");
        }
        else {
            System.out.println("Score 0 on the Large Straight line.");
        }
    }

    /**
     * Method scores the Yahtzee line of Yahtzee
     * @param finalHand
     */
    public void scoreYahtzee(Game game, ArrayList<Die> finalHand) {
        int count = 0;
        for(int i = 1; i <= game.dieSides; i++) {
            count = 0;
            for(int j = 0; j < game.numDie; j++) {
                if(finalHand.get(j).getSideUp() == i) {
                    count += 1;
                }
            }
            if(count == 5) {
                System.out.println("Score 50 on the Yahtzee line.");
                break;
            }
        }
        if(count != 5) {
            System.out.println("Score 0 on the Yahtzee line.");
        }
    }

    /**
     * Method scores the Chance line of Yahtzee
     * @param finalHand
     */
    public void scoreChance(Game game, ArrayList<Die> finalHand) {
        int score = 0;
        for(int i = 0; i < game.numDie;  i++) {
            score += finalHand.get(i).getSideUp();
        }
        System.out.println("Score " + score + " on the Chance line.");
    }
}
