package CS132Images;

import squint.SImage;
/**
 * ImageDarkener - an ImageTransformer that darkens the image
 * 
 * @author Ante Zovko
 * @version September 19th, 2018
 * 
 *
 */
public class ImageDarkener implements ImageTransformer {

	@Override
	public SImage transform(SImage picture) {
		
		return darkenImage(picture);
	}
	/**
	 * Method that assigns new values to the red, green and blue channels
	 * 
	 * @param si SImage si
	 * @return new SImage
	 */
	private static SImage darkenImage(SImage si)
	{
		int[][] newReds = darkenImageSingleChannel(si.getRedPixelArray());
        int[][] newGreens = darkenImageSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = darkenImageSingleChannel(si.getBluePixelArray());
	
        return new SImage(newReds, newGreens, newBlues);
	}
	
	/**
	 * Method that returns a modified array of pixels  
	 * 
	 * @param pixelArray array of pixels
	 * @return answer the modified array
	 */
	 private static int[][] darkenImageSingleChannel(int[][] pixelArray) {
	        int rows = pixelArray.length;
	        int columns = pixelArray[0].length;
	        int[][] answer = new int[rows][columns];
	        for (int r = 0; r < rows; r++) {
	            for (int c = 0; c < columns; c++) {
	                 answer[r][c] = (pixelArray[r][c] * 2) / 3;
	            }
	        }
	        return answer;
	    }
}
