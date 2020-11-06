public class Recursion {

    public static void main(String[] args) {
        
    }


    /**
     * Computes n factorial
     * 
     * @param n given number
     * @return n!
     * 
     * @throws IllegalArgumentException
     * 
     */
    int factorial(int n){
        if(n < 0)
            throw new IllegalArgumentException("No negative numbers");

        return n > 0 ? n * factorial(n):1;

    }
    
    /**
     * Returns the value of the function f(0)=1, f(n)=2*f(n-1)
     * for n >= 1 
     * 
     * @param n given number
     * @return 2*f(n-1)
     * 
     * @throws IllegalArgumentException
     * 
     */
    int fun(int n){

        if(n < 0)
            throw new IllegalArgumentException("No negative numbers");

        return n > 0 ? 2*fun(n-1):1;


    }
    /**
     * Fibonacci sequence
     * 
     * @param n given number
     * @return the fibonacci number at the nth position
     * 
     * @throws IllegalArgumentException
     * 
     */
    int fib(int n){

        if(n < 0)
            throw new IllegalArgumentException("No negative numbers");

        return n > 0 ? fib(n-1) + fib(n-2):1;

    }

    /**
     * Power calculation
     * 
     * @param x the base
     * @param n the power
     * 
     * @return x^n
     * 
     * @throws IllegalArgumentException
     */
    double power(double x, int n){

        if(n <= 0)
            throw new IllegalArgumentException("No negative numbers");

        return n > 0 ? x + power(x, n-1):1;
        

    }
   
    /**
     * Finds the greatest common denominator
     * 
     * @param a given number
     * @param b given number
     * 
     * @return greatest common denominator
     */
    int gcd(int a, int b){

        return b > 0 ? gcd(a, a % b):a;
        

    }
    
    static int counter;
    /**
     * Displays array contents
     * 
     * @param A given array
     */
    void display(int[] A){



        if(counter < A.length - 1){

            System.out.println(A[counter]);
            counter++;
            display(A);

        }
        

    }
    
    /**
     * Displays all integers contained in an array in reverse order
     * 
     * @param A given array
     * @param n size of the array
     * 
     * @throws IllegalArgumentException
     * 
     */
    void displayRev(int[] A, int n){

        if(n > A.length - 1)
            throw new IllegalArgumentException("Size invalid.");

        if(n >= 0){

            System.out.println(A[counter]);
            counter--;
            displayRev(A, n - 1);

        }
    }

     
    /**
     * Displays all integers in a linked list
     * 
     * @param n head
     */
    void displayList(Node n){

        Node current = n;

        if(n == null)
            return;
        
        else if(current == null)
            return;

        else {

            System.out.println(n.getData());
            displayList(current.getNext());

        }

    }
    /**
     * Displays all integers in a linked list in reverse order
     * 
     * @param n head
     */
    void displayRevList(Node n){

        if (n == null) 
            return; 
  
        displayRevList(n.getNext()); 
  
        System.out.print(n.getData()+" "); 

    }
    // Returns the sum of all integers in a linked list
    int sum(Node n){

        Node current = n;

        if(n == null)
            return 0;
        
        else if(current != null){

            return current.getData() + sum(current.getNext());

        }

        return 0;
        
    }
    
    /**
     * Returns first index i where A[i] == x, otherwise returns -1
     * 
     * @param A given list
     * @param n size
     * @param x key
     * @return index of element
     */
    int linearSearch(int[] A, int n,  int x){

        return (n < 0 || A[n]==x) ? n : linearSearch(A, n-1, x);

    }
    // Returns the index i where A[i] == x using binary search
    // Otherwise, returns -1
    int binarySearch(int[] arr, int right, int left, int x){

        { 
            if (right >= left) { 
                int mid = left + (right - left) / 2; 
      
                // If the element is present at the 
                // middle itself 
                if (arr[mid] == x) 
                    return mid; 
      
                // If element is smaller than mid, then 
                // it can only be present in left subarray 
                if (arr[mid] > x) 
                    return binarySearch(arr, left, mid - 1, x); 
      
                // Else the element can only be present 
                // in right subarray 
                return binarySearch(arr, mid + 1, right, x); 
            } 
      
            // We reach here when element is not present 
            // in array 
            return -1; 
        } 

    }
    
}
