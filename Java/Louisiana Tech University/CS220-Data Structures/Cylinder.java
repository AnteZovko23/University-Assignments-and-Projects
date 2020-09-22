/**
 * Author: Ante Zovko
 * Version: September 21st, 2020
 * 
 * Cylinder class
 * 
 */
public class Cylinder {

    // Class vars
    private double radius; // radius of a cylinder
    private double height; // height of a cylinder

    /**
     * Default Constructor
     * 
     */
    public Cylinder() {

        this.setRadius(0);
        this.setHeight(0);
    }

    /**
     * Constructor
     * 
     * @param r radius
     * @param h height
     * 
     */
    public Cylinder(double r, double h) {

        this.setRadius(r);
        this.setHeight(h);
    }


    /**
     * Displays cylinder dimensions
     * 
     */
    public void displayDimensions() {

        System.out.printf("Radius: %.2f\nHeight: %.2f\n", this.getRadius(), this.getHeight());

    }

    /**
     * Calculates cylinder volume
     * 
     * @return volume of the cylinder
     * 
     */
    public double getVolume() {

        return Math.PI * this.getHeight() * Math.pow(this.getRadius(), 2);

    }

    /**
     * Calculates cylinder area
     * 
     * @return area of the cylinder
     * 
     */
    public double getArea() {

        return 2 * Math.PI * this.getRadius() * this.getHeight() + 2 * Math.PI * Math.pow(this.getRadius(), 2);

    }


    /**
     * Gets height
     * 
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets height
     * 
     * @param height given height
     * 
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets radius
     * 
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets radius
     * 
     * @param radius given radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    

}
