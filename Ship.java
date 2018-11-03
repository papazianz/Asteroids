import java.awt.*;


public class Ship extends Polygon{

   
   int screenW = Screen.screenWidth;
   int screenH = Screen.screenHeight;
   
   // Sets the center of the Screen
   private double xCenter = screenW / 2, yCenter = screenH /2 ;

   private double xVelocity = 0, yVelocity = 0;
	
   // Holds the postion of the ship	
   public static int[] shipXPoints = {-16,11,-16,-8,-16};
   public static int[] shipYPoints = {-18,-3,12,-3,-18};
	
   // Ships dimensions
   private int shipWidth = 28, shipHeight = 29;
   
	// Sets the ships postion
   private double shipULXPos = getXCenter() + this.shipXPoints[0];
   private double shipULYPos = getYCenter() + this.shipYPoints[0];
	

   public double sittingAngle = 0, flyingAngle = 0;
	
	// Builds the ship
   public Ship()
   {
      super(shipXPoints, shipYPoints, 5);
   }
   
	// Gets & sets the values for the x & y center of ship
   public double getXCenter()
   { 
      return xCenter; 
   }
   public double getYCenter()
   { 
      return yCenter; 
   }
   public void setXCenter(double tempCenterX)
   { 
      this.xCenter = tempCenterX;
   }
   public void setYCenter(double tempCenterY)
   { 
      this.yCenter = tempCenterY;
   }


   // Used to move the ship with its co
   public void increaseXPos(double incXPos) 
   { 
      this.xCenter += incXPos;
   }
   public void increaseYPos(double incYPos) 
   {
      this.yCenter += incYPos; 
   }	
	
	// Gets & sets the x & y for upper left hand corner of ship
   public double getshipULXPos()
   { 
      return shipULXPos; 
   }
   public double getshipULYPos()
   { 
      return shipULYPos; 
   }
   public void setshipULXPos(double xULPos)
   { 
      this.shipULXPos = xULPos; 
   }
   public void setshipULYPos(double yULPos)
   { 
      this.shipULYPos = yULPos; 
   }
	
 
	// Gets & sets the x & y for upper left hand corner of ship
   public int getShipWidth()
   { 
      return shipWidth; 
   }
   public int getShipHeight()
   { 
      return shipHeight;
   }
	

  // Manages the velocity of the ship 
   public double getXVelocity()
   { 
      return xVelocity; 
   }
   public double getYVelocity()
   { 
      return yVelocity; 
   }
   public void setXVelocity(double tempXV)
   {
      this.xVelocity = tempXV;
   }
   public void setYVelocity(double tempYV)
   {
      this.yVelocity = tempYV;
   }
   public void increaseXVelocity(double tempXInc)
   { 
      this.xVelocity += tempXInc; 
   }
   public void increaseYVelocity(double tempYInc)
   { 
      this.yVelocity += tempYInc; 
   }
   public void decreaseXVelocity(double tempXDec)
   { 
      this.xVelocity -= tempXDec;
   }
   public void decreaseYVelocity(double tempYDec)
   { 
      this.yVelocity -= tempYDec;
   }

   // Manages the angle of the ship when moving
   public void setFlyingAngle(double flyingAngle)
   { 
      this.flyingAngle = flyingAngle; 
   }
   public double getFlyingAngle()
   { 
      return flyingAngle; 
   }
   public void increaseFlyingAngle(double flyingAngle)
   { 
      this.flyingAngle += flyingAngle;
   }
	
   public double shipXFlyingAngle(double xFlyingAngle)
   {
      return (double) (Math.cos(xFlyingAngle * Math.PI / 180));
   }
   public double shipYFlyingAngle(double yFlyingAngle)
   {
      return (double) (Math.sin(yFlyingAngle * Math.PI / 180));
   }
	
	
	// Manages the rotation of the ship
   public double getSittingAngle()
   { 
      return sittingAngle; 
   }
		
   public void increaseSittingAngle()
   { 
      if(getSittingAngle() >= 355) 
      { 
         sittingAngle = 0; 
      }
      else
      { 
         sittingAngle += 5; 
      }
   }
	
   public void decreaseSittingAngle()
   { 
      if(getSittingAngle() < 0) 
      { 
         sittingAngle = 355; 
      }
      else { 
         sittingAngle -= 5; 
      }
   }
	


	// Sets rectangle around the ship to manage its collision with rocks
   public Rectangle setBounds(){
   	
      return new Rectangle((int) getXCenter() - 16, (int) getYCenter() - 16, getShipWidth(), getShipHeight());
   	
   }
   
   // Formula written out: x + cos(angle) * leangth of line
   public double getShipTipX()
   {
      return this.getXCenter() + Math.cos(sittingAngle) * 14;
   
   }
   
  // Formula written out: x + sin(angle) * leangth of line
   public double getShipTipY()
   {
      return this.getYCenter() + Math.sin(sittingAngle) * 14;
   
   }
   
   public void move(){
   	
   	// Increase the x and y postions based on given velocity
   	
      this.increaseXPos(this.getXVelocity());
      this.increaseYPos(this.getYVelocity());
   
   	
   	// Postions the ship onm the opposide of the board if it goes over the edge
   	
      if(this.getXCenter() < 0)
      {
         this.setXCenter(screenW);
      } 
      else
         if (this.getXCenter() > screenW)
         {
            this.setXCenter(0);	
         }
         
      if(this.getYCenter() < 0)
      {
         this.setYCenter(screenH);
      }
      else
         if (this.getYCenter() > screenH)
         {
            this.setYCenter(0);	
         }
   	
   }
	
	
}
