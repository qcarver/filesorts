package me.qcarver.filesorts;
import java.util.Random;
import org.apache.commons.cli.HelpFormatter;

/**
 * Hello world!
 *
 */
public class App 
{
    private int A[] = null;
    private Sorter sorter = null;
    boolean verbose = false;
    
    
    public static void main(String[] args) {
        //Gui.main("me.qcarver.wumpus.Gui");
        Configuration configuration = new Configuration(args);

        if (!configuration.getHelp()) {
            App fileSorter = new App(configuration);
            if (configuration.getBatchMode()) {
                for (int i = 1; i < configuration.getArraySize(); i++) {
                    fileSorter.init(configuration.sortMode, i, 
                            configuration.verbose, configuration.seed);
                    fileSorter.go();
                }
            } else {
                fileSorter.go();
            }
        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FileSorter [options]\n",
                    "A project for CSE 5211\n",
                    configuration.getOptions(),
                    "", true);
        }
    }
    
    public App(Configuration configuration){
        init(configuration.sortMode, configuration.arraySize,
                configuration.verbose, configuration.seed);        
    }
    
    public void go(){
        if (verbose == true){
            System.out.println("Input Array");
            printArray(A);
        }
        sorter.sort(A);
        A=sorter.getA();
        if (verbose == true){
            A=sorter.getA();
            System.out.println("Output Array");
            printArray(A);
        }
        Stats stats = sorter.getStats();
        System.out.println("Sort type: " + stats.sortMode +
                            " arrayLen " + stats.arrayLen +
                            ", time: " + stats.sortingTime +
                            ", numReads:" + stats.numReads +
                            ", numWrites: " + stats.numWrites);
    }
    
    private void init(SortMode sortMode, int arraySize, boolean verbose, 
            long seed){
        this.verbose = verbose;
        A = makeRandomArray(arraySize, seed);

        if (sortMode == SortMode.MERGE_SORT){
            if (verbose) System.out.println("Merge Sorting...");
            sorter = new MergeSort();
        } else if (sortMode == SortMode.INSERTION_SORT) {
            if (verbose) System.out.println("Insertion Sorting..."); 
            sorter = new InsertionSort();
        } else if (sortMode == SortMode.QUICK_SORT){
            if (verbose) System.out.println("QuickSorting..."); 
            sorter = new QuickSort();
        } else {
            if (verbose) System.out.print("heapSorting...");
            sorter = new HeapSort();
        }
    }
    
    static public void printArray(int[] A){
        for (int i = 0; i < A.length; i++){
            System.out.print((i == 0)?A[i]:", "+A[i]);
        }
        System.out.println();
    }
    
    public int[] makeRandomArray(int length, long seed){
        int A[] = new int[length];
        if (verbose){
            System.out.println("Making rnd array of length " + length +
                    " from seed " + seed );
        }
        Random random = new Random(seed);
        for (int i = 0; i < A.length; i ++){
            A[i] = random.nextInt(65535);
        }
        return A;
    }
}
