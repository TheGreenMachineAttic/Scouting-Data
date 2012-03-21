/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;

import com.edinarobotics.filer.*;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Main;
import java.io.File;

/*
 * @author aoneill
 * @breif
 */

public class DefaultWorkspace
{
    private static Logger log = Main.log;

    // Initialize classes dealing with File operation
    private static FileCreator fileCreo = new FileCreator();
    private static FileScanner matchFileScanner = new FileScanner();

    // Assign a variable the current directory
    private static String matchListFile = Main.matchListFile;
    private static String matchFolderName = Main.matchFolderName;

    private static String workspaceFolderName = Main.workspaceFolderName;
    private static String commentFolderName = Main.commentFolderName;
    private static String teamFolderName = Main.teamFolderName;

    public DefaultWorkspace(String dir)
    {
        // If the default Workspace folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Work Space");
            new File(dir + "/" + workspaceFolderName).mkdir();
        }

        // If the default Team folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName + "/" + teamFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Team Folder");
            new File(dir + "/" + workspaceFolderName + "/" + teamFolderName).mkdir();
        }

        // If the default Comments folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName + "/" + commentFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Comment Folder");
            new File(dir + "/" + workspaceFolderName + "/" + commentFolderName).mkdir();
        }

        // if the Match folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName + "/" + matchFolderName).isDirectory()))
        {
            log.log("Main", "Creating default Match Folder");
            new File(dir + "/" + workspaceFolderName + "/" + matchFolderName).mkdir();
        }

        // If the Match List file is not created, create it.
        if(!matchFileScanner.isFileCreated(dir + "/" + workspaceFolderName, matchListFile))
        {
            log.log("Main", "Creating Match List");
            fileCreo.createFile(dir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);
            fileCreo.openFile(dir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);
            fileCreo.addMatchListHeader();
            fileCreo.closeFile();
        }
    }
}