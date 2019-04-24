


import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;

/**
 * 
 * Creates a Graphical User Interface with a menu bar which allows the users to
 * bump the value by either 1, 8 or set a completely arbitrary new value
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class UIClass {

	private JFrame frame;

	// GUI Comps
	private JButton Nudge;
	private JButton Shove;
	private Model number;

	// Menu Comps
	private JMenuBar jmb;

	// First drop down
	private JMenu file;
	private JMenuItem quit;

	// Second drop down
	private JMenu edit;
	private JMenuItem nudge;
	private JMenuItem shove;
	private JMenuItem set;

	// Third drop down
	private JMenu view;
	private JMenuItem addTxtViewer;
	private JMenuItem addBarViewer;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Input Window

					Model myNum = new Model();

					UIClass window = new UIClass(myNum);

					window.frame.setVisible(true);

					// Text Viewer

					TextViewer tx = new TextViewer(myNum);

					tx.frame.setVisible(true);

					myNum.addObserver(tx);

					tx.frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {

							myNum.removeObserver(tx);

						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Default Constructor summons the GUI
	 * 
	 * @param m the Model to be used
	 */
	public UIClass(Model m) {
		number = m;
		initialize();

		// Button Listeners

		Nudge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				number.bumpData(1);
			}
		});
		Shove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				number.bumpData(8);
			}
		});

		// Menu Listeners

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		nudge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				number.bumpData(1);
			}
		});
		shove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				number.bumpData(8);
			}
		});

		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {

					String userInput = JOptionPane.showInputDialog("Enter your value: ");
					number.setData(Integer.parseInt(userInput));

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Please enter a valid number!");
				}
			}
		});

		addTxtViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TextViewer tx2 = new TextViewer(number);

				tx2.frame.setVisible(true);

				number.addObserver(tx2);

				tx2.frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent ae) {

						number.removeObserver(tx2);

					}
				});
			}

		});

		addBarViewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				GRectViewer gr = new GRectViewer(number);

				gr.frame.setVisible(true);

				number.addObserver(gr);

				gr.frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {

						number.removeObserver(gr);

					}
				});
			}
		});
	}

	/**
	 * Creates the GUI Components
	 * 
	 * @see http://www.java2s.com/Tutorial/Java/0240__Swing/AddingMenuShortcuts.htm
	 * 
	 */
	private void initialize() {

		/************** The Frame *************/

		frame = new JFrame("Ante Zovko GUI");

		frame.setBounds(700, 0, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(0, 0, 70));

		// Nudge Button

		Nudge = new JButton("NUDGE");

		Nudge.setBounds(0, 100, 200, 200);
		Nudge.setBackground(new Color(7, 102, 82));

		frame.getContentPane().add(Nudge);

		// Shove Button

		Shove = new JButton("SHOVE");

		Shove.setBounds(292, 100, 200, 200);
		Shove.setBackground(new Color(255, 0, 132));

		frame.getContentPane().add(Shove);

		/*************** The Menu ******************/

		jmb = new JMenuBar();
		frame.setJMenuBar(jmb);

		// First drop down

		file = new JMenu("File");

		quit = new JMenuItem("Quit");

		// Second drop down

		edit = new JMenu("Edit");

		nudge = new JMenuItem("Nudge");
		shove = new JMenuItem("Shove");
		set = new JMenuItem("Set");

		// Third drop down

		view = new JMenu("View");

		addTxtViewer = new JMenuItem("Add a Text Viewer");
		addBarViewer = new JMenuItem("Add a Bar Viewer");

		// Shortcuts

		quit.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		nudge.setAccelerator(KeyStroke.getKeyStroke('A', CTRL_DOWN_MASK));
		shove.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		set.setAccelerator(KeyStroke.getKeyStroke('D', CTRL_DOWN_MASK));
		addTxtViewer.setAccelerator(KeyStroke.getKeyStroke('W', CTRL_DOWN_MASK));
		addBarViewer.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));

		// Adding menu components

		jmb.add(file);
		jmb.add(edit);
		jmb.add(view);

		// Adding Drop Down Components

		file.add(quit);

		edit.add(nudge);
		edit.add(shove);
		edit.add(set);

		view.add(addTxtViewer);
		view.add(addBarViewer);

	}

}
