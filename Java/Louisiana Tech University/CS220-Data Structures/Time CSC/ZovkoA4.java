import java.util.Scanner;

/**
 * Adds two times and displays the result
 * 
 * @author Ante Zovko
 * @version September 29th, 2020
 * 
 */
public class ZovkoA4 {

    public static void main(String[] args) {
        
        Scanner scnr = new Scanner(System.in);
        Time time1 = new Time();
        Time time2 = new Time();

        Time[] tempArr = {time1, time2};
        
        for(int i = 0; i < 2; i++){

            try {

                
                System.out.print("\nEnter days: ");
                int days = scnr.nextInt();

                System.out.print("\nEnter hours: ");
                int hours = scnr.nextInt();

                System.out.print("\nEnter mins: ");
                int mins = scnr.nextInt();
                
                tempArr[i].setTime(days, hours, mins);

            } catch(Exception e){

                System.out.println("\nInvalid Value!");
                i -= 1;
                continue;
            }

        }
            


        Time ReturnedTime = addTime(time1, time2);

        System.out.println();
        ReturnedTime.displayTime();

        scnr.close();
        

    }

    /**
     * Add two times
     * 
     * @param Time a given time object
     * @param Time b, given time object
     * 
     * @return Time calculated time
     * 
     */
    public static Time addTime(Time a, Time b){

        int[] time1 = a.getTime();
        int[] time2 = b.getTime();
        int mins;
        int hours;
        int days;

        int carry = 0;

        mins = (time1[2] + time2[2]) % 60;
        carry = (time1[2] + time2[2]) / 60;

        hours = (time1[1] + time2[1]) % 24;
        hours += carry;

        carry = (time1[1] + time2[1]) / 24;

        days = (time1[0] + time2[0]);
        days += carry;

        Time AddedTime = new Time();
        AddedTime.setTime(days, hours, mins);

        return AddedTime;
    }
    
}
