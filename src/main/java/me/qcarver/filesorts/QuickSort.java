/*
 * QUICKSORT(A,p,r)
 *  if p < r
 *      q=PARTITION(A,p,r)
 *      QUICKSORT(A,p,q-1)
 *      QUICKSORT(A,q+1,r)
 * 
 * PARTITION(A,p,r)
 *  x = A[r]
 *  i = p-1
 *  for j = p to r-1
 *      if A[j] ≤ x
 *          i = i + 1
 *          exchange A[i] with A[j]
 *  exchange A[i+1] with A[r]
 *  return i+1
 */
package me.qcarver.filesorts;

import java.util.Date;

/**
 *
 * @author Quinn
 */
public class QuickSort implements Sorter{
    private A A = null;
    private long sortingTime = 0;

    @Override
    public void sort(int[] A) {
        Date date = new Date();
        long timeStart = date.getTime();
        this.A = new A(A);
        quickSort(0, A.length-1);  
        date = new Date();
        long timeEnd = date.getTime();
        sortingTime = timeEnd - timeStart;
    }
    
    private void quickSort(int start, int end){
        if (start < end){
            int middle = partition(start, end);
            quickSort(start, middle-1);
            quickSort(middle+1, end);
        }
    }
    
    private int partition(int start, int end){
        int x = A.get(end);
        int i = start-1;
        for (int j = start; j < end; j++){
            if (A.get(j)<=x){
                ++i;
                A.exchange(i,j);
            }            
        }
        A.exchange(i+1,end);
        return i+1;
    }

    @Override
    public int[] getA() {
        return A.get();
    }

    @Override
    public Stats getStats() {
        return new Stats(SortMode.QUICK_SORT, A.get().length,
        sortingTime, A.getNumReads(), A.getNumWrites());
    }
    
}
