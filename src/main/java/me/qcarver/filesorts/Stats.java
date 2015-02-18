/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.filesorts;

/**
 * This object is just used as a tuple-return object to return sorting stats
 * @author Quinn
 */
public class Stats {
    long sortingTime = 0;
    int numReads = 0;
    int numWrites = 0;
    SortMode sortMode = SortMode.values()[0];
    int arrayLen = 0;
    
    public Stats(SortMode mode, int arrayLen, 
            long sortingTime, int numReads, int numWrites){
        this.sortMode = mode;
        this.arrayLen = arrayLen;
        this.sortingTime = sortingTime;
        this.numReads = numReads;
        this.numWrites = numWrites;      
    }
    
    /**
     * copy constructor
     * @param rhs 
     */
    public Stats(Stats rhs){
        this.sortMode = rhs.sortMode;
        this.arrayLen = rhs.arrayLen;
        this.sortingTime = rhs.sortingTime;
        this.numReads = rhs.numReads;
        this.numWrites = rhs.numWrites;  
    }
    
    /**
     * updates object as the running average of Stats
     * @param iteration the current iteration in a run with the same parameters
     * @param current the Stats output from the current iteration
     */
    public void keepRunningAverage(int iteration, Stats current){
        //NOTE .. this is pretty lossy because of integers going to floor
        sortMode = current.sortMode;
        arrayLen = ((arrayLen * iteration) + current.arrayLen) / (iteration + 1);
        sortingTime = ((sortingTime * iteration) + current.sortingTime) / (iteration + 1);
        numReads = ((numReads * iteration) + current.numReads) / (iteration + 1);
        numWrites = ((numWrites * iteration) + current.numWrites) / (iteration + 1);
    }
    
    public void printStats(){
        System.out.println(this.toString());
    }
    
    @Override
    public String toString(){
        String output = ("Sort type: " + sortMode
                + " arrayLen " + arrayLen
                + ", time: " + sortingTime
                + ", numReads:" + numReads
                + ", numWrites: " + numWrites);
        return output;
    }
}
