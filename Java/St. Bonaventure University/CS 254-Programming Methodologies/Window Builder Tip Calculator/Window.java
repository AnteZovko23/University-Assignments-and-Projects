import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import javafx.scene.paint.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

/**
 * GUI with 3 buttons
 * 
 * @author Ante Zovko
 * @version 3/27/2019
 *
 */
public class Window {

	protected Shell shell;
	protected Text displayValue;
	private Model model;
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			
			Window window = new Window();
				
			window.open();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		/************ The Viewer ****************/
		
		model = new Model();
		
		Viewer viewer = new Viewer(this);
		
		model.addObserver(viewer);
		
		
		
		/************ The Window ****************/
		
		shell = new Shell();
		shell.setLocation(new Point(800, 300));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		shell.setSize(450, 352);
		shell.setText("GUI by Ante Zovko");
		shell.setLayout(null);
		
		

		/************ Nudge Button ****************/
		
		Button btnNudge = new Button(shell, SWT.CENTER);

		btnNudge.setBackground(SWTResourceManager.getColor(34, 139, 34));
		btnNudge.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		btnNudge.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNudge.setBounds(42, 27, 131, 95);
		btnNudge.setText("Nudge");

		
		/************ Shove Button ****************/
		
		Button shoveButton = new Button(shell, SWT.NONE);

		shoveButton.setBackground(SWTResourceManager.getColor(255, 0, 0));
		shoveButton.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		shoveButton.setBounds(274, 27, 131, 95);
		shoveButton.setText("Shove");

		
		/************ Display Value  ****************/
		
		displayValue = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER | SWT.MULTI);
		displayValue.setEditable(false);
		displayValue.setText("\nModel Value Display");

		displayValue.setBackground(SWTResourceManager.getColor(255, 0, 255));
		displayValue.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		displayValue.setBounds(42, 188, 131, 95);

		
		/************ Set Button  ****************/
		
		Button setValue = new Button(shell, SWT.NONE);
		setValue.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.NORMAL));
		setValue.setBackground(SWTResourceManager.getColor(255, 215, 0));

		setValue.setBounds(274, 188, 131, 95);
		setValue.setText("Set");
		
		
		
		/************ Listeners  ****************/
		
		
		btnNudge.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				model.bumpData(1);
				

			}
		});
		
		
	

		shoveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				model.bumpData(8);

			}
		});

		setValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				try {
					String s;

					s = JOptionPane.showInputDialog("Please Enter A number");

					model.setData(Integer.parseInt(s));



				} catch (Exception nfe) {

					displayValue.setText("\nPlease Enter a Valid Number!");

				}

			}
		});

	}
	
	/**
	 * Returns the value text field
	 * 
	 * @return the value text field
	 */
	public Text getText() {
		return displayValue;
	}

	
}
