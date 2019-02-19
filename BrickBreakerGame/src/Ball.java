

public class Ball {
		private static int  steplength = 6; 
		private static double xCordinate = 640, yCordinate = 57, angle = 80;	// These are the coordinates of the ball's geometric center!!(important)
		public static final int RADIUS = 10;
		
		public static void setAngle(int a) 														// Set the direction of the ball, used when the ball is bounced on the plate
		{
			angle = a;
		}
		
		public static int getAngle() 
		{
			return (int)angle;
		}
		
		public static  double[] move() 															// make the ball move 1 step toward the certain direction
		{
			xCordinate += steplength * Math.cos(Math.toRadians(angle));
			yCordinate += steplength * Math.sin(Math.toRadians(angle));
			return new double[] {xCordinate, yCordinate};
		}
		
		public static double[] getCoordinate() 
		{
			return new double[] {xCordinate, yCordinate };
		}
		
		public static void bounce(int normalLineAngle) 										// Change the direction of the ball when it is bounced on the wall
		{																											// the parameter is the type of the normal line of bouncing..  
			if (normalLineAngle == 0 || normalLineAngle == 2)								// 0 and 2 means the ball is bouncing on a vertical wall
			{angle =180 - angle;}																			
			else if (normalLineAngle == 1 || normalLineAngle == 3) 						// 1 and 3 means the ball is bouncing on a horizontal wall
			{angle = -angle;}
			if (angle<0)
				angle = angle % 360 + 360;
			else 
				angle = angle % 360;
		}
		
		
		
}
