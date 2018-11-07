import java.awt.Polygon;
import java.awt.Rectangle;


/**
* Contains all of the properties for the railgun, extending the Polygon class.
*
* @author Nick Papazian
* @since 9/20/2018
**/
public class Railgun extends Polygon
{
   private int screenW = Screen.getScreenWidth();
   private int screenH = Screen.getScreenHeight();
   private double xCenter = 0, yCenter = 0;
   
   /** 
   * An int Array that holds the x and y coordinates 
   * for the Polygon construction of each bullet.
   **/
   public static int[] railgunXPoints = {-3, 3, 3, -3, -3}, railgunYPoints = {-3, -3, 3, 3, -3};
   
   private int railgunWidth = 6, railgunHeight = 6;
   private boolean onScreen = false;
   private double flyingAngle = 0;
   private double xVelocity = 5, yVelocity = 5;
   
   
   /**
   * Constructs the railgun bullet via Polygon constructor, 
   * using the railguns coordinates and ships flying angle.
   * @param xCenter 
   * @param yCenter 
   * @param flyingAngle 
    **/
   public Railgun(double xCenter, double yCenter, double flyingAngle)
   {
      super(railgunXPoints, railgunYPoints, 5);
      
      // Defines the center based on the coordinates of the ships tip.
      //FlyingAngle is the same as ship.
      this.xCenter = xCenter;
      this.yCenter = yCenter;
      this.flyingAngle = flyingAngle;
      
      // Tells program to show the ship
      this.onScreen = true;
      
      // Sets how quickly the railgun moves along the path defined
      // by setXVelocity and setYVelocity
      this.setXVelocity(this.railgunXFlyingAngle(flyingAngle) * 10);
      this.setYVelocity(this.railgunYFlyingAngle(flyingAngle) * 10);
      
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
   * Sets the x-coordinate center of the railgun. 
   * @param tempXCenter 
   **/  
   public void setXCenter(double tempXCenter)
   {
      this.xCenter = tempXCenter; 
   }
   
   /**
   * Sets the y-coordinate center of the railgun. 
   * @param tempYCenter 
   **/   
   public void setYCenter(double tempYCenter)
   { 
      this.yCenter = tempYCenter; 
   }
      
    /**
   * Moves the overall x-position of the railgun. 
   * @param tempXPos 
   **/   
   public void changeXPos(double tempXPos) 
   { 
      this.xCenter += tempXPos; 
   }
      
      /**
   * Moves the overall y-position of the railgun. 
   * @param tempYPos 
   **/   
   public void changeYPos(double tempYPos) 
   { 
      this.yCenter += tempYPos; 
   }
   
    /** @return xVelocity **/ 
   public double getXVelocity()
   { 
      return xVelocity; 
   }
   
   /** @return yVelocity **/  
   public double getYVelocity()
   { 
      return yVelocity; 
   }
      
    /**
   * Sets the x-direction velocity of the railgun. 
   * @param xVel   
   **/   
   public void setXVelocity(double xVel)
   { 
      this.xVelocity = xVel; 
   }
   
   /**
   * Sets the y-direction velocity of the railgun. 
   * @param yVel   
   **/   
   public void setYVelocity(double yVel)
   { 
      this.yVelocity = yVel; 
   }
   
   /** @return railgunWidth **/
   public int getWidth()
   { 
      return railgunWidth; 
   }
   
   /** @return railgunWidth **/
   public int getHeight()
   { 
      return railgunHeight; 
   }
   
 /**
   * Sets the angle of the railgun bullet while its moving.
   * @param flyingAngle 
   **/
   public void setFlyingAngle(double flyingAngle)
   {
      this.flyingAngle = flyingAngle;
   }
   
   /**
   * Gets the angle of the railgun bullet while its moving.
   * @return flyingAngle 
   **/   
   public double getFlyingAngle()
   { 
      return flyingAngle; 
   }
   
   /**
   * Creates a rectangle that is used for collision detection.
   * @return Rectangle 
   **/
   public Rectangle setBounds()
   {
      return new Rectangle((int) getXCenter() - 6, 
            (int) getYCenter() - 6, getWidth(), getHeight());
         
   }
   
   
   /**
   * Gets the x-coordinate of the railguns flying angle,
   * and converts it into radians so it can be used.
   * @param xFlyingAngle 
   * @return xFlyingAngle 
   **/   
   public double railgunXFlyingAngle(double xFlyingAngle)
   {
      return (double) (Math.cos(xFlyingAngle * Math.PI / 180));
   }
      
    /**
   * Gets the y-coordinate of the railguns flying angle,
   * and converts it into radians so it can be used.
   * @param yFlyingAngle 
   * @return yFlyingAngle 
   **/      
   public double railgunYFlyingAngle(double yFlyingAngle)
   {
      return (double) (Math.sin(yFlyingAngle * Math.PI / 180));
   }
   
   
   /**
   * Allows the railgun bullet to travel across 
   * If the railguns bullet goes over the edge, it will not be seen. 
   **/
   public void move()
   {
      if (this.onScreen)
      {
         // Increase the x and y origin value based on current velocity
         this.changeXPos(this.getXVelocity());
         this.changeYPos(this.getYVelocity());
         
         if (this.getXCenter() < 0)
         {
            this.onScreen = false;
         } 
         else
            if (this.getXCenter() > screenW)
            {
               this.onScreen = false;
            }
      
         if (this.getYCenter() < 0)
         {
            this.onScreen = false;
         } 
         else
            if (this.getYCenter() > screenH)
            {
               this.onScreen = false;
            }
      }
      
   }
   
   /** 
   * Changes the visibility of the railgun on screen.
   * @param set 
   * @return onScreen
   **/
   public boolean setOnScreen(boolean set)
   {
      return onScreen = set;
   }
   
    /** 
   * Checks the visibility of the railgun on screen.
   * @return onScreen 
   **/
   public boolean getOnScreen()
   {
      return onScreen;
   }
   
      
}
