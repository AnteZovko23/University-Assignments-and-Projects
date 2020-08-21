
/**
 * Model that keeps track of data quadratically
 * 
 * @author Ante Zovko
 * @version 3/14/2019
 *
 */
public class QuadraticModel extends ModelClass {
	private double data;

	/**
	 * Default Constructor
	 */
	public QuadraticModel() {

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
			slider.setValue((int) Math.sqrt(this.data));
		}
	}

	/**
	 * Sets the text field based on the slider's data
	 * 
	 * @param data the data
	 */
	public void setDataSlide(double data) {

		if (data != this.data) {
			this.data = Math.pow(data, 2);
			txt1.setText(String.valueOf(this.data));
		}

	}

}
