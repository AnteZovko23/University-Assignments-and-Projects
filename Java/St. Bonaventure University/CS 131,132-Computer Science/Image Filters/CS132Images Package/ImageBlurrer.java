package CS132Images;
/**
 * ImageBlurrer - an ImageTransformer that blurs the image by 
 *    replacing each pixel with the average of some of its neighbors.
 *    
 * @author David Levine
 * @version May 28, 2009
 */

import java.util.ArrayList;

import squint.SImage;

public class ImageBlurrer implements ImageTransformer {

    private int blurThreshhold;

    public ImageBlurrer(int blurThreshhold) {
        this.blurThreshhold = blurThreshhold;
    }

    public ImageBlurrer() {
        this(5);
    }

    @Override
    public SImage transform(SImage picture) {
        return blurImage(picture);
    }

    private SImage blurImage(SImage si) {
        int[][] newReds = blurImageSingleChannel(si.getRedPixelArray());
        int[][] newGreens = blurImageSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = blurImageSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    private int[][] blurImageSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                answer[r][c] = average(getNeighbors(pixelArray, r, c));
            }
        }
        return answer;
    }

    private int[] getNeighbors(int[][] matrix, int centerY, int centerX) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for (int r = centerY - blurThreshhold; r <= centerY + blurThreshhold; r++) {
            for (int c = centerX - blurThreshhold; c <= centerX + blurThreshhold; c++) {
                if (isValidIndex(matrix, r, c)) {
                    answer.add(matrix[r][c]);
                }
            }
        }

        int[] finalAnswer = new int[answer.size()];
        for (int i = 0; i < finalAnswer.length; i++)
            finalAnswer[i] = answer.get(i);

        return finalAnswer;
    }

    private boolean isValidIndex(int[][] matrix, int r, int c) {
        return (r >= 0) && (r < matrix.length) && (c >= 0) && (c < matrix[0].length);
    }

    private int average(int[] list) {
        int total = 0;
        for (int x : list) {
            total += x;
        }
        return total / list.length;
    }

}
