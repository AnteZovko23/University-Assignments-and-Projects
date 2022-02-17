package CS132Images;

import squint.SImage;
/**
 * ImageShrinker - an ImageTransformer that shrinks the image by 
 *    only keeping the even numbered pixels 
 *    
 * @author Ante Zovko
 * @version 26/10/2018
 */
public class ImageShrinker implements ImageTransformer {

	@Override
	public SImage transform(SImage picture) {
		
		return shrinkImage(picture);
	}
	
	/**
     * Method that assigns new values to the red, green and blue channels
     * 
     * @param si Simage si
     * @return new SImage
     */
    private SImage shrinkImage(SImage si) {
        int[][] newReds = shrinkImageSingleChannel(si.getRedPixelArray());
        int[][] newGreens = shrinkImageSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = shrinkImageSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    /**
     * Method that returns only the even numbered pixels in the array
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     */
    private int[][] shrinkImageSingleChannel(int[][] pixelArray) 
    {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        
        int[][] answer = new int[rows][columns];
        
        for (int r = 0; r < rows; r++ ) {
            for (int c = 0; c < columns; c++) 
            {
            	
                answer[r / 2][c / 2] = pixelArray[r][c];
            }
        }
        
        return answer;

}

}
