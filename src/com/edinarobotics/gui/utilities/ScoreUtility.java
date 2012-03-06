/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.gui.utilities;

import java.util.Random;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/*
 * @author aoneill
 * @breif
 */

public class ScoreUtility
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
    private static final int BALANCE_POINTS = 20;

    // Declare caps for random number generation
    private final static int RAND_AUTO_POINT_CAP = 3;
    private final static int RAND_END_POINT_CAP = 30;
    private final static int RAND_MAIN_POINT_CAP = 80;

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
                    System.err.println("Bad Score Values in the Autonomous!");
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
                    System.err.println("Bad Score Values in the Main Game!");
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
                    System.err.println("Bad Score Values in the End Game!");
                    ex.printStackTrace();
                }
                break;
                
            default:
                result = 0;
                break;
        }

        return result;
    }

    public int getScores(int period, JTextField top, JTextField left, JTextField right, JTextField bottom, JCheckBox box)
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
                    System.err.println("Bad Score Values in the Autonomous!");
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
                    System.err.println("Bad Score Values in the Main Game!");
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

                    result += box.isSelected() ?
                        BALANCE_POINTS : 0;
                }
                catch(Exception ex)
                {
                    System.err.println("Bad Score Values in the End Game!");
                    ex.printStackTrace();
                }
                break;

            default:
                result = 0;
                break;
        }

        return result;
    }

    public void randScore(int period, JTextField box)
    {
        int result;
        switch(period)
        {
            case AUTO:
                int randNum = new Random().nextInt(RAND_AUTO_POINT_CAP);
                result = randNum == 0 ? 0 : randNum + 3;
                break;

            case MAIN:
                result = new Random().nextInt(RAND_MAIN_POINT_CAP);
                break;

            case END:
                result = new Random().nextInt(RAND_END_POINT_CAP);
                break;

            default:
                result = 0;
                break;
        }

        box.setText(String.valueOf(result));
    }

    public void randScore(JCheckBox box)
    {
        box.setSelected(new Random().nextBoolean());
    }
}