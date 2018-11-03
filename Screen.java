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




public class Screen extends JFrame
{
   public static int screenHeight = 800;
   public static int screenWidth = 1200;
   static Ship battleShip = new Ship();
   public static boolean keyHeld = false;
   public static int keyHeldCode;
   public static ArrayList<Railgun> railguns = new ArrayList<Railgun>();

   String laserSound = "file:C:/Users/N/Documents/jgrasp/Asteroids/sounds/laser.WAV";
   String music = "file:C:/Users/N/Documents/jgrasp/Asteroids/sounds/music.WAV";

   public static void main(String [] args)
   {
      new Screen();
   }

   public Screen()
   {
      // Settings for JFrame
      this.setSize(screenWidth, screenHeight);
      this.setTitle("Sorta Asteroids");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
      DrawingPanel gamePanel = new DrawingPanel();
   
      playSound(music);
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
                  keyHeldCode = e.getKeyCode();
                  keyHeld = true;
               } 
               else if (e.getKeyCode()==83)
               {
                  System.out.println("Backward");
                  keyHeldCode = e.getKeyCode();
                  keyHeld = true;
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
               else if (e.getKeyCode()==KeyEvent.VK_ENTER){
                  System.out.println("Shoot");
                  
                  playSound(laserSound);
               
               // Creates a new railgun and passes the ships nose position
               // so the railgun can start there. Also passes the ships
               // rotation angle so the railgun goes in the right direction
               
                  railguns.add(new Railgun(battleShip.getShipTipX(),
                     battleShip.getShipTipY(), 
                     battleShip.getSittingAngle()));
               
               
                  System.out.println("SittingAngle " + battleShip.getSittingAngle());
                  System.out.println(railguns.size());
               
               }
            
            }
         
            @Override
            public void keyReleased(KeyEvent e) {
               keyHeld = false;
            }
            
         });                
   
      ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
   
      // Sets the delay of when to redraw the board
      executor.scheduleAtFixedRate(new RedrawScreen(this), 0L, 20L, TimeUnit.MILLISECONDS);
      this.setVisible(true);
   
   }
   

   public static void playSound(String sound)
   {
      URL fileLocation;
      try {
         fileLocation = new URL(sound);
       
        	    // Stores a predefined audio clip
         Clip clip = null;
      		
      		// Convert audio data to different playable formats
         clip = AudioSystem.getClip();
      		
      		// Holds a stream of a definite length
         AudioInputStream inputStream;
      
         inputStream = AudioSystem.getAudioInputStream(fileLocation);
      
      		// Make audio clip available for play
         clip.open( inputStream );
      			
      		// Define how many times to loop
         clip.loop(0);
      		
      		// Play the clip
         clip.start();
      }
      catch (MalformedURLException e1) {
      			// TODO Auto-generated catch block
         e1.printStackTrace();
      }
        	    
      catch (UnsupportedAudioFileException | IOException e1) {
      			// TODO Auto-generated catch block
         e1.printStackTrace();
      }
        	    
      catch (LineUnavailableException e1) {
      			// TODO Auto-generated catch block
         e1.printStackTrace();
      }
   	
   
   }

   // Implements runnable to continually redraw the screen

   class RedrawScreen implements Runnable
   {
      Screen theScreen;
   
      public RedrawScreen(Screen theScreen)
      {
         this.theScreen = theScreen;
      }
      public void run()
      {
         theScreen.repaint();
      }
   }

   class DrawingPanel extends JComponent
   {
      // Holds all the asteroid objects
      public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
      // Sets the x and y cordinates for each asteroid to there starting postions
      int[] asteroidXPoints = Asteroid.sAsteroidXPoints;
      int[] asteroidYPoints = Asteroid.sAsteroidYPoints;
   
      int width = Screen.screenWidth;
      int height = Screen.screenHeight;
      
   
   
      // Draws 50 asteroids on game board and stores them in ArrayList<Asteroid>
      public DrawingPanel()
      {
         for(int i = 0; i < 20; i++)
         {
            // Gives each asteroid a different starting postion
            int randomStartXPos = (int) (Math.random() * (Screen.screenWidth - 45) + 1);
            int randomStartYPos = (int) (Math.random() * (Screen.screenHeight - 45) + 1);
         
            asteroids.add(new Asteroid(Asteroid.getAsteroidXArray( randomStartXPos), 
                   Asteroid.getAsteroidYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
            Asteroid.asteroids = asteroids;
         
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
      
         //Draws and moves each asteroid on gameboard
         for (Asteroid asteroid : asteroids)
         {
            if(asteroid.onScreen)
            {
               asteroid.move(battleShip, Screen.railguns);
               graphicSettings.draw(asteroid);
            }
         }
         
         
         if(Screen.keyHeld == true && Screen.keyHeldCode == 68)
         {
            battleShip.increaseSittingAngle();
            System.out.println("Ship Angle: " + battleShip.getSittingAngle());
         }
         else if(Screen.keyHeld == true && Screen.keyHeldCode == 65)
         {
            battleShip.decreaseSittingAngle();
            System.out.println("Ship Angle: " + battleShip.getSittingAngle());
         }
         else if(Screen.keyHeld == true && Screen.keyHeldCode == 87)
         {
            battleShip.setFlyingAngle(battleShip.getSittingAngle());
            battleShip.increaseXVelocity(battleShip.shipXFlyingAngle(battleShip.getFlyingAngle())*0.2);
            battleShip.increaseYVelocity(battleShip.shipYFlyingAngle(battleShip.getFlyingAngle())*0.2);
         }
         
      
         battleShip.move();
         
         graphicSettings.setTransform(identity);
         graphicSettings.translate(battleShip.getXCenter(), battleShip.getYCenter());
         graphicSettings.rotate(Math.toRadians(battleShip.getSittingAngle()));
      
      
      
         graphicSettings.draw(battleShip);
         
         for(Railgun railgun : Screen.railguns)
         {
            railgun.move();
            if(railgun.onScreen){
            
            // Stroke the polygon railgun on the screen
            
               graphicSettings.setTransform(identity);
            
            // Changes the railguns center x & y vectors
            
               graphicSettings.translate(railgun.getXCenter(),railgun.getYCenter());
            
               graphicSettings.draw(railgun);
               
            
            }
         }
      }
   }

}




