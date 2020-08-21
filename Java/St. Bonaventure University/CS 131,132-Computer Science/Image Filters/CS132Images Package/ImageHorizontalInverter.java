package CS132Images;
/**
 * ImageHorizontalInverter - an ImageTransformer that flips through a horizontal axis.
 *    
 * @author David Levine and Ante Zovko
 * @version September 19th, 2018
 */


import squint.SImage;

public class ImageHorizontalInverter implements ImageTransformer {

    @Override
    public SImage transform(SImage picture) {
        return horizontalInvert(picture);
     }
    /**
     * Method that assigns new values to the red, green and blue channels
     * 
     * @param si Simage si
     * @return new SImage
     */
    private static SImage horizontalInvert(SImage si) {
        int[][] newReds = horizontalInvertSingleChannel(si.getRedPixelArray());
        int[][] newGreens = horizontalInvertSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = horizontalInvertSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }
    /**
     * Method that returns a horizontally inverted array of pixels
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     */
    private static int[][] horizontalInvertSingleChannel(int[][] pixelArray) {
 
    	 int rows = pixelArray.length;
         int columns = pixelArray[0].length;
         int[][] answer = new int[rows][columns];
         for (int r = 0; r < rows; r++) {
             for (int c = 0; c < columns; c++) {
                 answer[r][c] = pixelArray[rows - r - 1][c];
             }
         }
         return answer;
     }

}
