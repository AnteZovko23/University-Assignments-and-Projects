package CS132Images;

import javax.swing.JOptionPane;

import squint.SImage;
/**
 * ImageThumbNail - an ImageTransformer that shrinks any image to 
 * a value decided by the user
 *    
 * @author Ante Zovko
 * @version 26/9/2018
 *
 */
public class ImageImprovedThumbNail implements ImageTransformer {

	
	

	private String thumbNailSizeX1 = JOptionPane.showInputDialog(null, "Enter width (x axis)");
	
	private String thumbNailSizeY1 = JOptionPane.showInputDialog(null, "Enter height (y axis)");
	
	private int thumbNailSizeX = Integer.parseInt(thumbNailSizeX1);
	
	private int thumbNailSizeY = Integer.parseInt(thumbNailSizeY1);
	
	
	
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
     * Method that returns an array of pixels whose size is determined by the user
     * 
     * 
     * @param pixelArray the array of pixels
     * @return answer The new array of pixels
     * 
     */
    private int[][] thumbNailImageSingleChannel(int[][] pixelArray) {
    	
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        
        
        
        int factor1 = rows / thumbNailSizeX;
        int factor2 = columns / thumbNailSizeY;
        
        int[][] answer = new int[rows][columns];
        
        for (int r = 0; r < rows; r++ ) 
        {
            for (int c = 0; c < columns; c++)
            {
            	
            		answer[r / factor1][c / factor2] = pixelArray[r][c];
    
            }
        }
        
        return answer;
} }
