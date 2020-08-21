package CS132Images;
/**
 * ImageRotateClockwise - an ImageTransformer that rotates its source image clockwise
 *    by ninety degrees.
 *
 * @author David Levine
 * @version May 28, 2009
 */

import squint.SImage;

public class ImageRotateClockwise implements ImageTransformer {

    @Override
    public SImage transform(SImage picture) {
        return rotateClockwise(picture);
    }

    private SImage rotateClockwise(SImage picture) {
        int[][] newReds = rotateClockwiseSingleChannel(picture.getRedPixelArray());
        int[][] newGreens = rotateClockwiseSingleChannel(picture.getGreenPixelArray());
        int[][] newBlues = rotateClockwiseSingleChannel(picture.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    private int[][] rotateClockwiseSingleChannel(int[][] pixelArray) {
        int columns = pixelArray.length;
        int rows = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                answer[r][c] = pixelArray[c][rows - r - 1]; // Clockwise
            }
        }
        return answer;
    }

}
