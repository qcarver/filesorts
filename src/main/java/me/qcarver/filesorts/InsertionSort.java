/*
 * from page 18 of Cormen  ...bear in mind this psuedo is 1-based, we won't be
 * ISERTION-SORT(A)
 *  for j = 2 to A.length
 *      key = A[j]
 *      //Insert A[j] into the sorted sequence A[1...j-1]
 *      i = j-1
 *      while i > 0 and A[i]>key
 *          A[i+1] = A[i]
 *          i = i-1
 *      A[i+1] = key
 */
package me.qcarver.filesorts;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Quinn
 */
public class InsertionSort implements Sorter{
    private A A = null;
    private long sortingTime = 0;
    
    @Override
    public void sort(int[] A){
        Date date = new Date();
        long timeStart = date.getTime();
        this.A = new A(A);
        insertionSort(0, A.length-1);  
        date = new Date();
        long timeEnd = date.getTime();
        sortingTime = timeEnd - timeStart;
    }
    
    private void insertionSort(int start, int end){
        for (int j = 1 ; j < end + 1 ; j++){
            int key = A.get(j);
            int i = j-1;
            for (; ((i > -1)&&(A.get(i)>key));i--){
                A.set(i+1, A.get(i));
            }
            A.set(i+1, key);
        }
    }
    
    @Override
    public int[] getA(){
        return A.get();
    }
    
    @Override
    public Stats getStats(){
        return new Stats(SortMode.INSERTION_SORT, A.get().length,
                sortingTime, A.getNumReads(), A.getNumWrites());
    }
}
