
/**
 * Model that keeps track of data exponentially
 * 
 * @author Ante Zovko
 * @version 3/14/2019
 *
 */
public class ExponentialModel extends ModelClass {

	private double data;

	/**
	 * Default Constructor
	 * 
	 */
	public ExponentialModel() {

		this.data = 0;

	}

	/**
	 * Sets the slider based on the textfield's data
	 * 
	 * @param data the data
	 * 
	 * @see https://www.experts-exchange.com/questions/21435856/Logarithm-Base-2-in-Java.html
	 */
	public void setData(double data) {
		if (data != this.data) {
			this.data = data;
			slider.setValue((int) (Math.log(this.data) / Math.log(2)));
		}
	}

	/**
	 * Sets the text field based on the slider's data
	 * 
	 * @param data the data
	 */
	public void setDataSlide(double data) {

		if (data != this.data) {
			this.data = Math.pow(2, data);
			txt1.setText(String.valueOf(this.data));
		}

	}

}
