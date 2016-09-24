import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*; 

public class Snake extends JComponent 
{
    int x[] = new int [600]; // create an array for the body of the snake, for the x and y values
    int y[] = new int [600];
    int body; // initialize a value for the body length to be defined later

    boolean up;
    boolean down;
    boolean left;
    boolean right;

    // method to initialize the game
    public void startGame () // make a method for the starting of the game
    {
        body = 3; // initialize the body size to three

        for (int z = 0; z < body; z++) // set where the x value is going to be for all three segments, and more later
        {
            x[z] = 500 - 19*z; // this puts each on behind the one before it
            y[z] = 500;
        }

        Timer t = new Timer (35,new MoveListener ()); // create a time with the action performed method, defined below
        t.start (); // and start it to make it run

    }

    public void paintComponent(Graphics g) // use the paint component method to draw the head 
    {

        if (gameOn) // if the game is still on then
        {
            for (int z = 0; z < 1; z++) // for the first head draw the square with two eyes
            {
                g.setColor (Color.GREEN);
                g.fillRect (x[z],y[z], 20, 20); // draw the head
                g.setColor (Color.BLACK);
                g.fillRect (x[z] + 3, y[z] + 5 ,5,5); // then draw two eyes on it
                g.fillRect (x[z] + 13, y[z] + 5 ,5,5);
            }
            for (int z = 1; z < body; z++) // and for the rest of the body up to the full body length
            {
                g.setColor(Color.GREEN);
                g.fillRect (x[z],y[z],20,20); // draw every extra part

            }

        }
        
    }

    public void checkCollision () // a method to check collisions with the walls or itself
    {

        for (int i = 4; i <= body; i++) // check the length of the body after the original three so that
        {
            if (x[0] == x[i] && y[0] == y[i]) // if the head hits its own body at any point then stop
            {
                gameOn = false;
            }
        }

        if (x[0] < 0) // if the head hits the leftmost x value of the border then end the game
        {
            gameOn = false;
        }

        if (y[0] < 0) // if the head hits the top y value then stop
        {
            gameOn = false; 
        }

        if (x[0] > 580) // if the head hits the rightmost x value of the border, than stop
        {
            gameOn = false;
        }

        if (y[0] > 560) // and if the head hits the bottommost y value than stop
        {
            gameOn = false;
        }

    }

    public void move (int dir) // method to set the move direction of the snake
    {
        switch(dir)
        {
            case KeyEvent.VK_W: case KeyEvent.VK_UP:
            if(!down)
            {
                up = true; 
                left = false; 
                right = false; 
            }
            break;
            case KeyEvent.VK_S: case KeyEvent.VK_DOWN: 
            if(!up)
            {
                down = true; 
                left = false;
                right = false;
            }
            break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT: 
            if(!right)
            {
                up = false; 
                down = false;
                left = true;
            }
            break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:  
            if(!left)
            {
                up = false; 
                down = false;
                right = true;
            }
            break;
            case KeyEvent.VK_H:
            body+=5;
            break;
            case KeyEvent.VK_Q:
            gameOn = false;
            break;
        }
    }
    private class MoveListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e) // use the action listener to tell the snake what to do while the game is on
        {
            if (gameOn) // while the game is on
            {

                checkCollision (); // check if the body has collided with itself
                // and then make it move by...
                for (int z = body; z > 0; z--) // making each value of the snake equivalent to the one before it
                {
                    x[z] = x[(z-1)]; // each one is equivalent to the one before it
                    y[z] = y[(z-1)];
                }

                // than based on the direction that we get from the move method
                if (right)
                {
                    x[0] += 10; // to move right add to the x value
                }
                if (left)
                {
                    x[0] += -10; // to move left take away from the x value
                }
                if (up)
                {            
                    y[0] += -10; // to move up take away from the y value
                }
                if (down)
                {
                    y[0] += 10; // and to move down add to the y value
                }

            }
            repaint (); // and then repaint every time
        }
    }
}

