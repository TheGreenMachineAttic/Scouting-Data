package org.scouting.scout;

import org.scouting.filer.*;
import org.scouting.gui.*;
import java.io.*;
import java.util.*;

/*
 * @author aoneill
 * @breif A data entry and management program for 1816 in Java
 */

public class Main 
{
    private static final String VERSION = "1.0";
    private static boolean newGUI = true;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException
    {
        // Initialize classes dealing with File operation
        FileCreator fileCreo = new FileCreator();
        FileCreator logger = new FileCreator();
        FileScanner configScanner = new FileScanner();
        FileScanner teamListFileScanner = new FileScanner();
        FileScanner teamFileScanner = new FileScanner();
        Extracter extract = new Extracter();

        // Assign a variable the current directory
        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);

        // Initialize important stings used througout the program
        String configFile = "config.txt";
        String teamListFile = "TeamList.txt";
        String workspaceFolderName = "Workspace";
        String teamFolderName = "TeamDir";
        String commentFolderName = "Comments";
        String teamFile = "team.txt";

        // This string holds misc. data at different points in the program
        String data;

        // Set default values for the Directories of the different user-set options
        String teamFileDir = "C:/";
        String commentFileDir = "C:/";
        boolean logActivate = false;

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
            System.out.println("Creating default Work Space");
            new File(workspaceFolderName).mkdir();
        }

        // If the default Team folder is not created, create it.
        if(!(new File(workspaceFolderName + "/" + teamFolderName).isDirectory()))
        {
            System.out.println("Creating default Team Folder");
            new File(workspaceFolderName + "/" + teamFolderName).mkdir();
        }

        // If the default Comments folder is not created, create it.
        if(!(new File(workspaceFolderName + "/" + commentFolderName).isDirectory()))
        {
            System.out.println("Creating default Comment Folder");
            new File(workspaceFolderName + "/" + commentFolderName).mkdir();
        }

        // If the Config file is not created, create it.
        if(!configScanner.isFileCreated(currentDir + "/" + workspaceFolderName, configFile))
        {
            System.out.println("Creating Config File");
            fileCreo.createFile(currentDir + "/" + workspaceFolderName, configFile);
            fileCreo.openFile(currentDir + "/" + workspaceFolderName, configFile);
            
            fileCreo.addConfigEntries();
            fileCreo.closeFile();
        }

        // If the Team List file is not created, create it.
        if(!teamListFileScanner.isFileCreated(currentDir + "/" + workspaceFolderName, teamListFile))
        {
            System.out.println("Creating Team List");
            fileCreo.createFile(currentDir + "/" + workspaceFolderName, teamListFile);
            fileCreo.openFile(currentDir + "/" + workspaceFolderName, teamListFile);

            fileCreo.addTeamListHeader();
            fileCreo.closeFile();
        }

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

        // Initialize the Settings GUI
        SettingsGUI sGUI = new SettingsGUI();

        // Set the feilds in the Settings GUI
        sGUI.setTeamDirField(teamFileDir);
        sGUI.setCommentsDirField(commentFileDir);
        sGUI.setLogBox(logActivate);

        // Wait for the User to hit the Scout button
        while(!sGUI.getScoutStatus()) {}

        // Debug
        System.out.println("Scout! Pressed");

        // Store the values from the SettingsGUI in case the user chnged a file path or something
        teamFileDir = sGUI.getTeamDirPath();
        commentFileDir = sGUI.getCommentDirPath();
        logActivate = sGUI.getLogBox();

        // Hide the GUI
        sGUI.setVisible(false);

        // Open and update the Config File
        fileCreo.openFile(currentDir + "/" + workspaceFolderName, configFile);
        fileCreo.addUpdatedConfigEntries(teamFileDir, commentFileDir, logActivate);
        fileCreo.closeFile();



        // Data Entry starts here
        // Initialize the Data Entry GUI
        DataEntryGUI deGUI = new DataEntryGUI(VERSION);
        DataEntryGUI_2 deGUI2 = new DataEntryGUI_2(VERSION);

        // Wait for 100 Miliseconds to let the GUI load
        Thread.currentThread().sleep(100);

        // Start an infinite loop
        // The loop will exit once the user hits the 'x' button in the window
        while(true)
        {
            if(deGUI.isVisible())
            {
                newGUI = deGUI.getSwitch();
            }
            else
            {
                newGUI = deGUI2.getSwitch();
            }

            if(newGUI)
            {
                deGUI2.setVisible(true);
                deGUI.setVisible(false);

                // Wait for the submitted button to be pressed
                while(!deGUI2.getSubmittedFlag()) {}

                // Reset the boolean to hold the state of the submitted button
                deGUI2.resetSubmittedFlag();

                // Wait for 100 miliseconds to let the computer think
                // The program would have issues here, and giving it a small period to rest seemed to fix it
                Thread.currentThread().sleep(100);

                // Store the data from the GUI to their respective arrays
                currentMatch = deGUI2.getMatch();
                teamNumbers = deGUI2.getTeamNumbers();
                teamScores = deGUI2.getScores();
                teamPenalties = deGUI2.getPenalties();
                teamComments = deGUI2.getComments();
            }
            else
            {
                deGUI.setVisible(true);
                deGUI2.setVisible(false);

                // Wait for the submitted button to be pressed
                while(!deGUI.getSubmittedFlag()) {}

                // Reset the boolean to hold the state of the submitted button
                deGUI.resetSubmittedFlag();

                // Wait for 100 miliseconds to let the computer think
                // The program would have issues here, and giving it a small period to rest seemed to fix it
                Thread.currentThread().sleep(100);

                // Store the data from the GUI to their respective arrays
                currentMatch = deGUI.getMatch();
                teamNumbers = deGUI.getTeamNumbers();
                teamScores = deGUI.getScores();
                teamPenalties = deGUI.getPenalties();
                teamComments = deGUI.getComments();
            }            

            // Start the main for() loop for the 6 Teams represented in the GUI
            for(int i = 0; i < 6; i++)
            {
                // Store the Team's text file as their team number plus the .txt extension
                teamFile = Integer.toString(teamNumbers[i]) + ".txt";

                // If the team's file is not created, create the team's file, and add some standard content
                if(!teamFileScanner.isFileCreated(teamFileDir, teamFile))
                {
                    // Remember the changeLogActivate? This is where it would be implimented
                    if(logActivate) 
                    {
                        System.out.printf("Creating Team %d's Data File\n", teamNumbers[i]);
                        logger.addEntry(String.format("Creating Team %d's Data File", teamNumbers[i]));
                    }

                    // Create the File
                    fileCreo.createFile(teamFileDir, teamFile);
                    fileCreo.addTeamHeader();
                }

                // If the team file exists, do the following
                else
                {
                    // Open the file for Reading
                    teamFileScanner.openFile(teamFileDir, teamFile);

                    // Find how many lines there are in the team's file
                    int countLine = 0;
                    while(teamFileScanner.hasNextEntry())
                    {
                        countLine++;
                        teamFileScanner.getNextLine();
                    }

                    // Create an array to hold the already existing Data with a length
                    // determined by the number of lines in the file
                    String dataArray[] = new String[countLine];

                    // Open the file again to start scanning from the beginning of the file
                    teamFileScanner.openFile(teamFileDir, teamFile);

                    // Reset the line count
                    countLine = 0;

                    // While the file has content, add it to the newly created array
                    while(teamFileScanner.hasNextEntry())
                    {
                        dataArray[countLine] = teamFileScanner.getNextLine();
                        countLine++;
                    }

                    // Open the teams file with the File Creator
                    fileCreo.openFile(teamFileDir, teamFile);
                    for(int j = 0; j < countLine; j++)
                    {
                        // Add the old entries
                        fileCreo.addEntry(dataArray[j]);
                    }
                }
                // Add the new entry fro the round
                data = String.format("%d:%d:%d:%d:%s%s", currentMatch, teamScores[0][i], teamScores[1][i], teamScores[2][i], teamPenalties[i], System.getProperty("line.separator"));
                fileCreo.addEntry(data);

                // Close the file to save changes
                fileCreo.closeFile();

                // The reason the file's contents had to be read, stored, then written again is because
                // when you open the file with the file creator, it seems to wipe the data already there
                // And leave nothing behind. So the existing data must be stored and written before
                // Adding the next entry to avoid data loss.


                // Store the Team's Comment file as the team's number plus the ending of "-Comments.txt"
                teamFile = Integer.toString(teamNumbers[i]) + "-Comments.txt";

                // Try to open the file
                teamFileScanner.openFile(commentFileDir, teamFile);

                // If the file does not exist, create the file, and add a standard header
                if(!teamFileScanner.isFileCreated(commentFileDir, teamFile))
                {
                    // Remember the changeLogActivate? This is where it would be implimented
                    if(logActivate) 
                    {
                        System.out.printf("Creating Team %d's Commnets File\n", teamNumbers[i]);
                        //logger.addEntry(String.format("Creating Team %d's Commnets File", teamNumbers[i]));
                    }

                    // Create the file, add content, and close it
                    fileCreo.createFile(commentFileDir, teamFile);
                    fileCreo.openFile(commentFileDir, teamFile);
                    fileCreo.addCommentHeader();
                }
                // If the team comments file already has content, go through the smae process for the team files
                else if(!teamComments[i].equals(""))
                {
                    // Open the file for Reading
                    teamFileScanner.openFile(commentFileDir, teamFile);

                    // Find how many lines there are in the team's file
                    int countLine = 0;
                    while(teamFileScanner.hasNextEntry())
                    {
                        countLine++;
                        teamFileScanner.getNextLine();
                    }

                    // Create an array to hold the already existing Data with a length
                    // determined by the number of lines in the file
                    String dataArray[] = new String[countLine];

                    // Open the file again to start scanning from the beginning of the file
                    teamFileScanner.openFile(commentFileDir, teamFile);

                    // Reset the line count
                    countLine = 0;

                    // While the file has content, add it to the newly created array
                    while(teamFileScanner.hasNextEntry())
                    {
                        dataArray[countLine] = teamFileScanner.getNextLine();
                        countLine++;
                    }

                    // Open the teams file with the File Creator
                    fileCreo.openFile(commentFileDir, teamFile);
                    for(int j = 0; j < countLine; j++)
                    {
                        // Add old entries
                        fileCreo.addEntry(dataArray[j]);
                    }

                    // Add a seperator to the comments file to clearly mark where comments start and stop
                    fileCreo.addEntry();
                    fileCreo.addEntry("#########################");
                    fileCreo.addEntry(String.format("Round: %d", currentMatch));
                    fileCreo.addEntry();

                    // Add the next entry
                    fileCreo.addEntry(teamComments[i]);
                    
                }
                // Close the team file to save changes
                fileCreo.closeFile();


                // Do the same process again, but with a different file...

                // Open the Team List File
                teamListFileScanner.openFile(currentDir + "/" + workspaceFolderName, teamListFile);

                // Find how many lines there are in the Team List File
                int countLine = 0;
                while(teamListFileScanner.hasNextEntry())
                {
                    countLine++;
                    teamListFileScanner.getNextLine();
                }

                // Create an array to hold the already existing Data with a length
                // determined by the number of lines in the file
                String dataArray[] = new String[countLine];

                // Open the file again to start scanning from the beginning of the file
                teamListFileScanner.openFile(currentDir + "/" + workspaceFolderName, teamListFile);

                // Reset the line count
                countLine = 0;

                // While the file has content, add it to the newly created array
                while(teamListFileScanner.hasNextEntry())
                {
                    dataArray[countLine] = teamListFileScanner.getNextLine();
                    //System.out.println("           [" + dataArray[countLine] + "]\t[" + teamNumbers[i] + "]");

                    countLine++;
                }

                // Create a flag to check if a team is already present in the Team List File
                boolean teamAlreadyPresent = false;

                // Open the teams file with the File Creator
                fileCreo.openFile(currentDir + "/" + workspaceFolderName, teamListFile);
                for(int j = 0; j < countLine; j++)
                {
                    // Add the old entries
                    fileCreo.addEntry(dataArray[j]);

                    // If the team is already present in the Team File, cange the flag
                    if(dataArray[j].equals(Integer.toString(teamNumbers[i])))
                    {
                        System.out.println("Found Team " + dataArray[j] + " in TeamFile!");
                        teamAlreadyPresent = true;
                    }
                }

                // As long as the team is not present in the FIle, add the team
                if(!teamAlreadyPresent)
                {
                    fileCreo.addEntry(Integer.toString(teamNumbers[i]));
                }

                // Close and save the file
                fileCreo.closeFile();
            }

            // For Debug purposes
            System.out.println("-----------------------------------------");
        }
    }
}