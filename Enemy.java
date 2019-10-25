import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Attack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    public static double enemyAtkPow;
    public boolean isTurn = false;
    public static int enemyHealth;
    private Bar healthBar;

    public Enemy(int health, double atkPower, int BarWidth, int BarHeight)
    {
        enemyHealth = health;
        enemyAtkPow = atkPower;
        healthBar = new Bar("", "", health, health);
        healthBar.setShowTextualUnits(false);
        healthBar.setBarWidth(BarWidth);
        healthBar.setBarHeight(BarHeight);
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, getX(), getY() - 30);
    }

    public void act() 
    {
        //         PlayerWorld myWorld = (PlayerWorld)getWorld();
        //         List player = myWorld.getObjects(Player.class);
        //         Player a = getOneObjectAtOffset(0,0,Player.class);
        //         if (a!=null && Greenfoot.isKeyDown("space"))
        //         {
        //             myWorld.enterBattle(this);
        //         }
        healthBar.setLocation(getX(), getY() - 30);
        destroyEnemy();
        healthBar.setValue(enemyHealth);
        if (healthBar.getValue() == 0) {
            getWorld().removeObject(healthBar);
            getWorld().removeObject(this);
        }
    }    
    
    public void destroyEnemy() {
        Actor slicer = getOneIntersectingObject(Slicer.class);  
        if(slicer != null) {  
            enemyHealth--;
        }  
    }  

    public static int getHealth(){
        return enemyHealth;
    }

    public static double getAtkPow() {
        return enemyAtkPow;
    }

    public static void setHealth(int health) {
        enemyHealth = health;
    }

    //     public void initialize() {
    //         enemyAtkPow = 2;
    //     }
}
