import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
//import java.awt.Font;

/**
 * Opening message board.
 * 
 * @Autor Leon Wolters
 * @version 26-03-2011
 */
public class MessageDisplay extends Surfaces
{
    public static final float FONT_SIZE = 14.0f;
    public static int WIDTH;
    public static int HEIGHT;
    
    /**
     * 
     */
    public MessageDisplay(String msg, int w, int h)
    {
        PlayerWorld world = (PlayerWorld) getWorld();
        makeImage(msg);
        WIDTH = w;
        HEIGHT = h;
    }
    
    
    public MessageDisplay(String ans)
    {
        PlayerWorld world = (PlayerWorld) getWorld();
        WIDTH = 752;
        HEIGHT = 300;
        makeImage(ans);
    }
    
    /**
     *
     */
    public void act() 
    {
    }    
    
    /**
     *
     */
    private void makeImage(String title)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(new Color(0,0,0, 240));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(192,192,192, 240));
        image.fillRect(2, 2, WIDTH-4, HEIGHT-4);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.BLACK);
        image.drawString(title, 10, 25);
        setImage(image);
    }
}