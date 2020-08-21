import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * Model that tracks data
 * 
 * @author Ante Zovko
 * @version 3/14/2019
 *
 */
public class ModelClass {
	private double data;
	protected JTextField txt1;
	protected JSlider slider;

	/**
	 * Default Constructor
	 */
	public ModelClass() {

		this.data = 0;

	}

	/**
	 * Sets the slider based on the textfield's data
	 * 
	 * @param data the data
	 */
	public void setData(double data) {
		if (data != this.data) {
			this.data = data;
			slider.setValue((int) this.data);
		}
	}

	/**
	 * Sets the text field based on the slider's data
	 * 
	 * @param data the data
	 */
	public void setDataSlide(double data) {
		if (data != this.data) {
			this.data = data;
			txt1.setText(String.valueOf(this.data));
		}
	}

	/**
	 * Initializes the gui components
	 * 
	 * @param txt1   the text field
	 * @param slider the slider
	 */
	public void setComponents(JTextField txt1, JSlider slider) {
		this.txt1 = txt1;

		this.slider = slider;

	}

}
