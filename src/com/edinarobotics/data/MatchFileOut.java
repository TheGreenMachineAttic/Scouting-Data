/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;
/*
 * @author aoneill
 * @breif
 */

public class MatchFileOut 
{
    public MatchFileOut()
    {
        matchFile = "Match_" + String.valueOf(currentMatch) + ".txt";
        log.log("Main", "Match File name is " + matchFile);

        if(matchFileScanner.isFileCreated(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchFile))
        {
            log.log("Main", "Overwriting existing Match File with new data...");
            fileCreo.openFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchFile);
            fileCreo.addMatchHeader(currentMatch);

            for(int i = 0; i < 6; i++)
            {
                data = String.format("%d%s%d%s%d%s%d%s%s", teamNumbers[i], DATA_SEPARATOR, teamScores[0][i], DATA_SEPARATOR, teamScores[1][i], DATA_SEPARATOR, teamScores[2][i], DATA_SEPARATOR, teamPenalties[i]);
                fileCreo.addEntry(data);
            }
            fileCreo.closeFile();
        }
        else
        {
            log.log("Main", "Creating new Match File");
            fileCreo.createFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchFile);
            fileCreo.openFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchFile);
            fileCreo.addMatchHeader(currentMatch);

            for(int i = 0; i < 6; i++)
            {
                data = String.format("%d%s%d%s%d%s%d%s%s", teamNumbers[i], DATA_SEPARATOR, teamScores[0][i], DATA_SEPARATOR, teamScores[1][i], DATA_SEPARATOR, teamScores[2][i], DATA_SEPARATOR, teamPenalties[i]);
                fileCreo.addEntry(data);
            }
            fileCreo.closeFile();
        }




        matchFileScanner.openFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);

        int countLine = 0;
        while(matchFileScanner.hasNextEntry())
        {
            countLine++;
            matchFileScanner.getNextLine();
        }

        String dataArray[] = new String[countLine];

        matchFileScanner.openFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);
        countLine = 0;
        while(matchFileScanner.hasNextEntry())
        {
            dataArray[countLine] = matchFileScanner.getNextLine();
            countLine++;
        }

        fileCreo.openFile(currentDir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);
        for(int j = 0; j < countLine; j++)
        {
            fileCreo.addEntry(dataArray[j]);
        }

        fileCreo.addEntry(String.valueOf(currentMatch));
        fileCreo.closeFile();

        // For Debug purposes
        log.log();
    }
}