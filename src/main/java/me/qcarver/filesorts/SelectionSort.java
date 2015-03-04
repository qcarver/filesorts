/*
 * this is a version of QuickSort which uses a median of medians for
 * a pivot value
 */
package me.qcarver.filesorts;

import java.util.Date;

/**
 *
 * @author Quinn
 */
public class SelectionSort implements Sorter{
    private A A = null;
    private long sortingTime = 0;
    boolean verbose = true;
        
    /**
     * a custom override of the InsertionSortClass which makes it easier
     * for us to inject our own instance of A for sorting and return a
     * median (pivot) value
     */
    class PivotSorter extends InsertionSort{
            int start = 0, end = 0;
            void sort(A a, int start, int end){
                    this.A = a;
                    this.start = start;
                    this.end = end;
                    insertionSort(start, end);
                }
            int getPivot(){
                return start + (end - start)/2;
            }
    };

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
    
    /**
     * gets the medians of five adjacent subranges of start-to-end
     * and the pivot as the median value from those five subrange medians
     * @param start
     * @param end
     * @return 
     */
    private int getPivot(int start, int end){
        int length = end - start;
        int midIndex = -1, minIndex = -1, maxIndex = -1;
        String unsortedPivots = "";
        for (int n = 0; n < 5 ; n ++){
            PivotSorter pivotSorter = new PivotSorter();
            pivotSorter.sort(A,n * length/5, 
                    (n == 4)?length: n * length/5 + length/5);
            int candidate = pivotSorter.getPivot();
            if (verbose == true)
                unsortedPivots 
                        += ((maxIndex == -1)?"":", ") 
                        + A.get(candidate) + " ";
            if (maxIndex == -1){
                maxIndex = candidate;
            } else if (minIndex == -1){
                minIndex = candidate;
            } else if (midIndex == -1){ //third or sometimes fifth iteration
                //swap max and min?
                if (A.get(maxIndex)<A.get(minIndex)){
                    int _minIndex = minIndex;
                    minIndex = maxIndex;
                    maxIndex = _minIndex;
                }
                midIndex = candidate;
                //swap mid and min?
                if (A.get(midIndex)<A.get(minIndex)){
                    int _minIndex = minIndex;
                    minIndex = midIndex;
                    midIndex = _minIndex;
                }
                //swap mid and max?
                if (A.get(midIndex)>A.get(maxIndex)){
                    int _maxIndex = maxIndex;
                    maxIndex = midIndex;
                    midIndex = _maxIndex;
                }
            } else { //this is the fourth or sometimes fifth iteration
                if (A.get(candidate)>A.get(maxIndex)){
                    minIndex = midIndex;
                    midIndex = maxIndex;
                    maxIndex = candidate;
                } else if (A.get(candidate)<A.get(minIndex)){
                    maxIndex = midIndex;
                    midIndex = minIndex;
                    minIndex = candidate;
                } else {
                    if (midIndex < candidate){
                        minIndex = midIndex;
                        maxIndex = candidate;
                    } else {
                        minIndex = candidate;
                        maxIndex = minIndex;
                    }
                    midIndex =-1;
                }
            }            
        }
        if (midIndex == -1) midIndex = minIndex;
        
        if (verbose) System.out.println("medians: {" + unsortedPivots +
                "} pivot is " + A.get(midIndex));
        
        return midIndex;
    }
    
    private void quickSort(int start, int end){
        if (start < end){
            int pivot = getPivot(start, end);
            int middle = partition(start, pivot, end);
            quickSort(start, middle-1);
            quickSort(middle+1, end);
        }
    }
    
    private int partition(int start, int pivot, int end){
        int x = A.get(pivot);
        int i = start-1;
        for (int j = start; j < end; j++){
            if ((j!=x)&&(A.get(j)<=x)){
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
        return new Stats(SortMode.QUICK_SORT, A.length(),
        sortingTime, A.getNumReads(), A.getNumWrites());
    }
    
}
