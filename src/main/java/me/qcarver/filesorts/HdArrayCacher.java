/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.qcarver.filesorts;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Quinn
 */
public class HdArrayCacher {
    String fileName;
    
    public HdArrayCacher(String fileName){
        this.fileName = fileName;
    }
    
    public void write(int A[]) {
 
	try{
 
		FileOutputStream fos = new FileOutputStream(fileName);
		PrintWriter dos = new PrintWriter(fos);
 
		for (int i = 0; i < A.length; i++) {
                        if (i > 0)
                            dos.print(", ");
			dos.print(A[i]); //write the number to the file
		}
 
		dos.close();
 
		}
 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
 
	}
    
    public int[] read(){
        List list = new ArrayList<Integer>();
        int A[] = null;
        //reading file line by line in Java using BufferedReader       
        FileInputStream fis = null;
        BufferedReader reader = null;
      
        try {
            fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis));
                     
            boolean eof = false;
            for (; eof == false;) {
                String szNumber ="0"; 
                for (char c = '0' ; c != ','; c = (char) reader.read()) {
                    if (c == (char)-1) {
                        eof = true;
                        break;
                    } else if (c == ' ') 
                        continue;
                    else {
                        szNumber += c;
                        szNumber = szNumber.toString();
                    }
                }
                list.add(Integer.parseInt(szNumber));
            }
          
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        } catch (IOException ex) {
            System.out.println("IOException when opening: " + ex);
          
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
            System.out.println("IOException when closing: " + ex);
            }
        }

        A = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            A[i] = ((Integer)list.get(i)).intValue();
        }
        return A;
    }
}

