import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("all")

/**
 * 
 * Author: Ante Zovko 
 * Version: September 14th, 2020
 * 
 * Loops
 * 
 */
public class Homework3 {

    /********** Main Demo **************/
    public static void main(String[] args) {
        
    // 1. Write what is printed by the following program.

        demo1();

        /**
         *  Output:
         * 
         *  1
            4
            9
            16
            25
            36
            49
            64
            81
            100
            Total is 385
         * 
         * 
         */


    // 2. Write what is printed by the following program.

        demo2(10, 10);

        /**
         *  Output:
         * 
         *  >>>>>>>>>>
            <<<<<<<<<<
            >>>>>>>>>>
            <<<<<<<<<<
            >>>>>>>>>>
            <<<<<<<<<<
            >>>>>>>>>>
            <<<<<<<<<<
            >>>>>>>>>>
            <<<<<<<<<<
         * 
         */

    // 3. Write a program that uses a for-loop to print a table of integers from 0 to 256 and their
    // corresponding char values.

        intCharPairs(0, 256);

        /**
         *  Output:
         * 
         *  -------------------
            |Integer|Character|
            -------------------
            ...
            -------------------
            |59|;|
            -------------------
            |60|<|
            -------------------
            |61|=|
            -------------------
            |62|>|
            -------------------
            |63|?|
            -------------------
            |64|@|
            -------------------
            ...
         * 
         */

    // 4. Consider the following function.
    // n/2, n is even
    // 3n + 1, n is odd

    // Write a program that will read a positive integer from the user and will print the number
    // of times the function T must be applied to the integer until the result is 1

        try{

            System.out.println("Enter a number bigger than 0: ");
            Scanner scnr = new Scanner(System.in);
            int n = scnr.nextInt();

            if(n < 0){
                throw new Exception();
            }

            System.out.println("Number of function calls: " + T(n));

        } catch(Exception e){
            System.out.println("Invalid Input.");
        }

        /**
         *  Output:
         * 
         *  Enter a number bigger than 0: 
            3
            Number of function calls: 7 
         * 
         */

        
    // 5. Write a program that uses a do-while loop to read a series of non-negative integers
    // from the user and will print the largest integer that was entered

        System.out.println("You entered: " + seriesOfInts());

        /**
         *  Output:
         * 
         *  Enter an integer or -1 to exit: 
            2

            Enter an integer or -1 to exit: 
            4

            Enter an integer or -1 to exit: 
            12

            Enter an integer or -1 to exit: 
            14

            Enter an integer or -1 to exit: 
            -1
            You entered: [2, 4, 12, 14]
         * 
         */


    // 6. Use the method System.currentTimeMillis() (which returns type long) to
    //    display the amount of time it takes to do the following tasks. (You may need to use a
    //    variable of type long for the sums.)
    //    (a) Compute and display the sum of the integers from 1 to 1,000,000.
    //    (b) Compute and display the sum of the integers from 1 to 10,000,000.
    //    (c) Compute and display the sum of the integers from 1 to 100,000,000.
    //    (d) Compute and display the sum of the integers from 1 to 1,000,000,000.

        long value = 1000000;
        for(int i = 0; i < 4; i++){

            long[] values = benchmarkSums(value);
            System.out.println("\nSum: "+ String.format("%,d\n", values[1])+ "Time:\n" + values[0] + " ms. \n" + values[0]/1000.0 + " seconds");
            value *= 10;


        }

        /**
         *  Output:
         * 
         *  Sum: 499,999,500,000
            Time:
            4 ms. 
            0.004 seconds

            Sum: 49,999,995,000,000
            Time:
            6 ms. 
            0.006 seconds

            Sum: 4,999,999,950,000,000
            Time:
            43 ms. 
            0.043 seconds

            Sum: 499,999,999,500,000,000
            Time:
            361 ms. 
            0.361 seconds
         * 
         * 
         */

    }


    /**
     * 1. Write what is printed by the following program.
     * 
     * 
     */
    private static void demo1() {

        int x = 1;
        int total = 0;

        while (x <= 10) {
            int y = x * x;
            System.out.println(y);
            total += y;
            x++;
        }

        System.out.printf("Total is %d%n", total);

    }


    /**
     * 2. Write what is printed by the following program.
     * 
     * If row is odd print > else print <
     * 
     * @param rows number of rows
     * @param cols number of columns
     * 
     */
    private static void demo2(int rows, int cols) {

        int row = rows;

        while (row >= 1) {
            int col = 1;

            while (col <= cols) {
                System.out.print(row % 2 == 1 ? "<" : ">");
                col++;
                }

            row--;
            System.out.println();
        }


    }

    /**
     * 3. Write a program that uses a for-loop to print a table of integers from 0 to 256 and their
       corresponding char values
     * 
     * @param lowerLimit initial loop value
     * @param upperLimit upper loop value
     */
    private static void intCharPairs(int lowerLimit, int upperLimit) {
        System.out.println("-------------------");
        System.out.println("|Integer|Character|");
        System.out.println("-------------------");

        int i = lowerLimit;
        for(; i < upperLimit; i++){

        System.out.println("|"+ i + "|"+ (char) i +"|");
        System.out.println("-------------------");

        }   



    }

    static int funcCalls = 0;
    /**
     * NOTE: Implemented Recursively
     * 
     * 3. Write a program that uses a for-loop to print a table of integers from 0 to 256 and their
       corresponding char values
     * 
     * @param n the input number
     * @return number of function calls
     */
    private static int T(int n){
        if(n == 1)
            return funcCalls;

        else if(n % 2 == 0){
            n /= 2;
            funcCalls++;
            T(n);
        }
        else if(n % 2 == 1){
            n = (n*3) + 1;
            funcCalls++;
            T(n);
        }

        return funcCalls;

    }

    /**
     * Write a program that uses a do-while loop to read a series of non-negative integers
       from the user and will print the largest integer that was entered.
     * 
     * @return array of integers
     */
    private static ArrayList<Integer> seriesOfInts() {
        
        ArrayList<Integer> series = new ArrayList<>();
        Scanner scnr = new Scanner(System.in);

        int number = 0;
        do {
            
            try {

                System.out.println("\nEnter an integer or -1 to exit: ");
                number = scnr.nextInt();
    
                series.add(number);

            } catch(Exception e) {
                System.out.println("Invalid Input");
                scnr.next();
                continue;

            }
           
        } while(number != -1);

        scnr.close();
        series.remove(series.size() - 1);
        return series;

    }


    
    /**
     * 6. Use the method System.currentTimeMillis() (which returns type long) to
    //    display the amount of time it takes to do the following tasks. (You may need to use a
    //    variable of type long for the sums.)
    //    (a) Compute and display the sum of the integers from 1 to 1,000,000.
    //    (b) Compute and display the sum of the integers from 1 to 10,000,000.
    //    (c) Compute and display the sum of the integers from 1 to 100,000,000.
    //    (d) Compute and display the sum of the integers from 1 to 1,000,000,000.

     * 
     * 
     * @param upperLimit the loop limit
     * @return long[] array containing the sum and the time
     */
    private static long[] benchmarkSums(long upperLimit) {

        long start = System.currentTimeMillis();
        long sum = 0;
        long values[] = new long[2];
        
        for(long i = 1; i < upperLimit; i++){

            sum += i;

        }

        long stop = System.currentTimeMillis();
        
        values[0] = stop - start;
        values[1] = sum;

        return values;


    }




}