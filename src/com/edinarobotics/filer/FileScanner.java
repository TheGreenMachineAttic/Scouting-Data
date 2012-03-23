package com.edinarobotics.filer;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Main;

import java.io.File;
import java.util.Scanner;

/**
 * @author Alex O'Neill
 * @breif A class to take data from a text file
 */

public class FileScanner
{
    private Scanner read;
    private Logger log = Main.log;
    private static final String LOG_TAG = "File Scanner";

    private String recentName;
    private String recentPath;

    // Used to open the text file in the Scanner
    public void openFile(String path, String name)
    {
        // Try to open the file at the path specified, and warn the user if it
        // fails

        recentName = name;
        recentPath = path;

        log.log(LOG_TAG, "Opening " + name + " in " + path);
        try
        {
            read = new Scanner(new File(path + "/" + name));
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, "Could not open " + name + " in " + path);
        }
    }

    // Test to see if the file is created
    public boolean isFileCreated(String path, String name)
    {
        boolean res = false;

        // Try to find out if the file has content, and return true
        // If the file cant be read, warm the user
        try
        {
            read = new Scanner(new File(path + "/" + name));

            if(read.hasNext())
            {
                res = true;
            }
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, name + " not found in " + path);
        }
        return res;
    }

    // Used to check if the file has another entry
    public boolean hasNextEntry()
    {
        boolean result = false;

        // If the Scanner can read another line, return true
        result = read.hasNext();
        return result;
    }

    // Used to return the next line of text from the file
    public String getNextLine()
    {
        String result = "";

        // Try to read the file and return the result
        // If it fails, tell the user
        try
        {
            result = read.nextLine();
        }
        catch(Exception e)
        {
           log.log(LOG_TAG, "Error getting next line");
        }
        return result;
    }

    // Close the file to prevent memory leaks
    public void close()
    {
        try
        {
            log.log(LOG_TAG, "Closing " + recentName + " in " + recentPath);
            read.close();
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, "Error closing the stream");
        }
    }
}