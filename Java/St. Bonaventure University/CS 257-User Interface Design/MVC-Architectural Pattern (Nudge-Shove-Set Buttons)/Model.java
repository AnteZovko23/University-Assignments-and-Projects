import java.util.ArrayList;

/**
 * Creates the Model for the MVC
 * 
 * 
 * @author Ante Zovko
 * @version 2/21/2019
 *
 */
public class Model {

	private int data;
	private ArrayList<ModelObserver> observers = new ArrayList<>();

	/**
	 * Default Constructor
	 * 
	 */
	public Model() {
		this.data = 0;

	}

	/**
	 * Constructor
	 * 
	 * @param data Data Chosen by a User
	 */
	public Model(int data) {
		this.data = data;
	}

	/**
	 * Bumps the Data by a given amount
	 * 
	 * @param value the amount the data is bumped by
	 */
	public void bumpData(int value) {
		this.data += value;
		notifyObservers();
	}

	/**
	 * Adds a "follower" of this Model
	 * 
	 * @param obs the "follower" that was added
	 */
	public void addObserver(ModelObserver obs) {

		observers.add(obs);

	}

	/**
	 * Notifies all "followers" of this Model that a change occurred
	 * 
	 */
	public void notifyObservers() {

		for (ModelObserver obs : observers) {
			obs.update(this);
		}

	}

	/**
	 * 
	 * Sets the Data to a given amount
	 * 
	 * @param newData a given amount
	 */
	public void setData(int newData) {

		this.data = newData;
		notifyObservers();

	}

	/**
	 * Return the data
	 * 
	 * @return the data
	 */
	public int getData() {
		return this.data;
	}
}