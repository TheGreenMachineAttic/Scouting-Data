/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;
/*
 * @author aoneill
 * @breif
 */

public class TeamFileOut 
{
        // Store the Team's text file as their team number plus the .txt extension
        teamFile = Integer.toString(teamNumbers[i]) + ".txt";

        // If the team's file is not created, create the team's file, and add some standard content
        if(!teamFileScanner.isFileCreated(teamFileDir, teamFile))
        {
            // Remember the changelskjdflakjf? This is where it would be implimented
            System.out.println("Creating Team " + teamNumbers[i] + "'s Data File");

            // Create the File
            fileCreo.createFile(teamFileDir, teamFile);
            fileCreo.addTeamHeader();
        }

        // If the team file exists, do the following
        if(teamFileScanner.isFileCreated(teamFileDir, teamFile))
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
        data = String.format("%d%s%d%s%d%s%d%s%s%s", currentMatch, DATA_SEPARATOR, teamScores[0][i], DATA_SEPARATOR, teamScores[1][i], DATA_SEPARATOR, teamScores[2][i], DATA_SEPARATOR, teamPenalties[i], System.getProperty("line.separator"));
        fileCreo.addEntry(data);

        // Close the file to save changes
        fileCreo.closeFile();

        // The reason the file's contents had to be read, stored, then written again is because
        // when you open the file with the file creator, it seems to wipe the data already there
        // And leave nothing behind. So the existing data must be stored and written before
        // Adding the next entry to avoid data loss.
}