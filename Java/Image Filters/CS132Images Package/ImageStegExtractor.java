package CS132Images;

import squint.SImage;

public class ImageStegExtractor implements ImageTransformer { 

	
	private int divisionFactor;
	
	
	public ImageStegExtractor(int givenPower)
	{
		divisionFactor = (int) Math.pow(2, givenPower);
	}
	

	
	
	
	@Override
	 public SImage transform(SImage picture) 
	{
		
		
        return extract(picture);
	}
	
	

	
	
	private SImage extract(SImage si) {
        int[][] newReds = extractBitsSingleChannel(si.getRedPixelArray());
        int[][] newGreens = extractBitsSingleChannel(si.getGreenPixelArray());
        int[][] newBlues = extractBitsSingleChannel(si.getBluePixelArray());

        return new SImage(newReds, newGreens, newBlues);
    }

    private int[][] extractBitsSingleChannel(int[][] pixelArray) {
        int rows = pixelArray.length;
        int columns = pixelArray[0].length;
        int[][] answer = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
            	
                answer[r][c] = (pixelArray[r][c] % divisionFactor) * (int) ((Math.pow(2, 8) / divisionFactor));
            	  
            }
        }
        return answer;
    }

}
