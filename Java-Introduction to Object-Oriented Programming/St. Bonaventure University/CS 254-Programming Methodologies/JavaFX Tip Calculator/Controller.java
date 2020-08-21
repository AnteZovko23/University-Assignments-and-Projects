package application;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller for tip calculator GUI
 * 
 * @author Ante Zovko
 * @version 3/29/2019
 *
 */
public class Controller {

	private TipModel m;

	private TipViewer ti;

	// Menu

	@FXML
	private MenuItem Quit;

	@FXML
	private MenuItem About;

	// Radio Buttons

	@FXML
	private RadioButton allowAmount;

	@FXML
	private RadioButton allowPerc;

	@FXML
	private RadioButton allowRound;

	// Regular Buttons

	@FXML
	private Button Calculatebtn;

	@FXML
	private Button Reset;

	// Input fields

	@FXML
	private TextField txtAmount;

	@FXML
	private TextField txtPerc;

	@FXML
	private TextField txtRound;

	// Output Fields

	@FXML
	private TextField txtEffPer;

	@FXML
	private TextField txtFinalAmt;

	/**
	 * Default Constructor
	 * 
	 */
	public Controller() {

		m = new TipModel();

		ti = new TipViewer(this);

		m.addObserver(ti);

	}

	/************** MENU ITEMS ****************/

	/**
	 * 
	 * Exits the program
	 * 
	 */
	public void quit() {

		System.exit(0);

	}

	/**
	 * Info about the programmer and GUI
	 * 
	 */
	public void about() {

		JOptionPane.showMessageDialog(null, "Property of Ante Zovko\nBuilt using JavaFX\n"
				+ "Version: 3/29/2019\nLocation:" + " St. Bonaventure University\nInstructions: Enter information and "
				+ "you will get the tip amount.\nIn order to access the text fields you need to select them by pressing the corresponding buttons");

	}

	/************** RADIO BUTTONS ****************/

	/**
	 * Allows to edit the first text field only
	 * 
	 */
	public void allowAmount() {

		txtAmount.setEditable(true);
		txtPerc.setEditable(false);
		txtRound.setEditable(false);

	}

	/**
	 * Allows to edit the second text field only
	 * 
	 */
	public void allowPerc() {

		txtAmount.setEditable(false);
		txtPerc.setEditable(true);
		txtRound.setEditable(false);

	}

	/**
	 * Allows to edit the third text field only
	 * 
	 */
	public void allowRound() {

		txtAmount.setEditable(false);
		txtPerc.setEditable(false);
		txtRound.setEditable(true);

	}

	/************** INPUT TEXT FIELDS ****************/

	/**
	 * 
	 * Get the amount from the text field
	 * 
	 */
	public void getAmount() {

		try {

			m.setTotalBill(Double.parseDouble(txtAmount.getText()));

		} catch (Exception e) {

			txtAmount.setText("NaN");

		}

	}

	/**
	 * 
	 * Get the percentage from the text field
	 * 
	 */
	public void getPercentage() {

		try {

			m.setTipPer(Double.parseDouble(txtPerc.getText()));

		} catch (Exception e) {
			txtPerc.setText("NaN");
		}

	}

	/**
	 * Gets the qauntum from the textfield
	 * 
	 */
	public void getRound() {

		try {

			m.setTipQuantum(Double.parseDouble(txtRound.getText()));

		} catch (Exception e) {
			txtRound.setText("NaN");
		}

	}

	/************** BUTTON ****************/

	/**
	 * Sends the data to the model
	 * 
	 */
	public void calculate() {

		try {

			getAmount();
			getPercentage();
			getRound();

		} catch (Exception e) {

			txtFinalAmt.setText("NaN");
			txtEffPer.setText("NaN");
		}

	}

	/************** OUTPUT TEXT FIELDS ****************/

	/**
	 * Sets the view fields
	 * 
	 * @param amt the total amount
	 * @param per the effective percentage
	 */
	public void setView(String amt, String per) {

		txtFinalAmt.setText(amt);
		txtEffPer.setText(per);

	}

	/**
	 * Resets all text fields
	 * 
	 */
	public void reset() {

		txtAmount.setText("");
		txtPerc.setText("");
		txtRound.setText("");
		txtFinalAmt.setText("");
		txtEffPer.setText("");

	}

}
