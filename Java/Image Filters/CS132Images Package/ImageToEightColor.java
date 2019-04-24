package CS132Images;
/**
 * ImageToEightColor - an ImageTransformer that reduces the source image to
 *    one bit per color channel (hence eight colors overall)
 *
 * @author David Levine
 * @version May 28, 2009
 */


import squint.SImage;

public class ImageToEightColor implements ImageTransformer {

    private static final int DEFAULT_THRESHHOLD = 128;

    private int threshhold; // The dividing line between a 1 and a 0 for a given
                            // channel

    public ImageToEightColor(int threshhold) {
        this.threshhold = threshhold;
    }

    public ImageToEightColor() {
        this(DEFAULT_THRESHHOLD);
    }

    @Override
    public SImage transform(SImage picture) {
        return eightColor(picture);
    }

    private SImage eightColor(SImage picture) {
        int[][] newReds = eightColorSingleChannel(picture.getRedPixelArray());
        int[][] newGreens = eightColorSingleChannel(picture
                .getGreenPixelArray());
        int[][] newBlues = eightColorSingleChannel(picture.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);

    }

    private int[][] eightColorSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (pixelArray[r][c] > threshhold) {
                    answer[r][c] = 255;
                } else {
                    answer[r][c] = 0;
                }
            }
        }
        return answer;
    }

}
