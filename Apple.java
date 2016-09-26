import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends JComponent
{
    private int x,y;
    final Color color = Color.red;
    final int size = 10;
    Rectangle apel;
    Random gen;
    /**
     * Constructor for objects of class Apple
     */
    public Apple()
    {
        gen = new Random();
        x = gen.nextInt(670) + 15;
        y = gen.nextInt(670) + 15;
        apel = new Rectangle(x,y,size,size);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fill(apel);
    }
    
    public void move()
    {
        x = gen.nextInt(670) + 15;
        y = gen.nextInt(670) + 15;
        apel = new Rectangle(x,y,size,size);
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Rectangle getRect()
    {
        return apel;
    }
}
