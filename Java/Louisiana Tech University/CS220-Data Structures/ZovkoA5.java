import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ante Zovko
 * @version October 8, 2020
 * 
 * Reverses an array of user inputs
 * 
 */
public class ZovkoA5 {


    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        ArrayList<String> stringList = new ArrayList<>();
        String userInput = "";

        do{

            try{

                System.out.println("Enter a string or quit to stop: ");
                userInput = scnr.nextLine();
                if(userInput.equals("quit"))
                    break;
                stringList.add(userInput);

            } catch(Exception e){

                System.out.println("Invalid Input.");
                continue;

            }
           


        } while(true);

        System.out.println("\nEntered Array: " + stringList);
        System.out.println("Reversed Array: " + reverseArray(stringList));

        scnr.close();

    }



    /**
     * Reverses a given string ArrayList
     * 
     * @param list given arraylist
     * @return reversedArray reversed ArrayList
     */
    private static ArrayList<String> reverseArray(ArrayList<String> list){

        ArrayList<String> reversedArray = new ArrayList<>();

        for(int i = list.size() - 1; i >= 0; i--){

            reversedArray.add(list.get(i));

        }

        return reversedArray;


    }

    
}
