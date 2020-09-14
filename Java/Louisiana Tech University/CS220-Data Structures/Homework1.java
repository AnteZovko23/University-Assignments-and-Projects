/**
 * Author: Ante Zovko
 * Version: September 13th, 2020
 * 
 * Arithmetic, Data Types, and Printing
 * 
 * 
 */
 public class Homework1 {

    /********** Main Demo **************/
    public static void main(String[] args) {
        
    // 1. Compute the value of the int variable x after each statement is executed.

        System.out.println(computeX("a")); 
        System.out.println(computeX("b")); 
        
        /* Output:
         * 
         *  15
         *  3
         * 
         */


    // 2. Determine the output after all the following lines of code are executed.
        
        outputDemo();

        /* Output:
         * 
         *  ****
         * 
         *  ******
         * 
         */

    // 3. Let 𝑃 and 𝑄 represent boolean variables. Complete the following truth table

        truthTable();

        /** Output:
         * 
         *  -----------------------------------------------
            |   P   |   Q   |   !P    |  P & Q  |  P || Q |
            -----------------------------------------------
            | true  | true  |  false  |  true   |  true   | 
            -----------------------------------------------
            | true  | false |  false  |  false  |  true   | 
            -----------------------------------------------
            | false | true  |  true   |  false  |  true   | 
            -----------------------------------------------
            | false | false |  true   |  false  |  false  | 
            -----------------------------------------------
         * 
         */
    
    // 4. Determine the value and type returned by the following statements.

        mathFunctionsDemo(-4.2, 9, 6,2, 9, 0);

        /**
         * Output:
         * 
         *  Result: -5.0  Type: Double
            Result: 3.0  Type: Double
            Result: 36.0  Type: Double
            Result: 9  Type: Integer
            Result: 1.5707963267948966  Type: Double
            Result: 0.2196306443939059  Type: Double
         */
    

    // 5.  What are all the possible values returned by the following statement:

        System.out.println(possibleValues());

        /**
         * Output:
         * 
         * Range from 1-6
         * 
         */


    // 6. Write a program to simulate rolling a standard pair of dice

        rollingTwoDice();

         /**
         * Output:
         * 
         *  The first die is 1. The second die is 6
            Their total is: 7
         * 
         */

    // 7. Determine the value of the String variable x after each statement is executed.

        System.out.println(stringValue("a"));
        System.out.println(stringValue("b"));
        System.out.println(stringValue("c"));

        /**
         * Output:
         * 
         *  test23
            5test
            test6
         * 
         */
        
    // 8. Suppose that s1 = “three” and s2 = “3” are variables of type String.
    // Determine the value and type returned by the following statements.

        stringMethods("three", "3");
        
        /**
         * Output:
         * 
         *  Result: false  Type: Boolean
            Result: 5  Type: Integer
            Result: r  Type: Character
            Result: re  Type: String
            Result: THREE  Type: String
            Result: 2  Type: Integer
         */
        

    }




    /******************* Functions **********************/



    /**
     * Compute the value of the int variable x after each statement is executed.
     * 
     * @param identifier a or b
     * @return x the computed variable
     * 
     */
    private static int computeX(String identifier){
        
        if(identifier.equals("a"))
            return 7 + 3 * 6 / 2 - 1;
        
        else if(identifier.equals("b"))
            return 2 % 2 + 2 * 2 - 2 / 2;
        
        else
        return 0;

    }
    
    /**
     * Determine the output after all the following lines of code are executed.
     * 
     */
    private static void outputDemo(){

        System.out.print("*");
        System.out.println("***");
        System.out.println();
        System.out.print("****");
        System.out.println("**");

    }

    /**
     * Let 𝑃 and 𝑄 represent boolean variables. Complete the following truth table
     * 
     */
    private static void truthTable(){


        System.out.println("-----------------------------------------------");
        System.out.println("|   P   |   Q   |   !P    |  P & Q  |  P || Q |");
        System.out.println("-----------------------------------------------");
        System.out.println("| true  | true  |  "  + !true + "  |  " + (true && true) + "   |  " + (true || true) + "   | ");
        System.out.println("-----------------------------------------------");
        System.out.println("| true  | false |  "  + !true + "  |  " + (true && false) + "  |  " + (true || false) + "   | ");
        System.out.println("-----------------------------------------------");
        System.out.println("| false | true  |  " + !false + "   |  " + (false && true) + "  |  " + (false || true) + "   | " );
        System.out.println("-----------------------------------------------");
        System.out.println("| false | false |  " + !false + "   |  " + (false && false) + "  |  " + (false || false) + "  | " );
        System.out.println("-----------------------------------------------");

    }

    
    /**
     * Determine the value and type returned by the following statements.
     * 
     * @param a floor
     * @param b sqrt
     * @param c pow
     * @param d pow
     * @param e abs
     * @param f acos
     */
    private static void mathFunctionsDemo(double a, int b, int c, int d, int e, int f){

        System.out.println("Result: " + Math.floor(a) + "  " + ((Object)Math.floor(a)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + Math.sqrt(b) + "  " + ((Object) Math.sqrt(b)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + Math.pow(c, d) + "  " + ((Object)Math.pow(c, d)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + Math.abs(e) + "  " + ((Object)Math.abs(e)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + Math.acos(f) + "  " + ((Object)Math.acos(f)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + Math.random() + "  " + ((Object)Math.random()).getClass().getName().replace("java.lang.", "Type: "));
        


    }

    /**
     * What are all the possible values returned by the following statement:
     * 
     * @return range from 1-6
     */
    private static int possibleValues() {

        return (int) (Math.random() * 6) + 1;

    }

    
    /**
     * Write a program to simulate rolling a standard pair of dice
     * 
     */
    private static void rollingTwoDice(){
        int firstDie, secondDie;
        firstDie = possibleValues();
        secondDie = possibleValues();

        System.out.println("The first die is " + firstDie + ". The second die is " + secondDie);
        System.out.println("Their total is: " + (firstDie + secondDie));

    }

    /**
     * 
     * Determine the value of the String variable x after each statement is executed.
     * 
     * @param identifier a, b or c
     * @return x the string value
     */
    private static String stringValue(String identifier){
        
        if(identifier.equals("a"))
            return "test" + 2 + 3;

        else if(identifier.equals("b"))
            return 2 + 3 + "test";

        else if(identifier.equals("c"))
            return "test" + 2 * 3;
        
        else
            return "";
            
    }

    
    /**
     * Suppose that s1 = “three” and s2 = “3” are variables of type String.
     * Determine the value and type returned by the following statements.
     * 
     * @param s1 first string
     * @param s2 second string
     */
    private static void stringMethods(String s1, String s2){

        System.out.println("Result: " + s1.equals(s2) + "  " + ((Object)s1.equals(s2)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + s1.length() + "  " + ((Object) s1.length()).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + s1.charAt(2) + "  " + ((Object)s1.charAt(2)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + s1.substring(2, 4) + "  " + ((Object)s1.substring(2, 4)).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + s1.toUpperCase() + "  " + ((Object)s1.toUpperCase()).getClass().getName().replace("java.lang.", "Type: "));
        System.out.println("Result: " + s1.indexOf("re") + "  " + ((Object)s1.indexOf("re")).getClass().getName().replace("java.lang.", "Type: "));

    }
 
     
 }