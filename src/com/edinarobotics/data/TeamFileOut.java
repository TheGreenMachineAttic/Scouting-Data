/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;
import com.edinarobotics.filer.*;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Main;

import java.util.ArrayList;

/*
 * @author aoneill
 * @breif
 */

public class TeamFileOut 
{
    private static String teamFileDir = Main.teamFileDir;
    private static String dataSeparator = Main.DATA_SEPARATOR;

    private static FileScanner teamFileScanner = new FileScanner();
    private static FileCreator fileCreo = new FileCreator();

    private static Logger log = Main.log;

    public TeamFileOut(int teamNumber, int matchNumber, int autoScore, int mainScore, int endScore, String penalties)
    {
        // Store the Team's text file as their team number plus the .txt extension
        String teamFile = Integer.toString(teamNumber) + ".txt";

        ArrayList<String> list = new ArrayList<String>();

        // If the team's file is not created, create the team's file, and add some standard content
        if(!teamFileScanner.isFileCreated(teamFileDir, teamFile))
        {
            // Remember the changelskjdflakjf? This is where it would be implimented
            log.log("Team File", "Creating Team " + teamNumber + "'s Data File");

            // Create the File
            fileCreo.createFile(teamFileDir, teamFile);
            fileCreo.addTeamHeader();
            fileCreo.closeFile();
        }

        // If the team file exists, do the following
        if(teamFileScanner.isFileCreated(teamFileDir, teamFile))
        {
            // Open the file for Reading
            teamFileScanner.openFile(teamFileDir, teamFile);

            while(teamFileScanner.hasNextEntry())
            {
                list.add(teamFileScanner.getNextLine());
            }
        }

        // Add the new entry fro the round
        String data = String.format("%d%s%d%s%d%s%d%s%s%s",
                matchNumber, dataSeparator,
                autoScore, dataSeparator,
                mainScore, dataSeparator,
                endScore, dataSeparator,
                penalties, System.getProperty("line.separator"));

        list.add(data);

        fileCreo.addEntry(list);

        // Close the file to save changes
        fileCreo.closeFile();
    }
}