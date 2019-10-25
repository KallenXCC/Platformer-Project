import greenfoot.*;
import java.util.List;
/**
 * @author BuckLe Byhan
 * @version 2013
 */
public class Slicer extends Actor
{
    public static int swordDisappears = 7;
    public static boolean swordRight = true;

    public Slicer(int time, boolean right) {
        swordDisappears = time;
        swordRight = right;
        if (swordRight) {
            this.setImage("onesword.png");
        }
        else {
            this.setImage("oneswordLeft.png");
        }
    }

    public void act() 
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List player = myWorld.getObjects(Player.class);
        Player player1 = ((Player)player.get(0));
        if (swordRight) {
            this.setImage("onesword.png");
            this.setLocation(player1.getX() + 35, player1.getY());
        }
        else {
            this.setImage("oneswordLeft.png");
            this.setLocation(player1.getX() - 35, player1.getY());
        }
        if (getWorld() != null)  
        {
            swordDisappears--;
        }
        if(getWorld()!= null && swordDisappears <= 0)
        {
            getWorld().removeObject(this);
        }
    }    

}