import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapon extends Item
{
    /**
     * Act - do whatever the Weapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private static int dmg, mag;
    private static String name;

    public Weapon(String n, int d, int m)
    {
        name = n;
        dmg = d;
        mag = m;
    }

    public void act() 
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List player = myWorld.getObjects(Player.class);
        //Player a = getOneObjectAtOffset(0,0,Player.class);
        Actor a = getOneObjectAtOffset(0,0,Player.class);
        if (a!=null && ((Player)player.get(0)).has(this.getName()))
        {
            Player p = (Player)a;
            p.addWeapon(this);
            myWorld.removeObject(this);
        }
    }

    public static int getDmg()
    {
        return dmg;
    }

    public static int getMag()
    {
        return dmg;
    }

    public static String getName()
    {
        return name;
    }

}
