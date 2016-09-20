import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*; // import necessary classes

public class Main // class constructor
{
    public static void main (String [] args) // create the game within the main class
    {
        JFrame frame = new JFrame (); // create a new frame
        frame.setSize (600, 600); // set the size of the frame
        frame.setTitle ("Snake Game"); // title the frame
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // set how to close the frame 
        frame.setResizable (false); // dont allow the frame to be resizable due to necessary borders for the game
        frame.setFocusable (true); // allow the movement to actually occur
        final Snake c = new Snake (); // create a new snake game class, final so that it can be referenced within the inner class
        class KeyClickListener implements KeyListener // use a key listener here to that the setfocusable can see it
        {
            public void keyPressed (KeyEvent e) // if the key is pressed
            {
                c.move(e.getKeyCode ()); // use the move method from the snake class to define what direction the snake will move in
            }

            public void keyReleased (KeyEvent e) // empty method
            {

            }

            public void keyTyped (KeyEvent e) // unused method
            {

            }
        }

         // create a new keyclick listener to listen to the key clicks
        frame.addKeyListener (new KeyClickListener()); // and add it to the frame so that it can read them and the snake can move accordingly

        c.startGame (); // have the snake use the start game method to start the game and
        frame.add (c); // add the snake to the frame
        frame.setVisible (true); // and make it visible
    }
}
