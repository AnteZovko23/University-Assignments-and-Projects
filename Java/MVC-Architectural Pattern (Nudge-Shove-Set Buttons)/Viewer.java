
/**
 * Updates the value text field of the GUI
 * 
 * @author Ante Zovko
 * @version 3/27/2019
 *
 */
public class Viewer implements ModelObserver {

	private Window w;

	public Viewer(Window window) {

		w = window;

	}
	/**
	 * Updates the text field
	 * 
	 * @param Model m the model
	 * 
	 */
	@Override
	public void update(Model m) {

		w.getText().setText(String.valueOf("\nValue: " + m.getData()));

	}

}
