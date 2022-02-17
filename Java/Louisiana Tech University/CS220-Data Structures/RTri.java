
/**
 * Author: Ante Zovko
 * Version: September 21st, 2020
 * 
 * Static Methods and Class Objects
 * Right Triangle hypotenuse, perimeter and area
 * 
 */
public class RTri {
    
   
    
    /**
     * Returns the length of the hypotenuse of a right
       triangle with base length b and height length h.

     * @param b base length
     * @param h height
     *  
     * @return hypotenuse length
     * 
     */
    public static double hypotenuse(double b, double h) {

        return Math.sqrt(Math.pow(b, 2) + Math.pow(h, 2));

    }


    /**
     * Returns the perimeter of a right triangle with
       base length b and height length h
     * 
     * @param b base length
     * @param h height
     * 
     * @return perimeter
     * 
     */
    public static double perimeter(double b, double h) {

        return b + h + hypotenuse(b, h);

    }



    /**
     * Returns the area of a right triangle with
       base length b and height length h

     * @param b base length
     * @param h height
     * 
     * @return area
     * 
     */
    public static double area(double b, double h) {

        return 0.5 * b * h;

    }

}
