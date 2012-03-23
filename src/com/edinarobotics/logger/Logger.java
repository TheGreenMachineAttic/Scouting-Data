/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.logger;
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
    private void init() {}

    /**
     * Enables or disables the Logger. Allows or disallows System outs
     * @param enabled Set the state of the Logger
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * Display the message passed in
     * @param ID The identifier for the System out
     * @param message The message to be printed
     */
    public void log(String ID, String message)
    {
        String id = "[" + ID + "]";

        if(enabled)
            System.out.println(id + PREFIX + message + SUFFIX);
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