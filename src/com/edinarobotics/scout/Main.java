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
    public static String teamListFile = "TeamList.txt";
    public static String matchListFile = "Match-List.txt";
    public static String workspaceFolderName = "Workspace";
    public static String teamFolderName = "TeamDir";
    public static String commentFolderName = "Comments";
    public static String matchFolderName = "Matches";
    public static String teamFile = "team.txt";
    public static String matchFile;

    public static void main(String[] args) throws InterruptedException
    {
        log.setEnabled(true);
        log.log("Main", "Working in " +  currentDir);

        // This string holds misc. data at different points in the program
        String data;

        // Initialize Arrays / Variables to store team data input
        int teamNumbers[] = new int[6];
        int teamScores[][] = new int[3][6];
        String teamPenalties[] = new String[6];
        String teamComments[] = new String[6];

        // Variable to store the Current Match
        int currentMatch = 0;

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

        // Open the Config file to read defaults
        configScanner.openFile(currentDir + "/" + workspaceFolderName, configFile);

        // Read the config file until the line does not start with '#"
        // Used to skip a header comment on the file
        String nextLine = configScanner.getNextLine();
        while(nextLine.startsWith("#"))
        {
            nextLine = configScanner.getNextLine();
        }

        // Once you have found the content of the config file, take the data you need
        boolean abort = false;
        while(!abort)
        {
            // If the next line found in the scanner indicates the default Team Dir.
            // Extract the second entry and store it as the defaultTeaDir
            if(nextLine.startsWith("defaultTeamDir"))
            {
                teamFileDir = extract.extractEntry(nextLine, 2);

                // Due to the file structure of Windows, we need to also extract entry 3 because of the colon
                // in C:/
                if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
                {
                    teamFileDir = teamFileDir + ":" + extract.extractEntry(nextLine, 3);
                }
            }

            // This method is deprecated. It will be implimented in the future. Its purpose is
            // to print to a file what the System.out.print() methods would show on the console.
            if(nextLine.startsWith("changeLogActivate"))
            {
                if(extract.extractEntry(nextLine, 2).equals("true"))
                {
                    logActivate = true;
                }
            }

            // If the next line found in the scanner indicates the default Comment Dir.
            // Extract the second entry and store it as the defaultTeaDir
            if(nextLine.startsWith("defaultCommentDir"))
            {
                commentFileDir = extract.extractEntry(nextLine, 2);
                if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
                {
                    commentFileDir = commentFileDir + ":" + extract.extractEntry(nextLine, 3);
                }
            }

            // Once the configScanner runs out of lines, abort the loop, else, get the next line
            if(!configScanner.hasNextEntry())
            {
                abort = true;
            }
            else
            {
                nextLine = configScanner.getNextLine();
            }
        }

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