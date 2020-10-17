/**
 * @author Ante Zovko
 * @version October 17th, 2020
 * 
 * Homework 10 – Searching and Sorting
 */
public class Homework10 {

    public static void main(String[] args) {
        
        int[] testArray = {2,5,6,1,4,24,151,5,153,5,35,454,6,46,34,5,2344};

        System.out.println(linearSearch(testArray, 153) + "\n");

        System.out.println(binarySearch(testArray, 153) + "\n");

        displayArr(bubbleSort(testArray));

        displayArr(selectionSort(testArray));

        displayArr(insertionSort(testArray));


        

    }

    /**
     * Linear Search Implementation
     * 
     * @param arr given array 
     * @param key the key to be found
     * @return the position if found, else -1
     */
    private static int linearSearch(int[] A, int key){

        for(int i = 0; i< A.length; i++) {

            if(A[i] == key)
                return i;

        }

        return -1;

    }

    /**
     * Binary search implementation
     * 
     * @param arr the given array
     * @param key the key to be found
     * @return the position if found, else -1
     */
    private static int binarySearch(int[] arr, int key){

        int start = 0;

        int end = arr.length - 1;
        
        int mid;

        while(start <= end){

            mid = (start + end) / 2;

            if(arr[mid] == key)
                return mid;

            else if(key > arr[mid])
                start = mid + 1;

            else
                end = mid - 1;
        }

        return -1;


    }

    /**
     * Bubble sort implementation
     * 
     * @param list the given list
     * @return sorted list
     */
    private static int[] bubbleSort(int[] list){

        for (int i = 0; i < list.length; i++) {
			int j = list.length - 1;
			do {
				if (list[j] < list[j - 1]) {
					int temp = list[j];
					list[j] = list[j - 1];
					list[j - 1] = temp;
				}
				j--;
			} while (j > i);
        }
        
        return list;



    }

    /**
     * Selection sort implementation
     * 
     * @param list the given list
     * @return sorted list
     */
    private static int[] selectionSort(int[] list){

        for (int j = list.length - 1; j > 0; j--) {
			int maxpos = 0;
			for (int k = 1; k <= j; k++) {
				if (list[k] > list[maxpos]) {
					maxpos = k;
				}
			}
			if (j != maxpos) {
				int temp = list[j];
				list[j] = list[maxpos];
				list[maxpos] = temp;
			}
        }
        
        return list;

    }

    /**
     * Insertion sort implementation
     * 
     * @param list the given list
     * @return sorted list
     */
    private static int[] insertionSort(int[] list){

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
    

    private static void displayArr(int[] list){

        for(int x : list){

            System.out.print(x + " ");

        }
        System.out.println("\n");

    }

    
}