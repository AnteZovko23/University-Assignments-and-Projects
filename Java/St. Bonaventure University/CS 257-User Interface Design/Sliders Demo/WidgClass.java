
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Widget with a slider and text field
 * 
 * @author Ante Zovko
 * @version 3/14/2019
 *
 */
public class WidgClass extends JPanel implements ChangeListener {

	private JTextField txt1;
	private JSlider slider;
	private ModelClass model;
	private String title;
	private Border compound, redline, raisedbevel, loweredbevel;
	private Border compound2, redline2, raisedbevel2, loweredbevel2;
	private Border compound3, redline3, raisedbevel3, loweredbevel3;

	/**
	 * Constructor
	 * 
	 * @see https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
	 * @see https://www.youtube.com/watch?v=4U3wI4EsIZ8
	 * @param m  Model
	 * @param title the title
	 */
	public WidgClass(ModelClass m, String title) {

		model = m;
		this.title = title;

		/****************** Components **********************/

		// Text Field

		txt1 = new JTextField("Number");
		txt1.setBounds(340, 38, 120, 100);
		txt1.setFont(new Font("Verdana", Font.BOLD, 15));
		txt1.setHorizontalAlignment(SwingConstants.CENTER);

		// Slider

		slider = new JSlider(slider.HORIZONTAL, 0, 100, 0);
		slider.setPaintTicks(true);
		slider.setBounds(10, 40, 300, 100);

		/****************** Listeners **********************/

		slider.addChangeListener(this);

		txt1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					try {

						model.setData(Double.parseDouble(txt1.getText()));

					} catch (NumberFormatException nfe) {
						txt1.setText("Invalid Input");
					}

				}
			}
		});
		
		/**
		 * 
		 * Adds and Removes text from the TextField depending on if it receives Focus
		 * 
		 * @see https://stackoverflow.com/questions/16303916/how-to-make-jtextfield-text-disappear-when-tabbed-into-netbeans
		 */
		txt1.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				txt1.setText(null); // Empty the text field when it receives focus
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt1.setText("Press Enter"); // Appears when it loses focus
				txt1.setFont(new Font("Verdana", Font.BOLD, 15));
				txt1.setHorizontalAlignment(SwingConstants.CENTER);
			}

		});

	

		// Adding components to various places

		model.setComponents(txt1, slider);
		setLayout(null);
		add(slider);
		add(txt1);

		/****************** Border **********************/

		/**** For the whole widget ****/

		redline3 = BorderFactory.createLineBorder(Color.red);
		raisedbevel3 = BorderFactory.createRaisedBevelBorder();
		loweredbevel3 = BorderFactory.createLoweredBevelBorder();

		// This creates a 3d frame.
		compound3 = BorderFactory.createCompoundBorder(raisedbevel3, loweredbevel3);

		// Add a red outline to the frame.
		compound3 = BorderFactory.createCompoundBorder(redline3, compound3);

		// Add a title to the red-outlined frame.
		compound3 = BorderFactory.createTitledBorder(compound3, "", TitledBorder.CENTER, TitledBorder.ABOVE_TOP);

		setBorder(compound3);

		
		
		/**** For the slider ****/

		redline = BorderFactory.createLineBorder(Color.red);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();

		// This creates a 3d frame.
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

		// Add a red outline to the frame.
		compound = BorderFactory.createCompoundBorder(redline, compound);

		// Add a title to the red-outlined frame.
		compound = BorderFactory.createTitledBorder(compound, this.title, TitledBorder.CENTER, TitledBorder.BELOW_TOP);

		slider.setBorder(compound);

		
		
		/**** For the text field ****/

		redline2 = BorderFactory.createLineBorder(Color.red);
		raisedbevel2 = BorderFactory.createRaisedBevelBorder();
		loweredbevel2 = BorderFactory.createLoweredBevelBorder();

		// This creates a 3d frame.
		compound2 = BorderFactory.createCompoundBorder(raisedbevel2, loweredbevel2);

		// Add a red outline to the frame.
		compound2 = BorderFactory.createCompoundBorder(redline2, compound2);

		// Add a title to the red-outlined frame.
		compound2 = BorderFactory.createTitledBorder(compound2, this.title, TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP);

		txt1.setBorder(compound2);

	}

	/****************** Slider Modifications **********************/

	/**
	 * Allows for tick modifications in the slider
	 * 
	 * @param num  major tick spacing
	 * @param num2 increment of label table
	 * @param num3 minor tick spacing
	 */
	public void setSpacing(int num, int num2, int num3) {

		slider.setMajorTickSpacing(num);
		slider.setLabelTable(slider.createStandardLabels(num2));
		slider.setMinorTickSpacing(num3);
		slider.setPaintLabels(true);

	}

	/**
	 * Sets the max slider amount
	 * 
	 * @param num the max slider amount
	 */
	public void sliderMax(int num) {

		slider.setMaximum(num);

	}

	/**
	 * Sets the min slider amount
	 * 
	 * @param num the min slider amount
	 */
	public void sliderMin(int num) {
		slider.setMinimum(num);
	}

	/**
	 * Modifies data based on the state of the slider
	 * 
	 * @param arg0 a change of the state event
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {

		model.setDataSlide(slider.getValue());

	}

}
