import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;

import java.awt.geom.Line2D;

@SuppressWarnings("serial")

/**
 * @author Ante Zovko
 * @version October 11th, 2020
 * 
 * Creates a canvas for drawing the best fit line
 * 
 */
public class GraphingCanvas extends JPanel {

    // Class Variables
    private int width;
    private int height;
    
    private ArrayList<Point> arrayOfPoints;
    private static JTextField formula =  new JTextField();;

    /**
     * Constructor
     * 
     * @param width the width of the window
     * @param height the height of the window
     * @param arrayOfPoints ArrayList of points
     * 
     */
    public GraphingCanvas(int width, int height, ArrayList<Point> arrayOfPoints) {
        this.width = width;
        this.height = height;
        this.arrayOfPoints = arrayOfPoints;

    }

    /**
     * Overrides the paint component for drawing graphics
     * 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /**** Draw x and y axis */
        double max = 16;
        double min = 10;

        Graphics2D g2 = (Graphics2D) g;
        setBackground(Color.WHITE);
        g2.translate(width / 2, height / 2);
        g2.scale(3.0, 3.0);
        g2.draw(new Line2D.Double(-4 * 100, 0, 4 * 100, 0));
        g2.draw(new Line2D.Double(0, min * 100, 0, -max * 100));

        /**** Draw x and y axis */


        // Calculate the slope and intercept of the best fit line
        double[] slopeAndIntercept = LinearRegressionCalculation.linReg(arrayOfPoints);


        /**** Draw best fit line */
        
        // NOTE: The y coordinates needed to be reflected across the x axis because
        // of how java graphics handle coordinates (0,0 in top left) 
        double y2 = -(slopeAndIntercept[0]* 400 + slopeAndIntercept[1]);
        double y1 = -(slopeAndIntercept[0]* -400 + slopeAndIntercept[1]);

        g2.setColor(Color.RED);
        g2.draw(new Line2D.Double(-400, y1, 400, y2));

        /**** Draw best fit line */


        /**** Display the best fit equation in slope-intercept form */

        formula.setText(String.format("Equation: \n%.3fx + %.3f", slopeAndIntercept[0],slopeAndIntercept[1]));
        formula.setEditable(false);
        formula.setBounds(700, 545, 259, 108);
        formula.setBackground(Color.white);
        formula.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        formula.setFont(new Font("Roboto", Font.BOLD, 16));
        this.add(formula);

        /**** Display the best fit equation in slope-intercept form */


      
    }


}