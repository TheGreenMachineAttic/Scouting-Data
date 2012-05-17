/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;
import com.edinarobotics.filer.FileWriter;
import com.edinarobotics.filer.FileReader;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;
import java.util.ArrayList;

/*
 * @author aoneill
 * @breif
 */


public class TeamFileOut 
{
    // Vraiables used for file creation
    private static String teamFileDir = Global.teamFileDir;
    private static String dataSeparator = Global.DATA_SEPARATOR;

    private static FileReader teamFileScanner = new FileReader();
    private static FileWriter fileCreo = new FileWriter();
    
    private static String header[] = {"# Format #", "# <roundNum>" + Global.DATA_SEPARATOR + "<autoPoints>" + Global.DATA_SEPARATOR + "<GlobalPoints>" + Global.DATA_SEPARATOR + "<endPoints>" + Global.DATA_SEPARATOR +"<penalties>"};
    
    private static Logger log = Global.log;

    /**
     * Creates a data file for a given team with the given data
     * @param teamNumber the team number for which the data belongs
     * @param matchNumber the match the team was involved in
     * @param autoScore the Autonomous score the team received
     * @param mainScore the Main Game score the team received
     * @param endScore the End Game score the team received
     * @param penalties the Penalties the team received
     */
    public TeamFileOut(int teamNumber, int matchNumber, int autoScore, int mainScore, int endScore, String penalties)
    {
        // Store the Team's text file as their team number plus the .txt extension
        String teamFile = Integer.toString(teamNumber) + ".txt";

        // Create a list to store the file data
        ArrayList<String> list = new ArrayList<String>();

        // If the team's file is not created, create the team's file, and add some standard content
        if(!teamFileScanner.isFileCreated(teamFileDir, teamFile))
        {
            // Debug
            log.log("Team File", "Creating Team " + teamNumber + "'s Data File");

            // Create the File
            fileCreo.createFile(teamFileDir, teamFile);
            fileCreo.openFile(teamFileDir, teamFile);
            fileCreo.addEntry(header);
            fileCreo.closeFile();
        }

        // If the team file exists, do the following
        if(teamFileScanner.isFileCreated(teamFileDir, teamFile))
        {
            // Open the file for Reading
            teamFileScanner.openFile(teamFileDir, teamFile);

            // Store the existing data from the file into the list
            while(teamFileScanner.hasNextEntry())
            {
                list.add(teamFileScanner.getNextLine());
            }
            teamFileScanner.close();
        }

        // Open the file creator
        fileCreo.openFile(teamFileDir, teamFile);

        // Add the new entry fro the round
        String data = String.format("%d%s%d%s%d%s%d%s%s%s",
                matchNumber, dataSeparator,
                autoScore, dataSeparator,
                mainScore, dataSeparator,
                endScore, dataSeparator,
                penalties, System.getProperty("line.separator"));

        // Add the data
        list.add(data);

        // Write the data
        fileCreo.addEntry(list);

        // Close the file to save changes
        fileCreo.closeFile();
    }
}