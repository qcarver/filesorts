/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.filesorts;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.util.Random;
import org.apache.commons.cli.OptionBuilder;

/**
 *
 * @author Quinn
 */
public class Configuration {
    private Options options = null;
    
    SortMode sortMode = SortMode.MERGE_SORT;
    boolean help = false;
    int arraySize = 10;
    boolean batch = false;
    boolean verbose = false;
    Random random = new Random();
    long seed = random.nextInt();
       
    Configuration(String[] args) {
        parse(args);
    }
    
    /**
     * the size of the array to be sorted
     * @return the number of ints in the array
     */
    public int getArraySize(){
        return arraySize;
    }

    /**
     * either the default sort to use or the sort specified by the user
     * @return sort type
     */
    public SortMode getSortMode() {
        return sortMode;
    }
    
    /**
     * did the user request the Help menu
     * @return 
     */
    public boolean getHelp(){
        return help;
    }
    
    /**
     * did the user request to run in batch mode
     * @return 
     */
    public boolean getBatchMode(){
        return batch;
    }
    

    /**
     * Parses command line arguments into an object. After invoking this method
     * Use getters in this class to query the values of command line arguments.
     *
     * @param args
     */
    void parse(String[] args) {
        CommandLine cmd = null;
                
        this.options = new Options();
        options.addOption("b", "batchMode", false, "do several runs from 1 to "+
                " a specified arraySize, default is " + arraySize +
                " sortMode " + sortMode.toString());
        options.addOption("v", "verbose", false, "show more output " +
                "default is, " + ((verbose)?"true":"false"));
        options.addOption("e", "seed", true, "specify a random seed used to "+
                "make unsorted input lists, default is random");
        options.addOption("a", "arraySize", true, "number of ints to sort, "
                + "default is " + arraySize);
        options.addOption("s", "sortMode", true, 
                SortMode.MERGE_SORT.toString()+ " or " +
                SortMode.INSERTION_SORT.toString() + ". default is "+
                sortMode.toString());
        options.addOption(
                OptionBuilder.withArgName("property=value")
                .hasArgs(2)
                .withValueSeparator()
                .withDescription("use value for given property")
                .create("B")
                );
        


        CommandLineParser parser = new BasicParser();
        try {
            cmd = parser.parse(options, args);
            help = (cmd.hasOption("h")) ? true : false;
            batch = (cmd.hasOption("b"))? true : false;
            verbose = (cmd.hasOption("v"))? true: false;
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FileSorter",
                    App.class.getSimpleName() + " [options]",
                    options,
                    "", true);
            System.exit(0);
        }
        if (help){
             HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("FileSorter",
                    App.class.getSimpleName() + " [options]",
                    options,
                    "", true);
            System.exit(0);           
        }
        
        if (batch){
            BatchOptions batchOptions = 
                    new BatchOptions(cmd.getOptionProperties("B")); 
                    batchOptions.printBatchOptions();
        }

        arraySize = (cmd.hasOption("a")?
                Integer.parseInt(cmd.getOptionValue("arraySize")) : arraySize);
        sortMode = (cmd.hasOption("s")?
                    sortMode = 
                SortMode.getInstanceOf(cmd.getOptionValue("sortMode")):
                sortMode);
        seed = (cmd.hasOption("e")?
                Integer.parseInt(cmd.getOptionValue("seed")) : seed);
    }

    Options getOptions() {
        return options;
    }
}
