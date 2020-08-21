
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A SWT Popup menu Demo
 * 
 * @author Ante Zovko
 * @version 4/10/2019
 *
 */
public class StudentPopupFill {

	private Shell shlWindowbuilderpopupdemo;
	private Display display;
	private Text first;
	private Text second;
	private Text third;
	private Text fourth;
	private ArrayList<MenuItem> menuItems;
	private MenuItem m;
	private Menu popupMenu;
	private Text[] slots;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StudentPopupFill window = new StudentPopupFill();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shlWindowbuilderpopupdemo.open();
		shlWindowbuilderpopupdemo.layout();
		while (!shlWindowbuilderpopupdemo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWindowbuilderpopupdemo = new Shell();
		shlWindowbuilderpopupdemo.setSize(324, 371);
		shlWindowbuilderpopupdemo.setText("PopupDemo by Ante Zovko");
		shlWindowbuilderpopupdemo.forceFocus();

		first = new Text(shlWindowbuilderpopupdemo, SWT.BORDER);
		first.setBounds(44, 41, 163, 26);

		second = new Text(shlWindowbuilderpopupdemo, SWT.BORDER);
		second.setBounds(44, 93, 163, 26);

		third = new Text(shlWindowbuilderpopupdemo, SWT.BORDER);
		third.setBounds(44, 145, 163, 26);

		fourth = new Text(shlWindowbuilderpopupdemo, SWT.BORDER);
		fourth.setBounds(44, 197, 163, 26);

		slots = new Text[4];
		slots[0] = first;
		slots[1] = second;
		slots[2] = third;
		slots[3] = fourth;

		for (int i = 0; i < slots.length; i++) {

			slots[i].setEditable(false);
			options(slots[i]);

		}

		Menu menu = new Menu(shlWindowbuilderpopupdemo, SWT.BAR);
		shlWindowbuilderpopupdemo.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem clearMenuItem = new MenuItem(menu_1, SWT.NONE);
		clearMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				first.setText("");
				second.setText("");
				third.setText("");
				fourth.setText("");
				for (int i = 0; i < slots.length; i++) {

					options(slots[i]);

				}
			}
		});
		clearMenuItem.setText("Clear");

		MenuItem quitMenuItem = new MenuItem(menu_1, SWT.NONE);
		quitMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		quitMenuItem.setText("Quit");

	}

	/**
	 * Dynamically creates and updates the popup menu
	 * 
	 * @param t Text
	 * 
	 */
	private void options(Text t) {

		java.util.List<String> myList = new ArrayList<String>();

		myList.add("Clubs");
		myList.add("Diamonds");
		myList.add("Hearts");
		myList.add("Spades");

		Object[] s = myList.toArray();

		for (Object o : s) {
			if (first.getText().equals(o)) {
				myList.remove(o);
			}
			if (second.getText().equals(o)) {
				myList.remove(o);
			}
			if (third.getText().equals(o)) {
				myList.remove(o);
			}
			if (fourth.getText().equals(o)) {
				myList.remove(o);
			}
		}

		popupMenu = new Menu(shlWindowbuilderpopupdemo);

		menuItems = new ArrayList<>();

		for (int i = 0; i < myList.size(); i++) {

			m = new MenuItem(popupMenu, SWT.NONE);
			m.setText(myList.get(i));
			menuItems.add(m);

		}

		for (int i = 0; i < myList.size(); i++) {

			MenuItem temp = menuItems.get(i);

			temp.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event arg0) {

			
					t.setText(temp.getText());
					for (int i = 0; i < slots.length; i++) {

						slots[i].setEditable(false);
						options(slots[i]);

					}

				}
			});

		}

		t.setMenu(popupMenu);

	}

}
