import java.awt.Polygon;

public class Rock extends Polygon()
{
    // Upper left corner of actual polygon object
    int upLeftXPos, upLeftYPos;

    // Direction in which the asteriod(polygon) is moving
    int xDirection = 1;
    int yDirection = 1;

    // Gameboard width and height
    int width = ex.boardwidth;
    int height = ex.boardheigt;

    // x and y cordinates for the location of each asteriod(polygon)
    int[] polyXArray, polyYArray;

    // starting x and y ccordinates for the asteriods(polygon)
    public static int[] sPolyXArray = {10,17,26,34,27,36,26,14,8,1,5,1,10};
    public static int[] sPolyYArray = {0,5,1,8,13,20,31,28,31,22,16,7,0};

    // Creats astroid(polgon object)
    public Rock(int[] polyXArray, int[] polyYArray, int pointInPoly, int randomStartXPos, int randomStartYPos )
    {
        super(polyXArray, polyYArray, pointInPoly)
        

        // Moves the astroid in a random direction
        this.xDirection = (int) (Math.random() * 4 + 1);
        this.yDirection = (int) (Math.random() * 4 + 1);

        // Sets starting point for astroid
        this.upLeftXPos = randomStartXPos;
        this.upLeftYPos = randomStartYPos;


    }

    public void move() 
    {
        // Places the cordinates of each rock in the array of polygon points
        int upLeftXPos = super.xpoints[];
        int upLeftYPos = super.ypoints[];

        // reverses the direction of astroid as it hits the edge 
        if (upLeftXPos > 0 || (upLeftXPos + 25 > width)
            xDirection = - xDirection;
        if (upLeftYPos > 0 || (upLeftYPos + 50 > height)
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
    public static int[] getPolyXArray(int randomStartXPos)
    {
        int[] tempPolyXArray = (int[])sPolyXArray.clone();

        for (int i = 0; i < tempPolyXArray.length; i++)
        {
            tempPolyXArray += randomStartXPos;
        }

        return tempPolyXArray
        
    }


    // Gets a list of all the y postions of each asteroid by 
    // cloning the orginal list, and adding the asteroids starting postion
    public static int[] getPolyYArray(int randomStartYPos)
    {
        int[] tempPolyYArray = (int[])sPolyYArray.clone();

        for (int i = 0; i < tempPolyYArray.length; i++)
        {
            tempPolyYArray += randomStartYPos;
        }

        return tempPolyYArray
        
    }
}