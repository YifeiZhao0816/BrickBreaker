

//This is the class for the plate which the user controls.
public class Plate {
	private static int xPos = 640, yPos = 36, length = 128, width = 6;		//These coordinates all represents the geographic center of the object.
	
	// return the current coordinate of the plate
	public static int[] getCordinate() 
	{	return new int[] {xPos, yPos};	}
	
	// set the coordinate of the plate.
	public static void setCordinate(int x, int y)
	{	xPos = x; yPos = y;	}
	
	//return the current dimension
	public static int[] getdimension() {	return new int[] {length, width};	}
	
}
