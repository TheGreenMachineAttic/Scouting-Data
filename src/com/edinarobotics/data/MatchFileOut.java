/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;

import com.edinarobotics.filer.*;
import com.edinarobotics.scout.Main;
import com.edinarobotics.logger.Logger;

/*
 * @author aoneill
 * @breif
 */

public class MatchFileOut 
{
    private static String workspaceDir = Main.workspaceDir;
    private static String DATA_SEPARATOR = Main.DATA_SEPARATOR;

    private static FileScanner matchFileScanner = new FileScanner();
    private static FileCreator fileCreo = new FileCreator();

    private static Logger log = Main.log;

    public MatchFileOut(int matchNumber, int[] teamNumbers, int[][] teamScores, String[] teamPenalties)
    {
        String matchFile = "Match_" + String.valueOf(matchNumber) + ".txt";
        log.log("Main", "Match File name is " + matchFile);

        if(matchFileScanner.isFileCreated(workspaceDir, matchFile))
        {
            log.log("Match ", "Overwriting existing Match File with new data...");
            fileCreo.openFile(workspaceDir, matchFile);
            fileCreo.addMatchHeader(matchNumber);

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
            fileCreo.closeFile();
        }
        else
        {
            log.log("Main", "Creating new Match File");
            fileCreo.createFile(workspaceDir, matchFile);
            fileCreo.openFile(workspaceDir, matchFile);
            fileCreo.addMatchHeader(matchNumber);

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
            fileCreo.closeFile();
        }
    }
}