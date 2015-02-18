/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.filesorts;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Properties;

/**
 *
 * @author Quinn
 */
public class BatchOptions {

    int minArrayLen = 1;
    int maxArrayLen = 10;
    int numRunsPerLen = 1;
    Set<SortMode> whichSorts = null;

    public int getMaxArrayLen() {
        return maxArrayLen;
    }

    public int getMinArrayLen() {
        return minArrayLen;
    }

    public int getNumRunsPerLen() {
        return numRunsPerLen;
    }

    public Set<SortMode> getWhichSorts() {
        return whichSorts;
    }
    
    /**
     * default constructor, initialize with very default options
     */
    public BatchOptions(){
        whichSorts = new HashSet<SortMode>();
        whichSorts.add(SortMode.values()[0]);
    }

    /**
     * Preferred constructor which initializes from CommandLine.Properties
     * @param properties 
     */
    public BatchOptions(Properties properties) {
        init(properties);
    }

    /**
     * the private method called by the constructor to initialize this object
     * from command line Properties passed in
     * @param properties 
     */
    private void init(Properties properties) {
        whichSorts = new HashSet<SortMode>();
        for (Entry entry : properties.entrySet()) {
            String upperKey = entry.getKey().toString().toUpperCase();
            if (upperKey.contains("MIN")) {
                minArrayLen = parseIntValueFromEntry(entry, minArrayLen);
            } else if (upperKey.contains("MAX")) {
                maxArrayLen = parseIntValueFromEntry(entry, maxArrayLen);
            } else if (upperKey.contains("RUNS")) {
                numRunsPerLen = parseIntValueFromEntry(entry, numRunsPerLen);
            } else if (upperKey.contains("SORTS")) {
                String upperValue = entry.getValue().toString().toUpperCase();
                if (upperValue.contains("MERGE") || upperValue.contains("ALL")) 
                        whichSorts.add(SortMode.MERGE_SORT);
                if (upperValue.contains("INSERT") || upperValue.contains("ALL")) 
                        whichSorts.add(SortMode.INSERTION_SORT);
                if (upperValue.contains("QUICK") || upperValue.contains("ALL")) 
                        whichSorts.add(SortMode.QUICK_SORT);
                if (upperValue.contains("HEAP") || upperValue.contains("ALL")) 
                        whichSorts.add(SortMode.HEAP_SORT);
            }
        }
        if (whichSorts.isEmpty())
            whichSorts.add(SortMode.values()[0]);
    }

    /**
     * Helper function to parse integer values in from command line strings
     * @param entry the Entry containing Key,Value. Only value will be parsed
     * @param defaultValue the value that will be assigned if parsing fails
     * @return the integer equivalent of the command line Entry.Value
     */
    private int parseIntValueFromEntry(Entry entry, int defaultValue) {
        int value = defaultValue;
        try {
            value = Integer.parseInt(entry.getValue().toString());
        } catch (NumberFormatException e) {
            System.err.println("Couldn't parse " + entry.getKey()
                    + entry.getValue());
        }
        return value;
    }
    
    /**
     * prints the values of fields in this object populated by the command line
     */
    public void printBatchOptions(){
        String sorts = "";
        for (SortMode mode : whichSorts){
            if (sorts.isEmpty()){
               sorts += "whichSorts = " + mode.toString(); 
            } else {
                sorts += ", " + mode.toString();
            }
        }
        
        System.out.println("minArrayLen = "+ minArrayLen + 
                ", maxArrayLen = " + maxArrayLen +
                ", numRunsPerLen = " + numRunsPerLen +
                ", " + sorts);
    }
}
