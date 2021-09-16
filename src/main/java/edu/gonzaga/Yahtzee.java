/* (C)2021 */
package edu.gonzaga;

/*
*  This is the main class for the Yahtzee project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/

/** Main program class for launching Yahtzee program. */
public class Yahtzee {
    public static void main(String[] args) {
        System.out.println("Hello Yahtzee");

        Die die1 = new Die(6);
        System.out.println(die1);
        System.out.println("Now rolling our die!");
        die1.roll();
        System.out.println(die1);
        System.out.println("Cool, huh?");

        // misspelled this wrod
    }
}
