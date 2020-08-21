/**
 * A viewer for the Effective Percentage of the Tipping Calculator
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class EffPerViewer implements ModelObserver {

	UIClass ui;

	/**
	 * Constructor
	 * 
	 * @param ui the GUI
	 */
	public EffPerViewer(UIClass ui) {
		this.ui = ui;
	}

	/**
	 * Updates the output window when the input is changed
	 * 
	 * @param m the Model
	 */
	@Override
	public void update(TipModel m) {

		this.ui.getTxtOutput2().setText(m.getEffPer());

	}

}
