import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProfileButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProfileButton extends Actor
{
    private GreenfootImage profileButton = new GreenfootImage("ProfileButton.png");
    private GreenfootImage statsPage = new GreenfootImage("StatsPage.png");
    boolean mouseDown = false;
    /**
     * Act - do whatever the ProfileButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
                if (!mouseDown && Greenfoot.mousePressed(this)) {    
            setImage(statsPage);
            mouseDown = true;
        }
    }    
}
