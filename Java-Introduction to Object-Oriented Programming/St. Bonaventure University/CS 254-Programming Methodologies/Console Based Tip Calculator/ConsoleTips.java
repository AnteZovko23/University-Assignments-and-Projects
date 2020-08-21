import java.util.Scanner;

/**
 * Tipping Calculator
 * 
 * @author Ante Zovko
 * @version 2/7/2019
 *
 */
public class ConsoleTips {

	public static void main(String[] args) {

		Scanner cs = new Scanner(System.in);

		System.out.println("What is your current bill? ");
		double amount = cs.nextDouble();

		System.out.println("What is the percentage of the tip you want to leave?");
		double percentage = cs.nextDouble();
		percentage /= 100;

		double theTippingAmt = TipUtilities.calcTip(amount, percentage);

		System.out.println("How do you want to round the tip?");
		System.out.print("I want to round to the nearest: ");
		double quantum = cs.nextDouble();

		double theRoundedTipp = TipUtilities.round(theTippingAmt, quantum);

		System.out.println("What decimal format do you want?");
		int decimalDigits = cs.nextInt();

		String theFinalTipp = TipUtilities.formatNumber(theRoundedTipp, decimalDigits);

		System.out.println("This is your tip amount: $" + theFinalTipp);

		double finalAmt = Double.parseDouble(theFinalTipp) + amount;

		System.out.println("What decimal format do you want for the final amount?");
		decimalDigits = cs.nextInt();

		System.out.println("Your total amount now is: $" + TipUtilities.formatNumber(finalAmt, decimalDigits));

	}

}
