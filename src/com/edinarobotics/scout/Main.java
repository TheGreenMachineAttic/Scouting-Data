package com.edinarobotics.scout;

import com.edinarobotics.gui.SettingsGUI;
import com.edinarobotics.logger.Logger;


/*
 * @author aoneill
 * @breif A data entry and management program for 1816 in Java
 */

public class Main 
{
    private static Logger log = Global.log;
    private static String currentDir = Global.currentDir;
    
    /**
     * Class which runs on start-up
     * @param args arguments passed in
     */
    public static void main(String[] args)
    {
        // Set the logger to be enabled just for init
        log.setEnabled(true);
        log.setTimeStampEnabled(Logger.TIMER_TIME);
        log.log("Main", "Working in " +  currentDir);
        
        // Initialize the Settings GUI
        SettingsGUI settingsGUI = new SettingsGUI();
    }
}