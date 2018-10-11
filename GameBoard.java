import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class GameBoard extends JFrame
{
   // Width and height og the game board
   public static int boardHeight = 1000;
   public static int boardWidth = 800;

   public static void main(String [] args)
   {
      new GameBoard();
   }

   public GameBoard()
   {
      // Settings for JFrame
      this.setSize(boardWidth, boardHeight);
      this.setTitle("Sorta Asteroids");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      DrawingPanel gamePanel = new DrawingPanel();
   
      // Fits the drawing to the whole screen
      this.add(gamePanel, BorderLayout.CENTER);
   
      ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
   
      // Sets the delay of when to redraw the board
      executor.scheduleAtFixedRate(new RedrawBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);
   
      this.setVisible(true);
   
   }

   // Implements runnable to continually redraw the screen

   class RedrawBoard implements Runnable
   {
      GameBoard theBoard;
   
      public RedrawBoard(GameBoard theBoard)
      {
         this.theBoard = theBoard;
      }
   
   
      public void run()
      {
         theBoard.repaint();
      }
   
   }

   class DrawingPanel extends JComponent
   {
      // Holds all the rock objects
      public ArrayList<Rock> rocks = new ArrayList<Rock>();
   
      int[] rockXArray = Rock.sRockXArray;
      int[] rockYArray = Rock.sRockYArray;
   
      int width = GameBoard.boardWidth;
      int height = GameBoard.boardHeight;
   
      // Draws 50 rocks on game board and stores them in ArrayList<Rock>
      public DrawingPanel()
      {
         for(int i = 0; i < 50; i++)
         {
            // Gives each rock a different starting postion
            int randomStartXPos = (int) (Math.random() * (GameBoard.boardWidth - 45) + 1);
            int randomStartYPos = (int) (Math.random() * (GameBoard.boardHeight - 45) + 1);
         
            rocks.add(new Rock(Rock.getRockXArray( randomStartXPos), 
                   Rock.getRockYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
         
         
         }
      
      }
   
      public void paint(Graphics g) 
      {
         Graphics2D graphicSettings = (Graphics2D)g;
      
         graphicSettings.setColor(Color.BLACK);
         graphicSettings.fillRect(0, 0, getWidth(), getHeight());
         graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
         graphicSettings.setPaint( Color.WHITE);
      
         for (Rock rock : rocks)
         {
            rock.move();
            graphicSettings.draw(rock);
         }
      
      
      
      
      
      }
   }

}




