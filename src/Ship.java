import java.awt.Polygon;
import java.awt.Rectangle;


/**
* Contains all of the properties for the ship, extending the Polygon class.
*
* @author Nick Papazian
* @since 9/20/2018
**/
public class Ship extends Polygon
{
   private static int screenW = Screen.getScreenWidth();
   private static int screenH = Screen.getScreenHeight();
      // Ships dimensions
   private int shipWidth = 28, shipHeight = 29;   
   
   // Sets the center of the Screen
   private double xCenter = screenW / 2, yCenter = screenH / 2;
   private double xVelocity = 0, yVelocity = 0;
   
   /** Holds the position of the ship. **/ 
   private static int[] shipXPoints = {-16, 11, -16, -8, -16}, 
         shipYPoints = {-18, -3, 12, -3, -18};
   
   /** Holds the angle the ship is facing while still or moving. **/
   private double sittingAngle = 0, flyingAngle = 0;

   // Sets the ships position
   private double shipULXPos = getXCenter() + this.shipXPoints[0];
   private double shipULYPos = getYCenter() + this.shipYPoints[0];
   

/** Constructs the ship via Polygon constructor, using the ship coordinates. **/
   public Ship()
   {
      super(shipXPoints, shipYPoints, 5);
   }
   
        /** @return screenWidth **/
   public static int getScreenWidth()
   {
      return screenW;
   }
   
   /** @return screenHeight  **/
   public static int getScreenHeight()
   {
      return screenH;
   }
   
   /** @return xCenter **/
   public double getXCenter()
   { 
      return xCenter; 
   }
   
   /** @return yCenter **/
   public double getYCenter()
   { 
      return yCenter; 
   }
   
   /**
   * Sets the x-coordinate center of the ship. 
   * @param tempCenterX 
   **/
   public void setXCenter(double tempCenterX)
   { 
      this.xCenter = tempCenterX;
   }
   
    /**
   * Sets the y-coordinate center of the ship. 
   * @param tempCenterY 
   **/
   public void setYCenter(double tempCenterY)
   { 
      this.yCenter = tempCenterY;
   }


   /**
   * Increases the x-coordinate center of the ship. 
   * @param incXPos 
   **/
   public void increaseXPos(double incXPos) 
   { 
      this.xCenter += incXPos;
   }
   
   /**
   * Increases the y-coordinate center of the ship. 
   * @param incYPos 
   **/
   public void increaseYPos(double incYPos) 
   {
      this.yCenter += incYPos; 
   }  
   
   /**
   * Gets the x-coordinate of the ships upper left corner. 
   * @return shipULXPos 
   **/  
   public double getshipULXPos()
   { 
      return shipULXPos; 
   }
   
    /**
   * Gets the y-coordinate of the ships upper left corner. 
   * @return shipULXPos 
   **/  
   public double getshipULYPos()
   { 
      return shipULYPos; 
   }
   
    /**
   * Sets the x-coordinate of the ships upper left corner. 
   * @param xULPos 
   **/  
   public void setshipULXPos(double xULPos)
   { 
      this.shipULXPos = xULPos; 
   }
   
   /**
   * Sets the y-coordinate of the ships upper left corner. 
   * @param yULPos 
   **/  
   public void setshipULYPos(double yULPos)
   { 
      this.shipULYPos = yULPos; 
   }
   
 
   /** @return shipWidth  **/
   public int getShipWidth()
   { 
      return shipWidth; 
   }
   
   /** @return shipHeight  **/
   public int getShipHeight()
   { 
      return shipHeight;
   }
   

  /**
   * Gets the x-direction velocity of the ship. 
   * @return xVelocity  
   **/  
   public double getXVelocity()
   { 
      return xVelocity; 
   }
   
   /**
   * Gets the y-direction velocity of the ship. 
   * @return xVelocity  
   **/  
   public double getYVelocity()
   { 
      return yVelocity; 
   }
   
   /**
   * Sets the x-direction velocity of the ship. 
   * @param tempXV   
   **/  
   public void setXVelocity(double tempXV)
   {
      this.xVelocity = tempXV;
   }
   
   /**
   * Sets the y-direction velocity of the ship. 
   * @param tempYV   
   **/  
   public void setYVelocity(double tempYV)
   {
      this.yVelocity = tempYV;
   }
   
   /**
   * Increases the x-direction velocity of the ship. 
   * @param tempXInc    
   **/  
   public void increaseXVelocity(double tempXInc)
   { 
      this.xVelocity += tempXInc; 
   }
   
   /**
   * Increases the y-direction velocity of the ship. 
   * @param tempYInc     
   **/  
   public void increaseYVelocity(double tempYInc)
   { 
      this.yVelocity += tempYInc; 
   }
   
   /**
   * Decreases the y-direction velocity of the ship. 
   * @param tempXDec      
   **/  
   public void decreaseXVelocity(double tempXDec)
   { 
      this.xVelocity -= tempXDec;
   }
   
   /**
   * Decreases the y-direction velocity of the ship. 
   * @param tempYDec       
   **/  
   public void decreaseYVelocity(double tempYDec)
   { 
      this.yVelocity -= tempYDec;
   }

   /**
   * Sets the angle of the ship while its moving.
   * @param flyingAngle 
   **/
   public void setFlyingAngle(double flyingAngle)
   { 
      this.flyingAngle = flyingAngle; 
   }
   
    /**
   * Gets the angle of the ship while its moving.
   * @return flyingAngle 
   **/
   public double getFlyingAngle()
   { 
      return flyingAngle; 
   }
   
    /**
   * Increases the angle of the ship while its moving.
   * @param flyingAngle 
   **/
   public void increaseFlyingAngle(double flyingAngle)
   { 
      this.flyingAngle += flyingAngle;
   }
   
   /**
   * Gets the x-coordinate of the ships flying angle,
   * and converts it into radians so it can be used.
   * @param xFlyingAngle 
   * @return xFlyingAngle 
   **/
   public double shipXFlyingAngle(double xFlyingAngle)
   {
      return (double) (Math.cos(xFlyingAngle * Math.PI / 180));
   }
   
   /**
   * Gets the y-coordinate of the ships flying angle,
   * and converts it into radians so it can be used.
   * @param yFlyingAngle 
   * @return yxFlyingAngle 
   **/
   public double shipYFlyingAngle(double yFlyingAngle)
   {
      return (double) (Math.sin(yFlyingAngle * Math.PI / 180));
   }
   
   
      /**
   * Gets the angle the ship is facing while still. 
   * @return sittingAngle 
   **/  
   public double getSittingAngle()
   { 
      return sittingAngle; 
   }
   
    /**
   * Increases the angle the ship is facing while still by 5 degrees,
   * but keeps it within 360 degrees.  
   **/   
   public void increaseSittingAngle()
   { 
      if (getSittingAngle() >= 355) 
      { 
         sittingAngle = 0; 
      }
      else
      { 
         sittingAngle += 5; 
      }
   }
   
   /**
   * Decreases the angle the ship is facing while still by 5 degrees,
   * but keeps it within 360 degrees.  
   **/   
   public void decreaseSittingAngle()
   { 
      if (getSittingAngle() < 0) 
      { 
         sittingAngle = 355; 
      }
      else { 
         sittingAngle -= 5; 
      }
   }
   


   /**
   * Sets rectangle around the ship to manage its collision with rocks.
   * @return Rectangle 
   **/
   public Rectangle setBounds()
   {
      return new Rectangle((int) getXCenter() - 16, (int) getYCenter() - 16, 
            getShipWidth(), getShipHeight());
   }
   
   /**
   * Gets the x-coordinate for the ships tip.
   * Formula written out: x + cos(angle) * length of line
   * @return ShipTipX 
   **/
   public double getShipTipX()
   {
      return this.getXCenter() + Math.cos(sittingAngle) * 14;
   }
   
  /**
   * Gets the y-coordinate for the ships tip.
   * Formula written out: x + sin(angle) * length of line
   * @return ShipTipY 
   **/
   public double getShipTipY()
   {
      return this.getYCenter() + Math.sin(sittingAngle) * 14;
   }
   
    /**
   * Allows the ship to move around the screen.
   * Positions the ship on the opposite of the board if it goes over the edge.
   **/
   public void move()
   {
      
      // Increase the x and y positions based on given velocity
      this.increaseXPos(this.getXVelocity());
      this.increaseYPos(this.getYVelocity());
   
      
      if (this.getXCenter() < 0)
      {
         this.setXCenter(screenW);
      } 
      else
         if (this.getXCenter() > screenW)
         {
            this.setXCenter(0);  
         }
         
      if (this.getYCenter() < 0)
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
