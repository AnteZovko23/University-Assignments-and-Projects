package CS132Images;
/**
 * ImagePixelate - an ImageTransformer that pixelates the image by replacing all of the pixels
 *    in a square block with the average of each of the pixels in the block.
 *    
 * @author David Levine
 * @version May 28, 2009
 */


import java.util.ArrayList;

import squint.SImage;

public class ImagePixelate implements ImageTransformer {

    private int blockSize;

    public ImagePixelate(int blurThreshhold) {
        this.blockSize = blurThreshhold;
    }

    public ImagePixelate() {
        this(8);
    }

    @Override
    public SImage transform(SImage picture) {
        return pixelate(picture);
    }

    private SImage pixelate(SImage si) {
        int[][] newReds = pixelateSingleChannel(si.getRedPixelArray());
        int[][] newGreens = pixelateSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = pixelateSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    private int[][] pixelateSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int c = 0; c < columns; c += blockSize) {
            for (int r = 0; r < rows; r += blockSize) {
                int blockColor = average(getBlockElements(pixelArray, r, c));
                fillBlock(answer, r, c, blockColor);
            }
        }
        return answer;
    }

    private void fillBlock(int[][] answer, int upperY, int leftX, int blockColor) {
            for (int row = upperY; row < upperY + blockSize; row++) {
            for (int col = leftX; col < leftX + blockSize; col++) {
                if (isValidIndex(answer, row, col)) {
                    answer[row][col] = blockColor;
                }
            }
        }
    }

    private int[] getBlockElements(int[][] matrix, int upperY, int leftX) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for (int col = leftX; col < leftX + blockSize; col++) {
            for (int row = upperY; row < upperY + blockSize; row++) {
                if (isValidIndex(matrix, row, col)) {
                    answer.add(matrix[row][col]);
                }
            }
        }
        int[] finalAnswer = new int[answer.size()];
        for (int i = 0; i < finalAnswer.length; i++)
            finalAnswer[i] = answer.get(i);

        return finalAnswer;
    }

    private boolean isValidIndex(int[][] matrix, int r, int c) {
        return (r >= 0) && (r < matrix.length) && (c >= 0)
                && (c < matrix[0].length);
    }

    private int average(int[] list) {
        int total = 0;
        for (int x : list)
            total += x;
        return total / list.length;
    }

}
