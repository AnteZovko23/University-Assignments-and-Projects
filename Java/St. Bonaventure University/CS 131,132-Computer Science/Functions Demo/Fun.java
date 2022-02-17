/**
 * A collection of methods that can be expanded as students learn more
 * 
 * @author Ante Zovko and David Levine
 * @version 27 March 2018
 *
 */
public class Fun {

	/**
	 * Compute the sum of the factors of a given number.
	 * 
	 * @param n the value whose factors are being summed
	 * @return the sum of the factors of <code>n</code>
	 */
	public static int sumOfFactors(int n) {
		int total = 0;
		for (int factor = 1; factor <= n; factor++) {
			if (n % factor == 0) {
				total += factor;

			}
		}
		return total;
	}

	/**
	 * Determine whether or not an average is sufficient to pass a course. We assume
	 * that 60% is necessary to pass the course.
	 * 
	 * @param average the semester average
	 * @return <code>true</code> iff the student passes the course
	 */
	public static boolean passFail(double average) {
		return (average >= 59.5); // Since 59.5 and up "round" to a passing score
	}

	/**
	 * Perform a simple encryption on the given word
	 * 
	 * @param word the word to encrypt
	 * @return the encrypted version of the word
	 * 
	 */
	public static String encrypt(String word) {
		String code = "";
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			char xc = ' ';
			if (Character.isLetter(c)) {
				if (c == 'z') {
					xc = 'a';
				} else if (c == 'Z') {
					xc = 'A';
				} else {
					xc = (char) (c + 1);
				}
			} else {
				xc = c;
			}
			code += xc;
		}
		return code;
	}

	/**
	 * Compute the sum of two numbers
	 * 
	 * @param a the first number to sum
	 * @param b the second number to sum
	 * @return the sum of the parameters
	 *
	 */
	public static int sum(int a, int b) {
		int sum;
		sum = a + b;
		return sum;
	}

	/**
	 * Calculates the sum of all numbers up to given number
	 * 
	 * @param n the number where the adding stops
	 * @return the sum of the numbers
	 */
	public static int sumOfConsecutiveNumbers(int n) {
		int sum = 0;
		for (int i = 0; i <= n; i++) {
			sum += i;
		}
		return sum;

	}

	/**
	 * Determine the largest number out of Five
	 *
	 * @param a the first number
	 * @param b the second number
	 * @param c the third number
	 * @param d the forth number
	 * @param e the fifth number
	 * @return the largest number
	 */
	public static int largestOfFiveNumbers(int a, int b, int c, int d, int e) {
		int[] arrOfNums = { a, b, c, d, e };

		int largest = arrOfNums[0];

		for (int i = 1; i < arrOfNums.length; i++) {

			if (arrOfNums[i] > largest) {

				largest = arrOfNums[i];

			}

		}

		return largest;

	}

	/**
	 * Counts the number of times the letter 'e' appears in a String
	 * 
	 * @param str in which it counts the number of 'e' letters
	 * @return the number of times the letter 'e' appears
	 */
	public static int eLetterCounter(String str) {
		int count;
		count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'e') {
				count++;
			}
		}
		return count;
	}

	/**
	 * Calculates the sum of the first k odd integers
	 * 
	 * @param k the number of odd integers
	 * @return the sum of the odd integers
	 */
	public static int oddIntegerCounter(int k) {
		int sum;
		sum = (int) (Math.pow(k, 2));
		return sum;
	}

}
