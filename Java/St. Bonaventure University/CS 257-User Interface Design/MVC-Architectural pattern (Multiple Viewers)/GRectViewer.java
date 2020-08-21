import acm.graphics.*;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Creates a Canvas with a bar reading the Model data
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class GRectViewer implements ModelObserver {

	private GRect bar;
	private GCanvas canvas;
	public JFrame frame;
	private Model number;

	final private int yFrame = 500;

	private static int locationX = 1430;
	private static int locationY = 0;

	public GRectViewer(Model number2) {
		initialize();
		number = number2;
	}

	/**
	 * Creates the components for the GUI
	 * 
	 */
	private void initialize() {

		// Creating the Bar

		bar = new GRect(200, yFrame, 50, 0);

		bar.setColor(Color.red);
		bar.setFilled(true);

		// Canvas

		canvas = new GCanvas();

		canvas.setBounds(700, yFrame, 500, 460);
		canvas.add(bar);

		// Creating the JFrame

		frame = new JFrame("Bar Viewer");

		frame.setBounds(locationX, locationY * 10, 500, 540);
		locationX -= 20;
		locationY += 2;
		frame.add(canvas);

		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);

	}

	/**
	 * Updates the Bar
	 * 
	 */
	@Override
	public void update() {

		bar.setBounds(200, yFrame - number.getData(), 50, number.getData());

	}
}
