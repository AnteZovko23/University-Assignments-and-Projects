import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * UI Class that shows 4 widgets
 * 
 * @author Ante Zovko
 * @version 3/14/2019
 *
 */
public class UIClass {

	private JFrame frame;
	private ModelClass model;
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem quit;
	private JMenu help;
	private JMenuItem about;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Models

					ModelClass m = new ModelClass();

					ModelClass model2 = new ModelClass();

					QuadraticModel model3 = new QuadraticModel();

					ExponentialModel model4 = new ExponentialModel();

					/****************** WIDGETS **********************/

					// First

					WidgClass widget = new WidgClass(m, "Widget 1");
					JLabel label1 = new JLabel("Default Slider");
					label1.setBounds(235, -30, 100, 100);
					widget.add(label1);

					widget.setSpacing(10, 10, 2);

					// Second

					WidgClass widget2 = new WidgClass(model2, "Widget 2");

					JLabel label2 = new JLabel("Slider 3-3000");
					label2.setBounds(223, -30, 100, 100);
					widget2.add(label2);

					widget2.sliderMax(3000);
					widget2.sliderMin(3);
					widget2.setSpacing(100, 499, 50);

					// Third

					WidgClass widget3 = new WidgClass(model3, "Widget 3");

					JLabel label3 = new JLabel("Quadratic Slider");
					label3.setBounds(227, -30, 100, 100);
					widget3.add(label3);

					widget3.sliderMax(100);
					widget3.sliderMin(0);
					widget3.setSpacing(10, 10, 2);

					// Fourth

					WidgClass widget4 = new WidgClass(model4, "Widget 4");
					JLabel label4 = new JLabel("Exponential Slider");
					label4.setBounds(227, -30, 150, 100);
					widget4.add(label4);

					widget4.sliderMax(20);
					widget4.sliderMin(0);
					widget4.setSpacing(5, 2, 1);

					/****************** UI **********************/

					UIClass ui = new UIClass(m);

					// Adding widgets

					widget.setBounds(1, 0, 490, 150);
					widget2.setBounds(1, 170, 490, 150);
					widget3.setBounds(1, 330, 490, 150);
					widget4.setBounds(1, 500, 490, 150);

					ui.frame.getContentPane().add(widget);
					ui.frame.getContentPane().add(widget2);
					ui.frame.getContentPane().add(widget3);
					ui.frame.getContentPane().add(widget4);
					
					ui.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Constructor
	 * 
	 * @param m Model
	 */
	public UIClass(ModelClass m) {
		model = m;
		initialize();

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, "CS 257 Lab 7 Exercise" + "\nAuthor: Ante Zovko\nVersion: 3/13/2019"
						+ "\nLocation: St. Bonaventure University");
			}
		});

	}

	/**
	 * Summons the GUI
	 * 
	 * 
	 */
	public void initialize() {

		/*************** THE FRAME ********************/

		frame = new JFrame("Sliders Demo by Ante Zovko");
		frame.setBounds(700, 0, 500, 720);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		/*************** MENU ********************/

		menu = new JMenuBar();
		frame.setJMenuBar(menu);

		// First Drop Down

		file = new JMenu("File");
		quit = new JMenuItem("Quit");

		menu.add(file);
		file.add(quit);

		// Second Drop Down

		help = new JMenu("Help");
		about = new JMenuItem("About");

		menu.add(help);
		help.add(about);

		// Shortcuts

		quit.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke('A', CTRL_DOWN_MASK));

	}
}


