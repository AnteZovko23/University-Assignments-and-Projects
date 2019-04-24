package CS132Images;

import squint.SImage;
/**
 * ImageThumbNail - an ImageTransformer that shrinks any image to 
 *    50x50
 * @author Ante Zovko
 * @version 26/9/2018
 *
 */
public class ImageThumbNail implements ImageTransformer {
	
	
	private int thumbNailSize;
	
	/**
	 * Constructor that determines the size of the ThumbNail
	 * @param thumbNailSize
	 */
	public ImageThumbNail(int thumbNailSize)
	{	
		this.thumbNailSize = thumbNailSize;
	}
	
	@Override
	public SImage transform(SImage picture) {
		
		return thumbNailImage(picture);
	}
	
	/**
     * Method that assigns new values to the red, green and blue channels
     * 
     * @param si Simage si
     * @return new SImage
     */
    private SImage thumbNailImage(SImage si) {
        int[][] newReds = thumbNailImageSingleChannel(si.getRedPixelArray());
        int[][] newGreens = thumbNailImageSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = thumbNailImageSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }
    /**
     * Method that returns a 50x50 array of pixels (If the image is already
     * 50x50 or smaller, it just returns the image)
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     * 
     */
    private int[][] thumbNailImageSingleChannel(int[][] pixelArray) {
    	
        int rows = pixelArray.length ;
        int columns = pixelArray[0].length;
        
        int factor1 = rows / thumbNailSize;
        int factor2 = columns / thumbNailSize;
        
        int[][] answer = new int[rows][columns];
        
        for (int r = 0; r < rows; r++ ) 
        {
            for (int c = 0; c < columns; c++)
            {
            	if(rows <= 50 && columns <= 50)
            	{
            		answer[r][c] = pixelArray[r][c];
            	}
            	else
            	{
            		answer[r / factor1][c / factor2] = pixelArray[r][c];
            	}
            }
        }
        
        return answer;
}   }
