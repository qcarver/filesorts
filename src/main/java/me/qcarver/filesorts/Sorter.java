/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.filesorts;

/**
 * A collection of polymorphic interfaces that both sorters must employ
 * @author Quinn
 */
public interface Sorter {
    public void sort(int[] A);
    public int[] getA();
    public Stats getStats();
}
