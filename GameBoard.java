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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;




public class GameBoard extends JFrame
{
   // Width and height of the game board
   public static int boardHeight = 800;
   public static int boardWidth = 1200;

   public static boolean keyHeld = false;
   public static int keyHeldCode;
   
   public static int rotationAngle = 0;

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
      
      // Allows the program to know when the player is using the keyboard to control the ship
      addKeyListener(
         new KeyListener() {
         
            @Override
            public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
            }
         
         
            @Override
            public void keyPressed(KeyEvent e) {
               if (e.getKeyCode()==87)
               {
                  System.out.println("Forward");
               } 
               else if (e.getKeyCode()==83)
               {
                  System.out.println("Backward");
               }
               else if (e.getKeyCode()==68)
               {
                  System.out.println("Rotate Right");
               
                  keyHeldCode = e.getKeyCode();
                  keyHeld = true;
               }
               else if (e.getKeyCode()==65)
               {
                  System.out.println("Rotate left");
               
                  keyHeldCode = e.getKeyCode();
                  keyHeld = true;
               }
            
            
            }
         
            @Override
            public void keyReleased(KeyEvent e) {
               keyHeld = false;
            }
            
         });                
   
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
      // Sets the x and y cordinates for each rock to there starting postions
      int[] rockXArray = Rock.sRockXArray;
      int[] rockYArray = Rock.sRockYArray;
   
      int width = GameBoard.boardWidth;
      int height = GameBoard.boardHeight;
      
      Ship theShip = new Ship();
   
      // Draws 50 rocks on game board and stores them in ArrayList<Rock>
      public DrawingPanel()
      {
         for(int i = 0; i < 20; i++)
         {
            // Gives each rock a different starting postion
            int randomStartXPos = (int) (Math.random() * (GameBoard.boardWidth - 45) + 1);
            int randomStartYPos = (int) (Math.random() * (GameBoard.boardHeight - 45) + 1);
         
            rocks.add(new Rock(Rock.getRockXArray( randomStartXPos), 
                   Rock.getRockYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
            Rock.rocks = rocks;
         
         }
      
      }
   
      public void paint(Graphics g) 
      {
         Graphics2D graphicSettings = (Graphics2D)g;
         AffineTransform identity = new AffineTransform();
      
      
         //Sets backround color and dimen
         graphicSettings.setColor(Color.BLACK);
         graphicSettings.fillRect(0, 0, getWidth(), getHeight());
         
         
         graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
         graphicSettings.setPaint( Color.WHITE);
      
         //Draws and moves each rock on gameboard
         for (Rock rock : rocks)
         {
            rock.move();
            graphicSettings.draw(rock);
         }
         
         
         if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 68)
         {
            Ship.rotationAngle += 10;
         }
         else if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 65)
         {
            Ship.rotationAngle -= 10;
         }
         theShip.move();
         
         graphicSettings.setTransform(identity);
         graphicSettings.translate(GameBoard.boardWidth/2,GameBoard.boardHeight/2);
         graphicSettings.rotate(Math.toRadians(Ship.rotationAngle));
      
      
      
         graphicSettings.draw(theShip);
      }
   }

}




