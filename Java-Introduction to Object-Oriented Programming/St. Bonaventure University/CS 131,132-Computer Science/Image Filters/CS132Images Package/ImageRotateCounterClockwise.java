package CS132Images;

/**
 * ImageRotateCounterClockwise - an ImageTransformer that rotates its source image CounterClockwise
 *    by ninety degrees.
 *
 * @author Ante Zovko
 * @version 26/9/2018
 */

import squint.SImage;

public class ImageRotateCounterClockwise implements ImageTransformer {

    @Override
    public SImage transform(SImage picture) 
    {
        return rotateCounterClockwise(picture);
    }
    /**
     * Method that assigns new values to the red, green and blue channels
     * 
     * @param si Simage si
     * @return new SImage
     */
    private SImage rotateCounterClockwise(SImage picture)
    {
        int[][] newReds = rotateCounterClockwiseSingleChannel(picture.getRedPixelArray());
        int[][] newGreens = rotateCounterClockwiseSingleChannel(picture.getGreenPixelArray());
        int[][] newBlues = rotateCounterClockwiseSingleChannel(picture.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }
    /**
     * Method that returns an array of pixels that is turned Counter-Clockwise by
     * 90 degrees
     * 
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     * 
     */
    private int[][] rotateCounterClockwiseSingleChannel(int[][] pixelArray)
    {
        int columns = pixelArray.length;
        int rows = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) 
        {
            for (int c = 0; c < columns; c++) 
            {
                answer[r][c] = pixelArray[columns - c - 1][r]; // Counter-Clockwise
            }
        }
        return answer;
    }

}