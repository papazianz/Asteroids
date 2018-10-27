import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Arraylist;

public class Rock extends Polygon
{
   // Upper left corner postion of actual rock object
   int rockUpLeftXPos, rockUpLeftYPos;

   // Direction in which the asteriod(polygon) is moving
   int xDirection = 1;
   int yDirection = 1;

   // Gameboard width and height
   int width = GameBoard.boardWidth;
   int height = GameBoard.boardHeight;

   // x and y cordinates for the location of each asteriod(polygon)
   int[] rockXArray, rockYArray;

   // starting x and y ccordinates for the asteriods(polygon)
   public static int[] sRockXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
   public static int[] sRockYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};

   // Constructor for the rock 
   public Rock(int[] rockXArray, int[] rockYArray, int pointInPoly, int randomStartXPos, int randomStartYPos )
   {
      super(rockXArray, rockYArray, pointInPoly);
      
   
      // Moves the asteroid in a random direction
      this.xDirection = (int) (Math.random() * 6 + 1);
      this.yDirection = (int) (Math.random() * 6 + 1);
   
      // Sets starting point for astroid
      this.rockUpLeftXPos = randomStartXPos;
      this.rockUpLeftYPos = randomStartYPos;
   
   
   }
   
 
    public Rectangle setBounds()
    {
    return new Rectangle(super.xpoints[0], super.ypoints[0], rockWidth, rockHeight);
    }

   public void move() 
   {
      // Places the cordinates of each rock in the array of polygon cordinates
      int rockUpLeftXPos = super.xpoints[0];
      int rockUpLeftYPos = super.ypoints[0];
   
      // reverses the direction of astroid as it hits the edge 
      if (rockUpLeftXPos < 0 || (rockUpLeftXPos + 25 > width))
         xDirection = - xDirection;
      if (rockUpLeftYPos < 0 || (rockUpLeftYPos + 50 > height))
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
   public static int[] getRockXArray(int randomStartXPos)
   {
      int[] tempRockXArray = (int[])sRockXArray.clone();
   
      for (int i = 0; i < tempRockXArray.length; i++)
      {
         tempRockXArray[i] += randomStartXPos;
      }
   
      return tempRockXArray;
      
   }


   // Gets a list of all the y postions of each asteroid by 
   // cloning the orginal list, and adding the asteroids starting postion
   public static int[] getRockYArray(int randomStartYPos)
   {
      int[] tempRockYArray = (int[])sRockYArray.clone();
   
      for (int i = 0; i < tempRockYArray.length; i++)
      {
         tempRockYArray[i] += randomStartYPos;
      }
   
      return tempRockYArray;
      
   }
}
