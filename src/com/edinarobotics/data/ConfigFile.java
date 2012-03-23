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
    private static Extracter extract = new Extracter();
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
        if(!configScanner.isFileCreated(configDir, configFile))
        {
            log.log(LOG_TAG, "Error in Config File! (Not found)");
            log.log(LOG_TAG, "Creating new file with default values");
            configWrite(configFormat, defaultConfigValues);

            return defaultConfigValues;
        }
        else
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

                return configValues;
            }
        }
    }

    public void configWrite(String[] value)
    {
        configWrite(configFormat, value);
    }

    private void configWrite(String[] id, String[] value)
    {
        if(id.length == value.length)
        {
            fileCreo.createFile(configDir, configFile);
            fileCreo.openFile(configDir, configFile);
            fileCreo.addEntry("# Settings #");

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
            result[i] = extract.contentPast(data[i], 2);
            log.log(LOG_TAG, "Data Line " + i + ": "  + result[i]);
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