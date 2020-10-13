/**
 * @author Ante Zovko
 * @version October 11th, 2020
 * 
 * Point class with x and y coordinate
 * 
 */
public class Point {

    // Class Variables
    private double x;
    private double y;

    // Default Constructor
    public Point(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor
     * 
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y){

        this.x = x;
        this.y = y;

    }

    /**
     * Returns x coordinate
     * 
     * @return x coordinate
     */
    public double getXCoordinate() {
        return this.x;
    }

    /**
     * Returns y coordinate
     * 
     * @return y coordinate
     */
    public double getYCoordinate() {
        return this.y;
    }

    /**
     * Sets x coordinate
     * 
     * @param x x coordinate
     */
    public void setXCoordinate(double x) {
        this.x = x;
    }

    /**
     * Sets y coordinate
     * 
     * @param y y coordinate
     */
    public void setYCoordinate(double y) {
        this.y = y;
    }

    /**
     * Gets x and y coordinates
     * 
     * @return double array containing x and y coordinates
     */
    public double[] getBothCoordinates(){

        return new double[] {this.x, this.y};

    }

}