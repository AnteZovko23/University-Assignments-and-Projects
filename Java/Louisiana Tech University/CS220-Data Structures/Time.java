/**
 * Time.java
 * CSC 220-003 Fall 2020
 * Used to create Time objects for Assignment #4
 * DO NOT ALTER THIS FILE
 */
public class Time {
	
	/**
	 * private int variable for number of days
	 * Allowable values are >= 0
	 */
	private int days;
	
	/**
	 * private int variable for number of hours
	 * Allowable values are 0-23
	 */
	private int hours;
	
	/**
	 * private int variable for number of minutes
	 * Allowable values are 0-59
	 */
	private int mins;
	
	/**
	 * Constructor: Initializes private variables to zero
	 */
	public Time() {
		days = 0;
		hours = 0;
		mins = 0;
	}
	
	/**
	 * Method: sets the private variables to the inputs
	 *  
	 * @param d Sets value of days to d, where d >= 0
	 * @param h Sets value of hours to h, where 0 <= h <= 23
	 * @param m Sets value of mins to m, where 0 <= m <= 59
	 * @throws IllegalArgumentException Inputs must be within designated ranges
	 */
	public void setTime(int d, int h, int m) {
		if (d < 0 || h < 0 || h > 23 || m < 0 || m > 59)
			throw new IllegalArgumentException("Invalid time input.");
		
		days = d;
		hours = h;
		mins = m;
	}
	
	/**
	 * Method: returns reference to int[] containing values of private variables
	 *  
	 * @return Reference to int[] a = {days, hours, mins}. 
	 */
	public int[] getTime() {
		int[] a = {days, hours, mins};
		return a;
	}
	
	/**
	 * Method: prints values of private variables in the format "x days, y hours, z mins"
	 */
	public void displayTime() {
		System.out.println(days + " days, " + hours + " hours, " + mins + " mins");
	}
}