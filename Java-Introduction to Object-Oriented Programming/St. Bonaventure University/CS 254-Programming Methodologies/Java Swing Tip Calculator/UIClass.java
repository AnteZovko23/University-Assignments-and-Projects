
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Creates a GUI for a Tip Calculator
 * 
 * @author Ante Zovko
 * @version 3/2/2019
 *
 */
public class UIClass {

	private JFrame frame;

	private JLabel head;
	private JLabel jl;
	private JSeparator separator;

	private JLabel jAmount;
	private JTextField txt1;

	private JLabel jPercentage;
	private JTextField txt2;

	private JLabel jRounding;
	private JTextField txt3;

	private JButton button;

	private JLabel jTip;
	private JTextField txtOutput1;

	private JLabel jEff;
	private JTextField txtOutput2;

	private Font font;

	private TipModel tipModel;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TipModel model = new TipModel();

					UIClass gui = new UIClass(model);

					TipViewer tip = new TipViewer(gui);
					model.addObserver(tip);

					EffPerViewer effPer = new EffPerViewer(gui);
					model.addObserver(effPer);

					gui.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	/**
	 * Constructor for the GUI
	 * 
	 * @see https://stackoverflow.com/questions/4419667/detect-enter-press-in-jtextfield
	 * @param m the Model
	 */
	public UIClass(TipModel m) {
		initialize();

		tipModel = m;

		// Button Listener

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {

					// All Inputs Together

					tipModel.setTotalBill(Double.parseDouble(txt1.getText()));
					tipModel.setTipPer(Double.parseDouble(txt2.getText()));
					tipModel.setTipQuantum(Double.parseDouble(txt3.getText()));

					

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Invalid Input");
				}

			}
		});

		// TextField Listeners that wait for the ENTER key

		txt1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						// All Inputs Together

						tipModel.setTotalBill(Double.parseDouble(txt1.getText()));
						tipModel.setTipPer(Double.parseDouble(txt2.getText()));
						tipModel.setTipQuantum(Double.parseDouble(txt3.getText()));

						

					} catch (NumberFormatException nfe) {
						txt1.setText("Invalid Input");
					}
				}
			}
		});

		txt2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						// All Inputs Together

						tipModel.setTotalBill(Double.parseDouble(txt1.getText()));
						tipModel.setTipPer(Double.parseDouble(txt2.getText()));
						tipModel.setTipQuantum(Double.parseDouble(txt3.getText()));

						
					} catch (NumberFormatException nfe) {
						txt2.setText("Invalid Input");
					}
				}
			}
		});

		txt3.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						// All Inputs Together

						tipModel.setTotalBill(Double.parseDouble(txt1.getText()));
						tipModel.setTipPer(Double.parseDouble(txt2.getText()));
						tipModel.setTipQuantum(Double.parseDouble(txt3.getText()));

						
					} catch (NumberFormatException nfe) {
						txt3.setText("Invalid Input");
					}
				}
			}
		});

	}

	/**
	 * GUI Components
	 * 
	 */
	private void initialize() {

		font = new Font("Georgia", Font.BOLD, 12);

		/************ INPUT ****************/

		// Frame
		frame = new JFrame("Tip Calcualtor");
		frame.setBounds(700, 200, 450, 450);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.cyan);
		frame.getContentPane().setLayout(null);

		// Head
		head = new JLabel("THE TIPPING CALCULATOR");
		head.setFont(new Font("Monotype Corsiva", Font.ITALIC, 30));
		head.setBounds(45, 20, 500, 50);

		// Input Amount
		jAmount = new JLabel("Enter Amount:");
		jAmount.setFont(font);
		jAmount.setBounds(25, 100, 200, 20);

		txt1 = new JTextField();
		txt1.setBounds(40, 130, 70, 70);
		txt1.setFont(new Font("Verdana", Font.BOLD, 25));
		txt1.setHorizontalAlignment(SwingConstants.CENTER);

		// Input percentage

		jPercentage = new JLabel("Enter %:");
		jPercentage.setFont(font);
		jPercentage.setBounds(175, 100, 200, 20);

		txt2 = new JTextField();
		txt2.setBounds(170, 130, 70, 70);
		txt2.setFont(new Font("Verdana", Font.BOLD, 25));
		txt2.setHorizontalAlignment(SwingConstants.CENTER);

		// Rounding

		jRounding = new JLabel("Round to the Nearest:");
		jRounding.setFont(font);
		jRounding.setBounds(280, 100, 200, 20);

		txt3 = new JTextField();
		txt3.setBounds(310, 130, 70, 70);
		txt3.setFont(new Font("Verdana", Font.BOLD, 25));
		txt3.setHorizontalAlignment(SwingConstants.CENTER);

		// Button

		button = new JButton();
		button.setBounds(138, 250, 140, 30);
		button.setBackground(Color.pink);
		button.setLayout(new BorderLayout());

		jl = new JLabel("Calculate", JLabel.CENTER);
		jl.setFont(new Font("Georgia", Font.BOLD, 18));

		button.add(jl);

		// Separator

		separator = new JSeparator();
		separator.setBounds(0, 281, 450, 450);

		/************* OUTPUT ****************/

		// Tip

		jTip = new JLabel("Tip Amount:");
		jTip.setFont(font);
		jTip.setBounds(100, 290, 200, 20);

		txtOutput1 = new JTextField();
		txtOutput1.setEditable(false);
		txtOutput1.setBounds(105, 320, 70, 70);
		txtOutput1.setFont(new Font("Verdana", Font.BOLD, 25));
		txtOutput1.setHorizontalAlignment(SwingConstants.CENTER);
		

		// Eff %

		jEff = new JLabel("Effective %:");
		jEff.setFont(font);
		jEff.setBounds(243, 290, 200, 20);

		txtOutput2 = new JTextField();
		txtOutput2.setEditable(false);
		txtOutput2.setBounds(245, 320, 70, 70);
		txtOutput2.setFont(new Font("Verdana", Font.BOLD, 25));
		txtOutput2.setHorizontalAlignment(SwingConstants.CENTER);

		// Adding Components

		frame.getContentPane().add(jAmount);
		frame.getContentPane().add(txt1);
		frame.getContentPane().add(head);
		frame.getContentPane().add(jPercentage);
		frame.getContentPane().add(txt2);
		frame.getContentPane().add(jRounding);
		frame.getContentPane().add(txt3);
		frame.getContentPane().add(button);
		frame.getContentPane().add(separator);
		frame.getContentPane().add(txtOutput1);
		frame.getContentPane().add(jTip);
		frame.getContentPane().add(jEff);
		frame.getContentPane().add(txtOutput2);

	}

	/**
	 * Gets the output TextField
	 * 
	 * @return the output TextField
	 */
	public JTextField getTxtOutput1() {

		return txtOutput1;
	}

	/**
	 * Gets the output TextField
	 * 
	 * @return the output TextField
	 * @return
	 */
	public JTextField getTxtOutput2() {

		return txtOutput2;
	}

}
