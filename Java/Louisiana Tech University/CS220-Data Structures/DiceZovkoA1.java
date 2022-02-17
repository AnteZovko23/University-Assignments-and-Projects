
/**
 * Author: Ante Zovko
 * Version: September 15th, 2020
 * 
 * Rolls two dice and displays their sum
 * 
 * 
 */
class DiceZovkoA1 {

    public static void main(String[] args) {
        
        int firstDie, secondDie;
        firstDie = roll();
        secondDie = roll();
        
        System.out.println("The first die is " + firstDie + ". The second die is " + secondDie);
        System.out.println("Their total is: " + (firstDie + secondDie));


    }


    /**
     * Rolls a dice
     * 
     * @return range from 1-6
     */
    private static int roll() {

        return (int) (Math.random() * 6) + 1;

    }


}


