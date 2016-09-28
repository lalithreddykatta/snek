import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class Engine implements ActionListener
{
    Snake snake;
    ArrayList<Apple> apples;
    JFrame frame;
    public Engine()
    {
        frame = new JFrame("SnakeScreen");
        frame.setSize(700,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snake = new Snake();
        apples = new ArrayList<>();
        for (int i = 0;i < 10;i++)
        {
            apples.add(new Apple());
            frame.add(apples.get(i));
            frame.setVisible(true);
        }

        frame.add(snake);
        frame.setVisible(true);

        Timer t = new Timer(100,snake);
        t.start();

        class KeyBoard implements KeyListener
        {
            public void keyPressed(KeyEvent kevt)
            {
                switch(kevt.getKeyCode())
                {
                    case KeyEvent.VK_UP: case KeyEvent.VK_W:
                    snake.move(1);
                    break;
                    case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                    snake.move(2);
                    break;
                    case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                    snake.move(3);
                    break;
                    case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                    snake.move(4);
                    break;
                    case KeyEvent.VK_C:
                    snake.grow(10);
                    break;
                    case KeyEvent.VK_Q:
                    return;
                    default:
                    break;
                }
            }

            public void keyReleased(KeyEvent kevt)
            {

            }

            public void keyTyped(KeyEvent kevt)
            {

            }
        }

        frame.addKeyListener(new KeyBoard());

        Timer tim = new Timer(10,this);
        tim.start();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        checkCollision();
        //checkBounds();
    }

    public void checkCollision()
    {
        Rectangle rect = snake.getHead();
        for(Apple a:apples)
        {
            if(rect.intersects(a.getRect()))
            {
                a.move();
                snake.grow();
            }
        }
    }
}
