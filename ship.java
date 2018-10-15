import java.awt.Polygon;


public class Ship extends Polygon
{
   int shipUpLeftXPos, shipUpLeftYPos;
   
   int yDirection = 0;
   int xDirection = 0;
   
   int width = GameBoard.boardWidth;
   int height = GameBoard.boardHeight;
   
   
   public static int[] shipXArray = {500,527,500,508,500};
   public static int[] shipYArray = {400,415,430,415,400};

   public Ship()
   {
      // Creates the ship via calling the Polgon class
      super(shipXArray, shipYArray, 5);
   
   }
   
   public void move()
   {
      int shipUpLeftXPos = super.xpoints[0]; 
      int shipUpLeftYPos = super.ypoints[0];
      
      if (shipUpLeftXPos < 0 || (shipUpLeftXPos + 25) > width) xDirection = -xDirection; 
      if (shipUpLeftYPos < 0 || (shipUpLeftYPos + 50) > height) yDirection = -yDirection;
      
      for (int i = 0; i < super.xpoints.length; i++){
      	
         super.xpoints[i] += xDirection;
         super.ypoints[i] += yDirection;
      }
   }
}
