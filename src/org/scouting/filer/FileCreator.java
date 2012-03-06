package org.scouting.filer;

import java.util.Formatter;
import org.scouting.scout.Main;

/**
 * @author Alex O'Neill
 * @breif A class to create a text file
 */
public class FileCreator 
{
    private Formatter format;
    private String SEPARATOR = Main.DATA_SEPARATOR;

    // Used to create the text file
    public void createFile(String path, String name)
    {
        // Try to create the file, and warn the user if it fails
        try
        {
            System.out.println("Formatter: Creating " + name + " in " + path);
            format = new Formatter(path + "/" + name);
        }
        catch(Exception e)
        {
            System.out.println("Could not create file... D:");
        }
    }

    // Used to open the text file
    public void openFile(String path, String name)
    {
        // Try to open the file for editing, and warn the user if it fails
        try
        {
            System.out.println("Formatter: Opening " + name + " in " + path);
            format = new Formatter(path + "/" + name);
        }
        catch(Exception e)
        {
            System.out.println("Could not open file... D:");
        }
    }

    // Used to add directions to the config text file
    public void addConfigEntries()
    {
        // Once the file is open in the Formatter, put in the directions
        format.format("%s", "# Default Settings" + System.getProperty("line.separator"));
        format.format("%s%s%s%s%s", "defaultTeamDir", SEPARATOR, System.getProperty("user.dir"), "/Workspace/TeamDir", System.getProperty("line.separator"));
        format.format("%s%s%s%s%s", "defaultCommentDir", SEPARATOR, System.getProperty("user.dir"), "/Workspace/Comments", System.getProperty("line.separator"));
        format.format("%s%s%s%s", "changeLogActivate", SEPARATOR, "false", System.getProperty("line.separator"));
    }

    // Used to update data in the config text file
    public void addUpdatedConfigEntries(String teamFileDir, String commentFileDir, boolean changeLogActivate)
    {
        format.format("%s", "# Default Settings" + SEPARATOR + System.getProperty("line.separator"));
        format.format("%s", "defaultTeamDir" + SEPARATOR  + teamFileDir + System.getProperty("line.separator"));
        format.format("%s", "defaultCommentDir" + SEPARATOR  + commentFileDir + System.getProperty("line.separator"));
        format.format("%s%b%s", "changeLogActivate" + SEPARATOR , changeLogActivate, System.getProperty("line.separator"));
    }

    // Used to add a header to the team text file
    public void addTeamHeader()
    {
        format.format("%s%s", "# Format #", System.getProperty("line.separator"));
        format.format("%s%s", "# <roundNum>" + SEPARATOR , "<autoPoints>" + SEPARATOR , "<mainPoints>" + SEPARATOR , "<endPoints>" + SEPARATOR ,"<penalties>", System.getProperty("line.separator"));
    }

    // Used to add a header to the team's commnet text file
    public void addCommentHeader()
    {
        format.format("%s%s", "# Comments #", System.getProperty("line.separator"));
    }

    // Used to add a header to the team list text file
    public void addTeamListHeader()
    {
        format.format("%s%s", "# Team List #", System.getProperty("line.separator"));
    }

    // Adds a generic entry with a carriage return
    public void addEntry(String entry)
    {
        format.format("%s", entry + System.getProperty("line.separator"));
    }

    // Add only a new line if there is no content
    public void addEntry()
    {
        format.format("%s", System.getProperty("line.separator"));
    }

    // Used to close the text file
    // Make sure you do this!! It wont actaully add stuff to your file if you
    // don't!
    public void closeFile()
    {
        // Close the Formatter when you are done
        System.out.println("Formatter: Closing file");
        format.close();
    }

    public void addMatchHeader(int match)
    {
        format.format("%s%d%s%s", "# Match ", match, " #", System.getProperty("line.separator"));
    }

    public void addMatchListHeader()
    {
        format.format("%s%s", "# Match List #", System.getProperty("line.separator"));
    }
}
