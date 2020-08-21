import java.text.DecimalFormat;

/**
 * A viewer for the Tip Amount of the Tipping Calculator
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class TipViewer implements ModelObserver {

	private UIClass ui;

	/**
	 * Constructor
	 * 
	 * @param ui the GUI
	 */
	public TipViewer(UIClass ui) {
		this.ui = ui;

	}

	/**
	 * Updates the output window when the input is changed
	 * 
	 * @param m the Model
	 */
	@Override
	public void update(TipModel m) {

		String formatAmt;

		DecimalFormat df = new DecimalFormat("0.00");

		formatAmt = df.format(m.getRoundedTip());
//
//		return formatAmt;
		
		this.ui.getTxtOutput1().setText(formatAmt);

	}

}
