

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Game extends JComponent implements ActionListener{
	static ArrayList<Block> blocks = new ArrayList<Block>(); 				// This is an arraylist of all the existing blocks
	static int marginx = 1280, marginy = 720;									//This is the length and width of the actual window
	private static int angle;
	private Timer myTimer = new Timer(10,this);
	private static boolean just = false;
	
	public Game() 																			//This is the constructor of the class. It define the behavior of the class when it is first created
	{																								// This constructor will generate the blocks and add them to the blocks ArrayList.
		int randomNumber1, randomNumber2;
		boolean hasSame = false;
		 
		//Create two array for the possible location of the blocks
		int[] xCordinates = new int[10];
		for (int i = 0; i<10; i++) { xCordinates[i] = 128 * i; }
		int[] yCordinates = new int[5];
		for (int i = 0; i<5; i++) { yCordinates[i] = 360+72 * i; }	
		
		// Use for loop to generate random index, find the element of the corresponding index in the array created above to be a position.
		// then create a new brick with the selected location and add it into the arraylist.
		for(int i = 0; i<80; i++)
		{
			randomNumber1 = (int)(Math.random()*10);
			randomNumber2 = (int)(Math.random()*5);
			hasSame = false;			
			
			//prevent adding a block to the arraylist when the location is already occupied by another block in the arraylist.
			for(int k = 0; k<blocks.size(); k++) 
			{
				if (blocks.get(k).getCoordinate()[0]== xCordinates[randomNumber1] && blocks.get(k).getCoordinate()[1] == yCordinates[randomNumber2]) 
				{
					//blocks.get(k).add1Count();
					hasSame = true;
				}
			}
			if (!hasSame)
			{
				blocks.add(new Block(xCordinates[randomNumber1], yCordinates[randomNumber2]));
			}
		}
		run();																						// call the method that begin the game
	}

	
	// Start the timer. every 10 milliseconds the as defined in the beginning of the class, it will call the actionPerformed method below.
	// By this way is the animation of the ball movement created.
	private void run() 
	{
		myTimer.start();																		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Plate.setCordinate(MouseInfo.getPointerInfo().getLocation().x, 36);		// set the location of the plate to the position of the mouse
		Ball.move();																				// move the ball one step
		angle = boundingCheck(Ball.getCoordinate());									// call boundingCheck method to see if the ball collides with any 
																										// other components in the window/
		System.out.print( angle + "   ");
		if (angle != -1) 																			// if the ball truly collides with a component, change the angle of the ball
			Ball.bounce(angle);
		
		if (blocks.size() == 0)																	// stop the game if all bricks are destroyed or the user miss the ball
			gameFinish();
		else if (Ball.getCoordinate()[1] <= 36)
			gameFinish();
		
		System.out.println(Ball.getAngle());
		repaint();																					// paint the elements to the window
	}
	
	public void paintComponent(Graphics g) {						//This method draw the components to the window
		super.paintComponent(g);
		
		for(Block item:blocks) {
			g.setColor(Color.BLUE);
			g.drawRect(item.getCoordinate()[0], item.getCoordinate()[1], item.length,item .width );
		}
		//draw the ball
		g.setColor(Color.BLACK);
		g.drawOval((int)Ball.getCoordinate()[0] -Ball.RADIUS, (int)Ball.getCoordinate()[1]-Ball.RADIUS, 2*Ball.RADIUS, 2*Ball.RADIUS);
		// draw the plate
		g.setColor(Color.RED);
		g.drawRect(Plate.getCordinate()[0]-Plate.getdimension()[0]/2, Plate.getCordinate()[1]-Plate.getdimension()[1]/2,	Plate.getdimension()[0],Plate.getdimension()[1]);
	}
				
	private static int boundingCheck(double[] ballPosition) 			
		{
			//These variables represent the coordinates of the center of the object.
			int  x = -1, blockxPos, blockyPos; 
			double xPos, yPos;
			xPos = ballPosition[0];
			yPos = ballPosition[1];
			
			// Check if the ball is overlapping with an bricks, if so, bounce the ball and remove the brick
			for(int m = 0; m< blocks.size(); m++) 
			{
				blockxPos = blocks.get(m).getCoordinate()[0];
				blockyPos = blocks.get(m).getCoordinate()[1];
				if((Ball.getCoordinate()[0]) >= blockxPos && Ball.getCoordinate()[0]<=blockxPos+128)
				{
					if(Ball.getCoordinate()[1] + Ball.RADIUS >= blockyPos && Ball.getCoordinate()[1] < blockyPos+18 ) 
					{
						blocks.remove(m);
						return 3;
					}
					else if(Ball.getCoordinate()[1] - Ball.RADIUS <= blockyPos+36 && Ball.getCoordinate()[1] > blockyPos+18 ) 
					{
						blocks.remove(m);
						return 1;
					}
				}
				if (Ball.getCoordinate()[1] > blockyPos && Ball.getCoordinate()[1] < blockyPos + 36)
				{
					if (Ball.getCoordinate()[0] +Ball.RADIUS >blockxPos && Ball.getCoordinate()[0] < blockxPos +64 ) 
					{
						blocks.remove(m);
						return 0;
					}
					else if (Ball.getCoordinate()[0] - Ball.RADIUS <blockxPos + 128 && Ball.getCoordinate()[0] > blockxPos + 64) 
					{
						blocks.remove(m);
						return 2;
					}
				}
			}
			
			//check if the ball is bouncing on the edge of screen
			if(xPos>marginx-Ball.RADIUS)
				return 0;
			else if (xPos<Ball.RADIUS)
				return 2;
			else if (yPos>marginy - Ball.RADIUS)
				return 1;
			
			int plateXPos = Plate.getCordinate()[0];
			int plateYPos = Plate.getCordinate()[1];
			//check if the ball is bouncing on the plate. SKip 1 check if the ball just bounce off the plate
			if( xPos >= plateXPos - 64 && xPos <= plateXPos + 64 && yPos - Ball.RADIUS <= plateYPos + 3 && !just)
			{
				Ball.bounce(3);
				// add offset to angle depending on the relative position of ball and plate
				angle = Ball.getAngle() - (int)((xPos - plateXPos)/64 *1.5 * 45);
				if (angle>160)
					angle = 160;
				else if (angle < 20)
					angle = 20;
					
				just = true;
				Ball.setAngle(angle);
				return x;
			}
			else 
				just = false;
			
			return x;
		}
	
	private void gameFinish() 
		{
			myTimer.stop();
			System.out.println("Game Finished");
		}
}
