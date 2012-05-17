/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;

import com.edinarobotics.filer.*;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;
import java.io.File;

/*
 * @author aoneill
 * @breif
 */

public class DefaultWorkspace
{
    // Logger stuff
    private static Logger log = Global.log;
    private static String LOG_TAG = "Default Workspace";

    // Initialize classes dealing with File operation
    private static FileWriter fileCreo = new FileWriter();
    private static FileReader matchFileScanner = new FileReader();
    private static FileReader teamListFileScanner = new FileReader();

    // Assign variables for various dirs and titles
    private static String matchListFile = Global.matchListFile;
    private static String matchFolderName = Global.matchFolderName;

    private static String teamListFile = Global.teamListFile;

    private static String workspaceFolderName = Global.workspaceFolderName;
    private static String workspaceDir = Global.workspaceDir;
    private static String commentFolderName = Global.commentFolderName;
    private static String teamFolderName = Global.teamFolderName;

    /**
     * Creates a default workspace in a given folder
     * @param dir directory to create the folder in
     */
    public void createDefaultWorkspace(String dir)
    {
        // If the default Workspace folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Work Space");
            new File(dir + "/" + workspaceFolderName).mkdir();
        }

        // If the default Team folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName + "/" + teamFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Team Folder");
            new File(dir + "/" + workspaceFolderName + "/" + teamFolderName).mkdir();
        }

        // If the default Comments folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName + "/" + commentFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Comment Folder");
            new File(dir + "/" + workspaceFolderName + "/" + commentFolderName).mkdir();
        }

        // if the Match folder is not created, create it.
        if(!(new File(dir + "/" + workspaceFolderName + "/" + matchFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Match Folder");
            new File(dir + "/" + workspaceFolderName + "/" + matchFolderName).mkdir();
        }

        // If the Team List file is not created, create it.
        if(!teamListFileScanner.isFileCreated(dir + "/" + workspaceFolderName, matchListFile))
        {
            log.log(LOG_TAG, "Creating Match List file");
            fileCreo.createFile(dir + "/" + workspaceFolderName, teamListFile);
            fileCreo.closeFile();
        }

        // If the Match List file is not created, create it.
        if(!matchFileScanner.isFileCreated(dir + "/" + workspaceFolderName, matchListFile))
        {
            log.log(LOG_TAG, "Creating Match List");
            fileCreo.createFile(dir + "/" + workspaceFolderName + "/" + matchFolderName, matchListFile);
            fileCreo.closeFile();
        }
    }

    /**
     * Check to see if the workspace loaded is legit and fill in gaps
     */
    public void checkWorkspace()
    {
        // If the default Workspace folder is not created, create it.
        if(!(new File(workspaceDir).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Work Space");
            new File(workspaceDir).mkdir();
        }

        // If the default Team folder is not created, create it.
        if(!(new File(workspaceDir + "/" + teamFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Team Folder");
            new File(workspaceDir + "/" + teamFolderName).mkdir();
        }

        // If the default Comments folder is not created, create it.
        if(!(new File(workspaceDir + "/" + commentFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Comment Folder");
            new File(workspaceDir + "/" + commentFolderName).mkdir();
        }

        // if the Match folder is not created, create it.
        if(!(new File(workspaceDir + "/" + matchFolderName).isDirectory()))
        {
            log.log(LOG_TAG, "Creating default Match Folder");
            new File(workspaceDir + "/" + matchFolderName).mkdir();
        }

        // If the Team List file is not created, create it.
        if(!teamListFileScanner.isFileCreated(workspaceDir, teamListFile))
        {
            log.log(LOG_TAG, "Creating Match List file");
            fileCreo.createFile(workspaceDir, teamListFile);
            fileCreo.closeFile();
        }

        // If the Match List file is not created, create it.
        if(!matchFileScanner.isFileCreated(workspaceDir, matchListFile))
        {
            log.log(LOG_TAG, "Creating Match List file");
            fileCreo.createFile(workspaceDir, matchListFile);
            fileCreo.closeFile();
        }
    }

    /**
     * Check to see if a Worksapce is correctly made
     * @return 
     */
    public boolean isEverythingPresent()
    {
        // Boolean to store the result
        boolean result;

        // Check to see if the default Workspace is created
        result = new File(workspaceDir).isDirectory();
        result = result && new File(workspaceDir + "/" + teamFolderName).isDirectory();
        result = result && new File(workspaceDir + "/" + commentFolderName).isDirectory();
        result = result && new File(workspaceDir + "/" + matchFolderName).isDirectory();
        result = result && matchFileScanner.isFileCreated(workspaceDir, matchListFile);
        result = result && teamListFileScanner.isFileCreated(workspaceDir, teamListFile);
        
        // Return the result
        return result;
    }
}