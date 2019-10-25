import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Structures
{
    String myType;
    /**
     * Act - do whatever the Shop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Door(String type)
    {
        myType = type;
    }

    public void act() 
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List player = myWorld.getObjects(Player.class);
        List doors = myWorld.getObjects(Door.class);
        List portals = myWorld.getObjects(Portal.class);
        List buttons = myWorld.getObjects(ProfileButton.class);
        Actor a = getOneObjectAtOffset(0,0,Player.class);
        if(a != null) {
            if (Greenfoot.isKeyDown("space") && (myType == "townToBar"))
            {
                for (int i=0;i<doors.size();i++)
                {
                    myWorld.removeObject((Actor)doors.get(i));
                }
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));
                }
                ((Player)player.get(0)).setLocation(420, 450);
                Greenfoot.playSound("door_close.wav");
                myWorld.homeMenu();
                myWorld.bar();
            }
            else if (Greenfoot.isKeyDown("space") && myType == "barToTown")
            {
                for (int i=0;i<doors.size();i++)
                {
                    myWorld.removeObject((Actor)doors.get(i));
                }
                ((Player)player.get(0)).setLocation(170, 450);
                Greenfoot.playSound("door_close.wav");
                myWorld.homeMenu();
                if(!buttons.isEmpty())
                {
                    for(int m=0; m<buttons.size(); ++m)
                    {
                        if (((ProfileButton)(buttons.get(m))).getType() == "buttClench")    
                        {
                            myWorld.removeObject((Actor)(buttons.get(m)));
                        }
                    }
                }
                myWorld.town();
            }
            else if (Greenfoot.isKeyDown("space") && myType == "townToShop")
            {
                Player p = (Player)a;
                for (int i=0;i<doors.size();i++)
                {
                    myWorld.removeObject((Actor)doors.get(i));
                }
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));
                }
                ((Player)player.get(0)).setLocation(420, 450);
                Greenfoot.playSound("door_close.wav");
                myWorld.homeMenu();
                myWorld.shop();
            }
            else if (Greenfoot.isKeyDown("space") && myType == "shopToTown")
            {
                for (int i=0;i<doors.size();i++)
                {
                    myWorld.removeObject((Actor)doors.get(i));
                }
                for (int i=0;i<portals.size();i++)
                {
                    myWorld.removeObject((Actor)portals.get(i));
                }
                List weps = myWorld.getObjects(Weapon.class);
                if(!weps.isEmpty())
                {
                    myWorld.removeObject((Actor)(weps.get(0)));
                }
                ((Player)player.get(0)).setLocation(480, 450);
                Greenfoot.playSound("door_close.wav");
                myWorld.homeMenu();
                myWorld.town();
            }
        }
    }    
}
