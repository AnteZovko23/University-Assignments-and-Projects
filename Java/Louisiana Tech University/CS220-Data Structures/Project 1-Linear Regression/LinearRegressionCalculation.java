import java.util.ArrayList;

/**
 * @author Ante Zovko
 * @version October 11th, 2020
 * 
 * Given an array of points, calculates the best fit line equation in slope-intercept form (y = mx + b)
 * 
 * Reference: Altman, Naomi; Krzywinski, Martin (2015). 
 * "Simple linear regression". Nature Methods. 12 (11): 999–1000. doi:10.1038/nmeth.3627. 
 * ISSN 1548-7091. OCLC 5912005539. PMID 26824102.
 * 
 */
public class LinearRegressionCalculation {


    /**
     * Performs the calculation of the Linear Regression
     * 
     * @param L ArrayList of points
     * @return double array containing the slope and y-intercept value
     * @throws IllegalArgumentException 
     * 
     */
    public static double[] linReg(ArrayList<Point> L) {

        // Error if less than two points are provided in the array
        if(L.size() < 2)
            throw new IllegalArgumentException("At least two pairs of coordinates are required.");

        // Gets the arithmetic mean of each coordinate
        double[] coordinateArithmeticMean = getCoordinateArithmeticMean(L);

        // Gets the slope
        double slope = getSlope(L, coordinateArithmeticMean);

        // Gets the y-intercept
        double yIntercept = getYIntercept(slope, coordinateArithmeticMean);
        
        return new double[] {slope, yIntercept};

    }

   
    /**
     * Calculates the slope
     * 
     * @param L ArrayList of points
     * @param coordinateArithmeticMean x and y arithmetic mean
     * 
     * @return slope value
     */
    private static double getSlope(ArrayList<Point> L, double[] coordinateArithmeticMean) {

        return getNumeratorOfSlopeFormula(L, coordinateArithmeticMean)/getDenominatorOfSlopeFormula(L, coordinateArithmeticMean);

    }

    /**
     * Calculates the y-intercept
     * 
     * @param slope the slope of the line
     * @param coordinateArithmeticMean x and y arithmetic mean
     * 
     * @return the y intercept
     */
    private static double getYIntercept(double slope, double[] coordinateArithmeticMean) {

        return coordinateArithmeticMean[1] - slope * coordinateArithmeticMean[0];
    }

    /**
     * Calculates the numerator of the slope formula
     * 
     * @param L ArrayList of points
     * @param coordinateArithmeticMean x and y arithmetic mean
     * 
     * @return the numerator of the slope formula
     * 
     */
    private static double getNumeratorOfSlopeFormula(ArrayList<Point> L, double[] coordinateArithmeticMean) {

        double sum = 0;

        for(Point givenPoint : L){

            sum += ((givenPoint.getXCoordinate() - coordinateArithmeticMean[0]) * (givenPoint.getYCoordinate() - coordinateArithmeticMean[1]));

        }

        return sum;

    }

    /**
     * Calculates the denominator of the slope formula
     * 
     * @param L ArrayList of points
     * @param coordinateArithmeticMean x and y arithmetic mean
     * 
     * @return the denominator of the slope formula
     * 
     */
    private static double getDenominatorOfSlopeFormula(ArrayList<Point> L, double[] coordinateArithmeticMean) {

        double sum = 0;

        for(Point givenPoint : L){

            sum += Math.pow((givenPoint.getXCoordinate() - coordinateArithmeticMean[0]), 2);

        }

        return sum;

    }

    /**
     * Calculates the arithmetic mean of given x and y coordinates
     * 
     * @param L ArrayList of points
     * 
     * @return x and y arithmetic mean
     * 
     */
    private static double[] getCoordinateArithmeticMean(ArrayList<Point> L) {

        double sumOfXCoordinates = 0;
        double sumOfYCoordinates = 0;
        
        for(Point givenPoint : L){

            sumOfXCoordinates += givenPoint.getXCoordinate();
            sumOfYCoordinates += givenPoint.getYCoordinate();

        }

        return new double[] {sumOfXCoordinates/L.size(), sumOfYCoordinates/L.size()};

    }
    
}
