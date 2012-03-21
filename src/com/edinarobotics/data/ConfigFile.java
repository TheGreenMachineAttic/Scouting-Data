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

public class ConfigFile
{
    private static FileScanner configScanner = new FileScanner();
    private static FileCreator fileCreo = new FileCreator();
    private static Logger log = Main.log;

    private static String LOG_TAG = "Config File";
    private static String DATA_SEPARATOR = Main.DATA_SEPARATOR;

    private static String configDir = Main.configFileDir;
    private static String configFile = Main.configFile;
    private static int configEntries = 2;

    private static String[] configFormat = {"deafultWorkspace", "changeLogActivate"};
    private static String[] defaultConfigValues = {System.getProperty("user.dir"), "false"};
    private static String[] configValues = new String[configEntries];


    public String[] configRead()
    {
        // Open the ConfigFile file to read defaults
        configScanner.openFile(configDir, configFile);

        ArrayList<String> list = new ArrayList<String>();

        while(configScanner.hasNextEntry())
        {
            list.add(configScanner.getNextLine());
        }
        configScanner.close();

        list.remove(0);

        if(list.size() > configEntries)
        {
            log.log(LOG_TAG, "Error in Config File! (Too many entries)");
            log.log(LOG_TAG, "Creating new file with default values");

            configWrite(configFormat, defaultConfigValues);

            return defaultConfigValues;
        }
        else
        {
            String[] configData = listToArray(list);
            configValues = extractConfigValues(configData);
        }




//        // Read the config file until the line does not start with '#"
//        // Used to skip a header comment on the file
//        String nextLine = configScanner.getNextLine();
//        while(nextLine.startsWith("#"))
//        {
//            nextLine = configScanner.getNextLine();
//        }
//
//        // Once you have found the content of the config file, take the data you need
//        boolean abort = false;
//        while(!abort)
//        {
//            // If the next line found in the scanner indicates the default Team Dir.
//            // Extract the second entry and store it as the defaultTeaDir
//            if(nextLine.startsWith("defaultTeamDir"))
//            {
//                teamFileDir = extract.extractEntry(nextLine, 2);
//
//                // Due to the file structure of Windows, we need to also extract entry 3 because of the colon
//                // in C:/
//                if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
//                {
//                    teamFileDir = teamFileDir + ":" + extract.extractEntry(nextLine, 3);
//                }
//            }
//
//
//            if(nextLine.startsWith("changeLogActivate"))
//            {
//                if(extract.extractEntry(nextLine, 2).equals("true"))
//                {
//                    logActivate = true;
//                }
//            }
//
//            // If the next line found in the scanner indicates the default Comment Dir.
//            // Extract the second entry and store it as the defaultTeaDir
//            if(nextLine.startsWith("defaultCommentDir"))
//            {
//                commentFileDir = extract.extractEntry(nextLine, 2);
//                if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
//                {
//                    commentFileDir = commentFileDir + ":" + extract.extractEntry(nextLine, 3);
//                }
//            }
//
//            // Once the configScanner runs out of lines, abort the loop, else, get the next line
//            if(!configScanner.hasNextEntry())
//            {
//                abort = true;
//            }
//            else
//            {
//                nextLine = configScanner.getNextLine();
//            }
//        }
        String[] lol = new String[1];
        return lol;
    }

    public void configWrite(String[] id, String[] value)
    {
        if(id.length == value.length)
        {
            fileCreo.createFile(configDir, configFile);
            fileCreo.addEntry("# Default Settings #");

            for(int i = 0; i < id.length; i++)
            {
                fileCreo.addEntry(id[i] + DATA_SEPARATOR + value[i]);
            }

            fileCreo.closeFile();
        }
        else
        {
            log.log(LOG_TAG, "Error in config write out! (Id not equal to content)");
        }
    }

    private String[] extractConfigValues(String[] data)
    {
        String[] result = new String[configEntries];
        for(int i = 0; i < data.length; i++)
        {
            result[i] = data[i];
        }

        return result;
    }

    private String[] listToArray(ArrayList<String> list)
    {
        String[] result = new String[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            result[i] = list.get(i);
        }
        return result;
    }
}