/**	Class Dice
 * Encapsulation of methods for rolling an n-sided die, where
 * n is passed as an argument when a die is created. The default
 * value is 6.
 * 
 * @author S Andrianoff Modified by Ante Zovko
 * @version 6 Sept 2010 Modified 3/14/2019
 */

public class RandomDice implements Dice
{
	private int sides;

	/**
	 * Constructor for a Dice object. Default of six sides.
	 *
	 */
	public RandomDice()
	{
		sides = 6;
	}
	
	/**
	 * Constructor for a Dice object.  Number of sides given.
	 * @param numSides number of sides of Dice
	 */
	public RandomDice(int numSides)
	{
		sides = numSides;
	}
	
	/* (non-Javadoc)
	 * @see Dice#roll()
	 */
	@Override
	public int roll()
	{
		double x;
		x = Math.random();
		return (int) Math.floor(x*sides) + 1;
	}
}
