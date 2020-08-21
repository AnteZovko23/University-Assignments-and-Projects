import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Model for a Tip Calculator
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class TipModel {

	private double totalBill;
	private double tipPer;
	private double tipQuantum;
	private ArrayList<ModelObserver> obs;

	/**
	 * Default Constructor
	 */
	public TipModel() {
		this.totalBill = 0;
		this.tipPer = 0;
		this.tipQuantum = 0;

		obs = new ArrayList<>();
	}

	/**
	 * Constructor
	 * 
	 * @param totalBill  the total Amount
	 * @param tipPer     the Percentage
	 * @param tipQuantum the Quantum
	 */
	public TipModel(double totalBill, double tipPer, double tipQuantum) {
		this.totalBill = totalBill;
		this.tipPer = tipPer;
		this.tipQuantum = tipQuantum;

		obs = new ArrayList<>();
	}

	/**
	 * Adds a "Follower" to the model
	 * 
	 * @param o the Observer
	 */
	public void addObserver(ModelObserver o) {
		obs.add(o);
	}

	/**
	 * Notifies all "Followers" a change occured
	 */
	public void notifyObservers() {
		for (ModelObserver o : obs) {
			o.update(this);
		}
	}

	/**
	 * Sets the total amount
	 * 
	 * @param totalBill the total amount
	 */
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
		notifyObservers();
	}

	/**
	 * Sets the Tip Percentage
	 * 
	 * @param tipPer the Percentage
	 */
	public void setTipPer(double tipPer) {
		this.tipPer = tipPer;
		notifyObservers();
	}

	/**
	 * Sets the Quantum
	 * 
	 * @param tipQuantum the Quantum
	 */
	public void setTipQuantum(double tipQuantum) {
		this.tipQuantum = tipQuantum;
		notifyObservers();
	}

	/**
	 * Returns the tip rounded to 2 decimal places
	 * 
	 * @return formatAmt the Tip
	 */
	public double getRoundedTip() {

		// The Tip

		double tipPer = this.tipPer / 100;

		double tipAmount = this.totalBill * tipPer;

		// Round based on the Quantum

		double overage = tipAmount % this.tipQuantum;

		double amt = this.tipQuantum - overage;

		if ((this.tipQuantum / 2) < overage) {
			tipAmount += amt;
		}

		else {
			tipAmount -= overage;
		}

		
		return tipAmount;
		
		// Format

		
//		String formatAmt;
//
//		DecimalFormat df = new DecimalFormat("0.00");
//
//		formatAmt = df.format(tipAmount);
//
//		return formatAmt;
	}

	/**
	 * Returns the actual tip percentage after being rounded
	 * 
	 * @return formatAmt the Effective Percentage
	 */
	public String getEffPer() {
//		String amount = getRoundedTip();

//		Double amt = Double.parseDouble(amount);

		Double amt = getRoundedTip();
		
		Double effPer = (amt / this.totalBill) * 100;

		String formatAmt;

		DecimalFormat df = new DecimalFormat("0.00");

		formatAmt = df.format(effPer);

		return formatAmt;

	}

	/**
	 * Returns the string version of the data
	 * 
	 * @return the string version of the data
	 */
	public String toString() {
		return this.totalBill + "," + this.tipPer + "," + this.tipQuantum;
	}

}
