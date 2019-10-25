import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy1 extends Enemy
{
    public boolean switchDirection = false;
    public int timer = 50;

    public Enemy1() {
        super(50, 0.5, 30, 3);  
    }

    public void act() 
    {
        if (switchDirection) {
            this.setLocation(this.getX() - 1, this.getY());
        }
        else {
            this.setLocation(this.getX() + 1, this.getY());
        }
        if (timer <= 0) {
            timer = 50;
            switchDirection = !switchDirection;
        }
        else {
            timer--;
        }
        super.act();
    }    
}
