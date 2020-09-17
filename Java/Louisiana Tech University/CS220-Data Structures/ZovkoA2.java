
/**
 * Author: Ante Zovko
 * Version: September 17th, 2020
 * 
 * Displays the sum of given integers and how long it takes to compute it
 * 
 */
public class ZovkoA2 {


    public static void main(String[] args) {
        
        long value = 1000000;

        for(int i = 0; i < 4; i++){

            long[] values = benchmarkSums(value);
            System.out.println("\nSum: "+ String.format("%,d\n", values[1])+ "Time:\n" + values[0] + " ms. \n" + values[0]/1000.0 + " seconds");
            value *= 10;

        }


    }


    /**
     * Calculates the sum and time
     * 
     * 
     * @param upperLimit the loop limit
     * 
     * @return long[] array containing the sum and the time
     */
    private static long[] benchmarkSums(long upperLimit) {

        long start = System.currentTimeMillis();
        long sum = 0;
        long values[] = new long[2];
        
        for(long i = 1; i <= upperLimit; i++){

            sum += i;

        }

        long stop = System.currentTimeMillis();
        
        values[0] = stop - start;
        values[1] = sum;

        return values;


    }




}