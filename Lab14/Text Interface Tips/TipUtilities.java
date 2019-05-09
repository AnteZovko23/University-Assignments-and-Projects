import java.text.DecimalFormat;

/**
 * 
 * Simple tipping calculator
 * 
 * @author Ante Zovko
 * @version 1/24/2019
 *
 */
public class TipUtilities {
	
	/**
	 * Calculates the tip 
	 * 
	 * @param baseAmount the billed amount
	 * @param percentage the desired tipping percentage 
	 * 
	 * @return the tipping amount
	 */
	public static double calcTip(double baseAmount, double percentage) {
		
		return baseAmount * percentage;
	}
	
	
	
	/**
	 * Rounds the tip to a desired quantum
	 * 
	 * @param amountToRound the tipping amount to be rounded
	 * @param quantum the amount which we wish to round to
	 * 
	 * @return the rounded amount 
	 */
	public static double round(double amountToRound, double quantum) {
		
	
		double overage = amountToRound % quantum;
		
		double amt = quantum - overage;
		
		
		
		if((quantum/2) < overage)
		{
			return amountToRound + amt;
		}
		
		else
		{
			return amountToRound - overage;
		}
		
		
	}
	
	
	
	/**
	 * Formats the tipping amount to a desired number of decimal digits
	 * 
	 * @param num the tipping amount
	 * @param decimalDigits the number of digits
	 * @see https://www.baeldung.com/java-round-decimal-number
	 * 
	 * @return the formatted amount
	 * 
	 * 
	 */
	public static String formatNumber(double num, int decimalDigits) {
		
		
		String s = "";
		String roundedNum;
		
		for(int i = 0; i < decimalDigits; i++)
		{
			s += "0";
		}
		DecimalFormat df = new DecimalFormat("#." + s);
		roundedNum = df.format(num);
		
		
		
		
		
		
		
		
		
		return roundedNum;
	}
	
	

}
