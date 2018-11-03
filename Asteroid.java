import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Asteroid extends Polygon
{
   int asteroidULXPos, asteroidULYPos;
   int xDirection = 1;
   int yDirection = 1;
   int asteroidWidth = 26;
   int asteroidHeight = 31;
   int width = Screen.screenWidth;
   int height = Screen.screenHeight;
   public boolean onScreen = true;

   
   // x and y cordinates for the location of each asteriod
   int[] asteroidXPoints, asteroidYPoints;
   
   static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

   // starting x and y ccordinates for the asteriods
   public static int[] sAsteroidXPoints = {10,17,26,34,27,36,26,14,8,1,5,1,10};
   public static int[] sAsteroidYPoints = {0,5,1,8,13,20,31,28,31,22,16,7,0};

   // Contructor for the asteroid 
   public Asteroid(int[] asteroidXPoints, int[] asteroidYPoints, int pointInPoly, int randomStartXPos, int randomStartYPos )
   {
      super(asteroidXPoints, asteroidYPoints, pointInPoly);
      
   
      // Moves the asteroid in a random direction
      this.xDirection = (int) (Math.random() * 4 + 1);
      this.yDirection = (int) (Math.random() * 4 + 1);
   
      // Sets starting point for astroid
      this.asteroidULXPos = randomStartXPos;
      this.asteroidULYPos = randomStartYPos;
   }
   
   public Rectangle setBounds()
   {
      return new Rectangle(super.xpoints[0], super.ypoints[0], asteroidWidth, asteroidHeight);
   }



   public void move(Ship battleShip, ArrayList<Railgun> railguns) 
   {
      //Sets a rectangle around a asteroid to use a base to check against
      Rectangle baseAsteroid = this.setBounds();
      
      for(Asteroid asteroid: asteroids)
      {
      
         if (asteroid.onScreen)
         {
         
         
         //Creats a rectangle around the rest of the asteroids
            Rectangle otherAsteroid = asteroid.setBounds();
         
         
         // Checks asteroids against each other
            if(asteroid != this && otherAsteroid.intersects(baseAsteroid))
            {
               int tempX = this.xDirection;
               int tempY = this.yDirection;
            
               this.xDirection = asteroid.xDirection;
               this.yDirection = asteroid.yDirection;
            
               asteroid.xDirection = tempX;
               asteroid.yDirection = tempY;
            }
            
            Rectangle shipBox = battleShip.setBounds();
            
            if (otherAsteroid.intersects(shipBox))
            {
               battleShip.setXCenter(battleShip.screenW/2);
               battleShip.setYCenter(battleShip.screenH/2);
               
               battleShip.setXVelocity(0);
               battleShip.setYVelocity(0);
            }
            for(Railgun railgun : railguns){
            	
            	// Make sure the Torpedo is on the screen
            	
               if(railgun.onScreen){
               
               	// NEW Check if a torpedo hits a Rock
               	
                  if(otherAsteroid.contains(railgun.getXCenter(),railgun.getYCenter())){
                  	
                     asteroid.onScreen = false;
                     railgun.onScreen = false;
                  	
                     System.out.println("HIT");
                  	
                  	// NEW play explosion sound if rock is destroyed
                  	
                  }
               
               }
            	
            }
         
         }
      }
   
      // Places the cordinates of each asteroid in the array of polygon cordinates
      int asteroidULXPos = super.xpoints[0];
      int asteroidULYPos = super.ypoints[0];
   
      // reverses the direction of astroid as it hits the edge 
      if (asteroidULXPos < 0 || (asteroidULXPos + 35 > width))
         xDirection = - xDirection;
      if (asteroidULYPos < 0 || (asteroidULYPos + 50 > height))
         yDirection = - yDirection;
   
      // Constenly moves the astroids by adding 
      // the random direction to the the x and y cordinates
      for (int i = 0; i < super.xpoints.length; i++)
      {
         super.xpoints[i] += xDirection;
         super.ypoints[i] += yDirection;
      }          
   }


   // Gets a list of all the x postions of each asteroid by 
   // cloning the orginal list, and adding the asteroids starting postion
   public static int[] getAsteroidXArray(int randomStartXPos)
   {
      int[] tempAsteroidXArray = (int[])sAsteroidXPoints.clone();
   
      for (int i = 0; i < tempAsteroidXArray.length; i++)
      {
         tempAsteroidXArray[i] += randomStartXPos;
      }
      return tempAsteroidXArray;
   }


   // Gets a list of all the y postions of each asteroid by 
   // cloning the orginal list, and adding the asteroids starting postion
   public static int[] getAsteroidYArray(int randomStartYPos)
   {
      int[] tempAsteroidYArray = (int[])sAsteroidYPoints.clone();
   
      for (int i = 0; i < tempAsteroidYArray.length; i++)
      {
         tempAsteroidYArray[i] += randomStartYPos;
      }
      return tempAsteroidYArray;    
   }
}
