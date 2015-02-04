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
}
