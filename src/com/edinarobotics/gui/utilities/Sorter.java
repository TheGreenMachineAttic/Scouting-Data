/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.gui.utilities;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Main;


/*
 * @author aoneill
 * @breif
 */

public class Sorter 
{
    // Declare modes for sorting
    public static final int LOW_TO_HIGH = 1;
    public static final int HIGH_TO_LOW = 2;

    // Declare stuff pertaining to Logging
    private static Logger log = Main.log;
    private static String LOG_TAG = "Sorter";

    // Variable to store the number of Data Points for use in the sorter
    private int DATA_POINTS;

    /**
     * Set the number of Data Points upon initialization
     * @param dataPoints number of data points to be used for sorting
     */
    public Sorter(int dataPoints)
    {
        DATA_POINTS = dataPoints;
    }

    /**
     * Sort the array using the member specified according to the direction
     * @param array data to be sorted
     * @param member column of the data to be sorted by
     * @param direction the direction of the Sorting based on the direction variables
     * @return the sorted array
     */
    public String[][] sortBest(String array[][], int member, int direction)
    {
        // Declare variables needed for sorting
        int arrayLength = getArrayLength(array);
        DataRow list[] = new DataRow[arrayLength];
        DataRow dr2;
        DataRow dr1;
        DataRow parser = new DataRow();

        // Iterate through the array and add the contents to a DataRow
        for(int mainC = 0; mainC < arrayLength; mainC++)
        {
            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
        }

        // Declare variables needed for sorting
        boolean finished = false;
        int iter = 0;
        
        // While the sorting is not finished, continue with the sorting
        while(!finished)
        {
            // Assume its finished
            finished = true;
            
            // Iterate through the length of the array (the number of DataRows)
            for(int tIter = 1; tIter < arrayLength; tIter++)
            {
                // Store the contents of the DataRow to temperary variabes
                dr1 = list[tIter - 1];
                dr2 = list[tIter];

                // Switch case based on the direction
                switch(direction)
                {
                    // Sort Low to High
                    case LOW_TO_HIGH:
                        
                        // If the value of the data at the member for one is greater than the other,
                        // then switch them around
                        if(Double.parseDouble(dr2.valueAt(member)) < Double.parseDouble(dr1.valueAt(member)))
                        {
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            // We are not finished yet, are we?
                            finished = false;
                            
                            // Add to the sort count
                            iter++;
                        }
                        break;
                        
                    // Sort High to Low
                    case HIGH_TO_LOW:
                        
                        // If the value of the data at the member for two is greater than the other,
                        // then switch them around
                        if(Double.parseDouble(dr2.valueAt(member)) > Double.parseDouble(dr1.valueAt(member)))
                        {
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            // We are not finished yet, are we?
                            finished = false;
                            
                            // Add to the sort count
                            iter++;
                        }
                        break;
                        
                    // If the direction specified does not correspond to the one above,
                    // then notify the console and dont do anything and finish
                    default:
                        log.log(LOG_TAG, "Invalid Sorting Option!");
                        break;
                }
            }
        }

        // Tell the console how many loops it took to sort
        log.log(LOG_TAG, "Loops to Sort: " + iter);

        // Return the sorted array
        return parser.dataRowArrayToStringArray(list, DATA_POINTS);
    }

    /**
     * Filters Strings based on content. If there is content, move that row up
     * @param array data to be sorted
     * @param member column of the data to be sorted by
     * @param direction the direction of the Sorting based on the direction variables
     * @return the sorted array
     */
    public String[][] filterPenalties(String array[][], int member, int direction)
    {
        // Declare variables for Sorting
        int arrayLength = getArrayLength(array);
        DataRow list[] = new DataRow[arrayLength];
        DataRow dr2;
        DataRow dr1;
        DataRow parser = new DataRow();

        // Iterate through the array and add data to DataRows
        for(int mainC = 0; mainC < arrayLength; mainC++)
        {
            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
        }

        // Declare variables for use in the following while loop
        boolean finished = false;
        int iter = 0;
        
        // While the sorting is not finished, continue sorting
        while(!finished)
        {
            // Assume that sorting is finished
            finished = true;
            
            // Iterate though the DataRows
            for(int tIter = 1; tIter < arrayLength; tIter++)
            {
                // Store the DataRows from the list to variables temporarily
                dr1 = list[tIter - 1];
                dr2 = list[tIter];

                // Switch case based on direction
                switch(direction)
                {
                    // Sorting for Low to High
                    case LOW_TO_HIGH:
                        
                        // If the value of the data at the member for two has content and the other does not,
                        // then switch them around
                        if(dr2.valueAt(member).equals("none") && !dr1.valueAt(member).equals("none"))
                        {
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            // We are not finished yet, are we?
                            finished = false;
                            
                            // Add to the sort count
                            iter++;
                        }
                        break;
                        
                    // Sorting for High to Low
                    case HIGH_TO_LOW:
                        
                        // If the value of the data at the member for one has content and the other does not,
                        // then switch them around
                        if(!dr2.valueAt(member).equals("none") && dr1.valueAt(member).equals("none"))
                        {
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            // We are not finished yet, are we?
                            finished = false;
                            
                            // Add to the sort count
                            iter++;
                        }
                        break;
                        
                    // If the sorting direction does not correspond with the possible options,
                    // notify the console and stop sorting
                    default:
                        log.log(LOG_TAG, "Invalid Sorting Option!");
                        break;
                }
            }
        }

        // Tell the console how many loops it took to sort
        log.log(LOG_TAG, "Loops to Sort: " + iter);

        // Return the sorted array
        return parser.dataRowArrayToStringArray(list, DATA_POINTS);
    }

    /**
     * Extract the length of the Array
     * @param array the data to be analyzed
     * @return the width of the array
     */
    public int getArrayLength(String array[][])
    {
        // Variables used
        boolean done = false;
        int count = 0;
        
        // While there is more width, continue testing
        while(!done)
        {
            // Try to assign a String as the value from the array
            // If it works, add to the counter
            try
            {
                String xyz = array[count][0];
                count++;
            }
            
            // If it fails, stop the while loop
            catch(Exception e)
            {
                done = true;
            }
        }
        
        // Return the width of the array
        return count;
    }
}