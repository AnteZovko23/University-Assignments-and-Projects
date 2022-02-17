
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

/**
 * Creates a Viewer that reads the data from the Model
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class TextViewer implements ModelObserver {

	public JFrame frame;
	private JTextField txtField;
	private Model number;

	private static int location = 0;

	public TextViewer(Model myNum) {
		initialize();
		number = myNum;
	}

	/**
	 * Creates the GUI Components
	 * 
	 */
	public void initialize() {

		// Frame

		frame = new JFrame("First Text Viewer");

		frame.setBounds(location * 10, location * 10, 500, 500);
		location += 2;

		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.blue);

		// Txt Field

		txtField = new JTextField();

		txtField.setBounds(180, 150, 150, 150);
		txtField.setBackground(new Color(137, 255, 253));
		txtField.setHorizontalAlignment(SwingConstants.CENTER);
		txtField.setEditable(false);
		txtField.setFont(new Font("Verdana", Font.BOLD, 40));

		frame.getContentPane().add(txtField);

	}

	/**
	 * Updates the Viewer based on the Model
	 * 
	 */
	@Override
	public void update() {

		txtField.setText(String.valueOf(number.getData()));

	}

}
