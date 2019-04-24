/**
 * A program to carry on conversations with a human user. This is the initial
 * version that:
 * <ul>
 * <li>Uses indexOf to find strings</li>
 * <li>Handles responding to simple words and phrases</li>
 * </ul>
 * This version uses a nested if to handle default responses.
 * 
 * @author Laurie White and Ante Zovko
 * @version March 13th, 2018
 */
public class Magpie2 {
	/**
	 * Get a default greeting
	 * 
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		String response = "";
		statement = statement.trim();
		if (statement.length() == 0) {

			response = "Say something, please";

		} else if (findKeyword(statement, "mother") >= 0 || findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0 || findKeyword(statement, "brother") >= 0)

		{
			response = "Tell me more about your family.";
		} else if (findKeyword(statement, "dog") >= 0 || (findKeyword(statement, "cat") >= 0))

		{
			response = "Tell me more about your pets.";
		}

		else if (findKeyword(statement, "Steven") >= 0 || (findKeyword(statement, "Andrianoff") >= 0))

		{

			response = "He works at St. Bonaventure University";
		}

		else if (findKeyword(statement, "no") >= 0) {
			response = "Why so negative?";
		}

		else if (findKeyword(statement, "food") >= 0) {
			response = "I prefer chocolate";
		}

		else if (findKeyword(statement, "sport") >= 0) {
			response = "I like basketball.";
		}

		else if (findKeyword(statement, "book") >= 0)

		{
			response = "I have read Crime and Punishment";
		}

		// Responses with their own special methods
		else if (findKeyword(statement, "I want to") >= 0)

		{
			response = transformIWantToStatement(statement);
		} else if (findKeyword(statement, "I want", 0) >= 0) {
			response = transformIWantStatement(statement);
		}

		else {

			int psn = findKeyword(statement, "I", 0);

			if (psn >= 0 && findKeyword(statement, "you", psn) >= 0) {
				response = transformIYouStatement(statement);
			} else {
				response = getRandomResponse();
			}
		}
		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into "What
	 * would it mean to <something>?"
	 * 
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "I want <something>." and transform it into Would you
	 * really be happy if you had <something>?"
	 * 
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */

	private String transformIWantStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 7).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}

	/**
	 * Take a statement with "you <something> me" and transform it into "What makes
	 * you think that I <something> you?"
	 * 
	 * @param statement the user statement, assumed to contain "you" followed by
	 *                  "me"
	 * @return the transformed statement
	 */

	private String transformIYouStatement(String statement) {
		// Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement.length() - 1);
		}

		int psnOfYou = findKeyword(statement, "I", 0);
		int psnOfMe = findKeyword(statement, "you", psnOfYou + 2);

		String restOfStatement = statement.substring(psnOfYou + 2, psnOfMe).trim();
		return "Why do you " + restOfStatement + " me?";
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method
	 * will check that the given goal is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 * 
	 * @param statement the string to search
	 * @param goal      the string to search for
	 * @param startPos  the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's
	 *         not found
	 */
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim();
		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		// Refinement--make sure the goal isn't part of a word
		while (psn >= 0) {
			// Find the string of length 1 before and after the word
			String before = " ";
			String after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}

			// If before and after aren't letters, we've found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0))
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
		}

		return -1; // Our goal was not present
	}

	/**
	 * Search for one word in phrase. The search is not case sensitive. This method
	 * will check that the given goal is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no"). The search begins at the beginning
	 * of the string.
	 * 
	 * @param statement the string to search
	 * @param goal      the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's
	 *         not found
	 */
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * 
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 6; // changed to 6 from 4
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "Interesting, tell me more.";
		} else if (whichResponse == 1) {
			response = "Hmmm.";
		} else if (whichResponse == 2) {
			response = "Do you really think so?";
		} else if (whichResponse == 3) {
			response = "You don't say.";
		} else if (whichResponse == 4) {
			response = "That is your opinion.";
		} else if (whichResponse == 5) {
			response = "Ask me something else.";
		}

		return response;
	}

}
