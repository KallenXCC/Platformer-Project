import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBoss extends Enemy
{
    public boolean switchDirection = false;
    public int timer = 50;

    public EnemyBoss() {
        super(10000, 3.3, 100, 10);  
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
