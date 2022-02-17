
/**
 * 
 * @author Ante Zovko
 * @version October 13th, 2020
 * 
 * Insertion sort implementation
 * 
 */
public class ZovkoA6 {
    
    /**
     * Insertion sort algorithm
     * 
     * @param list the given list
     * 
     * @return the sorted list
     */
    public static int[] insertionSort(int[] list){


        int n = list.length;

		for (int j = 1; j < n; j++) {

			int k = list[j];
            int i = j - 1;
            
			while (i >= 0 && list[i] > k) {
				list[i + 1] = list[i];
				i--;
            }
            
            list[i + 1] = k;
            
        }

        return list;


    }


}
