import java.util.ArrayList;

@SuppressWarnings("all")

/**
 * Author: Ante Zovko
 * Version: September 21st, 2020
 * 
 * Methods
 * 
 */
public class Homework5 {

    public static void main(String[] args) {
        

        /* Given the String s, prints the message “Hello, <s>!”
        where the string is substituted into the message */

        greet("Ante");

        /**
         * Output: 
         * 
         * Hello Ante!
         * 
         */



        /* Computes and returns the value of n! */

        System.out.println(factorial(4));

        /**
         * Output:
         * 
         * 24
         * 
         */



         /* Computes and returns the value of the math function
        “n choose r”. May call the method factorial you wrote
        before. */

        System.out.println(choose(7, 2));

        /**
         * Output:
         * 
         * 21
         * 
         */



         /* Computes and returns the length of the hypotenuse of a
        right triangle with base length a and height length b.
        May call the method Math.sqrt */

        System.out.println(pythag(3, 4));
        
        /**
         * Output:
         * 
         * 5.0
         * 
         */


        /* Prints every element of the array A to the screen. */

        int[] A = {1, 2, 3, 4, 5};
        printArray(A);


        /**
         * Output:
         * 
         * 1 2 3 4 5 
         * 
         */


        /* Returns i where A[i] is the largest element of the
        array. If the largest value appears more than once,
        it will return the smallest i. */

        int[] B = {5, 4, 2, 14, 3, 14, 2};
        System.out.println(maxLocation(B));
        
        /**
         * Output:
         * 
         * 2
         * 
         */


        /* Returns i where A[i] is the largest element of the
        array. If the largest value appears more than once,
        it will return the smallest i. */

        int[] C = {3, 4, 2, 14, 4, 3, 2};
        System.out.println(linearSearch(C, 3));

        /**
         * Output:
         * 
         * 2
         * 
         */


        
        /* Reverses the characters of s and returns that String.
        May call the methods s.length and s.charAt */
        System.out.println(reverseString("Ante Zovko"));

        /**
         * Output:
         * 
         * okvoZ etnA
         * 
         */

        
        
        /* Returns true if s is a palindrome, and false otherwise.
        May call the methods reverseString you wrote before and
        s.equals */

        System.out.println(isPalindrome("deified"));

        /**
         * Output:
         * 
         * true
         * 
         */


        /* Returns the reference to an array containing all the
        elements of A in reverse order. */

        for(int x : reverseArray(A))
            System.out.print(x + " ");

        System.out.println();


        /**
         * Output:
         * 
         * 5 4 3 2 1
         * 
         */




        /* Computes and returns the reference to a 2x2 boolean
        array that ORs all the corresponding entries of
        each input 2x2 boolean array */

        boolean[][] bool1 = {{false, false}, {true, true}};
        boolean[][] bool2 = {{false, true}, {true, false}};
        boolean[][] joined = join(bool1, bool2);

        for(int i = 0; i < joined.length; i++) {

            for(int j = 0; j < joined[0].length; j++) {

                System.out.print(joined[i][j] + " ");
            }
        }
        System.out.println();


        // Output: 
        // false, true, true, true



        /* Computes and returns the reference to a 2x2 boolean
        array that ANDs all the corresponding entries of
        each input 2x2 boolean array */

        boolean[][] meet = meet(bool1, bool2);

        for(int i = 0; i < meet.length; i++) {

            for(int j = 0; j < meet[0].length; j++) {

                System.out.print(meet[i][j] + " ");
            }
        }
        System.out.println();

        // Output: 
        // false false true false

    }




    /**
     * Given the String s, prints the message “Hello, <s>!”
        where the string is substituted into the message

     * @param s given string
     */
    private static void greet(String s) {

        System.out.printf("Hello %s!\n", s);

    }


    /**
     * Computes and returns the value of n!
     * 
     * @param n given number
     * @return n the value of n!
     * @throws IllegalArgumentException
     */
    private static int factorial(int n) {

        if(n < 0)
            throw new IllegalArgumentException("Enter positive integer.");
        // Ternary Operator solution
        return n > 0 ? n * factorial(n - 1):1;


        // Recursive solution

        // if(n == 0){
        //     return 1;
        // }
        // else {
        //     return n * factorial(n - 1);
        // }

        // Iterative solution

        // int factorial = 1;
        // for(int i = n; i > 0; i--){
        //     factorial *= i;
        // }
        // return factorial;

    }


    /**
     * Computes and returns the value of the math function
        “n choose r”
     * 
     * @param n First number
     * @param r Second number
     * 
     * @return Computed "choose" value
     */
    private static int choose(int n, int r) {

        return factorial(n)/(factorial(r)*factorial(n-r));

    }


    /**
     * Computes the hypotenuse of a right triangle 
     * 
     * @param a length
     * @param b width
     * 
     * @return hypotenuse
     */
    private static double pythag(double a, double b) {

        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

    }

    
    /**
     * Prints every element of the array A to the screen
     * 
     * @param A given array
     */
    private static void printArray(int[] A) {

        for(int x : A) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


    /**
     *  Finds i where A[i] is the largest element of the
        array. If the largest value appears more than once,
        it will return the smallest i. 
     * 
     * @param A given array
     * @return i if A[i] is the largest element, smallest i if the largest
     * element appears more than once
     */
    private static int maxLocation(int[] A) {

        int largestValue = A[0];
        int largestIndex = 0;

        int smallestValue = A[0];
        int smallestIndex = 0;


        for(int i = 0; i < A.length; i++) {

            if(A[i] > largestValue) {
                largestValue = A[i];
                largestIndex = i;
            }

            if(A[i] < smallestValue) {
                smallestValue = A[i];
                smallestIndex = i;
            }

        }

        int counter = 0;

        for(int x : A) {
            if(x == largestValue)
                counter++;
            
            if(counter > 1)
                return smallestIndex;
        }

        return largestIndex;
    }


    /**
     * Returns i where A[i] is the largest element of the
       array. If the largest value appears more than once,
       it will return the smallest i. 
     * 
     * 
     * @param A given Array
     * @param x search target
     * 
     * @return x position if x is found once, smallest value position if x is found more than once
     * and -1 if x is not found
     * 
     */
    private static int linearSearch(int[] A, int x) {

        int largestValue = A[0];
        int largestIndex = 0;
        int xPosition = -1;

        int smallestValue = A[0];
        int smallestIndex = 0;

        for(int i = 0; i < A.length; i++) {

            if(A[i] == x)
                xPosition = i;


            if(A[i] < smallestValue) {
                smallestValue = A[i];
                smallestIndex = i;
            }
            

        }

        int counter = 0;
        for(int num : A) {
            if(num == x)
                counter++;
            
            if(counter > 1)
                return smallestIndex;
        }

        
        if(counter == 1 && xPosition != -1)
            return xPosition;
        else
            return -1;
    }

    /**
     * Reverses the characters of s and returns that String.
     * 
     * @param s given string
     * 
     * @return reversed string
     * 
     */
    private static String reverseString(String s) {


        String reversedString = "";

        for(int i = s.length() - 1; i >= 0; i--){

            reversedString += s.charAt(i);

        }


        return reversedString;
    }

    /**
     * Returns true if s is a palindrome, and false otherwise
     * 
     * @param s given string
     * @return true if palindrome
     */
    private static boolean isPalindrome(String s) {

        return s.equals(reverseString(s));

    }


    /**
     * Returns the reference to an array containing all the
       elements of A in reverse order
     * 
     * @param A given array
     * 
     * @return reversed array
     */
    private static int[] reverseArray(int[] A) {

        int[] reversedArray = new int[A.length];

        for(int i = 0; i < A.length; i++){

            reversedArray[i] = A[A.length - 1 - i];

        }
        
        return reversedArray;
    }


    /**
     * 
     * ORs each entry of given arrays
     * 
     * @param A given 2D array
     * @param B given 2D array
     * 
     * @return joined array values
     * 
     */
    private static boolean[][] join(boolean[][] A, boolean[][] B) {

        boolean[][] joined = new boolean[2][2];

        for(int i = 0; i < joined.length; i++) {

            for(int j = 0; j < joined[0].length; j++) {

                joined[i][j] = (A[i][j] || B[i][j]);

            }

        }

        return joined;

    }

    /**
     * ANDs each entry of given arrays
     * 
     * @param A given array
     * @param B given array
     * 
     * @return AND array
     */
    private static boolean[][] meet(boolean[][] A, boolean[][] B) {

        boolean[][] meet = new boolean[2][2];

        for(int i = 0; i < meet.length; i++) {

            for(int j = 0; j < meet[0].length; j++) {

                meet[i][j] = (A[i][j] && B[i][j]);

            }

        }

        return meet;

    }


}