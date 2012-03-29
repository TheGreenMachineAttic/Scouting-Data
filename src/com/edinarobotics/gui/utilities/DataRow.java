/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.gui.utilities;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;

/*
 * @author aoneill
 * @breif
 */

public class DataRow 
{
    // Logger for the class
    private static Logger log = Global.log;
    private static String LOG_ID = "Data Row";
    
    // Variable to store the passed in information
    private String dataRow[];

    /**
     * Construct the class without passed in data for parsing
     */
    public DataRow() {}
    
    /**
     * Construct the class with the passed in data
     * @param array the data to be put into a row
     */
    public DataRow(String array[])
    {
        dataRow = array;
    }

    /**
     * Construct the class with passed in data. 
     * Converts A 2d array to a 1d array
     * @param array the data to be 
     * @param rowNumber the row number in the array to be extracted
     * @param arrayWidth the width of the array
     */
    public DataRow(String array[][], int rowNumber, int arrayWidth)
    {
        // Assumes that the first dimension is the column count
        
        // String array to store the data
        String result[] = new String[arrayWidth];
        
        // Copy over the array
        System.arraycopy(array[rowNumber], 0, result, 0, arrayWidth);

        // retrun the result
        dataRow = result;
    }

    /**
     * Return the value of the array at the specified member
     * @param member the location to recall
     * @return the value of the array at the member
     */
    public String valueAt(int member)
    {
        return dataRow[member];
    }

    /**
     * Print the data of the Data Row
     * For use in debugging
     */
    public void printRowData()
    {
        log.log(LOG_ID, "--------------");
        log.log(LOG_ID, "Data Row Data:");
        log.log(LOG_ID, "== Length: " + dataRow.length);
        log.log(LOG_ID, "== Data:");
        for(int i = 0; i < dataRow.length; i++)
        {
            log.log(LOG_ID, "== Row[" + i + "]: " + dataRow[i]);
        }
        log.log(LOG_ID, "--------------");
    }

    /**
     * Converts an array of Data Rows into a 2d array
     * @param array the Data Row Array
     * @return the 2d array
     */
    public String[][] dataRowArrayToStringArray(DataRow array[], int finalWidth)
    {
        // Establish length and width data
        int finalLength = array.length;
        
        // Initiaize the variable to be returned
        String result[][] = new String[finalLength][finalWidth];

        // Iterate through the Data Row list and convert the data
        for(int i = 0; i < finalLength; i++)
        {
            for(int j = 0; j < finalWidth; j++)
            {
                result[i][j] = array[i].valueAt(j);
            }
        }

        // Return the resulting array
        return result;
    }
}