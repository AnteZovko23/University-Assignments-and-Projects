
import java.util.Arrays;

/**
 * @author Ante Zovko
 * @version October 4th, 2020
 * 
 *  Arrays
 * 
 */
public class Homework7 {


    public static void main(String[] args) {
        
        // 1. Determine the output after the following lines of code are executed.
        demo1();

        /**
         * Output:
         * 
         *  1 2 
            3 
            4 5 6 
         * 
         */


        // 2.Write a method that receives any number of double arguments and returns their average
        System.out.println(varArgsAvg(2.76, 3.14, 23.33, 4.2));

        /**
         * Output:
         * 
         * 8.3575
         * 
         */

        // 3. Write a method with signature void sumCoord(MyPoint... S) (in a new Java
        // file) that receives any number of MyPoint objects and will print two values to the
        // screen
        sumCoord(new MyPoint(0,3), new MyPoint(1,3), new MyPoint(2, 3));

        /**
         * Output:
         * 
         * X Sum: 3.00
           Y Sum: 9.00
         * 
         */

        // 4. Write a program that uses these methods to do the following:
        // (a) Sort the double array {8.4, 9.3, 0.2, 7.9, 3.4}.
        // (b) Fills an int[10] array with the value 7 in each position.
        // (c) Copies the int[6] array {1, 2, 3, 4, 5, 6} into another int[6] array.
        // (d) Prints whether or not the arrays create in the previous part are equal.
        // (e) Performs a binary search on the array {1, 3, 5, 7, 9, 11} for the number 7 and
        // prints the location index.

        arrayMethods("a");

        /**
         * Output: 
         * 
         * a) [0.2, 3.4, 7.9, 8.4, 9.3]
         * 
         * b) [7, 7, 7, 7, 7, 7, 7, 7, 7, 7]
         * 
         * c) First Array: [1, 2, 3, 4, 5, 6]
              Second Array: [1, 2, 3, 4, 5, 6]

         * d) First Array: [1, 2, 3, 4, 5, 6]
              Second Array: [1, 2, 3, 4, 5, 6]
              Equal? true}}
         * 
         * e) Position: 3
         * 
         */

        

    }


    /**
     * 1. Determine the output after the following lines of code are executed.
     * 
     */
    private static void demo1() {

        int[][] myarray = {{1,2},{3},{4,5,6}};
        for (int[] row : myarray) {
        for (int col : row) {
        System.out.printf("%d ", col);
        }
        System.out.println();
        }


    }

    /**
     * 2. Write a method that receives any number of double arguments and returns their
          average.

     * @param nums given number for avg calculation
     * 
     * @return the average
     */
    private static double varArgsAvg(double... nums) {
        double sum = 0;

        for(double x : nums){
            sum += x;
        }

        return sum / nums.length;

    }

    /**
     * 
       3. Write a method with signature void sumCoord(MyPoint... S) (in a new Java
       file) that receives any number of MyPoint objects and will print two values to the
       screen
     * 
     * @param S given MyPoint objects
     * 
     */
    private static void sumCoord(MyPoint... S) {

        double xCoorSum = 0;
        double yCoorSum = 0;

        for(MyPoint p : S){

            xCoorSum += p.getX();
            yCoorSum += p.getY();

        }

        System.out.printf("X Sum: %.2f\nY Sum: %.2f\n", xCoorSum, yCoorSum);
        

    }

    /**
     *   Write a program that uses these methods to do the following:
        (a) Sort the double array {8.4, 9.3, 0.2, 7.9, 3.4}.
        (b) Fills an int[10] array with the value 7 in each position.
        (c) Copies the int[6] array {1, 2, 3, 4, 5, 6} into another int[6] array.
        (d) Prints whether or not the arrays create in the previous part are equal.
        (e) Performs a binary search on the array {1, 3, 5, 7, 9, 11} for the number 7 and
        prints the location index.
     * 
     * 
     * @param identifier a, b, c, d, e
     * 
     */
    private static void arrayMethods(String identifier){

        if(identifier.equals("a")){

            double[] arr = {8.4, 9.3, 0.2, 7.9, 3.4};
            Arrays.sort(arr);
            System.out.println(Arrays.toString(arr));

        }

        else if(identifier.equals("b")){

            int[] arr = new int[10];
            Arrays.fill(arr, 7);
            System.out.println(Arrays.toString(arr));

        }
        
        else if(identifier.equals("c")){

            int[] arr = {1, 2, 3, 4, 5, 6};
            int[] arr2 = new int[6];

            arr2 = Arrays.copyOf(arr, 6);
            System.out.println("First Array: " + Arrays.toString(arr) + "\nSecond Array: " + Arrays.toString(arr2));

        }

        else if(identifier.equals("d")) {

            int[] arr = {1, 2, 3, 4, 5, 6};
            int[] arr2 = new int[6];

            arr2 = Arrays.copyOf(arr, 6);
            System.out.println("First Array: " + Arrays.toString(arr) + "\nSecond Array: " + Arrays.toString(arr2));

            System.out.println("Equal?: " + Arrays.equals(arr, arr2));

        }

        else if(identifier.equals("e")){

            int arr[] =  {1, 3, 5, 7, 9, 11};

            System.out.println("Position: " + Arrays.binarySearch(arr, 7));

        }

    }

    
}
