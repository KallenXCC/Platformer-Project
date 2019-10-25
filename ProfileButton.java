import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class ProfileButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProfileButton extends Actor
{
    public boolean clicked = false;
    private String type;
    /**
     * Act - do whatever the ProfileButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ProfileButton(String imgName, String ButtonType)
    {
        setImage(imgName);
        type = ButtonType;
    }

    public void act() 
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List player = myWorld.getObjects(Player.class);
        if (!clicked && (Greenfoot.mousePressed(this) || Greenfoot.isKeyDown("p") || Greenfoot.isKeyDown("b") || Greenfoot.isKeyDown("i")))
        {
            if((Greenfoot.mousePressed(this) && type == "move") || Greenfoot.isKeyDown("m"))
            {
                ((Player)player.get(0)).move(10);
            }
            else if ((Greenfoot.mousePressed(this) && type == "profile") || Greenfoot.isKeyDown("p"))
            {
                myWorld.profilePage();
            }
            else if ((Greenfoot.mousePressed(this) && type == "back") || Greenfoot.isKeyDown("b"))
            {
                myWorld.homeMenu();
            }
            else if (type == "buttClench")
            {
                ((Player)player.get(0)).setCurrentHealth(((Player)player.get(0)).getMaxHealth());
                ((Player)player.get(0)).setCurrentMana(((Player)player.get(0)).getMaxMana());

                //myWorld.profilePage();
            }
            else if ((Greenfoot.mousePressed(this) && type == "inventory") || Greenfoot.isKeyDown("i"))
            {
                myWorld.inventoryPage();
            }
            else if (type == "sword")
            {
                ((Player)player.get(0)).equip("sword");
            }
        }
    }    

    public String getType()
    {
        return type;
    }

    public boolean isClicked()
    {
        return clicked;
    }
}
