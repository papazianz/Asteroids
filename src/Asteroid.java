import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


/**
* Contains the asteroids properties, extending the Polygon class.
*
* @author Nick Papazian
* @since 9/20/2018
**/
public class Asteroid extends Polygon
{
   private int asteroidULXPos, asteroidULYPos;
   private int xDirection = 1;
   private int yDirection = 1;
   private int asteroidWidth = 26;
   private int asteroidHeight = 31;
   private int width = Screen.getScreenWidth();
   private int height = Screen.getScreenHeight();
   
   /** Monitors if the reference object is on the screen. **/
   private boolean onScreen = true;   
   
   // x and y coordinates for the location of each asteroid
   private int[] asteroidXPoints, asteroidYPoints;
   
   /** An Arraylist that hold all of the asteroid objects. **/
   public static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

   /** Array's that hold all of the starting coordinates for the asteroids. **/
   public static int[] sAsteroidXPoints = {10, 17, 26, 34, 27, 36, 26, 14, 8, 1, 5, 1, 10},
      sAsteroidYPoints = {0, 5, 1, 8, 13, 20, 31, 28, 31, 22, 16, 7, 0};

   


   /** 
   * Constructs the asteroid by calling the Polygon constructor via super().
   * Also sets starting positions.
   * @param asteroidXPoints 
   * @param asteroidYPoints 
   * @param pointInPoly 
   * @param randomStartXPos 
   * @param randomStartYPos 
   **/
   public Asteroid(int[] asteroidXPoints, int[] asteroidYPoints, 
          int pointInPoly, int randomStartXPos, int randomStartYPos)
   {
      super(asteroidXPoints, asteroidYPoints, pointInPoly);
      
   
      // Moves the asteroid in a random direction
      this.xDirection = (int) (Math.random() * 4 + 1);
      this.yDirection = (int) (Math.random() * 4 + 1);
   
      // Sets starting point for asteroid
      this.asteroidULXPos = randomStartXPos;
      this.asteroidULYPos = randomStartYPos;
   }
   
   /** 
   * Uses the Rectangle class to set collision box around the object. 
   * @return Rectangle
   **/
   public Rectangle setBounds()
   {
      return new Rectangle(super.xpoints[0], super.ypoints[0], 
            asteroidWidth, asteroidHeight);
   }

   /**
   * Sets onScreen.
   * @param set 
   * @return onScreen 
   **/
   public boolean setOnScreen(boolean set)
   {
      onScreen = set;
      return onScreen;
   }
   
   /**
   * Gets onScreen.
   * @return onScreen 
   **/
   public boolean getOnScreen()
   {
      return onScreen;
   }
   
     /**
   * Gets the array containing the starting Asteroid X points.
   * @return sAsteroidXPoints 
   **/
   public static int[] getSAsteroidXPoints()
   {
      return sAsteroidXPoints;
   }
   
      /**
   * Gets the array containing the starting Asteroid Y points.
   * @return sAsteroidYPoints 
   **/     
   public static int[] getSAsteroidYPoints()
   {
      return sAsteroidYPoints;
   }
   
   /** 
   * Sets collision boxed around the ship, asteroid, and railgun bullets.  
   * Determines what happens when an object collides with each other, 
   * also moves the asteroid.
   * @param battleShip  
   * @param railguns 
   **/
   public void move(Ship battleShip, ArrayList<Railgun> railguns) 
   {
      //Sets a rectangle around an asteroid to use as a base to check against
      Rectangle baseAsteroid = this.setBounds();
      
      for (Asteroid asteroid: asteroids)
      {
      
         if (asteroid.getOnScreen())
         {
         
         
         //Creates a rectangle around the rest of the asteroids
            Rectangle otherAsteroid = asteroid.setBounds();
         
         
         // Checks asteroids against each other
            if (asteroid != this && otherAsteroid.intersects(baseAsteroid))
            {
               int tempX = this.xDirection;
               int tempY = this.yDirection;
            
               this.xDirection = asteroid.xDirection;
               this.yDirection = asteroid.yDirection;
            
               asteroid.xDirection = tempX;
               asteroid.yDirection = tempY;
            }
            
            Rectangle shipBox = Screen.getShip().setBounds();
            
            if (otherAsteroid.intersects(shipBox))
            {
               Screen.getShip().setXCenter(Screen.getShip().getScreenWidth() / 2);
               Screen.getShip().setYCenter(Screen.getShip().getScreenHeight() / 2);
               
               Screen.getShip().setXVelocity(0);
               Screen.getShip().setYVelocity(0);
            }
            for (Railgun railgun : railguns)
            {
               if (railgun.getOnScreen())
               {
               // Checks if a torpedo hits a Rock
                  if (otherAsteroid.contains(railgun.getXCenter(), 
                     railgun.getYCenter()))
                  {
                     asteroid.setOnScreen(false);
                     railgun.setOnScreen(false);
                     System.out.println("HIT"); 
                  }
               }  
            }
         }
      }
   
      // Places the coordinates of each asteroid in the array of polygon coordinates
      int asteroidULXPos = super.xpoints[0];
      int asteroidULYPos = super.ypoints[0];
   
      // reverses the direction of asteroid as it hits the edge 
      if (asteroidULXPos < 0 || (asteroidULXPos + 35 > width))
      {
         xDirection = -xDirection;
      }
      if (asteroidULYPos < 0 || (asteroidULYPos + 50 > height))
      {
         yDirection = -yDirection;
      }
   
      // Constantly moves the asteroids by adding 
      // the random direction to the x and y coordinates
      for (int i = 0; i < super.xpoints.length; i++)
      {
         super.xpoints[i] += xDirection;
         super.ypoints[i] += yDirection;
      }          
   }

   /**
   * Gets a list of all the Y positions of each asteroid by 
   * cloning the original list, and adding the asteroids starting position.
   * @param randomStartXPos 
   * @return tempAsteroidXArray 
   **/
   public static int[] getAsteroidXArray(int randomStartXPos)
   {
      int[] tempAsteroidXArray = (int[]) sAsteroidXPoints.clone();
   
      for (int i = 0; i < tempAsteroidXArray.length; i++)
      {
         tempAsteroidXArray[i] += randomStartXPos;
      }
      return tempAsteroidXArray;
   }

   /**
   * Gets a list of all the Y positions of each asteroid by 
   * cloning the original list, and adding the asteroids starting position.
   * @param randomStartYPos 
   * @return tempAsteroidYArray 
   **/
   public static int[] getAsteroidYArray(int randomStartYPos)
   {
      int[] tempAsteroidYArray = (int[]) sAsteroidYPoints.clone();
   
      for (int i = 0; i < tempAsteroidYArray.length; i++)
      {
         tempAsteroidYArray[i] += randomStartYPos;
      }
      return tempAsteroidYArray;    
   }

}
