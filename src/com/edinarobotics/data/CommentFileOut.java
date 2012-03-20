/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;
/*
 * @author aoneill
 * @breif
 */

public class CommentFileOut 
{
        // Store the Team's Comment file as the team's number plus the ending of "-Comments.txt"
        teamFile = Integer.toString(teamNumbers[i]) + "-Comments.txt";

        // Try to open the file
        teamFileScanner.openFile(commentFileDir, teamFile);

        // If the file does not exist, create the file, and add a standard header
        if(!teamFileScanner.isFileCreated(commentFileDir, teamFile))
        {
            // Remember the changelskjdflakjf? This is where it would be implimented
            System.out.println("Creating Team " + teamNumbers[i] + "'s Commnent File");

            // Create the file, add content, and close it
            fileCreo.createFile(commentFileDir, teamFile);
            fileCreo.openFile(commentFileDir, teamFile);
            fileCreo.addCommentHeader();
        }
        // If the team comments file already has content, go through the same process for the team files
        if(!teamComments[i].equals(""))
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

        log.log();

    

}