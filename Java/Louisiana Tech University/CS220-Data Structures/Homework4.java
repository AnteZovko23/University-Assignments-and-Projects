import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("all")


/**
 * Author: Ante Zovko Version: September 20th, 2020
 * 
 * Homework 4 – Switch, Try-Catch, Arrays
 * 
 */


public class Homework4 {


    public static void main(String[] args) {
        

        
        // 1. Write what is printed by the following program.
    
        demo1();

        /**
         *  Output 
         * 
         * 1
         * ...
         * 5
         * 6
         * 7
         * 8
         * ...
         * 31
         * 
         */


         // 2. Write a program that will offer four menu choices for unit conversion: inches to feet,
         // feet to inches, feet to yards, and yards to feet.

         Scanner scnr = new Scanner(System.in);
         
         try {
            
            // while(true)
            System.out.println("Welcome to my unit conversion program!");
            System.out.println("1. Inches to feet");
            System.out.println("2. Feet to inches");
            System.out.println("3. Feet to yards");
            System.out.println("4. Yards to feet");

            System.out.print("\nEnter your menu choice: ");
            int choice = scnr.nextInt();
            
            unitConversion(choice, scnr);

         } catch(Exception e) {
             System.out.println("Invalid input.");
         }


         /**
          * Output:
          *
          * Welcome to my unit conversion program!
            1. Inches to feet
            2. Feet to inches
            3. Feet to yards
            4. Yards to feet

            Enter your menu choice: 4
            Enter the number of yards: 3.0 
            3.0 yards is equal to 9.0 feet
          */


          // 3.Write a program that reads two integers (using int types), will use integer division to
          // divide the first integer by the second integer, and then prints the result

          integerDivision();

          /**
           * Output:
           * 
           * Input the first integer: 16
            Input the first integer: 0
            Cannot divide by zero. Try again.
            Input the first integer: 16
            Input the first integer: 3
            Their integer quotient is: 5
           * 
           */


          // 4. Write a program that reads a series of positive integers and will print the integers in
          // reverse order of entry

          reverseInts(20);

          /**
           * Output:
           * 
        *   Input an integer or 0 to stop: 2
            Input an integer or 0 to stop: 11
            Input an integer or 0 to stop: 5
            Input an integer or 0 to stop: 10
            Input an integer or 0 to stop: 8 
            Input an integer or 0 to stop: 0

            The reverse order of your entries is: 
            8
            10
            5
            11
            2
           * 
           * 
           */


        // 5. Write a program that will create a 3 × 5 array of integers and fill it with random
        // numbers from 0 to 9.

        intMatrix(3, 5, 0, 9, 4);


        /**
         * Output:
         * 
         *  9 4 6 5 6 
            0 7 5 4 2 
            2 0 4 0 9 

            Positions (0, 1), (1, 3), (2, 2) contain 4
         * 
         */



    }



    // 1.
    private static void demo1() {

        int x;

        for (x = 0; x <= 50; ++x) {

            if (x % 3 == 0)
                continue;
            System.out.println(x);

            if (x == 31)
                break;

            }

    }


    // 2.
    private static void unitConversion(int choice, Scanner scnr) {

        float userValue, value;

        switch (choice) {

            case 1:
                System.out.print("Enter the number of inches: ");
                userValue = scnr.nextFloat();                
                value = userValue / 12;
                System.out.printf("%.1f inches is equal to %.1f feet\n", userValue, value);
                break;
            
            case 2:
                System.out.print("Enter the number of feet: ");
                userValue = scnr.nextFloat();       
                value = userValue * 12;
                System.out.printf("%.1f feet is equal to %.1f inches\n",  userValue, value);
                break;

            case 3:
                System.out.print("Enter the number of feet: ");
                userValue = scnr.nextFloat();
                value = userValue / 3;
                System.out.printf("%.1f feet is equal to %.1f yards\n", userValue, value);
                break;
            
            case 4:
                System.out.print("Enter the number of yards: ");
                userValue = scnr.nextFloat();        
                value = userValue * 3;
                System.out.printf("%.1f yards is equal to %.1f feet\n",  userValue, value);
                break;

            default:
                System.out.println("Illegal choice. I quit!");
                // System.exit(1);
                break;
        }
    }


      // 3.
      private static void integerDivision() {
        Scanner scnr = new Scanner(System.in);

        while(true){
            try {

                System.out.print("Input the first integer: ");
                int firstInt = scnr.nextInt();
    
                System.out.print("Input the second integer: ");
                int secondInt = scnr.nextInt();

                System.out.println("Their integer quotient is: " + (firstInt/secondInt));
                break;
                
            }catch(ArithmeticException ae){

                System.out.println("Cannot divide by zero. Try again.");
                continue;

            } catch (Exception e) {
                
                System.out.println("Invalid input.");
                scnr.next();
                continue;

            }

        }
        

    }

    // 4.
    private static void reverseInts(int limit){

        Scanner scnr = new Scanner(System.in);
        int[] arr = new int[limit];
        int counter = 0;
        int value;

        for(int i = 0; i < limit; i++) {

            try{

                System.out.print("Input an integer or 0 to stop: ");
                
                value = scnr.nextInt();

                if(value == 0){
                   break;
                }
                counter++;
                arr[i] = value;
                
               
            }catch(Exception e){

                System.out.println("Invalid Input.");
                i -= 1;
                scnr.next();
                continue;
            }
        }

        
        System.out.println("\nThe reverse order of your entries is: ");
        for(int i = counter - 1; i >= 0; i--){

            System.out.println(arr[i]);

        }
    }


    // 5. 
    /**
     * 
     * @param rows rows of the matrix
     * @param cols columns of the matrix
     * @param lowerLimit lower limit of the random nums
     * @param upperLimit upper limit of the random nums
     * @param target the number to be found
     */
    private static void intMatrix(int rows, int cols, int lowerLimit, int upperLimit, int target) {

        int[][] imx = new int[rows][cols];
        ArrayList<String> pairs = new ArrayList<>();

        for(rows = 0; rows < imx.length; rows++){

            for(cols = 0; cols < imx[0].length; cols++){

                imx[rows][cols] = (int) (Math.random() * upperLimit) + lowerLimit;
                if(imx[rows][cols] == target){
                    pairs.add(String.format("(%d, %d)", rows, cols));
                }

            }

        }

        for(rows = 0; rows < imx.length; rows++){

            for(cols = 0; cols < imx[0].length; cols++){

                System.out.print(imx[rows][cols] + " ");

            }

            System.out.print("\n");

        }
        
        if(!pairs.isEmpty())
            System.out.println(String.format("\nPositions %s contain %d", pairs, target).replaceAll("\\[|\\]", ""));
        else
            System.out.printf("\nNo %ds in the matrix.\n", target);

    }

}