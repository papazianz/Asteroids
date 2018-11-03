import java.awt.Polygon;
import java.awt.Rectangle;

public class Railgun extends Polygon
{
   int screenW = Screen.screenWidth;
   int screenH = Screen.screenHeight;

   private double xCenter = 0, yCenter = 0;

   public static int[] railgunXPoints = {-3,3,3,-3,-3};
   public static int[] railgunYPoints = {-3,-3,3,3,-3};
   private int railgunWidth = 6, railgunHeight = 6;
   public boolean onScreen = false;
   private double flyingAngle = 0;
   private double xVelocity = 5, yVelocity = 5;
	
   public Railgun(double xCenter, double yCenter, double flyingAngle)
   {
   	
   	// Creates a Polygon by calling the super or parent class Polygon	
      super(railgunXPoints, railgunYPoints, 5);
   	
   	// Defines the center based on the vectors of
   	// the ships nose. flyingAngle is the same as ship
   	
      this.xCenter = xCenter;
      this.yCenter = yCenter;
      this.flyingAngle = flyingAngle;
   	
   	// Tells program to show the ship
   	
      this.onScreen = true;
   	
   	// Sets how quickly the railgun moves along the path defined
   	// by setXVelocity and setYVelocity
   	
      this.setXVelocity(this.railgunXFlyingAngle(flyingAngle)*10);
      this.setYVelocity(this.railgunYFlyingAngle(flyingAngle)*10);
   	
   }
	
	// Gets & sets the values for the x & y center of railgun
	
   public double getXCenter(){ 
      return xCenter; }
		
   public double getYCenter(){ 
      return yCenter; }
		
   public void setXCenter(double tempXCenter){ this.xCenter = tempXCenter; }
		
   public void setYCenter(double tempYCenter){ this.yCenter = tempYCenter; }
		
   public void changeXPos(double tempXPos) { this.xCenter += tempXPos; }
		
   public void changeYPos(double tempYPos) { this.yCenter += tempYPos; }
	
	// Gets, sets, the railgun velocity
	
   public double getXVelocity(){ 
      return xVelocity; }
		
   public double getYVelocity(){ 
      return yVelocity; }
		
   public void setXVelocity(double xVel){ this.xVelocity = xVel; }
		
   public void setYVelocity(double yVel){ this.yVelocity = yVel; }
	
	// Gets & sets the x & y for upper left hand corner of ship
	
   public int getWidth()
   { 
      return railgunWidth; 
   }
   public int getHeight()
   { 
      return railgunHeight; 
   }
	
	// Set and increase the railgun movement angle
	
   public void setFlyingAngle(double flyingAngle)
   {
      this.flyingAngle = flyingAngle;
   }
		
   public double getFlyingAngle()
   { 
      return flyingAngle; 
   }
	
	// Artificial rectangle that is used for collision detection
	
   public Rectangle setBounds()
   {
      return new Rectangle((int) getXCenter() - 6, (int) getYCenter() - 6, getWidth(), getHeight());
   		
   }
	
	// Calculate the railgun angle of movement
	
	// By taking the cos of the angle I get the adjacent value of x
	// Angle * Math.PI / 180 converts degrees into radians
		
   public double railgunXFlyingAngle(double xFlyingAngle){
   		
      return (double) (Math.cos(xFlyingAngle * Math.PI / 180));
   		
   }
		
	// By taking the sin of the angle I get the opposite value of y
	// Angle * Math.PI / 180 converts degrees into radians 
		
   public double railgunYFlyingAngle(double yFlyingAngle){
   		
      return (double) (Math.sin(yFlyingAngle * Math.PI / 180));
   		
   }
	
   public void move(){
   	
      if(this.onScreen){
      	
      	// Increase the x origin value based on current velocity
      	
         this.changeXPos(this.getXVelocity());
      
      // If the ship goes off the board flip it to the other side of the board
      
         if(this.getXCenter() < 0){
         
            this.onScreen = false;
         
         } else
            if (this.getXCenter() > screenW){
            
               this.onScreen = false;
            
            }
      
      // Increase the x origin value based on current velocity 
      
         this.changeYPos(this.getYVelocity());
      
      // If the ship goes off the board flip it to the other side of the board
      
         if(this.getYCenter() < 0){
         
            this.onScreen = false;
         
         } else
            if (this.getYCenter() > screenH){
            
               this.onScreen = false;
            
            }
      
      } // END OF onScreen CHECK
   	
   }

	
}
