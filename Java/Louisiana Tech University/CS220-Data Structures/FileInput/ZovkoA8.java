package FileInput;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author: Ante Zovko
 * @version: November 15th, 2020
 * 
 * Reads an input.txt file containing numbers and
 * writes their average to an output.txt file
 * 
 */
public class ZovkoA8 {



    public static void main(String[] args) {
        
        try {

            Scanner scnr = new Scanner(new File("input.txt"));
            FileWriter writer = new FileWriter(new File("output.txt"));
            
            double sum = 0;
            double counter = 0;

            while(scnr.hasNextDouble()){

                sum += scnr.nextDouble();
                counter++;

            }

            // Round to 3 decimal places
            writer.write(String.format("%.3f", (sum / counter)));

            scnr.close();
            writer.close();
    
    
        }catch(Exception e){
    
            System.out.println("Error processing the file");
    
        }

    }
   


}