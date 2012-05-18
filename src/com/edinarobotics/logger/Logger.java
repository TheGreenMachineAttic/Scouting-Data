/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;


/*
 * @author aoneill
 * @breif
 */

public class Logger 
{
    // Variables for output
    private static String PREFIX = " ";
    private static String SUFFIX = "";
    
    // Variable to store output
    private boolean enabled = false;
    private boolean timeStamp = false;
    private int timeMode;
    private long time;

    public static int NO_TIMER = 0;
    public static int SYSTEM_TIME = 1;
    public static int TIMER_TIME = 2;
    
    /**
     * Initialize the Logger
     */
    public Logger() 
    {
        init();
    }

    /**
     * Init function. Nothing currently present
     */
    private void init() 
    {
        time = System.currentTimeMillis();
    }

    /**
     * Enables or disables the Logger. Allows or disallows System outs
     * @param enabled Set the state of the Logger
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    /**
     * Enables or disables the Time Stamp output in the Logger.
     * If enabled, log messages have a time stamp before the log ID and message
     * @param mode Set the state of the Logger
     */
    public void setTimeStampEnabled(int mode)
    {
        if(mode == SYSTEM_TIME || mode == TIMER_TIME)
        {
            timeStamp = enabled;
            timeMode = mode;
        }
    }

    /**
     * Display the message passed in if enabled
     * @param ID The identifier for the System out
     * @param message The message to be printed
     */
    public void log(String ID, String message)
    {
        String id = "[" + ID + "]";
        String output = "";

        if(enabled)
        {
            if(timeStamp)
            {
                if(timeMode == SYSTEM_TIME)
                {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                    output += format.format(date) + " ";
                }
                else if(timeMode == TIMER_TIME)
                {
                    double timer = (System.currentTimeMillis() - time);
                    output += "[" + String.valueOf(timer/1000) + "] ";
                }
            }
            
            output += id + PREFIX + message + SUFFIX;
            System.out.println(output);
        }
    }

    /**
     * Display just an empty line without a tag if enabled
     */
    public void log()
    {
        if(enabled)
            System.out.println();
    }
}