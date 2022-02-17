package CS132Images;

import squint.SImage;
/**
 * ImageSepiaTone - an ImageTransformer that adds the sepia tone to the image
 * 
 * @author Ante Zovko
 * @version September 20th, 2018
 * @see https://www.geeksforgeeks.org/image-procesing-java-set-6-colored-image-sepia-image-conversion/
 *
 */
public class ImageSepiaTone implements ImageTransformer {

	@Override
	public SImage transform(SImage picture) {
		
		return sepiaTone(picture);
	}
	
	/**
	 * Method that assigns new values to the red, green and blue channels
	 * 
	 * @param si SImage si
	 * @return new SImage
	 */
	private static SImage sepiaTone(SImage si)
	{
		int[][] newReds = sepiaToneSingleChannel(si.getRedPixelArray(),si.getGreenPixelArray(),si.getBluePixelArray(),  0.393, 0.769, 0.189);
		int[][] newGreens = sepiaToneSingleChannel2(si.getRedPixelArray(),si.getGreenPixelArray(),si.getBluePixelArray(),  0.349, 0.686, 0.131);
		int[][] newBlues = sepiaToneSingleChannel3(si.getRedPixelArray() ,si.getGreenPixelArray(),si.getBluePixelArray(),  0.272, 0.534, 0.131);
		
		return new SImage(newReds, newGreens, newBlues);
	}

	/**
	 * Creates a new red channel
	 * 
	 * @param pixelArray the Red Pixel array
	 * @param pixelArray2 the Green Pixel array
	 * @param pixelArray3 the Blue Pixel array
	 * @param d constant
	 * @param e constant
	 * @param f constant
	 * @return answer the new red pixel array
	 */
	private static int[][] sepiaToneSingleChannel(int[][] pixelArray,int[][] pixelArray2, int[][] pixelArray3,  double d, double e, double f)
	{
		int rows = pixelArray.length;
		int columns = pixelArray[0].length;
		   
		int[][] answer = new int[rows][columns];
	    for (int r = 0; r < rows; r++) 
	    {
	    	for (int c = 0; c < columns; c++) 
	    	{
	             answer[r][c] =  (int) ((pixelArray[r][c] * d) + (pixelArray2[r][c] * e) + (pixelArray3[r][c] * f)) ; 
	        }
	    }
	       
	    return answer;
	}
	
	/**
	 * Creates a new green channel
	 * 
	 * @param pixelArray the Red Pixel array
	 * @param pixelArray2 the Green Pixel array
	 * @param pixelArray3 the Blue Pixel array
	 * @param d constant
	 * @param e constant
	 * @param f constant
	 * @return answer the new green pixel array
	 */
	private static int[][] sepiaToneSingleChannel2(int[][] pixelArray,int[][] pixelArray2, int[][] pixelArray3,  double d, double e, double f)
	{
		int rows = pixelArray.length;
		int columns = pixelArray[0].length;
		   
		int[][] answer = new int[rows][columns];
	    for (int r = 0; r < rows; r++) 
	    {
	    	for (int c = 0; c < columns; c++) 
	    	{
	             answer[r][c] =  (int) ((pixelArray[r][c] * d) + (pixelArray2[r][c] * e) + (pixelArray3[r][c] * f)) ; 
	        }
	    }
	       
	    return answer;
	}
	
	/**
	 * Creates a new blue channel
	 * 
	 * @param pixelArray the Red Pixel array
	 * @param pixelArray2 the Green Pixel array
	 * @param pixelArray3 the Blue Pixel array
	 * @param d constant
	 * @param e constant
	 * @param f constant
	 * @return answer the new blue pixel array
	 */
	private static int[][] sepiaToneSingleChannel3(int[][] pixelArray,int[][] pixelArray2, int[][] pixelArray3,  double d, double e, double f)
	{
		int rows = pixelArray.length;
		int columns = pixelArray[0].length;
		   
		int[][] answer = new int[rows][columns];
	    for (int r = 0; r < rows; r++) 
	    {
	    	for (int c = 0; c < columns; c++) 
	    	{
	             answer[r][c] =  (int) ((pixelArray[r][c] * d) + (pixelArray2[r][c] * e) + (pixelArray3[r][c] * f)) ; 
	        }
	    }
	       
	    return answer;
	}
	

}
