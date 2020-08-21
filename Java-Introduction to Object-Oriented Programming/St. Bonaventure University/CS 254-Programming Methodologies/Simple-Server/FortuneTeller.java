import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads fortunes from a text file
 * 
 * @author Ante Zovko
 * @version 3/14/2019
 *
 */
public class FortuneTeller {

	private File file;
	private ArrayList<String> arr;
	private Scanner cs;
	private ArrayList<String> fortunes;
	private RandomDice ranDice;

	public static void main(String[] args) {

		FortuneTeller fortune = new FortuneTeller("Fortunes.txt");

		if (args.length != 0) {
			System.out.println(fortune.getFortune(args[0]));
		} else {
			System.out.println(fortune.getFortune());
		}
	}

	/**
	 * Default Constructor
	 * 
	 * @param name the file name
	 */
	public FortuneTeller(String name) {

		try {
			file = new File(name);
			arr = new ArrayList<>();
			cs = new Scanner(file);
			fortunes = new ArrayList<>();

			while (cs.hasNext()) {
				arr.add(cs.nextLine().toLowerCase());
			}

			ranDice = new RandomDice(arr.size());

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Returns a random fortune
	 * 
	 * @return a random fortune
	 */
	public String getFortune() {

		return arr.get(ranDice.roll() - 1);

	}

	/**
	 * Returns a random fortune that contains the keyword
	 * 
	 * @param key the keyword
	 * @return the fortune
	 */
	public String getFortune(String key) {

		key = key.toLowerCase();

		for (int i = 0; i < arr.size(); i++) {

			if (arr.get(i).contains(key)) {
				fortunes.add(arr.get(i));
			}

		}

		RandomDice ranDice2 = new RandomDice(fortunes.size());

		if (fortunes.size() == 0) {
			return "Nothing good comes when one thinks of " + key;
		} else {
			return fortunes.get(ranDice2.roll() - 1);
		}

	}

}
