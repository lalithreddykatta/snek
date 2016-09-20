import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*; // impor the necessary classes for the code

public class Snake extends JComponent // exted jcomponent to use the jframe and the like
{
    int x[] = new int [600]; // create an array for the body of the snake, for the x and y values
    int y[] = new int [600];
    int body; // initialize a value for the body length to be defined later

    int randx; // initialize values for the random x and y of the fruit location
    int randy;
    int score;
    boolean gameOn = true; // set a boolean variable for the starting and stopping of the game
    boolean up; // set boolean variables for the movement in a certain directino
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

        appleLocation (590); // use the method for drawing the apple to place an apple, defined below
        Timer t = new Timer (35,new MoveListener ()); // create a time with the action performed method, defined below
        t.start (); // and start it to make it run

    }

    public void paintComponent(Graphics g) // use the paint component method to draw the head and fruit
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
            g.setColor (Color.RED); // change the color
            g.fillRect (randx, randy, 10, 10); // draw the fruit

        }
        else // if the game has been stopped due to a loss than
        {
            String f = "GAME OVER"; // stop drawing the snake and instead draw a game over symbol
            g.setColor (Color.RED);
            g.setFont (new Font ("Sans Serif", Font.PLAIN, 35));
            g.drawString(f, 175, 300);

        }
    }

    public void appleLocation (int max) // method for giving the apple a location
    {
        randx = (int) (Math.random() * max); // use the math random method multiplyed by a maximum number for the x
        randy = (int) (Math.random() * max); // do the same thing for the y
        for (int z = 0; z <= body; z++) // use a for loop to check for the length of the body
        {
            if (randx == x[z] && randy == y[z]) // and if the x and y value of the body and fruit match later in the body redraw it
            {
                randx = (int) (Math.random() * max);
                randy = (int) (Math.random() * max);
            }
        }
    }

    public void checkFruit () // a method to check if you hit the fruit
    {

        for (int d = x[0]; d <= x[0] + 20; d++) // check the x value of the head and add twenty due the snake being free moving so that we have to check every x value of the entire head
        {
            for (int e = y[0]; e <= y[0] + 20; e++) // same to check every y value of the head when it hits from the left or right
            {
                for (int i = randx; i <= randx + 10; i++) // check the fruits x value and add ten to cover the entire x side
                {
                    for (int c = randy; c <= randy + 10; c++) // and the same for the y side
                    {
                        if ((d == i) && (e == c)) // and if the x and y values intersect 
                        {
                            body += 5; // make the body bigger by one 
                            appleLocation (500); // and redraw the fruit
                            score++;
                            for (int r = 0; r < 50; r++)
                            {
                                System.out.println();
                            }
                            if (score <=1)
                                System.out.println ("You have gotten " +score+ " apple.");
                            else
                                System.out.println ("You have gotten " +score+ " apples.");
                        }
                    }
                }
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

    public void move (int code1) // method to set the move direction of the snake
    {
        switch(code1)
        {
            case KeyEvent.VK_W: case KeyEvent.VK_UP:// if the button pressed is up, than set up to true and left and right to false
            if(!down)
            {

                up = true; // down is declared in the conditional statement so that the body cannot be moved back against itself, only forward
                left = false; 
                right = false; 
            }
            break;
            case KeyEvent.VK_S: case KeyEvent.VK_DOWN: // if the button pressed is down, than set down to true and left and right to false 
            if(!up)
            {
                down = true; // up is declared in the conditional statement for the reason above
                left = false;
                right = false;
            }
            break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT: // if the button pressed is left, than set left to true and up and down to false
            if(!right)
            {
                up = false; // right is declared in the conditional statement for the reason above
                down = false;
                left = true;
            }
            break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT: // if the button pressed is right, than set right to true and up and down to false
            if(!left)
            {
                up = false; // left is declared in the conditional statement for the reason above
                down = false;
                right = true;
            }
            break;
            case KeyEvent.VK_L:
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
                checkFruit (); // check if the fruit has hit

                checkCollision (); // check if the body has collided with itself
                // and then make it move by...
                for (int z = body; z > 0; z--) // making eacj value of the head equivalent to the one before it, going down to zero
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

