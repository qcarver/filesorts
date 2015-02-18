/*
 * from Cormen, Introduction to Algorithms 3rd Ed, Ch.6
 * 
 * MAX-HEAPIFY(A,i)
 *  l = LEFT(i)
 *  r = RIGHT(i)
 *  if l ≤ heap-size and A[l] > A[i]
 *      largest = l
 *  else largest = i
 *  if r ≤ heap-size and A[r] > A[largest]
 *      largest = r
 *  if largest ≠ i
 *      exchange A[i] with A[largest]
 *      MAX-HEAPIFY(A,largest)
 * 
 * BUILD-MAX-HEAP(A)
 *  A.heap-size = A.length
 *  for i = [A.length/2]downto 1
 *      MAX-HEAPIFY(A,i)
 * 
 * HEAPSORT(A)
 *  BUILD-MAX-HEAP(A)
 *  for i=A.length downto 2
 *      exchange A[1] with A[i]
 *      A.heap-size = A.heap-size-1
 *      MAX-HEAPIFY(A,1)
 *  
 */
package me.qcarver.filesorts;

import java.util.Date;

/**
 *
 * @author Quinn
 */
public class HeapSort implements Sorter{
    private A A = null;
    private long sortingTime = 0;
    private int last = 0;
    
    @Override
    public void sort(int[] A) {
        Date date = new Date();
        long timeStart = date.getTime();
        this.A = new A(A);
        last = this.A.length()-1;
        heapSort();   
        date = new Date();
        long timeEnd = date.getTime();
        sortingTime = timeEnd - timeStart;
    }
    
    private void heapSort(){
       buildMaxHeap();
       for (int i = A.length() - 1; i > -1; i--){
           A.exchange(0, i);
           --last;
           maxHeapify(0);
        }
    }
    
    private void buildMaxHeap(){
        last = A.length() - 1;
        for (int i = last/2 ; i > -1 ; i--)
            maxHeapify(i);
    }
    
    private int parent(int i){
        return i/2;
    }
    
    private int leftChild(int i){
        return 2 * i;
    }
    
    private int rightChild(int i){
        return 2 * i + 1;
    }
    
    private void maxHeapify(int i){
        int largest = i;
        int l = leftChild(i);
        int r = rightChild(i);
        if ((l <= last) && (A.get(l) > A.get(i))){
            largest = l;
        }
        if (( r <= last) && (A.get(r) > A.get(largest))){
            largest = r;
        }
        if (largest != i){
            A.exchange(i, largest);
            maxHeapify(largest);
        }
    }

    @Override
    public int[] getA() {
        return A.get();
    }

    @Override
    public Stats getStats() {
        return new Stats(SortMode.HEAP_SORT, A.get().length,
        sortingTime, A.getNumReads(), A.getNumWrites());
    }
}
