

// This class define the property of a block in the game
public class Block {
		public final int width = 36, length = 128;
		private int xCordinate, yCordinate;	//, blockCount;			// These are the coordinate of geometric center of the block! 
																							// blockcount is currently useless.
		
		public Block(int x, int y, int count) 									// Constructor override
		{
			xCordinate = x;
			yCordinate =y;
			//blockCount = count;
		}
		
		public Block(int x, int y) 												// Constructor override
		{
			xCordinate = x;
			yCordinate =y;
			//blockCount = 1;
		}
		
		//public void add1Count() {blockCount++;}
		
		public int[] getCoordinate()												// Return the coordinate of the block
		{
			return new int[] {xCordinate ,yCordinate};
		}
		
		//public void hit() {blockCount--;}
}
