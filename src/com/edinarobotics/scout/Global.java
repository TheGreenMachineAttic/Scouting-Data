/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.scout;

import com.edinarobotics.logger.Logger;

/**
 *
 * @author aoneill
 */
public class Global 
{   
    // Create a logger for the program
    public static Logger log = new Logger();

    // Assign a variable the current directory
    public static final String currentDir = System.getProperty("user.dir");
    public static final String VERSION = "1.0.1";
    public static final String DATA_SEPARATOR = ":";

    public static String workspaceDir = "C:/";
    public static String teamFileDir = "C:/";
    public static String commentFileDir = "C:/";
    public static boolean logActivate = false;

    // Initialize important strings used througout the program
    public static final String configFile = "config.txt";
    public static final String configFileDir = System.getProperty("user.dir");

    public static final String teamListFile = "TeamList.txt";
    public static String teamFile = "team.txt";
    
    public static final String matchListFile = "Match-List.txt";
    public static final String matchFolderName = "Matches";
    public static String matchFile;

    public static final String workspaceFolderName = "Workspace";
    public static final String commentFolderName = "Comments";
    public static final String teamFolderName = "TeamDir";
}
