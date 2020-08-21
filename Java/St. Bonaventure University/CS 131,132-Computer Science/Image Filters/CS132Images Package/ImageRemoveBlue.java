package CS132Images;

import squint.SImage;
/**
 * ImageRemoveBlue - an ImageTransformer that removes the color blue
 * @author Ante Zovko
 * @version September 20th, 2018
 *
 */
public class ImageRemoveBlue implements ImageTransformer {

	@Override
	public SImage transform(SImage picture)
	{
	
		return removeBlue(picture);
	}
	
	/**
	 *  Method that assigns new values to the red, green and blue channels
	 * 
	 * @param si SImage
	 * @return new SImage
	 */
	private static SImage removeBlue(SImage si)
	{
		int[][] newReds = si.getRedPixelArray();
		int[][] newGreens = si.getGreenPixelArray();
		int[][] newBlues = assignConstantSingleChannel(si.getBluePixelArray(), 0);
		
		return new SImage(newReds, newGreens, newBlues);
	}
	
	 /** 
     *  Assign every pixel in the plane the same color value
     *  @param pixelArray the initial array of pixels (actually only its size matters)
     *  @param newValue the color value that each pixel will get
     *  @return a new array of the same size as pixelArray where every value is newValue
     */
   public static int[][] assignConstantSingleChannel(int[][] pixelArray, int newValue)
   {
	   int rows = pixelArray.length;
	   int columns = pixelArray[0].length;
	   
	   int[][] answer = new int[rows][columns];
       for (int r = 0; r < rows; r++) {
           for (int c = 0; c < columns; c++) {
                answer[r][c] = newValue;
           }
       }
       return answer;
   }
}
