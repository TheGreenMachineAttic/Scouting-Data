package com.edinarobotics.scout;
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
    public static final String VERSION = "1.0.1";
    public static final String DATA_SEPARATOR = ":";

    public static Logger log = new Logger();

    // Initialize classes dealing with File operation
    private static FileCreator fileCreo = new FileCreator();
    private static FileScanner configScanner = new FileScanner();
    private static FileScanner teamListFileScanner = new FileScanner();
    private static FileScanner teamFileScanner = new FileScanner();
    private static FileScanner matchFileScanner = new FileScanner();
    private static Extracter extract = new Extracter();

    // Assign a variable the current directory
    private static String currentDir = System.getProperty("user.dir");


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

        // If the default Workspace folder is not created, create it.
        if(!(new File(workspaceFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Work Space");
            new File(workspaceFolderName).mkdir();
        }

        workspaceDir = currentDir + "/" + workspaceFolderName;

        // If the default Team folder is not created, create it.
        if(!(new File(workspaceFolderName + "/" + teamFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Team Folder");
            new File(workspaceFolderName + "/" + teamFolderName).mkdir();
        }

        // If the default Comments folder is not created, create it.
        if(!(new File(workspaceFolderName + "/" + commentFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Comment Folder");
            new File(workspaceFolderName + "/" + commentFolderName).mkdir();
        }

        // If the Config file is not created, create it.
        if(!configScanner.isFileCreated(currentDir + "/" + workspaceFolderName, configFile))
        {
            log.log("Main", "Creating Config File");
            fileCreo.createFile(currentDir + "/" + workspaceFolderName, configFile);
            fileCreo.openFile(currentDir + "/" + workspaceFolderName, configFile);
            
            fileCreo.addConfigEntries();
            fileCreo.closeFile();
        }

        // If the Team List file is not created, create it.
        if(!teamListFileScanner.isFileCreated(currentDir + "/" + workspaceFolderName, teamListFile))
        {
            log.log("Main", "Creating Team List");
            fileCreo.createFile(currentDir + "/" + workspaceFolderName, teamListFile);
            fileCreo.openFile(currentDir + "/" + workspaceFolderName, teamListFile);

            fileCreo.addTeamListHeader();
            fileCreo.closeFile();
        }

        // if the Match folder is not created, create it.
        if(!(new File(workspaceFolderName + "/" + matchFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Match Folder");
            new File(workspaceFolderName + "/" + matchFolderName).mkdir();
        }

        // If the Match List file is not created, create it.
        if(!matchFileScanner.isFileCreated(currentDir + "/" + workspaceFolderName, matchListFile))
        {
            log.log("Main", "Creating Match List");
            fileCreo.createFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);
            fileCreo.openFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);

            fileCreo.addMatchListHeader();
            fileCreo.closeFile();
        }

        log.log();

        // Initialize the Settings GUI,.
        SettingsGUI sGUI = new SettingsGUI();

        // Set the feilds in the Settings GUI
        sGUI.setTeamDirField(teamFileDir);
        sGUI.setCommentsDirField(commentFileDir);
        sGUI.setLogBox(logActivate);

        // Wait for the User to hit the Scout button
        while(!sGUI.getScoutStatus()) {}

        // Store the values from the SettingsGUI in case the user chnged a file path or something
        teamFileDir = sGUI.getTeamDirPath();
        commentFileDir = sGUI.getCommentDirPath();
        logActivate = sGUI.getLogBox();
        log.setEnabled(logActivate);

        // Hide the GUI
        sGUI.setVisible(false);

        // Open and update the Config File
        fileCreo.openFile(currentDir + "/" + workspaceFolderName, configFile);
        fileCreo.addUpdatedConfigEntries(teamFileDir, commentFileDir, logActivate);
        fileCreo.closeFile();

        // Data Entry starts here
        // Initialize the Data Entry GUI
        // DataEntryGUI deGUI = new DataEntryGUI(VERSION);
        new DataEntryGUI_4(VERSION);

        // Wait for 100 Miliseconds to let the GUI load
        Thread.sleep(100);
    }
}