/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.filesorts;

/**
 * an enumerator to indicate what type of sort is in context
 * @author Quinn
 */
   public enum SortMode {
        
        MERGE_SORT("mergeSort"),
        INSERTION_SORT("insertionSort"),
        QUICK_SORT("quickSort"),
        HEAP_SORT("heapSort");
        String name;
        @Override
            public String toString(){
            return name;
        }
        
        SortMode(String _name){
            name = _name;
        }
        
        /**
         * basically parses enumeration string into an enumeration
         * if the string doesn't match it silently returns the first enumeration
         * @param _name the exact string which 
         * @return enumeration matching input -- or first enumeration
         */
        static public SortMode getInstanceOf(String _name) {
            SortMode sortMode = SortMode.values()[0];
            for (SortMode enumeration: SortMode.values()){
                if (enumeration.toString().compareTo(_name)==0){
                    sortMode = enumeration;
                }
            }
            return sortMode;
        }
    }
