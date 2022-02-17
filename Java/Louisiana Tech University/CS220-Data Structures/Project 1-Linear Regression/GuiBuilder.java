import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



@SuppressWarnings("serial")
/**
 * @author Ante Zovko
 * @version October 11th, 2020
 * 
 * Creates a Graphical User Interface for Plotting a Best Fit Line
 * 
 */
public class GuiBuilder extends JFrame {

    // Class Variables
    private final int GWINDOW_HEIGHT = 685;
    private final int GWINDOW_WIDTH = 960;

    private ArrayList<Point> arrayOfPoints;

    private static int locationY = 0;
    private static int locationX = 350;


    public GuiBuilder() {

        // Get the Input from the user
        this.arrayOfPoints = userInput();

        // Window settings
        this.setTitle("Ante Zovko: Project 1 - Linear Regression");
        this.setSize(GWINDOW_WIDTH, GWINDOW_HEIGHT);
        this.setLocation(locationX, locationY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        
        // Graph the x and y axis and the best fit line
        this.getContentPane().add(new GraphingCanvas(GWINDOW_WIDTH, GWINDOW_HEIGHT, arrayOfPoints));

        this.setResizable(false);
        this.setVisible(true);


    }


    /**
     * Gets input from the user
     * 
     * @return ArrayList of points
     */
    private ArrayList<Point> userInput() {

    
       return getArrayOfPoints(getUserNumberOfPoints());
     

    }

    /**
     * Gets the number of points from the user
     * 
     * @return number of points
     */
    private int getUserNumberOfPoints() {

        while(true){
            try{

                String userNumberOfPoints = JOptionPane.showInputDialog(this, "How many points would you like to enter?");

                if(userNumberOfPoints == null)
                    System.exit(0);

                int numberOfPoints = Integer.parseInt(userNumberOfPoints);

                if(numberOfPoints < 2){
                    JOptionPane.showMessageDialog(this, "You have to enter a minimum of two points", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                
                return numberOfPoints;
    
            } catch(Exception e){
               
                JOptionPane.showMessageDialog(this, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
    
            }
            
        }

    }

    /**
     * Gets x and y coordinates for each point from the user
     * 
     * @param numberOfPoints number of points
     * 
     * @return ArrayList of Points
     */
    private ArrayList<Point> getArrayOfPoints(int numberOfPoints) {

        ArrayList<Point> arrayOfPoints = new ArrayList<>();

        for(int i = 0; i < numberOfPoints; i++){

            
                try{

                    String userPoints = JOptionPane.showInputDialog(this, "Enter x and y coordinates separated by a comma: x,y");
                    if(userPoints == null)
                        System.exit(0);

                    String[] coordinates = userPoints.split(",");

                    if(coordinates.length < 2)
                        throw new IllegalArgumentException();
                    
                    arrayOfPoints.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));   
    
                }
                catch(Exception e){

                    JOptionPane.showMessageDialog(this, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
                    i-=1;
                    
                } 


        }

        return arrayOfPoints;


        

    }
    
    
}
