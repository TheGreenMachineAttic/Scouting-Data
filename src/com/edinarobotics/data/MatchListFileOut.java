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

public class MatchListFileOut
{
    // Initialize classes dealing with File operation
    private static FileCreator fileCreo = new FileCreator();
    private static FileScanner matchFileScanner = new FileScanner();
    public static Logger log = Main.log;

    // Assign a variable the current directory
    public static String currentDir = Main.currentDir;
    public static String workspaceDir = Main.workspaceDir;

    public static String matchListFile = Main.matchListFile;
    public static String matchFolderName = Main.matchFolderName;

    public MatchListFileOut(int matchNumber)
    {
        matchFileScanner.openFile(workspaceDir, matchListFile);

        ArrayList<String> list = new ArrayList<String>();
        while(matchFileScanner.hasNextEntry())
        {
            list.add(matchFileScanner.getNextLine());
        }

        list.add(String.valueOf(matchNumber));

        fileCreo.openFile(workspaceDir, matchListFile);
        fileCreo.addEntry(list);
        fileCreo.closeFile();

        // For Debug purposes
        log.log();
    }
}