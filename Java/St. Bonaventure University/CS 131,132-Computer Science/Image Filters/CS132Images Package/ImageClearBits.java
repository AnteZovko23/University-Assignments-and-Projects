package CS132Images;

import squint.SImage;
/**
 * 
 * ImageClearBits - an ImageTransformer that clears a predetermined number of bits from an image
 * 
 * @author Ante Zovko 
 * @version October 4th, 2018
 *
 */
public class ImageClearBits implements ImageTransformer {

	 
	private int divisionFactor;
	
	public ImageClearBits(int givenPower)
	{
		divisionFactor = (int) Math.pow(2, givenPower);
	}
	
	
	@Override
	 public SImage transform(SImage picture) 
	{
		
		
        return clearBits(picture);
	}
	
	
	
	
	
	/**
	 * Method that assigns new values to the red, green and blue channels
	 * 
	 * @param si Simage si
	 * @return new SImage
	 */
	private SImage clearBits(SImage si) {
        int[][] newReds = clearBitsSingleChannel(si.getRedPixelArray());
        int[][] newGreens = clearBitsSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = clearBitsSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }
	
	/**
     * Method that returns clears a predetermined number of bits in an Image
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     */
    private int[][] clearBitsSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                answer[r ][c ] = pixelArray[r] [c] / divisionFactor * divisionFactor;
            }
        }
        return answer;
    }
	
	
	
	
	
	
	

}
