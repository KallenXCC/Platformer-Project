import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
//import java.awt.Font;
import java.lang.Integer;
import java.util.List;
/**
 * Opening message board.
 * 
 * @Autor Leon Wolters
 * @version 26-03-2011
 */
public class Inventory extends Actor
{
    //private static final float FONT_SIZE = 14.0f;
    private static int WIDTH;
    private static int HEIGHT;
    private static List<Weapon> weapons;
    
//     public Inventory(Player p, int w, int h)
//     {
//         PlayerWorld world = (PlayerWorld) getWorld();
//         makeImage();
//         WIDTH = w;
//         HEIGHT = h;
//         player = p;
//     }

    public Inventory(List a)
    {
        WIDTH = 752;
        HEIGHT = 300;
        weapons = a;
        makeImage();
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
    private void makeImage()
    {
        PlayerWorld world = (PlayerWorld) getWorld();
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(new Color(0,0,0, 240));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(192,192,192, 240));
        image.fillRect(2, 2, WIDTH-4, HEIGHT-4);
        Font font = image.getFont();
        font = font.deriveFont(26.0f);
        image.setFont(font);
        image.setColor(Color.BLACK);
        image.drawString("____________________Inventory________________________", 10, 25);
        font = font.deriveFont(20.0f);
        image.setFont(font);
        setImage(image);
       // world.addObject(new Weapon("adada", 10 ,1), 200, 175);
//         for(int a=0; a<weapons.size(); ++a)
//         {
//             world.addObject(weapons.get(a), 200, 175);
//         }
        //image.drawString("", 20, 25);
//         image.drawString("Health: " + player.getCurrentHealth() + " / " + player.getMaxHealth(), 200, 75);
//         image.drawString("Mana: " + player.getCurrentMana() + " / " + player.getMaxMana(), 500, 75);
//         image.drawString("Strength: " + player.getStr(), 200, 125);
//         image.drawString("Intelligence: " + player.getInt(), 200, 175);
//         image.drawString("Dexterity: " + player.getDex(), 200, 225);
//         image.drawString("Gold: " + player.getGold(), 450, 175);
        
    }
}