package com.edinarobotics.scout;
import com.edinarobotics.data.ConfigFile;
import com.edinarobotics.filer.*;
import com.edinarobotics.gui.*;
import com.edinarobotics.logger.*;

import java.io.*;

/*
 * @author aoneill
 * @breif A data entry and management program for 1816 in Java
 */

public class Main 
{
    // Create a logger for the program
    public static Logger log = new Logger();

    // Assign a variable the current directory
    public static String currentDir = System.getProperty("user.dir");
    public static final String VERSION = "1.0.1";
    public static final String DATA_SEPARATOR = ":";

    public static String workspaceDir = "C:/";
    public static String teamFileDir = "C:/";
    public static String commentFileDir = "C:/";
    public static boolean logActivate = false;

    // Initialize important stings used througout the program
    public static String configFile = "config.txt";
    public static String configFileDir = currentDir;

    public static String teamListFile = "TeamList.txt";
    public static String teamFile = "team.txt";
    
    public static String matchListFile = "Match-List.txt";
    public static String matchFolderName = "Matches";
    public static String matchFile;

    public static String workspaceFolderName = "Workspace";
    public static String commentFolderName = "Comments";
    public static String teamFolderName = "TeamDir";

    public static void main(String[] args) throws InterruptedException
    {
        log.setEnabled(true);
        log.log("Main", "Working in " +  currentDir);

        // Initialize the Settings GUI
        new SettingsGUI();
    }
}