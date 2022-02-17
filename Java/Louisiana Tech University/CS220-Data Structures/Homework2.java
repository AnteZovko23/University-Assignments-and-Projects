

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
@SuppressWarnings("all")

/**
 * 
 * Author: Ante Zovko 
 * Version: September 14th, 2020
 * 
 * User Input, If/Else, Dialog Boxes, File Input/Output
 * 
 */
public class Homework2 {


    /********** Main Demo **************/
    public static void main(String[] args) {

        // 1. Write a program that asks for the user’s name, and then greets the user by
        // name by
        // converting it to uppercase letters

        getUppercaseName("Ante");

        /**
         * Output:
         * 
         * Hello, ANTE, nice to meet you!
         * 
         */

        // 2. Write a program that asks the user to input three integers and then uses
        // the
        // System.out.printf method to display their sum and product in the command
        // window

        printFormat();

        /**
         * Output:
         * 
         * Enter an integer: 2 Enter an integer: 4 Enter an integer: 5 The Product: 40.
         * The sum: 11
         * 
         * 
         */

        // 3. Write a program that helps the user count his change. The program should
        // ask how
        // many quarters he has, then how many dimes, then how many nickels, and then
        // how
        // many pennies. The program will then display how much money he has, in dollars
        // formatted properly to two decimal places.

        changeCount();

        /**
         * Output:
         * 
         * Enter the number of quarters: 5 Enter the number of dimes: 2 Enter the number
         * of nickels: 1 Enter the number of pennies: 3 Total: $1.53
         * 
         */

        // 4. Write a program that asks the user to input a name as a string and an age
        // as an integer
        // and then uses the System.out.printf method to say whether or not that person
        // is
        // allowed to vote

        isAllowedToVote();

        /**
         * Output:
         * 
         * Enter your name: Ante Enter your age: 22 Ante is 22 and is allowed to vote.
         * 
         * 
         */

        // 5. Write a program that asks the user to input their first and last name
        // separated by a
        // single space. The program will display the number of characters in each name
        // and
        // output the user’s initials.

        nameCharCount();

        /**
         * Output:
         * 
         * Your first name is Ante which has 4 characters. Your first name is Zovko
         * which has 5 characters. Your initials are AZ
         * 
         * 
         */

        // 6. Write a program that asks for an integer in a dialog box and then another
        // dialog box will
        // appear to say whether that integer was odd or even

        isOddEvenDialogBox();

        // 7. A file named “testdata.txt” contains the following information: The first
        // line contains the
        // name of a student. Each of the next three lines contains an integer, which
        // represents
        // the student’s three exam scores. Write a program that will read the
        // information in the
        // file and then write the following information to the file “result.txt”: The
        // first line will
        // contain the name of the student. The second line will contain the average
        // grade on the
        // three exams.

        getAverageTestResults("testdata.txt", "result.txt");

    }

    /******************* Functions **********************/

    /**
     * 
     * Write a program that asks for the user’s name, and then greets the user by
     * name by converting it to uppercase letters
     * 
     * @param name given name
     * 
     */
    private static void getUppercaseName(String name) {

        System.out.println("Hello, " + name.toUpperCase() + ", nice to meet you!");

    }

    /**
     * Write a program that asks the user to input three integers and then uses the
     * System.out.printf method to display their sum and product in the command
     * window
     * 
     * 
     * @return 0 if no errors, -1 otherwise
     * 
     */
    private static int printFormat() {

        int sumResult = 0;
        int productResult = 1;

        Scanner input = new Scanner(System.in);
        
        ArrayList<Integer> intArr = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            try {

                System.out.println("Enter an integer: ");
                intArr.add(input.nextInt());

            } catch (Exception e) {

                System.out.println("Not an integer!");
                return -1;
            }
        }

        for (int i = 0; i < intArr.size(); i++) {

            productResult *= intArr.get(i);
            sumResult += intArr.get(i);

        }

        System.out.printf("The Product: %d. The sum: %d\n", productResult, sumResult);
        return 0;

    }

    /**
     * Write a program that helps the user count his change. The program should ask
     * how many quarters he has, then how many dimes, then how many nickels, and
     * then how many pennies. The program will then display how much money he has,
     * in dollars formatted properly to two decimal places.
     * 
     * 
     */
    private static void changeCount() {

        Scanner input = new Scanner(System.in);


        final double QUARTER = 0.25;
        final double DIME = 0.10;
        final double NICKEL = 0.05;
        final double PENNY = 0.01;
        double change = 0;

        try {

            System.out.println("Enter the number of quarters: ");
            change += (QUARTER * input.nextInt());
            System.out.println("Enter the number of dimes: ");
            change += (DIME * input.nextInt());
            System.out.println("Enter the number of nickels: ");
            change += (NICKEL * input.nextInt());
            System.out.println("Enter the number of pennies: ");
            change += (PENNY * input.nextInt());

            System.out.printf("Total: $%.2f\n", change);

        } catch (Exception e) {

            System.out.println("Enter an integer!");

        }

    }

    /**
     * Write a program that asks the user to input a name as a string and an age as
     * an integer and then uses the System.out.printf method to say whether or not
     * that person is allowed to vote
     * 
     * 
     */
    private static void isAllowedToVote() {

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String name = input.nextLine();
            System.out.println("Enter your age: ");
            int age = input.nextInt();

            System.out.printf(isAllowedtoVoteHelper(name, age));

        } catch (Exception e) {
            System.out.println("Invalid input!");
        }

    }

    /**
     * Helper for isAllowedToVote
     * 
     * @param name given name
     * @param age  given age
     * @return if allowed to vote or not
     */
    private static String isAllowedtoVoteHelper(String name, int age) {

        return age >= 18 ? name + " is " + age + " and is allowed to vote.\n"
                : name + " is " + age + " and is not allowed to vote.\n";

    }

    /**
     * Write a program that asks the user to input their first and last name
     * separated by a single space. The program will display the number of
     * characters in each name and output the user’s initials
     * 
     * 
     */
    private static void nameCharCount() {

        try {

            Scanner input = new Scanner(System.in);

            System.out.println("Enter your first and last name: ");
            String name = input.nextLine();

            String[] str = name.split(" ");

            System.out.println("Your first name is " + str[0] + ", which has " + str[0].length() + " characters.");
            System.out.println("Your first name is " + str[1] + ", which has " + str[1].length() + " characters.");
            System.out.println("Your initials are " + str[0].charAt(0) + str[1].charAt(0));

        } catch (Exception e) {
            System.out.println("Invalid Input!");
        }

    }


    /**
     * Write a program that asks for an integer in a dialog box and then another dialog box will
       appear to say whether that integer was odd or even

     */
    private static void isOddEvenDialogBox() {

        try {

            String num = JOptionPane.showInputDialog(null, "Enter a number: ");

            if (Integer.parseInt(num) % 2 == 0) {

                JOptionPane.showMessageDialog(null, num + " is even");

            } else {

                JOptionPane.showMessageDialog(null, num + " is odd");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Invalid Input!");
        }

    }

    /**
     * 
     *  A file named “testdata.txt” contains the following information: The first line contains the
        name of a student. Each of the next three lines contains an integer, which represents
        the student’s three exam scores. Write a program that will read the information in the
        file and then write the following information to the file “result.txt”: The first line will
        contain the name of the student. The second line will contain the average grade on the
        three exams.
     * 
     * 
     */
    private static void getAverageTestResults(String inputFile, String outputFile) {

        try {

            File testScores = new File(inputFile);
            File avgScore = new File(outputFile);

            avgScore.createNewFile();
            FileWriter writer = new FileWriter(avgScore);

            Scanner fileReader = new Scanner(testScores);
            ArrayList<String> inputVals = new ArrayList<>();
            double scoreTotal = 0;

            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                inputVals.add(data);
            }

            writer.write(inputVals.get(0));

            for(int i = 1; i < inputVals.size(); i++){

                scoreTotal += Double.parseDouble(inputVals.get(i));

            }

            writer.write("\nAverage Test Score: " + (scoreTotal/(inputVals.size() - 1)));

            writer.close();
            fileReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }




    

}