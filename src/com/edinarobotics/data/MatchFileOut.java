/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;

import com.edinarobotics.filer.FileCreator;
import com.edinarobotics.filer.FileScanner;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;

/*
 * @author aoneill
 * @breif
 */

public class MatchFileOut 
{
    // Variables for file creation
    private static String workspaceDir = Global.workspaceDir;
    private static String matchFolderName = Global.matchFolderName;
    private static String DATA_SEPARATOR = Global.DATA_SEPARATOR;

    private static FileScanner matchFileScanner = new FileScanner();
    private static FileCreator fileCreo = new FileCreator();

    // Logger stuff
    private static Logger log = Global.log;
    private static String LOG_TAG = "Match File";
    
    private static String[] header = {"# Match File #"};

    /**
     * Creates a file to store the data for a given match
     * @param matchNumber the match number
     * @param teamNumbers the array of teams involved
     * @param teamScores the scores for the teams
     * @param teamPenalties the penalties for the teams
     */
    public MatchFileOut(int matchNumber, int[] teamNumbers, int[][] teamScores, String[] teamPenalties)
    {
        String matchFile = "Match_" + String.valueOf(matchNumber) + ".txt";
        log.log(LOG_TAG, "Match File: " + matchFile);

        // Check to see if a match already exists
        if(matchFileScanner.isFileCreated(workspaceDir + "/" + matchFolderName, matchFile))
        {
            // Notify the user
            log.log(LOG_TAG, "Overwriting existing Match File with new data...");
        }
        else
        {
            // Notify the user
            log.log(LOG_TAG, "Creating new Match File");
        }
        
        // Create and open the file, plus add the header
        fileCreo.createFile(workspaceDir + "/" + matchFolderName, matchFile);
        fileCreo.openFile(workspaceDir + "/" + matchFolderName, matchFile);
        fileCreo.addEntry(header);

        // Iterate through the teams, and add data
        for(int i = 0; i < 6; i++)
        {
            String data = String.format("%d%s%d%s%d%s%d%s%s", 
                    teamNumbers[i], DATA_SEPARATOR,
                    teamScores[0][i], DATA_SEPARATOR,
                    teamScores[1][i], DATA_SEPARATOR,
                    teamScores[2][i], DATA_SEPARATOR,
                    teamPenalties[i]);

            fileCreo.addEntry(data);
        }
        
        // Close the file
        fileCreo.closeFile();
    }
}