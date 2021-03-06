import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


/**
* Contains the asteroids properties, extending the Polygon class.
*
* @author Nick Papazian
* @since 9/20/2018
**/
public class BigRock extends Polygon
{
   public int asteroid_PLL, asteroid_PY;
   private int x_d = 1;
   private int y_d = 1;
   private int asteroid_W = 26;
   private int asteroid_H = 31;
   private int width = Screen.getScreenWidth();
   private int height = Screen.getScreenHeight();
   protected static int asteriodCount = 20;
   
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
      this.x_d = (int) (Math.random() * 4 + 1);
      this.y_d = (int) (Math.random() * 4 + 1);
   
      // Sets starting point for asteroid
      this.asteroid_PLL = randomStartXPos;
      this.asteroid_PY = randomStartYPos;
   }
   
   /** 
   * Uses the Rectangle class to set collision box around the object. 
   * @return Rectangle
   **/
   public Rectangle setBounds()
   {
      return new Rectangle(super.xpoints[0], super.ypoints[0], 
            asteroidWidth + 2, asteroidHeight + 2);
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
               int tempX = this.x_d;
               int tempY = this.y_d;
            
               this.x_d = asteroid.x_d;
               this.y_d = asteroid.y_d;
            
               asteroid.x_d = tempX;
               asteroid.y_d = tempY;
            }
            
            Rectangle shipBox = Screen.getShip().setBounds();
            
            if (otherAsteroid.intersects(shipBox))
            {
               Screen.getShip().setXCenter(Screen.getShip().getScreenWidth() / 2);
               Screen.getShip().setYCenter(Screen.getShip().getScreenHeight() / 2);
               
               Screen.getShip().setXVelocity(0);
               Screen.getShip().setYVelocity(0);
               Screen.scoreCount = 0;
               Screen.score.setText("Score: " + Screen.scoreCount);
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
                     Screen.scoreCount += 10; 
                     Screen.score.setText("Score: " + Screen.scoreCount);
                     System.out.println("HIT"); 
                     
                     Asteroid.asteriodCount -= 1;
                     Screen.asteroidCounter.setText("Asteroids Remaining: " + Asteroid.asteriodCount);
                  }
               }  
            }
         }
      }
   
      // Places the coordinates of each asteroid in the array of polygon coordinates
      int asteroid_PLL = super.xpoints[0];
      int asteroid_PY = super.ypoints[0];
   
      // reverses the direction of asteroid as it hits the edge 
      if (asteroid_PLL < 0 || (asteroid_PLL + 35 > width))
      {
         x_d = -x_d;
      }
      if (asteroid_PY < 0 || (asteroid_PY + 50 > height))
      {
         y_d = -y_d;
      }
   
      // Constantly moves the asteroids by adding 
      // the random direction to the x and y coordinates
      for (int i = 0; i < super.xpoints.length; i++)
      {
         super.xpoints[i] += x_d;
         super.ypoints[i] += y_d;
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
