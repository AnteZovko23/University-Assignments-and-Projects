/**
 * Author: Ante Zovko
 * Version: September 21st, 2020
 * 
 * Cylinder Class Testing
 * 
 */
public class CylinderTest {

    public static void main(String[] args) {
        
        // Create Cylinder Instance with default constructor
        Cylinder defaultCylinder = new Cylinder();

        // DefaultCylinder dimensions
        defaultCylinder.displayDimensions();

        // Output:
        // Radius: 0.00
        // Height: 0.00


        // Setters Test
        defaultCylinder.setHeight(5);
        defaultCylinder.setRadius(7);
        defaultCylinder.displayDimensions();

        // Output:
        // Radius: 7.00
        // Height: 5.00


        // Constructor Overload Test
        Cylinder overloadCylinder = new Cylinder(12, 15);

        // Getters Test
        System.out.println(overloadCylinder.getRadius());
        System.out.println(overloadCylinder.getHeight());

        // Output:
        // 12
        // 15


        // Area Test
        System.out.printf("Default Cylinder Area: %.2f\n", defaultCylinder.getArea());
        System.out.printf("Overload Cylinder Area: %.2f\n", overloadCylinder.getArea());

        // Output:
        // Default Cylinder Area: 527.79
        // Overload Cylinder Area: 2035.75


        // Volume Test
        System.out.printf("Default Cylinder Area: %.2f\n", defaultCylinder.getVolume());
        System.out.printf("Overload Cylinder Area: %.2f\n", overloadCylinder.getVolume());
        
        // Output:
        // Default Cylinder Area: 769.69
        // Overload Cylinder Area: 6785.84


    }
    
}
