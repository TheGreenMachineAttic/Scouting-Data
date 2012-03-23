/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.gui.utilities;

import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/*
 * @author aoneill
 * @breif
 */

public class ScoreUtility
{
    // Declare Identifiers for use in the utility
    public static final int AUTO = 1;
    public static final int MAIN = 2;
    public static final int END = 3;

    // Declare variables for Autonomous Score modification
    private static final int AUTO_ADDITION = 3;
    private static final int MAIN_ADDITION = 0;
    private static final int END_ADDITION = 0;

    // Declare constants for scoring values
    private static final int TOP_HOOP_VAL = 3;
    private static final int MIDDLE_HOOP_VAL = 2;
    private static final int LOW_HOOP_VAL = 1;
    private static final int BALANCE_POINTS = 20;

    // Declare caps for random number generation
    private final static int RAND_AUTO_POINT_CAP = 1;
    private final static int RAND_END_POINT_CAP = 12;
    private final static int RAND_MAIN_POINT_CAP = 6;

    /**
     * Compile scores from the given input devices
     * @param period the period of Game play
     * @param top the top input box
     * @param left the left input box
     * @param right the right input box
     * @param bottom the bottom input box
     * @return the compiled score
     */
    public int getScores(int period, JTextField top, JTextField left, JTextField right, JTextField bottom)
    {
        // Declare the variable to store the score
        int result = 0;

        // Variables to store the text from the boxes
        String topText = top.getText();
        String leftText = left.getText();
        String rightText = right.getText();
        String bottomText = bottom.getText();

        // Switch statement based on the period specified
        switch(period)
        {
            // Autonomous Scoring
            case AUTO:
                
                // Try to compile the scores by parsing integers
                // If it fails, notify the user
                try
                {
                    result += topText.equals("") ?
                        0 : (TOP_HOOP_VAL * Integer.parseInt(topText) + AUTO_ADDITION);

                    result += leftText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(leftText) + AUTO_ADDITION);

                    result += rightText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(rightText) + AUTO_ADDITION);

                    result += bottomText.equals("") ?
                        0 : (LOW_HOOP_VAL * Integer.parseInt(bottomText) + AUTO_ADDITION);
                }
                catch(Exception ex)
                {
                    ErrorGUI errorGUI = new ErrorGUI("Bad Score values in Autonomous!\n\n"
                            + ex.getClass().getName() + "\n"
                            + ex.getMessage(), ErrorGUI.ERROR_LOW);
                }
                break;
                
            // Main Game Scoring
            case MAIN:
                
                // Try to compile the scores by parsing integers
                // If it fails, notify the user
                try
                {
                    result += topText.equals("") ?
                        0 : (TOP_HOOP_VAL * Integer.parseInt(topText) + MAIN_ADDITION);

                    result += leftText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(leftText) + MAIN_ADDITION);

                    result += rightText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(rightText) + MAIN_ADDITION);

                    result += bottomText.equals("") ?
                        0 : (LOW_HOOP_VAL * Integer.parseInt(bottomText) + MAIN_ADDITION);
                }
                catch(Exception ex)
                {
                    ErrorGUI errorGUI = new ErrorGUI("Bad Score values in Main Game!\n\n"
                            + ex.getClass().getName() + "\n"
                            + ex.getMessage(), ErrorGUI.ERROR_LOW);
                }
                break;

            // End Game Scoring
            case END:
                
                // Try to compile the scores by parsing integers
                // If it fails, notify the user
                try
                {
                    result += topText.equals("") ?
                        0 : (TOP_HOOP_VAL * Integer.parseInt(topText) + END_ADDITION);

                    result += leftText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(leftText) + END_ADDITION);

                    result += rightText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(rightText) + END_ADDITION);

                    result += bottomText.equals("") ?
                        0 : (LOW_HOOP_VAL * Integer.parseInt(bottomText) + END_ADDITION);
                }
                catch(Exception ex)
                {
                    ErrorGUI errorGUI = new ErrorGUI("Bad Score values in End Game!" + "\n\n"
                            + ex.getClass().getName() + "\n"
                            + ex.getMessage(), ErrorGUI.ERROR_LOW);
                }
                break;
                
            // If the period specified is not one of the above options, set the resultant
            // score as 0
            default:
                result = 0;
                break;
        }

        // Return the result score
        return result;
    }

    /**
     * Compile scores from the given input devices
     * @param period the period of Game play
     * @param top the top input box
     * @param left the left input box
     * @param right the right input box
     * @param bottom the bottom input box
     * @param box the End Game balance check box
     * @return the compiled score
     */
    public int getScores(int period, JTextField top, JTextField left, JTextField right, JTextField bottom, JCheckBox box)
    {
        // Declare the variable to store the score
        int result = 0;

        // Variables to store the text from the boxes
        String topText = top.getText();
        String leftText = left.getText();
        String rightText = right.getText();
        String bottomText = bottom.getText();

        // Switch statement based on the period specified
        switch(period)
        {
            // Autonomous Scoring
            case AUTO:
                
                // Try to compile the scores by parsing integers
                // If it fails, notify the user
                try
                {
                    result += topText.equals("") ?
                        0 : (TOP_HOOP_VAL * Integer.parseInt(topText) + AUTO_ADDITION);

                    result += leftText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(leftText) + AUTO_ADDITION);

                    result += rightText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(rightText) + AUTO_ADDITION);

                    result += bottomText.equals("") ?
                        0 : (LOW_HOOP_VAL * Integer.parseInt(bottomText) + AUTO_ADDITION);
                }
                catch(Exception ex)
                {
                    ErrorGUI errorGUI = new ErrorGUI("Bad Score values in Autonomous!" + "\n\n"
                            + ex.getClass().getName() + "\n"
                            + ex.getMessage(), ErrorGUI.ERROR_LOW);
                }
                break;

            // Main Game Scoring
            case MAIN:
                
                // Try to compile the scores by parsing integers
                // If it fails, notify the user
                try
                {
                    result += topText.equals("") ?
                        0 : (TOP_HOOP_VAL * Integer.parseInt(topText) + MAIN_ADDITION);

                    result += leftText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(leftText) + MAIN_ADDITION);

                    result += rightText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(rightText) + MAIN_ADDITION);

                    result += bottomText.equals("") ?
                        0 : (LOW_HOOP_VAL * Integer.parseInt(bottomText) + MAIN_ADDITION);
                }
                catch(Exception ex)
                {
                    ErrorGUI errorGUI = new ErrorGUI("Bad Score values in Main Game!" + "\n\n"
                            + ex.getClass().getName() + "\n"
                            + ex.getMessage(), ErrorGUI.ERROR_LOW);
                }
                break;

            // End Game Scoring
            case END:
                
                // Try to compile the scores by parsing integers
                // Include a balance checkbox for End Game
                // If it fails, notify the user
                try
                {
                    result += topText.equals("") ?
                        0 : (TOP_HOOP_VAL * Integer.parseInt(topText) + END_ADDITION);

                    result += leftText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(leftText) + END_ADDITION);

                    result += rightText.equals("") ?
                        0 : (MIDDLE_HOOP_VAL * Integer.parseInt(rightText) + END_ADDITION);

                    result += bottomText.equals("") ?
                        0 : (LOW_HOOP_VAL * Integer.parseInt(bottomText) + END_ADDITION);

                    result += box.isSelected() ?
                        BALANCE_POINTS : 0;
                }
                catch(Exception ex)
                {
                    ErrorGUI errorGUI = new ErrorGUI("Bad Score values in End Game!" + "\n\n"
                            + ex.getClass().getName() + "\n"
                            + ex.getMessage(), ErrorGUI.ERROR_LOW);
                }
                break;
                
            // If the period specified is not one of the above options, set the resultant
            // score as 0
            default:
                result = 0;
                break;
        }

        // Return the resultant score
        return result;
    }

    /**
     * Utility to randomize scores based on Game period
     * @param period the Game period
     * @param box the text box to be given a random value
     */
    public void randScore(int period, JTextField box)
    {
        // Variable to store the score to be written to the text box
        int result;
        
        // Randomize the score based on Game period
        switch(period)
        {
            // Autonomous
            case AUTO:
                int randNum = new Random().nextInt(RAND_AUTO_POINT_CAP);
                result = randNum == 0 ? 0 : randNum + 3;
                break;

            // Main Game
            case MAIN:
                result = new Random().nextInt(RAND_MAIN_POINT_CAP);
                break;

            // End Game
            case END:
                result = new Random().nextInt(RAND_END_POINT_CAP);
                break;

            // If the period specified does not correspond with the ones above,
            // set the score to be writen as 0
            default:
                result = 0;
                break;
        }

        // Wite the score to the text box
        box.setText(String.valueOf(result));
    }

    /**
     * Randomize the selection of a check box
     * @param box the check box to be randomized
     */
    public void randScore(JCheckBox box)
    {
        // Set the box as a random boolean
        box.setSelected(new Random().nextBoolean());
    }

    /**
     * Randomize a selection box from an upper limit to a lower limit
     * @param box the selection box to be randomized
     * @param lowerLimit the upper limit of randomization
     * @param upperLimit the lower limit of randomization
     */
    public void randScore(JComboBox box, int lowerLimit, int upperLimit)
    {
        // Check to see if the upper limit is less than the lower limit
        // If so, do nothing
        // Else, get a random score
        if(upperLimit - lowerLimit > 0)
        {
            // Get a random number between the lower and the upper limit
            int num = lowerLimit + new Random().nextInt(upperLimit - lowerLimit);

            // Set the selection index of the box to be the number
            box.setSelectedIndex(num);
        }
    }
}