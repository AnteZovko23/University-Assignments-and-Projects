package application;
import java.text.DecimalFormat;

/**
 * A viewer for the Tip Amount and Effective Percentage of the Tipping Calculator
 * 
 * @author Ante Zovko
 * @version 3/29/2019
 *
 */
public class TipViewer implements ModelObserver {

	private Controller control;

	/**
	 * Constructor
	 * 
	 * @param c the Controller
	 */
	public TipViewer(Controller c) {
		
		control = c;

	}

	/**
	 * Updates the output windows when the input is changed
	 * 
	 * @param m the Model
	 */
	@Override
	public void update(TipModel m) {

		String formatPer;
		String formatAmt;
		
		
		DecimalFormat df = new DecimalFormat("0.00");

		formatAmt = df.format(m.getRoundedTip());
		
		formatPer = df.format(m.getEffPer());

		
		control.setView(formatAmt, formatPer);
		
		
	}

}
