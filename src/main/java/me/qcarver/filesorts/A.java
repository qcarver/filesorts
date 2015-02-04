package me.qcarver.filesorts;

/**
 * Purpose of this class is to act as much like an array as possible,
 * but to access the entire array from disk every time there is a read and write
 * ...it's for an academic exercise
 * 
 * @author Quinn
 */
public class A {
    private int A[] = null;
    HdArrayCacher disk = null;
    private int reads = 0, writes = 0;
    
    /**
     * construct new Array to be cached to disk
     * @param A 
     */
    public A(int A[]){
        disk = new HdArrayCacher("hdArrayCache.txt");
        this.A = A;
        disk.write(this.A);
        writes ++;
    }
    
    /**
     * returns the value at an index
     * @param index 
     * @return int value
     */
    public int get(int index){
        A = disk.read();
        reads ++;
        return A[index];
    }
    
    /**
     * A[index] = value
     * @param index index where value will be changed
     * @param value new value to store at index
     */
    public void set(int index, int value){
       A[index] = value; 
       writes ++;
       disk.write(A);
    }
    
    /**
     * returns the entire array
     * @return entire array
     */
    public int[] get(){
        A = disk.read();
        reads ++;
        return A;
    }
    
    /**
     * returns the number of times the array was read to disk
     * @return 
     */
    public int getNumReads(){
        return reads;
    }
    
    /**
     * returns the number of times the array was written to disk
     * @return 
     */
    public int getNumWrites(){
        return writes;
    }

}
