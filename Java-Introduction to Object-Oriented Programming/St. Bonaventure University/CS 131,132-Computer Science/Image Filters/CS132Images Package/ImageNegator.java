package CS132Images;
/**
 * ImageNegator - an ImageTransformer that negates the image by 
 *    replacing each pixel with the its numeric complement.
 *    
 * @author David Levine
 * @version May 28, 2009
 */


import squint.SImage;

public class ImageNegator implements ImageTransformer {

    @Override
    public SImage transform(SImage picture) {
        return negateImage(picture);
    }

    private static SImage negateImage(SImage si) {
        int[][] newReds = negateImageSingleChannel(si.getRedPixelArray());
        int[][] newGreens = negateImageSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = negateImageSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    private static int[][] negateImageSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                 answer[r][c] = 255 - pixelArray[r][c];
            }
        }
        return answer;
    }

}
