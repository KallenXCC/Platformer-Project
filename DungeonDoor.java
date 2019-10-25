import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class DungeonDoors here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DungeonDoor extends Structures
{
    String myType;
    /**
     * Act - do whatever the DungeonDoors wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public DungeonDoor(String type)
    {
        myType = type;
    }

    public void act() 
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List player = myWorld.getObjects(Player.class);
        List doors = myWorld.getObjects(Door.class);
        List dungDoors = myWorld.getObjects(DungeonDoor.class);
        List portals = myWorld.getObjects(Portal.class);
        List buttons = myWorld.getObjects(ProfileButton.class);
        Actor a = getOneObjectAtOffset(0,0,Player.class);
        if (a!=null && Greenfoot.isKeyDown("space") && (myType == "train"))
        {
            Greenfoot.playSound("door_close.wav");
            for (int i=0;i<portals.size();i++)
            {
                myWorld.removeObject((Actor)portals.get(i));
            }
                        for (int i=0;i<dungDoors.size();i++)
            {
                myWorld.removeObject((Actor)dungDoors.get(i));
            }
            myWorld.training();
        }
        else if ((a != null && Greenfoot.isKeyDown("space") ) && (myType == "Dungeon1"))
        {
            Greenfoot.playSound("door_close.wav");
                        for (int i=0;i<portals.size();i++)
            {
                myWorld.removeObject((Actor)portals.get(i));
            }
                        for (int i=0;i<dungDoors.size();i++)
            {
                myWorld.removeObject((Actor)dungDoors.get(i));
            }
            myWorld.dungeon1();
        }
    }    
}
