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
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

/**
* Contains the programs graphical properties, utilizing the library's Swing, JFrame, and Graphics2D.  
* Also handles user input and contains the programs main method.
*
* @author Nick Papazian
* @since 9/20/2018
**/
public class Screen extends JFrame
{
   /** Holds the Screens height and width. **/
   private static int screenHeight = 1000, screenWidth = 1200;

   /** Creates the ship that will be referenced. **/
   private static Ship battleShip = new Ship();
   
   /** Monitors if the "shoot" key or any of the movement keys are being held. **/
   private static boolean KeyPressed = false;
   
   /** Holds the "shoot" key code and movement key code. **/
   private static int KeyCode;
   
   /** Holds each railgun bullet shot. **/
   private static ArrayList<Railgun> railguns = new ArrayList<Railgun>();

   private String laserSound = 
      "file:C:/Users/N/Documents/jgrasp/Asteroids/sounds/laser.WAV";

   /** Creates the Screen. 
   * @param args 
   **/
   public static void main(String [] args)
   {
      new Screen();
   }
   
   /** @return screenWidth **/
   public static int getScreenWidth()
   {
      return screenWidth;
   }
   
   /** @return screenHeight  **/
   public static int getScreenHeight()
   {
      return screenHeight;
   }
   
    /** @return battleShip   **/
   public static Ship getShip()
   {
      return battleShip;
   }
   

   /** Constructs the Screen with JFrame.
   * Also receives user input via keyboard.
   **/
   public Screen()
   {
      // Settings for JFrame
      this.setSize(getScreenWidth(), getScreenHeight());
      this.setTitle("Sorta Asteroids");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      DrawingPanel gamePanel = new DrawingPanel();
      
      // Fits the drawing to the whole screen
      this.add(gamePanel, BorderLayout.CENTER);
      
      // Monitors keyboard strokes
      addKeyListener(
          new KeyListener() {
          
             @Override
             public void keyTyped(KeyEvent e) {
             // TODO Auto-generated method stub
             
             }
          
          
             @Override
             public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 87)
                {
                   KeyCode = e.getKeyCode();
                   KeyPressed = true;
                } 
                else if (e.getKeyCode() == 83)
                {
                   KeyCode = e.getKeyCode();
                   KeyPressed = true;
                }
                else if (e.getKeyCode() == 68)
                {
                   KeyCode = e.getKeyCode();
                   KeyPressed = true;
                }
                else if (e.getKeyCode() == 65)
                {
                   KeyCode = e.getKeyCode();
                   KeyPressed = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                   playSound(laserSound);
                
                  // Creates a new railgun bullet based off 
                  // of the ships nose tip position                  
                   railguns.add(new Railgun(getShip().getShipTipX(),
                      getShip().getShipTipY(), 
                      getShip().getSittingAngle()));
                
                
                   System.out.println("SittingAngle " 
                        + getShip().getSittingAngle());
                   System.out.println("Bullets Fired: " + railguns.size());
                
                }
             
             }
          
             @Override
             public void keyReleased(KeyEvent e) {
                KeyPressed = false;
             }
            
          });                
   
      ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
   
      // Sets the delay of when to redraw the board
      executor.scheduleAtFixedRate(new RedrawScreen(this), 
         0L, 20L, TimeUnit.MILLISECONDS);
      this.setVisible(true);
   
   }
   
   /** Plays a sound from the file provided.
   * @param sound 
    **/
   public static void playSound(String sound)
   {
      URL fileLocation;
      try {
         fileLocation = new URL(sound);
         Clip clip = null;
         clip = AudioSystem.getClip();
         AudioInputStream inputStream;
      
         inputStream = AudioSystem.getAudioInputStream(fileLocation);
         clip.open(inputStream);
      
         clip.loop(0);
         clip.start();
      }
      catch (MalformedURLException e1) 
      {
      // TODO Auto-generated catch block
         e1.printStackTrace();
      }
             
      catch (UnsupportedAudioFileException | IOException e1) 
      {
      // TODO Auto-generated catch block
         e1.printStackTrace();
      }
             
      catch (LineUnavailableException e1) 
      {
      // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      
   
   }

   /** Implements runnable to continually redraw the screen. **/
   class RedrawScreen implements Runnable
   {
      private Screen theScreen;
   
      RedrawScreen(Screen theScreen)
      {
         this.theScreen = theScreen;
      }
      public void run()
      {
         theScreen.repaint();
      }
   }
   /** Draws the asteroids, ship, and railgun bullets on the screen. **/
   class DrawingPanel extends JComponent
   {
      // Holds all the asteroid objects
      private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
      
      // Sets the x and y coordinates for 
      // each asteroid to their starting positions
      private int[] asteroidXPoints = Asteroid.sAsteroidXPoints;
      private int[] asteroidYPoints = Asteroid.sAsteroidYPoints;
   
      private int width = Screen.getScreenWidth();
      private int height = Screen.getScreenHeight();
   
      // Draws 50 asteroids on game board and stores them in ArrayList<Asteroid>
      DrawingPanel()
      {
         int count = 0;
         while (count < 30)
         {
            // Gives each asteroid a different starting position
            int randomStartXPos = (int) (Math.random()
               * (Screen.getScreenWidth() - 45) + 1);
            int randomStartYPos = (int) (Math.random()
               * (Screen.getScreenHeight() - 45) + 1);
         
            asteroids.add(new Asteroid(Asteroid.getAsteroidXArray(randomStartXPos), 
                   Asteroid.getAsteroidYArray(randomStartYPos),
                     13, randomStartXPos, randomStartYPos));
            Asteroid.asteroids = asteroids;
            count++;
         }
         
      
      }
   
      public void paint(Graphics g) 
      {
         Graphics2D graphicSettings = (Graphics2D)g;
         AffineTransform identity = new AffineTransform();
      
      
         //Sets background color and dimensions
         graphicSettings.setColor(Color.BLACK);
         graphicSettings.fillRect(0, 0, getWidth(), getHeight());
         
         
         graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON);
         graphicSettings.setPaint(Color.WHITE);
      
         //Draws and moves each asteroid on Screen
         for (Asteroid asteroid : asteroids)
         {
            if (asteroid.getOnScreen())
            {
               asteroid.move(getShip(), Screen.railguns);
               graphicSettings.draw(asteroid);
            }
         }
         
         
         if (Screen.KeyPressed && Screen.KeyCode == 68)
         {
            getShip().increaseSittingAngle();
            System.out.println("Ship Angle: " + getShip().getSittingAngle());
         }
         else if (Screen.KeyPressed && Screen.KeyCode == 65)
         {
            getShip().decreaseSittingAngle();
            System.out.println("Ship Angle: " + getShip().getSittingAngle());
         }
         else if (Screen.KeyPressed && Screen.KeyCode == 87)
         {
            getShip().setFlyingAngle(getShip().getSittingAngle());
            getShip().increaseXVelocity(getShip().shipXFlyingAngle(getShip()
               .getFlyingAngle()) * 0.2);
            getShip().increaseYVelocity(getShip().shipYFlyingAngle(getShip()
               .getFlyingAngle()) * 0.2);
         }
         else if (Screen.KeyPressed && Screen.KeyCode == 83)
         {
            getShip().setFlyingAngle(getShip().getSittingAngle());
            getShip().decreaseXVelocity(getShip().shipXFlyingAngle(getShip()
               .getFlyingAngle()) * 0.1);
            getShip().decreaseYVelocity(getShip().shipYFlyingAngle(getShip()
               .getFlyingAngle()) * 0.1);
         }
         
      
         getShip().move();
         
         graphicSettings.setTransform(identity);
         graphicSettings.translate(getShip().getXCenter(),
            getShip().getYCenter());
         graphicSettings.rotate(Math.toRadians(getShip().getSittingAngle()));
      
      
      
         graphicSettings.draw(getShip());
         
         for (Railgun railgun : Screen.railguns)
         {
            railgun.move();
            if (railgun.getOnScreen())
            {
               graphicSettings.setTransform(identity);
            
            // Changes the railguns center x & y coordinates
               graphicSettings.translate(railgun.getXCenter(),
                  railgun.getYCenter());
            
               graphicSettings.draw(railgun);
               
            
            }
         }
      }
   }

}





