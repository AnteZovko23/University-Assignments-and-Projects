
/**
 * Linear and Binary Search Algorithms 
 * 
 * @author Ante Zovko
 * @version October 23, 2018
 * 
 *
 */
public class SearchingMain {
	
	public static final int NOT_FOUND = -1;
	
		
	/********** Search Functions *********/
		
		
	/**
	 * Performs a Linear search algorithm on an array
	 * 
	 * @param list the array 
	 * @param key the object to be found
	 * @return i the position (-1 if not found)
	 * 
	 */
	 private static int linearSearch(Object[] list, Object key)
	 {

		 for(int i = 0; i < list.length; i++)
		 {
			 if(key.equals(list[i]))
			 {
				return i;
			 }
			 
		 }
		
		return NOT_FOUND;
		 
	 }
	
	 
	 /**
	  * Performs a Binary search algorithm on an array
	  * @param list the array
	  * @param key the object to be found
	  * @return mid the position (-1 if not found)
	  */
	 private static <E> int binarySearch(Comparable<E>[] list, E key)
	 {
		 int low = 0;
		 
		 int high = list.length - 1;
		 
		 int mid;
		 
		 while (low <= high) 
		 {
			mid = (low + high) / 2;
			
			if (list[mid].compareTo(key) == 0)
			{
				return mid;
			}
			else if (list[mid].compareTo(key) < 0)
			{
				low = (mid + 1);
			}
			else
			{
				high = (mid - 1);
			}
		 }
		 return NOT_FOUND;
			
	 }
		 
		 
		 
	 }


