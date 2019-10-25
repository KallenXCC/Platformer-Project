import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Portal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Portal extends Structures
{
    private String myType;
    private String myLock;
    private GreenfootImage lockedPortal = new GreenfootImage("Portal_Lock.png");
    private GreenfootImage unlockedPortal = new GreenfootImage("Portal.png");

    /**
     * Act - do whatever the Portal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Portal(String type, String lock)
    {
        myType=type;
        myLock = lock;
    }

    public void act() 
    {
        if (myLock == "locked")
        {
            setImage(lockedPortal);
        }

        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List player = myWorld.getObjects(Player.class);
        List doors = myWorld.getObjects(Door.class);
        List portals = myWorld.getObjects(Portal.class);
        Actor a = getOneObjectAtOffset(0,0,Player.class);

        if (myLock == "unlocked")
        {
            if (a!=null && Greenfoot.isKeyDown("space") && myType == "townToDung")
            {
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));
                }
                for (int i=0;i<doors.size();i++)
                {
                    myWorld.removeObject((Actor)doors.get(i));
                }
                ((Player)player.get(0)).setLocation(80,450);
                Greenfoot.playSound("portal.wav");
                myWorld.homeMenu();
                myWorld.dung();
            }
            else if (a!=null && Greenfoot.isKeyDown("space") && myType == "townToTrain")
            {   
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));
                }
                for (int i=0;i<doors.size();i++)
                {
                    myWorld.removeObject((Actor)doors.get(i));
                }
                ((Player)player.get(0)).setLocation(675,450);
                Greenfoot.playSound("portal.wav");
                myWorld.homeMenu();
                myWorld.train();

            }
            else if (a != null && Greenfoot.isKeyDown("space") && (myType == "trainToTown"))
            {
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));

                }
                ((Player)player.get(0)).setLocation(80,450);
                Greenfoot.playSound("portal.wav");
                myWorld.homeMenu();
                List weps = myWorld.getObjects(RightPlatform.class);
                if(!weps.isEmpty())
                    myWorld.removeObject((Actor)(weps.get(0)));
                List weps2 = myWorld.getObjects(LeftPlatform    .class);
                if(!weps2.isEmpty())
                    myWorld.removeObject((Actor)(weps2.get(0)));
                myWorld.town();
            }
            else if (a != null && Greenfoot.isKeyDown("space") && (myType == "dungToTown"))
            {
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));
                }
                ((Player)player.get(0)).setLocation(650,450);
                Greenfoot.playSound("portal.wav");
                myWorld.homeMenu();
                myWorld.town();

            }
        }
    }

    public String getLock()
    {
        return myLock;
    }

    public void unlock()
    {
        setImage(unlockedPortal);
        myLock = "unlocked";
    }

    public void lock()
    {
        setImage(lockedPortal);
        myLock = "locked";
    }

}    

