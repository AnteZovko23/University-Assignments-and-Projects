package CS132Images;

import squint.SImage;
/**
 * ImageDarkener - an ImageTransformer that Brightens the image
 * 
 * @author Ante Zovko
 * @version September 19th, 2018
 *
 */
public class ImageBrightener implements ImageTransformer {

	@Override
	public SImage transform(SImage picture) {
		
		return brightenImage(picture);
	}
	/**
	 *  Method that assigns new values to the red, green and blue channels
	 * 
	 * @param si SImage
	 * @return new SImage
	 */
	private static SImage brightenImage(SImage si)
	{
		int[][] newReds = brightenImageSingleChannel(si.getRedPixelArray());
		int[][] newGreens = brightenImageSingleChannel(si.getGreenPixelArray());
		int[][] newBlues = brightenImageSingleChannel(si.getBluePixelArray());
		
		return new SImage(newReds, newGreens, newBlues);
	}
	
	/**
     * Method that returns a horizontally inverted array of pixels
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     */
	 private static int[][] brightenImageSingleChannel(int[][] pixelArray) {
	        int rows = pixelArray.length;
	        int columns = pixelArray[0].length;
	        int[][] answer = new int[rows][columns];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < columns; c++) {
	                 answer[r][c] = (pixelArray[r][c] + 255) / 2;
	            }
	        }
	        return answer;
	    }
}
