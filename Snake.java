import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Snake extends JComponent implements ActionListener
{
    ArrayList<Rectangle> snake;
    final Color color = Color.green;
    int length;
    int dir;

    public Snake()
    {
        dir = 0;
        length = 3;
        snake = new ArrayList<Rectangle>();
        for(int i = 0;i < length;i++){
            snake.add(new Rectangle(250,250,25,25));
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        for(Rectangle r: snake)
        {
            g2.fill(r);
        }
    }

    public void actionPerformed(ActionEvent evt)
    {
        int x = (int) snake.get(0).getX();
        int y = (int) snake.get(0).getY();

        for(int i = length;i > 1;i--)
        {
            snake.set(i-1, snake.get(i-2));
        }

        switch(dir)
        {
            case 1:
            y-=25;
            break;
            case 2:
            x+=25;
            break;
            case 3:
            y+=25;
            break;
            case 4:
            x-=25;
            break;
            default:
            break;
        }

        snake.set(0,new Rectangle(x,y,25,25));
        repaint();
    }

    public void move(int dir)
    {
        this.dir = dir;
    }

    public void grow()
    {
        snake.add(new Rectangle(getTail()));
        
        length++;
    }

    private Rectangle getTail()
    {
        return snake.get(length-1);
    }
    public void grow(int x)
    {
        length+=x;
    }
    
    public Rectangle getHead()
    {
        return snake.get(0);
    }
}
