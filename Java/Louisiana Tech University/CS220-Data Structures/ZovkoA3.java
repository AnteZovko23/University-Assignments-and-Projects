import javax.swing.JOptionPane;

/**
 * 
 * Author: Ante Zovko
 * Version: September 17th, 2020
 * 
 * Dialog Box demo that determines if the given number is even or odd
 * 
 */
public class ZovkoA3 {


    public static void main(String[] args) {
        

        isOddEvenDialogBox();


    }


    /**
     * 
     * Dialog Box demo that determines if the given number is even or odd
     * 
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



}