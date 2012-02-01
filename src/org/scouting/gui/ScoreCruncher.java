/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.gui;

import javax.swing.JTextField;

/*
 * @author aoneill
 * @breif
 */

public class ScoreCruncher 
{
    public static final int AUTO = 1;
    public static final int MAIN = 2;
    public static final int END = 3;

    private static final int AUTO_ADDITION = 3;
    private static final int MAIN_ADDITION = 0;
    private static final int END_ADDITION = 0;

    private static final int TOP_HOOP_VAL = 3;
    private static final int MIDDLE_HOOP_VAL = 2;
    private static final int LOW_HOOP_VAL = 1;

    public int getScores(int period, JTextField top, JTextField left, JTextField right, JTextField bottom)
    {
        int result = 0;

        String topText = top.getText();
        String leftText = left.getText();
        String rightText = right.getText();
        String bottomText = bottom.getText();

        switch(period)
        {
            case AUTO:
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
                    System.err.println("Bad Score Values!");
                    ex.printStackTrace();
                }
                break;

            case MAIN:
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
                    System.err.println("Bad Score Values!");
                    ex.printStackTrace();
                }
                break;

            case END:
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
                    System.err.println("Bad Score Values!");
                    ex.printStackTrace();
                }
                break;
                
            default:
                result = 0;
                break;
        }


        return result;
    }
}