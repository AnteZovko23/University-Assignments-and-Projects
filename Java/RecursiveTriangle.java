import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import acm.graphics.GCanvas;
import acm.graphics.GLine;
import acm.graphics.GOval;

/**
 * 
 * Creates a canvas with lines pointing to the centroid of a triangle
 * 
 * @author Ante Zovko
 * @version April 5th, 2019
 * 
 * 
 */

public class RecursiveTriangle {

	private final int GWINDOW_HEIGHT = 685;
	private final int GWINDOW_WIDTH = 960;

	private static int locationY = 0;
	private static int locationX = 350;

	private static int numOfWindows = 0;

	private JFrame mainFrame;
	private GCanvas triangles;

	boolean b = false;

	JFrame dotFrame;
	GCanvas dotCourse;

	JPanel jp;

	JLabel l;
	JLabel l2;

	JLabel lbl;
	JLabel lbl2;
	JLabel lbl3;
	JLabel lbl4;
	JLabel lbl5;
	JLabel lbl6;
	JLabel lbl7;

	JLabel lbl2nx;
	JLabel lbl3nx;
	JLabel lbl4nx;
	JLabel lbl5nx;
	JLabel lbl6nx;
	JLabel lbl7nx;

	JTextField textField;
	JTextField textField2;
	JTextField textField3;
	JTextField textField4;
	JTextField textField5;
	JTextField textField6;
	JTextField textField7;

	JButton execute;
	JButton reset;
	JButton defaultVer;
	JButton randomColors;
	JButton pickColors;

	JCheckBox box; // YES

	// Menu Comps
	private JMenuBar jmb;

	// First drop down
	private JMenu file;
	private JMenu newWindow;
	private JMenu centroid;
	private JMenuItem closeWindow;
	private JMenuItem quit;

	private JMenu view;
	private JMenu centroidToggle;
	private JMenuItem randomCentroidToggle;
	private JMenuItem specificCentroidToggle;
	private JMenu sierpinskiToggle;
	private JMenuItem randomSierpinskiToggle;
	private JMenuItem specificSierpinskiToggle;
//	private JMenu ultimateDotRaceToggle;

	private JMenu sierpinski;
	private JMenuItem randomSierpinskiWindow;
	private JMenuItem specificSierpinskiWindow;

	private JMenuItem dotRace;
//	private JMenu dotRaceView;

	// Second drop down
	private JMenuItem defaultMenu;
	private JMenuItem resetMenu;

	private JMenuItem newRandomWindow;
	private JMenuItem newPickWindow;

	// Third drop down
	private JMenu help;
	private JMenuItem about;

	Color line1;
	Color line2;
	Color line3;

	int x1;
	int x2;
	int x3;
	int y1;
	int y2;
	int y3;
	int depth;

	JButton dotStartRace;
	JButton dotBackgroundColors;
	JButton dotBack;
	JButton dotDefault;
	JButton dotReset;

	JTextField dotTextField;
	JTextField dotTextField2;

	JLabel dotLbl;
	JLabel dotLbl2;

	ArrayList<GOval> dotArr;
	ArrayList<Color> dotColors;

	int amountOfDots;
	int farthestX;
	int speed;
	int space;
	int dotSize;

	Timer timer;

	Color dotColor;

	Color dotRestColor;

	JLabel dotWarning;
	JLabel dotWarning2;

	Color c;

	public static void main(String[] args) {

		try {
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {

					@SuppressWarnings("unused")
					RecursiveTriangle gui = new RecursiveTriangle();
					numOfWindows++;
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public RecursiveTriangle() {
		createWindows();
		textFieldAction();
//		centroidText();
		centroidDynamic(); // YES

	}

	private void createWindows() {
		mainFrame = new JFrame("Recursive Triangles v0.8.2 (Pre-Release) ");
		mainFrame.setSize(GWINDOW_WIDTH, GWINDOW_HEIGHT);
		mainFrame.setLocation(locationX, locationY * 50);
		if (locationY < 2) {
			locationY++;

		} else {
			if (locationX <= 600) {
				locationY = 0;
				locationX += 100;
			} else {
				locationY = 0;
				locationX = 0;

			}

		}
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setBackground(Color.white);

		mainFrame.addWindowListener(new WindowAdapter() {

			public void windowClosed(WindowEvent we) {
				numOfWindows--;
				if (numOfWindows == 0) {
					System.exit(0);
				}
			}

		});

		triangles = new GCanvas(); // drawing space
		triangles.setPreferredSize(new Dimension(GWINDOW_WIDTH, GWINDOW_HEIGHT));
		triangles.setLocation(0, 0);
		triangles.setBackground(Color.black);
		randomColor();

		centroid(478, 40, 145, 400, 812, 400, 4);

		menuBar();

		Font font = new Font("Verdana", Font.BOLD, 10);
		Font fontTxt = new Font("Verdana", Font.BOLD, 30);
		Font fontWarning = new Font("Verdana", Font.BOLD | Font.ITALIC, 12);

		execute = new JButton("CREATE");
		execute.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 22));

		execute.setBounds(384, 635, 190, 50);

		reset = new JButton("RESET");
		reset.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 22));
		reset.setBackground(Color.white);

		reset.setBounds(625, 635, 150, 50);

		defaultVer = new JButton("DEFAULT");
		defaultVer.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 22));
		defaultVer.setBackground(Color.white);

		defaultVer.setBounds(191, 635, 150, 50);

		randomColors = new JButton("<html>RANDOM" + "<p>COLORS</html>");
		randomColors.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 20));
		randomColors.setBackground(Color.white);

		randomColors.setBounds(0, 635, 150, 50);

		pickColors = new JButton("<html>CHOOSE" + "<p>COLORS</html>");
		pickColors.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 20));
		pickColors.setBackground(Color.white);

		pickColors.setBounds(810, 635, 150, 50);

		centroidButtons();

		mainFrame.add(pickColors);
		mainFrame.add(randomColors);
		mainFrame.add(defaultVer);
		mainFrame.add(reset);
		mainFrame.add(execute);

		lbl = new JLabel("Enter the Depth");
		lbl.setBounds(3, 15, 100, 10);
		lbl.setForeground(Color.black);
		lbl.setFont(new Font("Verdana", Font.BOLD, 10));

		lbl2 = new JLabel("X Coordinate");
		lbl2.setBounds(156, 10, 100, 10);
		lbl2.setForeground(Color.black);
		lbl2.setFont(font);

		lbl2nx = new JLabel("(Left Vertex)");
		lbl2nx.setBounds(155, 20, 100, 12);
		lbl2nx.setForeground(Color.black);
		lbl2nx.setFont(font);

		lbl3 = new JLabel("Y Coordinate");
		lbl3.setBounds(297, 10, 100, 10);
		lbl3.setForeground(Color.black);
		lbl3.setFont(font);

		lbl3nx = new JLabel("(Left Vertex)");
		lbl3nx.setBounds(296, 20, 100, 12);
		lbl3nx.setForeground(Color.black);
		lbl3nx.setFont(font);

		lbl4 = new JLabel("X Coordinate");
		lbl4.setBounds(443, 10, 100, 10);
		lbl4.setForeground(Color.black);
		lbl4.setFont(font);

		lbl4nx = new JLabel("(Top Vertex)");
		lbl4nx.setBounds(443, 20, 100, 12);
		lbl4nx.setForeground(Color.black);
		lbl4nx.setFont(font);

		lbl5 = new JLabel("Y Coordinate");
		lbl5.setBounds(585, 10, 100, 10);
		lbl5.setForeground(Color.black);
		lbl5.setFont(font);

		lbl5nx = new JLabel("(Top Vertex)");
		lbl5nx.setBounds(585, 20, 100, 12);
		lbl5nx.setForeground(Color.black);
		lbl5nx.setFont(font);

		lbl6 = new JLabel("X Coordinate");
		lbl6.setBounds(728, 10, 100, 10);
		lbl6.setForeground(Color.black);
		lbl6.setFont(font);

		lbl6nx = new JLabel("(Right Vertex)");
		lbl6nx.setBounds(723, 20, 100, 12);
		lbl6nx.setForeground(Color.black);
		lbl6nx.setFont(font);

		lbl7 = new JLabel("Y Coordinate");
		lbl7.setBounds(878, 10, 100, 10);
		lbl7.setForeground(Color.black);
		lbl7.setFont(font);

		lbl7nx = new JLabel("(Right Vertex)");
		lbl7nx.setBounds(874, 20, 100, 12);
		lbl7nx.setForeground(Color.black);
		lbl7nx.setFont(font);

		/*************** IN THE MAIN *******************************/

		box = new JCheckBox("Dynamically update Triangle Coordinates");
		box.setFont(fontWarning);
		box.setBackground(new Color(220, 220, 220));
		box.setBounds(650, 145, 600, 13);
		box.setSelected(true);

		centroidBox(); // YES

		/************************************************************/

		getNewText(fontTxt);

		l = new JLabel("Entering more than 14 is not recommended!");
		l.setBounds(0, 145, 600, 13);
		l.setForeground(new Color(220, 20, 60));
		l.setFont(fontWarning);
		l.setVisible(false);

		l2 = new JLabel("Warning: Out of Bounds");
		l2.setBounds(0, 145, 600, 13);
		l2.setForeground(new Color(220, 20, 60));
		l2.setFont(fontWarning);
		l2.setVisible(false);

		jp = new JPanel(); ///////////////////////////////////////// ****************************

		jp.add(l2);
		jp.add(l); /////////

		jp.setBounds(0, 471, 960, 215);
		jp.setLayout(null);

		jp.setBackground(new Color(220, 220, 220));

		jp.add(box); // YES
		jp.add(lbl);
		jp.add(lbl2);
		jp.add(lbl3);
		jp.add(lbl4);
		jp.add(lbl5);
		jp.add(lbl6);
		jp.add(lbl7);

		jp.add(lbl2nx);
		jp.add(lbl3nx);
		jp.add(lbl4nx);
		jp.add(lbl5nx);
		jp.add(lbl6nx);
		jp.add(lbl7nx);

		jp.add(textField);
		jp.add(textField2);
		jp.add(textField3);
		jp.add(textField4);
		jp.add(textField5);
		jp.add(textField6);
		jp.add(textField7);

		mainFrame.add(jp);

		mainFrame.add(triangles);
		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	/**********************
	 * ADDDDDDDDDDDDDDDD TOOOOOOOOOOO MMMMMAAAAAAAAAAAAAIIIIIIIIINNNNNNNN
	 */
	private void centroidBox() {
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (box.isSelected()) {
					System.out.println("TEST");
					removeTextListeners();
					buttonAction();
					textFieldAction();
					centroidDynamic();
				} else {
					removeTextKeyListeners();
					removeTextKeyListeners();
					textFieldAction();
					buttonAction();

					System.out.println(textField4.getKeyListeners().length);
					centroidText();

					System.out.println("TEST2");
				}

			}

		});
	}

	private void RemoveBoxListeners() {

		for (int i = 0; i < box.getItemListeners().length; i++) {

			box.removeItemListener(box.getItemListeners()[i]);

		}

	}

	private void SierpinskiBox() {
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (box.isSelected()) {
					System.out.println("TEST");
					removeTextListeners();
					buttonAction();
					textFieldAction();
					sierpinskiDynamic();
				} else {
					removeTextKeyListeners();
					removeTextKeyListeners();
					textFieldAction();
					buttonAction();

					System.out.println(textField4.getKeyListeners().length);
					sierpinskiText();

					System.out.println("TEST2");
				}

			}

		});
	}

	private void centroidButtons() {

		execute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTextInvalid()) {
					newBlankCanvas();
				} else {
					newCanvas();
				}
			}
		});

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				resetCentroidEverything();
			}

		});

		defaultVer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				defaultCentroidTriangle();
			}

		});

		randomColors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTextInvalid()) {
					newBlankCanvas();
				} else {
					randomColor();
					newCanvas();
				}

			}

		});

		pickColors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTextInvalid()) {
					newBlankCanvas();

				} else {

					pickColor();
					newCanvas();
				}
			}

		});
	}

	private void sierpinskiButtons() {

		execute.setBounds(384, 635, 190, 50);

		execute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTextInvalid()) {
					newBlankCanvas();
				} else {
					newSierpinski();
				}
			}
		});

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				resetSierpinskiEverything();
			}

		});

		defaultVer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				defaultSierpinskiTriangle();
			}

		});

		randomColors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTextInvalid()) {
					newBlankCanvas();
				} else {
					randomColor();
					newSierpinski();
				}

			}

		});

		pickColors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTextInvalid()) {
					newBlankCanvas();

				} else {

					pickColor();
					newSierpinski();
				}
			}

		});
	}

	private void getNewText(Font fontTxt) {

		textField = new JTextField("4");
		textField.setBounds(0, 40, 100, 100);
		textField.setBackground(Color.black);
		textField.setVisible(true);
		textField.setForeground(Color.white);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(fontTxt);

		textField2 = new JTextField("478");
		textField2.setBounds(429, 40, 100, 100);
		textField2.setBackground(Color.black);
		textField2.setVisible(true);
		textField2.setForeground(Color.white);
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setFont(fontTxt);

		textField3 = new JTextField("40");
		textField3.setBounds(572, 40, 100, 100);
		textField3.setBackground(Color.black);
		textField3.setVisible(true);
		textField3.setForeground(Color.white);
		textField3.setHorizontalAlignment(SwingConstants.CENTER);
		textField3.setFont(fontTxt);

		textField4 = new JTextField("145");
		textField4.setBounds(143, 40, 100, 100);
		textField4.setBackground(Color.black);
		textField4.setVisible(true);
		textField4.setForeground(Color.white);
		textField4.setHorizontalAlignment(SwingConstants.CENTER);
		textField4.setFont(fontTxt);

		textField5 = new JTextField("400");
		textField5.setBounds(286, 40, 100, 100);
		textField5.setBackground(Color.black);
		textField5.setVisible(true);
		textField5.setForeground(Color.white);
		textField5.setHorizontalAlignment(SwingConstants.CENTER);
		textField5.setFont(fontTxt);

		textField6 = new JTextField("812");
		textField6.setBounds(715, 40, 100, 100);
		textField6.setBackground(Color.black);
		textField6.setVisible(true);
		textField6.setForeground(Color.white);
		textField6.setHorizontalAlignment(SwingConstants.CENTER);
		textField6.setFont(fontTxt);

		textField7 = new JTextField("400");
		textField7.setBounds(861, 40, 100, 100);
		textField7.setBackground(Color.black);
		textField7.setVisible(true);
		textField7.setForeground(Color.white);
		textField7.setHorizontalAlignment(SwingConstants.CENTER);
		textField7.setFont(fontTxt);

	}

	/**
	 * 
	 * Recursive method that creates a centroid
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 * @param depth
	 * 
	 * 
	 */
	public void centroid(int x1, int y1, int x2, int y2, int x3, int y3, int depth) {

		GLine edge1 = new GLine(x1, y1, x2, y2);
		edge1.setColor(line1);
		triangles.add(edge1);

		GLine edge2 = new GLine(x2, y2, x3, y3);
		edge2.setColor(line2);
		triangles.add(edge2);

		GLine edge3 = new GLine(x3, y3, x1, y1);
		edge3.setColor(line3);
		triangles.add(edge3);

		int centroidx = (x1 + x2 + x3) / 3;
		int centroidy = (y1 + y2 + y3) / 3;

		if (depth > 0) {
			centroid(x1, y1, x2, y2, centroidx, centroidy, depth - 1);
			centroid(x1, y1, x3, y3, centroidx, centroidy, depth - 1);
			centroid(x3, y3, x2, y2, centroidx, centroidy, depth - 1);
		}

	}

	public void sierpinski(int x1, int y1, int x2, int y2, int x3, int y3, int depth) {

		GLine edge1 = new GLine(x1, y1, x2, y2);
		edge1.setColor(line1);
		triangles.add(edge1);

		GLine edge2 = new GLine(x2, y2, x3, y3);
		edge2.setColor(line2);
		triangles.add(edge2);

		GLine edge3 = new GLine(x3, y3, x1, y1);
		edge3.setColor(line3);
		triangles.add(edge3);

		int midx12 = ((x1 + x2) / 2);
		int midy12 = ((y1 + y2) / 2);
		int midx13 = ((x2 + x3) / 2);
		int midy13 = ((y2 + y3) / 2);
		int midx23 = ((x1 + x3) / 2);
		int midy23 = ((y1 + y3) / 2);

		if (depth >= 0) {
			sierpinski(x1, y1, midx12, midy12, midx23, midy23, depth - 1);
			sierpinski(midx12, midy12, x2, y2, midx13, midy13, depth - 1);
			sierpinski(midx23, midy23, midx13, midy13, x3, y3, depth - 1);
		}
	}

	private void newCanvas() {

		newBlankCanvas();
		buttonAction();
		centroid(x1, y1, x2, y2, x3, y3, depth);

	}

	private void newSierpinski() {

		newBlankCanvas();
		buttonAction();
		sierpinski(x1, y1, x2, y2, x3, y3, depth);

	}

	private void newBlankCanvas() {

		mainFrame.remove(triangles);

		triangles = new GCanvas(); // drawing space
		triangles.setPreferredSize(new Dimension(GWINDOW_WIDTH, GWINDOW_HEIGHT));
		triangles.setLocation(0, 0);
		triangles.setBackground(Color.black);
		mainFrame.add(triangles);
		mainFrame.pack();
		centroid(0, 0, 0, 0, 0, 0, 0);

	}

	private void resetCentroidEverything() {
		textField.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
		textField5.setText("");
		textField6.setText("");
		textField7.setText("");
		x1 = 100;
		x2 = 100;
		x3 = 100;
		y1 = 100;
		y2 = 100;
		y3 = 100;
		depth = 0;
		l.setVisible(false);
		l2.setVisible(false);
		newBlankCanvas();
	}

	private void resetSierpinskiEverything() {
		textField.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
		textField5.setText("");
		textField6.setText("");
		textField7.setText("");
		depth = 0;
		x1 = 0;
		x2 = 0;
		x3 = 0;
		y1 = 0;
		y2 = 0;
		y3 = 0;
		depth = 0;
		l.setVisible(false);
		l2.setVisible(false);
		newBlankCanvas();
	}

	private void defaultCentroidTriangle() {
		newBlankCanvas();
		textField.setText("4");
		textField2.setText("478");
		textField3.setText("40");
		textField4.setText("145");
		textField5.setText("400");
		textField6.setText("812");
		textField7.setText("400");
		centroid(478, 40, 145, 400, 812, 400, 4);
	}

	private void defaultSierpinskiTriangle() {
		newBlankCanvas();
		textField.setText("4");
		textField2.setText("478");
		textField3.setText("40");
		textField4.setText("145");
		textField5.setText("400");
		textField6.setText("812");
		textField7.setText("400");
		sierpinski(478, 40, 145, 400, 812, 400, 4);
	}

	/**************************************************/

	private void textFieldAction() {

		textField.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField.getText().length() > 3) {
					textField.setText(textField.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField.getText()) >= 10) { // YES
						l.setVisible(true);
						l2.setVisible(false);
					} else {
						l.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});

		textField4.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField4.getText().length() > 3) {
					textField4.setText(textField4.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField4.getText()) < 0 || Integer.parseInt(textField4.getText()) > 960) {
						l.setVisible(false);
						l2.setVisible(true);
					} else {
						l2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});

		textField5.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField5.getText().length() > 3) {
					textField5.setText(textField5.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField5.getText()) < 0 || Integer.parseInt(textField5.getText()) > 470) {
						l.setVisible(false);
						l2.setVisible(true);
					} else {
						l2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});
		textField2.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField2.getText().length() > 3) {
					textField2.setText(textField2.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField2.getText()) < 0 || Integer.parseInt(textField2.getText()) > 960) {
						l.setVisible(false);
						l2.setVisible(true);
					} else {
						l2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});
		textField3.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField3.getText().length() > 3) {
					textField3.setText(textField3.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField3.getText()) < 0 || Integer.parseInt(textField3.getText()) > 470) {
						l.setVisible(false);
						l2.setVisible(true);
					} else {
						l2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});
		textField6.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField6.getText().length() > 3) {
					textField6.setText(textField6.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField6.getText()) < 0 || Integer.parseInt(textField6.getText()) > 960) {
						l.setVisible(false);
						l2.setVisible(true);

					} else {
						l2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});

		textField7.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (textField7.getText().length() > 3) {
					textField7.setText(textField7.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(textField7.getText()) < 0 || Integer.parseInt(textField7.getText()) > 470) {
						l.setVisible(false);
						l2.setVisible(true);

					} else {
						l2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});

	}

	private void buttonAction() {
		try {
			depth = Integer.parseInt(textField.getText());
		} catch (Exception e) {
			newBlankCanvas();

		}

		try {
			if (Integer.parseInt(textField2.getText()) >= 0 && Integer.parseInt(textField2.getText()) <= 960) {
				x1 = Integer.parseInt(textField2.getText());
			} else {
				if (Integer.parseInt(textField2.getText()) < 0) {
					x1 = 0;
					textField2.setText("0");
				} else {
					x1 = 960;
					textField2.setText("960");
				}
			}
		} catch (Exception e) {
			newBlankCanvas();
		}

		try {
			if (Integer.parseInt(textField3.getText()) >= 0 && Integer.parseInt(textField3.getText()) <= 470) {
				y1 = Integer.parseInt(textField3.getText());
			} else {
				if (Integer.parseInt(textField3.getText()) < 0) {
					y1 = 0;
					textField3.setText("0");

				} else {
					y1 = 470;
					textField3.setText("470");
				}
			}
		} catch (Exception e) {

			newBlankCanvas();

		}

		try {
			if (Integer.parseInt(textField4.getText()) >= 0 && Integer.parseInt(textField4.getText()) <= 960) {
				x2 = Integer.parseInt(textField4.getText());
			} else {
				if (Integer.parseInt(textField4.getText()) < 0) {
					x2 = 0;
					textField4.setText("0");
				} else {
					x2 = 960;
					textField4.setText("960");
				}
			}
		} catch (Exception e) {

			newBlankCanvas();

		}

		try {

			if (Integer.parseInt(textField5.getText()) >= 0 && Integer.parseInt(textField5.getText()) <= 470) {
				y2 = Integer.parseInt(textField5.getText());
			} else {
				if (Integer.parseInt(textField5.getText()) < 0) {
					y2 = 0;
					textField5.setText("0");

				} else {
					y2 = 470;
					textField5.setText("470");
				}
			}
		} catch (Exception e) {

			newBlankCanvas();

		}

		try {

			if (Integer.parseInt(textField6.getText()) >= 0 && Integer.parseInt(textField6.getText()) <= 960) {
				x3 = Integer.parseInt(textField6.getText());
			} else {
				if (Integer.parseInt(textField6.getText()) < 0) {
					x3 = 0;
					textField6.setText("0");
				} else {
					x3 = 960;
					textField6.setText("960");
				}
			}

		} catch (Exception e) {

			newBlankCanvas();

		}

		try {
			if (Integer.parseInt(textField7.getText()) >= 0 && Integer.parseInt(textField7.getText()) <= 470) {
				y3 = Integer.parseInt(textField7.getText());
			} else {
				if (Integer.parseInt(textField7.getText()) < 0) {
					y3 = 0;
					textField7.setText("0");

				} else {
					y3 = 470;
					textField7.setText("470");
				}
			}
		} catch (Exception e) {

			newBlankCanvas();

		}

	}

	/**
	 * @see https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
	 * 
	 */
	private void menuBar() {

		Font menuFont = new Font("Verdana", Font.BOLD, 14);

		//

		jmb = new JMenuBar();
		mainFrame.setJMenuBar(jmb);

		file = new JMenu("File");
		file.setFont(menuFont);
		jmb.add(file);

		view = new JMenu("View");
		view.setFont(menuFont);
		jmb.add(view);

		centroidToggle = new JMenu("Centroid");
		centroidToggle.setFont(menuFont);
		view.add(centroidToggle);

		randomCentroidToggle = new JMenuItem("with Random Colors");
		randomCentroidToggle.setFont(menuFont);
		centroidToggle.add(randomCentroidToggle);

		specificCentroidToggle = new JMenuItem("with Specific Colors");
		specificCentroidToggle.setFont(menuFont);
		centroidToggle.add(specificCentroidToggle);

		sierpinskiToggle = new JMenu("Sierpinski Triangle");
		sierpinskiToggle.setFont(menuFont);
		view.add(sierpinskiToggle);

		randomSierpinskiToggle = new JMenuItem("with Random Colors");
		randomSierpinskiToggle.setFont(menuFont);
		sierpinskiToggle.add(randomSierpinskiToggle);

		specificSierpinskiToggle = new JMenuItem("with Specific Colors");
		specificSierpinskiToggle.setFont(menuFont);
		sierpinskiToggle.add(specificSierpinskiToggle);

		defaultMenu = new JMenuItem("Default");
		defaultMenu.setFont(menuFont);
		view.add(defaultMenu);

		resetMenu = new JMenuItem("Reset");
		resetMenu.setFont(menuFont);
		view.add(resetMenu);

		newWindow = new JMenu("New");
		newWindow.setFont(menuFont);
		file.add(newWindow);

		JMenuItem test = new JMenuItem("Test");

		view.add(test);

		centroid = new JMenu("Centroid Window");

		newRandomWindow = new JMenuItem("With Random Colors");
		newRandomWindow.setFont(menuFont);
		newPickWindow = new JMenuItem("With Specific Colors");
		newPickWindow.setFont(menuFont);

		sierpinski = new JMenu("Sierpinski Triangle Window");

		randomSierpinskiWindow = new JMenuItem("With Random Colors");
		randomSierpinskiWindow.setFont(menuFont);

		specificSierpinskiWindow = new JMenuItem("With Specific Colors");
		specificSierpinskiWindow.setFont(menuFont);

		sierpinski.add(randomSierpinskiWindow);
		sierpinski.add(specificSierpinskiWindow);

		dotRace = new JMenuItem("Ultimate Dot Race (Coming Soon)");
//		dotRace.setEnabled(false);
		dotRace.setFont(menuFont);

		centroid.setFont(menuFont);
		sierpinski.setFont(menuFont);

		centroid.add(newRandomWindow);
		centroid.add(newPickWindow);

		newWindow.add(centroid);
		newWindow.add(sierpinski);
		newWindow.add(dotRace);

		closeWindow = new JMenuItem("Close");
		closeWindow.setFont(menuFont);
		file.add(closeWindow);

		quit = new JMenuItem("Quit");
		quit.setFont(menuFont);
		file.add(quit);

		help = new JMenu("Help");
		help.setFont(menuFont);
		jmb.add(help);

		about = new JMenuItem("About");
		about.setFont(menuFont);

		help.add(about);

		/////

		quit.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke('I', CTRL_DOWN_MASK));
		closeWindow.setAccelerator(KeyStroke.getKeyStroke('W', CTRL_DOWN_MASK));

		newRandomWindow.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
		newPickWindow.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK + ALT_DOWN_MASK));

		randomSierpinskiWindow.setAccelerator(KeyStroke.getKeyStroke('M', CTRL_DOWN_MASK));
		specificSierpinskiWindow.setAccelerator(KeyStroke.getKeyStroke('M', CTRL_DOWN_MASK + ALT_DOWN_MASK));

		randomCentroidToggle.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
		specificCentroidToggle.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK + ALT_DOWN_MASK));

		randomSierpinskiToggle.setAccelerator(KeyStroke.getKeyStroke('T', CTRL_DOWN_MASK));
		specificSierpinskiToggle.setAccelerator(KeyStroke.getKeyStroke('T', CTRL_DOWN_MASK + ALT_DOWN_MASK));

		resetMenu.setAccelerator(KeyStroke.getKeyStroke('B', CTRL_DOWN_MASK));

		defaultMenu.setAccelerator(KeyStroke.getKeyStroke('D', CTRL_DOWN_MASK));

		dotRace.setAccelerator(KeyStroke.getKeyStroke('B', CTRL_DOWN_MASK));

		//////

		dotRace.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				createDotRace();

				numOfWindows++;
			}

		});

		defaultMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				defaultCentroidTriangle();
			}

		});

		resetMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				resetCentroidEverything();
				resetSierpinskiEverything();
			}

		});

		specificSierpinskiToggle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				removeTextListeners();
				removeButtonListeners();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultSierpinskiTriangle();
					}

				});

				sierpinskiButtons();
				sierpinskiText();
				pickColor();
				newSierpinski();

			}

		});

		specificCentroidToggle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				removeTextListeners();
				removeButtonListeners();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultCentroidTriangle();
					}

				});

				centroidButtons();
				centroidText();
				pickColor();
				newCanvas();

			}

		});

		randomCentroidToggle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				removeTextListeners();
				removeButtonListeners();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultCentroidTriangle();
					}

				});

				centroidButtons();

//					centroidText();

				randomColor();
				newCanvas();

			}

		});

		randomSierpinskiToggle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				// THIIIIIISSSIISIISI

				RemoveBoxListeners();
				removeTextListeners();
				removeButtonListeners();
				removeTextKeyListeners();
				removeTextKeyListeners();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultSierpinskiTriangle();
					}

				});

//				sierpinskiText();
				if (box.isSelected()) {
					sierpinskiDynamic();
				}
//				
				sierpinskiButtons();
				randomColor();
				newSierpinski();
				SierpinskiBox();

			}

		});

		randomSierpinskiWindow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				RecursiveTriangle res1 = new RecursiveTriangle();
				res1.newBlankCanvas();
				res1.removeTextListeners();
				res1.removeButtonListeners();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultSierpinskiTriangle();
					}

				});

				res1.sierpinskiText();
				res1.sierpinskiButtons();
				res1.randomColor();
				res1.newSierpinski();

				numOfWindows++;

			}

		});

		specificSierpinskiWindow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				RecursiveTriangle res1 = new RecursiveTriangle();
				res1.newBlankCanvas();
				res1.removeTextListeners();
				res1.removeButtonListeners();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultSierpinskiTriangle();
					}

				});

				res1.sierpinskiText();
				res1.sierpinskiButtons();
				res1.pickColor();
				res1.newSierpinski();
				numOfWindows++;

			}

		});

		newRandomWindow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				@SuppressWarnings("unused")
				RecursiveTriangle res1 = new RecursiveTriangle();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultCentroidTriangle();
					}

				});

				numOfWindows++;

				/************************ CHANGED 12:31 PM **********************/
			}

		});

		newPickWindow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				RecursiveTriangle res1 = new RecursiveTriangle();

				defaultMenu.removeActionListener(defaultMenu.getActionListeners()[0]);

				defaultMenu.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {

						defaultCentroidTriangle();
					}

				});
				res1.newBlankCanvas();
				res1.pickColor();
				res1.newCanvas();
				numOfWindows++;

			}

		});

		closeWindow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				mainFrame.dispose();

			}

		});

		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				if (JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to close ALL Windows?", "Quit",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);

				}
			}

		});

		about.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(mainFrame, "Author: Ante Zovko\nLocation: St. Bonaventure University"
						+ "\nVersion: v0.9.1\nApril 5th, 2019", "About", JOptionPane.INFORMATION_MESSAGE);

			}

		});

	}

	private int randomNum() {

		Random ran = new Random();
		int x = ran.nextInt(256);

		return x;

	}

	private void randomColor() {

		line1 = new Color(randomNum(), randomNum(), randomNum());
		line2 = new Color(randomNum(), randomNum(), randomNum());
		line3 = new Color(randomNum(), randomNum(), randomNum());
	}

	private void pickColor() {

		line1 = JColorChooser.showDialog(mainFrame, "Please choose your color", Color.white);
		line2 = JColorChooser.showDialog(mainFrame, "Please choose your color", Color.white);
		line3 = JColorChooser.showDialog(mainFrame, "Please choose your color", Color.white);

	}

	/**
	 * 
	 * @see Lab 7, St. Bonaventure University: "Password Utils.java"
	 * @return
	 */
	private boolean isTextInvalid() {

		ArrayList<JTextField> arr = new ArrayList<>();
		arr.add(textField);
		arr.add(textField2);
		arr.add(textField3);
		arr.add(textField4);
		arr.add(textField5);
		arr.add(textField6);
		arr.add(textField7);

		for (JTextField txt : arr) {

			if (txt.getText().trim().length() == 0) {
				return true;
			}
			for (int letter = 0; letter < txt.getText().length(); letter++) {
				if ("abcdefghijklmnopqrstuvwxyz".indexOf(txt.getText().toLowerCase().charAt(letter)) >= 0) {
					return true;
				} else if ("!@#$`~%^&*()\\/_+|}{',;\":?><-=].[]".indexOf(txt.getText().charAt(letter)) >= 0) {
					return true;
				}
			}

		}

		return false;

	}

	private void centroidDynamic() {

		textField.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.getKeyCode() == 38) {
						depth = Integer.parseInt(textField.getText());
						depth++;
						textField.setText("" + depth);

					}

					else if (fe.getKeyCode() == 40) {
						depth = Integer.parseInt(textField.getText());
						if (depth != 0) {
							depth--;
						}

						textField.setText("" + depth);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					depth = Integer.parseInt(textField.getText());
					newCanvas();

				}

			}

		});

		textField4.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						x2 = Integer.parseInt(textField4.getText());
						x2 += 30;
						textField4.setText("" + x2);

					} else if (fe.getKeyCode() == 38) {
						x2 = Integer.parseInt(textField4.getText());
						x2++;
						textField4.setText("" + x2);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						x2 = Integer.parseInt(textField4.getText());
						x2 -= 30;
						textField4.setText("" + x2);

					}

					else if (fe.getKeyCode() == 40) {
						x2 = Integer.parseInt(textField4.getText());
						x2--;
						textField4.setText("" + x2);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					x2 = Integer.parseInt(textField4.getText());
					newCanvas();

				}

			}

		});

		textField5.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						y2 = Integer.parseInt(textField5.getText());
						y2 += 30;
						textField5.setText("" + y2);

					} else if (fe.getKeyCode() == 38) {
						y2 = Integer.parseInt(textField5.getText());
						y2++;
						textField5.setText("" + y2);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						y2 = Integer.parseInt(textField5.getText());
						y2 -= 30;
						textField5.setText("" + y2);

					}

					else if (fe.getKeyCode() == 40) {
						y2 = Integer.parseInt(textField5.getText());
						y2--;
						textField5.setText("" + y2);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					y2 = Integer.parseInt(textField5.getText());
					newCanvas();

				}

			}

		});

		textField2.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						x1 = Integer.parseInt(textField2.getText());
						x1 += 30;
						textField2.setText("" + x1);

					} else if (fe.getKeyCode() == 38) {
						x1 = Integer.parseInt(textField2.getText());
						x1++;
						textField2.setText("" + x1);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						x1 = Integer.parseInt(textField2.getText());
						x1 -= 30;
						textField2.setText("" + x1);

					}

					else if (fe.getKeyCode() == 40) {
						x1 = Integer.parseInt(textField2.getText());
						x1--;
						textField2.setText("" + x1);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					x1 = Integer.parseInt(textField2.getText());
					newCanvas();

				}

			}

		});

		textField3.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						y1 = Integer.parseInt(textField3.getText());
						y1 += 30;
						textField3.setText("" + y1);

					} else if (fe.getKeyCode() == 38) {
						y1 = Integer.parseInt(textField3.getText());
						y1++;
						textField3.setText("" + y1);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						y1 = Integer.parseInt(textField3.getText());
						y1 -= 30;
						textField3.setText("" + y1);

					}

					else if (fe.getKeyCode() == 40) {
						y1 = Integer.parseInt(textField3.getText());
						y1--;
						textField3.setText("" + y1);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					y1 = Integer.parseInt(textField3.getText());
					newCanvas();

				}

			}

		});

		textField6.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						x3 = Integer.parseInt(textField6.getText());
						x3 += 30;
						textField6.setText("" + x3);

					} else if (fe.getKeyCode() == 38) {
						x3 = Integer.parseInt(textField6.getText());
						x3++;
						textField6.setText("" + x3);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						x3 = Integer.parseInt(textField6.getText());
						x3 -= 30;
						textField6.setText("" + x3);

					}

					else if (fe.getKeyCode() == 40) {
						x3 = Integer.parseInt(textField6.getText());
						x3--;
						textField6.setText("" + x3);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					x3 = Integer.parseInt(textField6.getText());
					newCanvas();

				}

			}

		});

		textField7.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						y3 = Integer.parseInt(textField7.getText());
						y3 += 30;
						textField7.setText("" + y3);

					} else if (fe.getKeyCode() == 38) {
						y3 = Integer.parseInt(textField7.getText());
						y3++;
						textField7.setText("" + y3);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						y3 = Integer.parseInt(textField7.getText());
						y3 -= 30;
						textField7.setText("" + y3);

					}

					else if (fe.getKeyCode() == 40) {
						y3 = Integer.parseInt(textField7.getText());
						y3--;
						textField7.setText("" + y3);

					}
					newCanvas();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					y3 = Integer.parseInt(textField7.getText());
					newCanvas();

				}

			}

		});

	}

	private void sierpinskiDynamic() {

//		textField.addKeyListener(new KeyAdapter() {
//
//			public void keyPressed(KeyEvent fe) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					if (fe.getKeyCode() == 38) {
//						depth = Integer.parseInt(textField.getText());
//						depth++;
//						textField.setText("" + depth);
//
//					}
//
//					else if (fe.getKeyCode() == 40) {
//						depth = Integer.parseInt(textField.getText());
//						if (depth != 0) {
//							depth--;
//						}
//
//						textField.setText("" + depth);
//
//					}
//					newCanvas();
//				}
//
//			}
//
//			public void keyReleased(KeyEvent fez) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					depth = Integer.parseInt(textField.getText());
//					newCanvas();
//
//				}
//
//			}
//
//		});

		textField4.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent fe) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					if (fe.isControlDown() && fe.getKeyCode() == 38) {

						x2 = Integer.parseInt(textField4.getText());
						x2 += 30;
						textField4.setText("" + x2);

					} else if (fe.getKeyCode() == 38) {
						x2 = Integer.parseInt(textField4.getText());
						x2++;
						textField4.setText("" + x2);

					}

					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
						x2 = Integer.parseInt(textField4.getText());
						x2 -= 30;
						textField4.setText("" + x2);

					}

					else if (fe.getKeyCode() == 40) {
						x2 = Integer.parseInt(textField4.getText());
						x2--;
						textField4.setText("" + x2);

					}
					newSierpinski();
				}

			}

			public void keyReleased(KeyEvent fez) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					x2 = Integer.parseInt(textField4.getText());
					newSierpinski();

				}

			}

		});
//
//		textField5.addKeyListener(new KeyAdapter() {
//
//			public void keyPressed(KeyEvent fe) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					if (fe.isControlDown() && fe.getKeyCode() == 38) {
//
//						y2 = Integer.parseInt(textField5.getText());
//						y2 += 30;
//						textField5.setText("" + y2);
//
//					} else if (fe.getKeyCode() == 38) {
//						y2 = Integer.parseInt(textField5.getText());
//						y2++;
//						textField5.setText("" + y2);
//
//					}
//
//					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
//						y2 = Integer.parseInt(textField5.getText());
//						y2 -= 30;
//						textField5.setText("" + y2);
//
//					}
//
//					else if (fe.getKeyCode() == 40) {
//						y2 = Integer.parseInt(textField5.getText());
//						y2--;
//						textField5.setText("" + y2);
//
//					}
//					newCanvas();
//				}
//
//			}
//
//			public void keyReleased(KeyEvent fez) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					y2 = Integer.parseInt(textField5.getText());
//					newCanvas();
//
//				}
//
//			}
//
//		});
//
//		textField2.addKeyListener(new KeyAdapter() {
//
//			public void keyPressed(KeyEvent fe) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					if (fe.isControlDown() && fe.getKeyCode() == 38) {
//
//						x1 = Integer.parseInt(textField2.getText());
//						x1 += 30;
//						textField2.setText("" + x1);
//
//					} else if (fe.getKeyCode() == 38) {
//						x1 = Integer.parseInt(textField2.getText());
//						x1++;
//						textField2.setText("" + x1);
//
//					}
//
//					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
//						x1 = Integer.parseInt(textField2.getText());
//						x1 -= 30;
//						textField2.setText("" + x1);
//
//					}
//
//					else if (fe.getKeyCode() == 40) {
//						x1 = Integer.parseInt(textField2.getText());
//						x1--;
//						textField2.setText("" + x1);
//
//					}
//					newCanvas();
//				}
//
//			}
//
//			public void keyReleased(KeyEvent fez) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					x1 = Integer.parseInt(textField2.getText());
//					newCanvas();
//
//				}
//
//			}
//
//		});
//
//		textField3.addKeyListener(new KeyAdapter() {
//
//			public void keyPressed(KeyEvent fe) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					if (fe.isControlDown() && fe.getKeyCode() == 38) {
//
//						y1 = Integer.parseInt(textField3.getText());
//						y1 += 30;
//						textField3.setText("" + y1);
//
//					} else if (fe.getKeyCode() == 38) {
//						y1 = Integer.parseInt(textField3.getText());
//						y1++;
//						textField3.setText("" + y1);
//
//					}
//
//					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
//						y1 = Integer.parseInt(textField3.getText());
//						y1 -= 30;
//						textField3.setText("" + y1);
//
//					}
//
//					else if (fe.getKeyCode() == 40) {
//						y1 = Integer.parseInt(textField3.getText());
//						y1--;
//						textField3.setText("" + y1);
//
//					}
//					newCanvas();
//				}
//
//			}
//
//			public void keyReleased(KeyEvent fez) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					y1 = Integer.parseInt(textField3.getText());
//					newCanvas();
//
//				}
//
//			}
//
//		});
//
//		textField6.addKeyListener(new KeyAdapter() {
//
//			public void keyPressed(KeyEvent fe) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					if (fe.isControlDown() && fe.getKeyCode() == 38) {
//
//						x3 = Integer.parseInt(textField6.getText());
//						x3 += 30;
//						textField6.setText("" + x3);
//
//					} else if (fe.getKeyCode() == 38) {
//						x3 = Integer.parseInt(textField6.getText());
//						x3++;
//						textField6.setText("" + x3);
//
//					}
//
//					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
//						x3 = Integer.parseInt(textField6.getText());
//						x3 -= 30;
//						textField6.setText("" + x3);
//
//					}
//
//					else if (fe.getKeyCode() == 40) {
//						x3 = Integer.parseInt(textField6.getText());
//						x3--;
//						textField6.setText("" + x3);
//
//					}
//					newCanvas();
//				}
//
//			}
//
//			public void keyReleased(KeyEvent fez) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					x3 = Integer.parseInt(textField6.getText());
//					newCanvas();
//
//				}
//
//			}
//
//		});
//
//		textField7.addKeyListener(new KeyAdapter() {
//
//			public void keyPressed(KeyEvent fe) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					if (fe.isControlDown() && fe.getKeyCode() == 38) {
//
//						y3 = Integer.parseInt(textField7.getText());
//						y3 += 30;
//						textField7.setText("" + y3);
//
//					} else if (fe.getKeyCode() == 38) {
//						y3 = Integer.parseInt(textField7.getText());
//						y3++;
//						textField7.setText("" + y3);
//
//					}
//
//					else if (fe.isControlDown() && fe.getKeyCode() == 40) {
//						y3 = Integer.parseInt(textField7.getText());
//						y3 -= 30;
//						textField7.setText("" + y3);
//
//					}
//
//					else if (fe.getKeyCode() == 40) {
//						y3 = Integer.parseInt(textField7.getText());
//						y3--;
//						textField7.setText("" + y3);
//
//					}
//					newCanvas();
//				}
//
//			}
//
//			public void keyReleased(KeyEvent fez) {
//
//				if (isTextInvalid()) {
//					newBlankCanvas();
//					x1 = 0;
//					x2 = 0;
//					x3 = 0;
//					y1 = 0;
//					y2 = 0;
//					y3 = 0;
//					depth = 0;
//				} else {
//
//					y3 = Integer.parseInt(textField7.getText());
//					newCanvas();
//
//				}
//
//			}
//
//		});

	}

	private void centroidText() {

		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {
					depth = Integer.parseInt(textField.getText());
					newCanvas();
				}

			}

		});

		textField2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newCanvas();
				}

			}

		});
		textField3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newCanvas();
				}
			}

		});
		textField4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newCanvas();
				}

			}

		});

		textField5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newCanvas();
				}

			}

		});
		textField6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newCanvas();
				}

			}

		});
		textField7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newCanvas();
				}

			}

		});

	}

	private void sierpinskiText() {
		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

//					depth = Integer.parseInt(textField.getText());
				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {
					newSierpinski();
				}

			}

		});

		textField2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newSierpinski();
				}

			}

		});
		textField3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newSierpinski();
				}
			}

		});
		textField4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newSierpinski();
				}

			}

		});

		textField5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newSierpinski();
				}

			}

		});
		textField6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newSierpinski();
				}

			}

		});
		textField7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (isTextInvalid()) {
					newBlankCanvas();
					x1 = 0;
					x2 = 0;
					x3 = 0;
					y1 = 0;
					y2 = 0;
					y3 = 0;
					depth = 0;
				} else {

					newSierpinski();
				}

			}

		});

	}

	private void removeTextListeners() {

		ArrayList<JTextField> arr = new ArrayList<>();
		arr.add(textField);
		arr.add(textField2);
		arr.add(textField3);
		arr.add(textField4);
		arr.add(textField5);
		arr.add(textField6);
		arr.add(textField7);

		for (JTextField j : arr) {

			for (int i = 0; i < j.getActionListeners().length; i++) {

				j.removeActionListener(j.getActionListeners()[i]);

			}

		}
	}

	private void removeTextKeyListeners() {

		ArrayList<JTextField> arr = new ArrayList<>();
		arr.add(textField);
		arr.add(textField2);
		arr.add(textField3);
		arr.add(textField4);
		arr.add(textField5);
		arr.add(textField6);
		arr.add(textField7);

		for (JTextField j : arr) {

			for (int i = 0; i < j.getKeyListeners().length; i++) {

				j.removeKeyListener(j.getKeyListeners()[i]);

			}
		}
	}

	private void removeButtonListeners() {

		ArrayList<JButton> arr = new ArrayList<>();
		arr.add(execute);
		arr.add(defaultVer);
		arr.add(pickColors);
		arr.add(randomColors);
		arr.add(reset);

		for (JButton j : arr) {

			for (int i = 0; i < j.getActionListeners().length; i++) {

				j.removeActionListener(j.getActionListeners()[i]);

			}

		}

	}

	/****************************
	 * EVERYTHING AFTER THIS IS ABOUT THE DOT RACE
	 *********************************/

	/**
	 * Creates JFrame window and graphics canvas for animation
	 */
	private void createDotRace() {
		dotFrame = new JFrame("Dot Races");
		dotFrame.setSize(GWINDOW_WIDTH, GWINDOW_HEIGHT);
		dotFrame.setLocation(locationX, locationY * 50);
		if (locationY < 2) {
			locationY++;

		} else {
			if (locationX <= 600) {
				locationY = 0;
				locationX += 100;
			} else {
				locationY = 0;
				locationX = 0;

			}

		}

		dotFrame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				numOfWindows--;
				if (timer != null) {
					timer.stop();
				}
			}

		});

		dotFrame.setResizable(false);

		dotFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		dotCourse = new GCanvas(); // drawing space
		dotCourse.setPreferredSize(new Dimension(GWINDOW_WIDTH, GWINDOW_HEIGHT));
		dotCourse.setLocation(0, 0);
		dotCourse.setBackground(Color.black);

		JPanel dotJP = new JPanel();
		dotJP.setLayout(null);
		dotJP.setBounds(0, 525, 960, 215);
		dotJP.setBackground(Color.white);

		Font fontTxt = new Font("Verdana", Font.BOLD, 50);
		Font fontWarning = new Font("TAHOMA", Font.BOLD | Font.ITALIC, 12);

		dotReset = new JButton("CLEAR");
		dotReset.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 22));
		dotReset.setBackground(Color.white);
		dotReset.setBounds(0, 0, 190, 80);

		dotDefault = new JButton("DEFAULT");
		dotDefault.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 22));
		dotDefault.setBackground(Color.white);
		dotDefault.setBounds(0, 80, 190, 80);

		dotBack = new JButton("RESET RACE");
		dotBack.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 20));
		dotBack.setBackground(Color.white);
		dotBack.setBounds(770, 0, 190, 80);
		dotBack.setEnabled(false);

		dotBackgroundColors = new JButton("BACKGROUND");
		dotBackgroundColors.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 20));
		dotBackgroundColors.setBackground(Color.white);
		dotBackgroundColors.setBounds(770, 80, 190, 80);

		dotStartRace = new JButton("RACE!");
		dotStartRace.setFont(new Font("TAHOMA", Font.BOLD + Font.ITALIC, 40));
		dotStartRace.setBounds(370, 0, 220, 160);

		dotTextField = new JTextField("7");
		dotTextField.setBounds(190, 35, 180, 100);
		dotTextField.setBackground(Color.black);
		dotTextField.setVisible(true);
		dotTextField.setForeground(Color.white);
		dotTextField.setHorizontalAlignment(SwingConstants.CENTER);
		dotTextField.setFont(fontTxt);

		dotTextField2 = new JTextField("10");
		dotTextField2.setBounds(590, 35, 180, 100);
		dotTextField2.setBackground(Color.black);
		dotTextField2.setVisible(true);
		dotTextField2.setForeground(Color.white);
		dotTextField2.setHorizontalAlignment(SwingConstants.CENTER);
		dotTextField2.setFont(fontTxt);

		dotLbl = new JLabel("Number of Dots");
		dotLbl.setBounds(203, 7, 200, 20);
		dotLbl.setForeground(Color.black);
		dotLbl.setFont(new Font("Verdana", Font.BOLD, 17));

		dotLbl2 = new JLabel("Speed");
		dotLbl2.setBounds(650, 7, 200, 20);
		dotLbl2.setForeground(Color.black);
		dotLbl2.setFont(new Font("Verdana", Font.BOLD, 17));

		dotWarning = new JLabel("Cannot enter more than 175");
		dotWarning.setBounds(190, 140, 600, 13);
		dotWarning.setForeground(new Color(220, 20, 60));
		dotWarning.setFont(fontWarning);
		dotWarning.setVisible(false);

		dotWarning2 = new JLabel("Cannot enter more than 150");
		dotWarning2.setBounds(590, 140, 600, 13);
		dotWarning2.setForeground(new Color(220, 20, 60));
		dotWarning2.setFont(fontWarning);
		dotWarning2.setVisible(false);

		dotJP.add(dotWarning);
		dotJP.add(dotWarning2);
		dotJP.add(dotLbl);
		dotJP.add(dotLbl2);
		dotJP.add(dotTextField);
		dotJP.add(dotTextField2);
		dotJP.add(dotStartRace);
		dotJP.add(dotBackgroundColors);
		dotJP.add(dotBack);
		dotJP.add(dotDefault);
		dotJP.add(dotReset);

		dotFrame.add(dotJP);

		dotTextBound();
		drawDots();
		defaultDots();
		textAction();
		dotButtonActions();

		dotFrame.add(dotCourse);

		dotFrame.pack();
		dotFrame.setVisible(true);

	}

	/**************************
	 * HEREEEEEEEEEEEEEEEEEEE
	 ********************************/
	private void dotButtonActions() {

		dotStartRace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (Integer.parseInt(dotTextField.getText()) > 0 && Integer.parseInt(dotTextField.getText()) < 175
							&& Integer.parseInt(dotTextField2.getText()) > 0
							&& Integer.parseInt(dotTextField2.getText()) <= 150) {

						if (amountOfDots != Integer.parseInt(dotTextField.getText())) {
							amountOfDots = Integer.parseInt(dotTextField.getText());
							speed = Integer.parseInt(dotTextField2.getText());
							newDotBlankCanvas();
							drawDots();
							race();
						} else {
							amountOfDots = Integer.parseInt(dotTextField.getText());
							speed = Integer.parseInt(dotTextField2.getText());
							race();
						}

					} else {

						dotTextField.setText("7");
						newDotBlankCanvas();
						defaultDots();
						dotWarning.setVisible(false);
						race();

					}
				} catch (Exception nfe) {
					newDotBlankCanvas();
				}

			}

		});

		dotReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dotBack.setEnabled(false);

				newDotBlankCanvas();
				dotResetEverything();
				if (timer != null) {
					timer.stop();

				}

				for (int i = 0; i < dotCourse.getMouseListeners().length; i++) {
					dotCourse.removeMouseListener(dotCourse.getMouseListeners()[i]);
				}

				dotCourse.removeMouseListener(dotCourse.getMouseListeners()[0]);
				dotStartRace.setEnabled(false);
			}

		});

		dotDefault.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				defaultDots();
				if (timer != null) {
					timer.stop();
				}

				dotBack.setEnabled(false);
				dotStartRace.setEnabled(true);
				dotTextField.setEnabled(true);
				dotTextField2.setEnabled(true);
			}

		});

		dotBackgroundColors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dotCourse.setBackground(c);
				if (timer != null) {
					timer.stop();
				}
				c = JColorChooser.showDialog(dotFrame, "Please choose your color", Color.white);

				if (timer != null && leadingXCoordinate() + dotSize < 960 && leadingXCoordinate() > 6) {
					timer.start();
				}

				dotCourse.setBackground(c);
				drawNotRandomDots();

			}

		});

		dotBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timer != null) {
					timer.stop();
				}

				dotBack.setEnabled(false);

				newDotBlankCanvas();

				if (c != null) {
					dotCourse.setBackground(c);
				}

				for (GOval g : dotArr) {

					g.setLocation(5, g.getY());
				}

				drawNotRandomDots();

				dotStartRace.setEnabled(true);
				dotTextField.setEnabled(true);
				dotTextField2.setEnabled(true);

			}

		});

	}

	private void textAction() {

		dotTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isDotTextInvalid()) {
					newDotBlankCanvas();
					if (c != null) {
						dotCourse.setBackground(c);
					}
					if (timer != null) {
						timer.stop();
					}
					dotStartRace.setEnabled(false);
				} else {

					if (timer != null) {
						timer.stop();
					}

					farthestX = leadingXCoordinate();

					try {
						if (Integer.parseInt(dotTextField.getText()) > 0
								&& Integer.parseInt(dotTextField.getText()) < 175) {
							if (amountOfDots != Integer.parseInt(dotTextField.getText())) {
								amountOfDots = Integer.parseInt(dotTextField.getText());
								newDotBlankCanvas();

								if (c != null) {
									dotCourse.setBackground(c);
								}

								if (isDotSpeedInvalid()) {
									dotStartRace.setEnabled(false);
								} else {
									dotStartRace.setEnabled(true);
								}

								drawDots();

							} else {

								if (farthestX > 5) {
									amountOfDots = Integer.parseInt(dotTextField.getText());
									newDotBlankCanvas();

									if (c != null) {
										dotCourse.setBackground(c);
									}

									drawDots();
									dotStartRace.setEnabled(true);
								} else {

									if (c != null) {
										dotCourse.setBackground(c);
									}
									amountOfDots = Integer.parseInt(dotTextField.getText());
									if (isDotSpeedInvalid()) {
										dotStartRace.setEnabled(false);
									} else {
										dotStartRace.setEnabled(true);
									}

									drawNotRandomDots();
								}

							}
						} else {
							defaultDots();
							if (c != null) {
								dotCourse.setBackground(c);
							}

						}

					} catch (Exception nfe) {

						defaultDots();
						if (c != null) {
							dotCourse.setBackground(c);
						}
					}

				}
			}

		});

		dotTextField2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isDotSpeedInvalid()) {
//					newDotBlankCanvas();

					if (c != null) {
						dotCourse.setBackground(c);
					}

					if (timer != null) {
						timer.stop();
					}
					dotStartRace.setEnabled(false);
				}

				else {
					if (timer != null) {
						timer.stop();
					}

					farthestX = leadingXCoordinate();

					try {

						if (Integer.parseInt(dotTextField2.getText()) > 0
								&& Integer.parseInt(dotTextField2.getText()) < 151) {

							if (speed != Integer.parseInt(dotTextField2.getText())) {
								speed = Integer.parseInt(dotTextField2.getText());
								newDotBlankCanvas();

								if (c != null) {
									dotCourse.setBackground(c);
								}

								for (GOval g : dotArr) {

									g.setLocation(5, g.getY());
								}

								drawNotRandomDots();
								dotStartRace.setEnabled(true);
							} else {
								if (farthestX > 5) {
									speed = Integer.parseInt(dotTextField2.getText());
									newDotBlankCanvas();

									if (c != null) {
										dotCourse.setBackground(c);
									}

									for (GOval g : dotArr) {

										g.setLocation(5, g.getY());
									}

									drawNotRandomDots();

									dotStartRace.setEnabled(true);
								} else {

									if (c != null) {
										dotCourse.setBackground(c);
									}

									speed = Integer.parseInt(dotTextField2.getText());
									dotStartRace.setEnabled(true);
								}
							}

						}

						else {
							defaultDots();
							if (c != null) {
								dotCourse.setBackground(c);
							}

						}

					} catch (Exception nfe) {
						defaultDots();

						if (c != null) {
							dotCourse.setBackground(c);
						}
					}

				}

			}

		});

	}

	private void drawDots() {

		dotArr = new ArrayList<>();

		dotColors = new ArrayList<>();

		space = (int) (GWINDOW_HEIGHT - 160) / ((amountOfDots * 2) + (amountOfDots + 1));

		int y = 0;

		dotSize = space * 2;

		y += space;
		for (int i = 0; i < amountOfDots; i++) {

			GOval shape = new GOval(5, y, dotSize, dotSize);

			shape.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent me) {

					if (timer != null) {
						timer.stop();
					}
					dotColor = JColorChooser.showDialog(dotFrame, "Please choose your Dot color", Color.white);

					if (timer != null && leadingXCoordinate() + dotSize < 960 && leadingXCoordinate() > 6) {
						timer.start();
					}

					shape.setFillColor(dotColor);
					dotColors.remove(dotArr.indexOf(shape));
					dotColors.add(dotArr.indexOf(shape), dotColor);

				}
			});

			dotRestColor = new Color(randomNum(), randomNum(), randomNum());
			shape.setFillColor(dotRestColor);
			dotColors.add(dotRestColor);
			shape.setFilled(true);
			dotCourse.add(shape);
			y += space * 3;
			dotArr.add(shape);

		}

	}

	/************* POSSIBLE MALFUNCTIONS *******************/

	private void drawNotRandomDots() {

		for (int i = 0; i < amountOfDots; i++) {

			GOval s = dotArr.get(i);

			s.setFillColor(dotColors.get(i));
			s.setFilled(true);
			dotCourse.add(s);

		}

	}

	private void race() {

		timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dotWarning.setVisible(false);
				dotWarning2.setVisible(false);
				dotBack.setEnabled(true);

				dotStartRace.setEnabled(false);
				dotTextField.setEnabled(false);
				dotTextField2.setEnabled(false);

				int space = (int) (GWINDOW_HEIGHT - 160) / ((amountOfDots * 2) + (amountOfDots + 1));

				int dotSize = space * 2;

				int moveX = 0;

				for (int i = 0; i < dotArr.size(); i++) {
					moveX = roll(speed);

					if ((dotArr.get(i).getX() + dotSize + moveX) < GWINDOW_WIDTH) {
						dotArr.get(i).setLocation(dotArr.get(i).getX() + moveX, dotArr.get(i).getY());
						farthestX = leadingXCoordinate();
					} else {
						dotArr.get(i).setLocation(GWINDOW_WIDTH - dotSize, dotArr.get(i).getY());
						((Timer) e.getSource()).stop();
						dotTextField.setEnabled(true);
						dotTextField2.setEnabled(true);
						dotBack.setEnabled(true);

					}

				}

			}

		});
		timer.start();

	}

	public int leadingXCoordinate() {

		int farthestRightX = (int) dotArr.get(0).getX();

		for (int i = 0; i < dotArr.size(); i++) {
			if (dotArr.get(i).getX() > farthestRightX) {
				farthestRightX = (int) dotArr.get(i).getX();
			}

		}
		return farthestRightX;

	}

	private int roll(int sides) {

		double x;
		x = Math.random();
		return (int) Math.floor(x * sides) + 1;

	}

	/**
	 * 
	 * @see Lab 7, St. Bonaventure University: "Password Utils.java"
	 * @return
	 */
	private boolean isDotTextInvalid() {

		ArrayList<JTextField> arr = new ArrayList<>();
		arr.add(dotTextField);

		for (JTextField txt : arr) {

			if (txt.getText().trim().length() == 0) {
				return true;
			}
			for (int letter = 0; letter < txt.getText().length(); letter++) {
				if ("abcdefghijklmnopqrstuvwxyz".indexOf(txt.getText().toLowerCase().charAt(letter)) >= 0) {
					return true;
				} else if ("!@#$`~%^&*()\\/_+|}{',;\":?><-=].[]".indexOf(txt.getText().charAt(letter)) >= 0) {
					return true;
				}
			}

		}

		return false;

	}

	/**
	 * 
	 * @see Lab 7, St. Bonaventure University: "Password Utils.java"
	 * @return
	 */
	private boolean isDotSpeedInvalid() {

		ArrayList<JTextField> arr = new ArrayList<>();
		arr.add(dotTextField2);

		for (JTextField txt : arr) {

//			if (txt.getText().trim().length() == 0) {
//				return true;
//			}
			for (int letter = 0; letter < txt.getText().length(); letter++) {
				if ("abcdefghijklmnopqrstuvwxyz".indexOf(txt.getText().toLowerCase().charAt(letter)) >= 0) {
					return true;
				} else if ("!@#$`~%^&*()\\/_+|}{',;\":?><-=].[]".indexOf(txt.getText().charAt(letter)) >= 0) {
					return true;
				}
			}

		}

		return false;

	}

	private void newDotBlankCanvas() {

		dotFrame.remove(dotCourse);

		dotCourse = new GCanvas(); // drawing space
		dotCourse.setPreferredSize(new Dimension(GWINDOW_WIDTH, GWINDOW_HEIGHT));
		dotCourse.setLocation(0, 0);
		dotCourse.setBackground(Color.black);
		dotFrame.add(dotCourse);
		dotFrame.pack();

		dotCourse.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent me) {

				dotCourse.setBackground(c);
				if (timer != null) {
					timer.stop();
				}
				c = JColorChooser.showDialog(dotFrame, "Please choose your color", Color.white);

				if (timer != null && leadingXCoordinate() + dotSize < 960 && leadingXCoordinate() > 6) {
					timer.start();
				}

				dotCourse.setBackground(c);
				drawNotRandomDots();
			}

		});

	}

	private void dotResetEverything() {

		dotTextField.setText("");
		dotTextField2.setText("");
		if (timer != null) {
			timer.stop();
		}
		dotStartRace.setEnabled(true);
		dotTextField.setEnabled(true);
		dotTextField2.setEnabled(true);
		amountOfDots = 0;
		c = Color.black;
	}

	private void defaultDots() {
		newDotBlankCanvas();
		ArrayList<Color> defColors;
		dotTextField.setText("7");
		dotTextField2.setText("10");
		amountOfDots = 7;
		speed = 10;
		dotStartRace.setEnabled(true);
		Color def1 = new Color(231, 0, 0);
		Color def6 = new Color(255, 140, 0);
		Color def2 = new Color(255, 239, 0);
		Color def3 = new Color(0, 129, 31);
		Color def4 = new Color(0, 68, 255);
		Color def5 = new Color(118, 0, 137);

		Color def7 = new Color(75, 0, 130);

		defColors = new ArrayList<>();

		defColors.add(def1);
		defColors.add(def6);
		defColors.add(def2);
		defColors.add(def3);
		defColors.add(def4);
		defColors.add(def7);
		defColors.add(def5);

		drawDots();

		dotColors.clear();
		dotColors.addAll(defColors);

		c = Color.black;

		for (int i = 0; i < dotArr.size(); i++) {

			dotArr.get(i).setFillColor(defColors.get(i));

		}

	}

	private void dotTextBound() {

		dotTextField.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (dotTextField.getText().length() > 3) {
					dotTextField.setText(dotTextField.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(dotTextField.getText()) >= 175) {
						dotWarning.setVisible(true);
						dotWarning2.setVisible(false);
					} else {
						dotWarning.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});

		dotTextField2.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke) {

				if (isDotSpeedInvalid()) {

					dotStartRace.setEnabled(false);
				} else {
					dotStartRace.setEnabled(true);
				}

				if (dotTextField2.getText().trim().length() == 0) {
					dotStartRace.setEnabled(false);
				}

				if (dotTextField2.getText().length() > 3) {
					dotTextField2.setText(dotTextField2.getText().substring(0, 3));
				}

				try {
					if (Integer.parseInt(dotTextField2.getText()) > 150) {
						dotWarning.setVisible(false);
						dotWarning2.setVisible(true);
					} else {
						dotWarning2.setVisible(false);
					}
				} catch (NumberFormatException e) {

				}

			}

		});

	}

}
