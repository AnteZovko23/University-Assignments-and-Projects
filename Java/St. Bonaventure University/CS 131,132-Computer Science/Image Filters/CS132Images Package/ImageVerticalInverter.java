package CS132Images;
/**
 * ImageVerticalInverter - an ImageTransformer that flips through a 
 *                         vertical axis.
 *    
 * @author David Levine
 * @version May 28, 2009
 */

import squint.SImage;

public class ImageVerticalInverter implements ImageTransformer {

    @Override
    public SImage transform(SImage picture) {
        return verticalInvert(picture);
    }

    private static SImage verticalInvert(SImage si) {
        int[][] newReds = verticalInvertSingleChannel(si.getRedPixelArray());
        int[][] newGreens = verticalInvertSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = verticalInvertSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    private static int[][] verticalInvertSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                answer[r][c] = pixelArray[r][columns - c - 1];
            }
        }
        return answer;
    }

}
