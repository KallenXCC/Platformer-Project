import greenfoot.*;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    // instance variables - replace the example below with your own
    private static final int EAST = 0;
    private static final int WEST = 1;
    private static final int NORTH = 2;
    private static final int SOUTH = 3;
    private int direction;

    private int speed = 7;
    boolean jumping = false;
    private int vSpeed = 0;
    private int jumpStrength = 17;
    private int acceleration = 1;
    private int apexTimer;
    private int prevY = 0;

    public static int str, dex, intel, gold, currentHealth, currentMana, maxMana;
    public static int maxHealth = 1;

    private GreenfootImage pMoveLeft = new GreenfootImage("char_move_left.png");
    private GreenfootImage pMoveRight = new GreenfootImage("char_move_right.png");

    public static boolean isTurn = true;
    /**
     * Constructor for objects of class Player
     */
    public Player(int s, int d, int i, int maxHP, int maxMP)
    {
        str = s;
        dex = d;
        intel = i; 
        maxHealth = maxHP;
        maxMana = maxMP;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void act()
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        List enemy = myWorld.getObjects(Enemy.class);
        if(Greenfoot.isKeyDown("right"))
        {
            this.setImage(pMoveRight);
            if (!(checkRight()) || vSpeed < 0)
            {
                this.moveRight();
            }
            //             else
            //             {
            //                 setLocation(getX()+3, getY());
            //             }
        } 
        if(Greenfoot.isKeyDown("left"))
        {
            this.setImage(pMoveLeft);
            if (!(checkLeft()) ||vSpeed < 0)
            {
                this.moveLeft();
            }
            //             else
            //             {
            //                  setLocation(getX()-3, getY());
            //             }
        } 
        if (Greenfoot.isKeyDown("up") && jumping == false)
        {
            apexTimer = 100;
            jump();
            Greenfoot.playSound("Jump1.wav");
        }
        checkFall();

        /*if(((ProfileButton)buttons.get(0)).isClicked())
        {
        setDirection(EAST);
        super.move(15);
        world.addObject(display, 199, 118);
        }*/
        if (Greenfoot.isKeyDown("a") && !enemy.isEmpty() && isTurn) {
            attack((Enemy)enemy.get(0));
            isTurn = false;
        }
        //         if (currentHealth <= 0) {
        //             die();
        //         }
    }

    public void jump()
    {
        jumping = true;
        vSpeed =- jumpStrength;
        fall();
    }

    public void fall()
    {
        prevY = getY();
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed+acceleration;
    }

    public void moveRight()
    {
        setLocation ( getX() + speed, getY() );
    }

    public void moveLeft()
    {
        setLocation ( getX() - speed, getY() );
    }

    public void checkFall()
    {
        if (onGround())
        {
            vSpeed = 0;
            jumping = false;
        }
        else
        {
            fall();

        }
    }

    public boolean checkLeft()
    {
        Actor bumper = null;
        int counter = 0;
        int max = (int)(getImage().getHeight() / 2);
        while (counter < max && bumper == null)
        {
            bumper = getOneObjectAtOffset (-1*( getImage().getWidth() / 2), max - counter, Surfaces.class);
            counter++;
        }
        return bumper != null;
    }

    public boolean checkRight()
    {
        Actor bumper = null;
        int counter = 0;
        int max = (int)(getImage().getHeight() / 2);
        while (counter < max && bumper == null)
        {
            bumper = getOneObjectAtOffset ( getImage().getWidth() / 2, max - counter , Surfaces.class);
            counter++;
        }
        return bumper != null;
    }

    public Actor getSurface()
    {
        Actor under = getOneObjectAtOffset ( 0, getImage().getHeight() / 2, Surfaces.class);
        int counter = -1;
        int max = vSpeed + 2;
        while (counter <= max && under == null)
        {
            under = getOneObjectAtOffset ( 0, getImage().getHeight() / 2 + counter, Surfaces.class);
            counter++;
        }
        return under;
    }

    public void tryMove(int dist)
    {
        if (!(checkRight()) && dist > 0 )
            setLocation(getX() + dist, getY());
        if (!(checkLeft()) && dist < 0 )
            setLocation(getX() + dist, getY());
    }

    public boolean onGround()
    {
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        //Add for other surfaces
        Surfaces under = null;

        int counter = 1;
        int max = vSpeed;

        while (counter <= max && under == null)
        {
            under = (Surfaces)getOneObjectAtOffset(0, getImage().getHeight() / 2 + counter, Surfaces.class);
            counter++;
        }
        if (under != null)
        {
            int newY;
            newY = under.getY() - (under.getImage().getHeight()/2) - ((getImage().getHeight() / 2))-1;  
            //System.out.println(under.getY() + " " + under.getImage().getHeight()/2 + " " + newY + " "  + getY());
            setLocation(getX(), newY);
        }
        return under != null;
    }

    public static void setCurrentHealth(int health)
    {
        currentHealth = health;
    }

    public static void setCurrentMana(int mana)
    {
        currentMana = mana;
    }

    public static void setMaxHealth(int maxHP)
    {
        maxHealth = maxHP;
    }

    public static void setMaxMana(int maxM)
    {
        maxMana = maxM;
    }

    public static void setStr(int s)
    {
        str = s;
    }

    public static void setInt(int i)
    {
        intel = i;
    }

    public static void setDex(int d)
    {
        dex = d;
    }

    public static void setGold(int g)
    {
        gold = g;
    }

    public static int getCurrentHealth()
    {
        return currentHealth;
    }

    public static int getCurrentMana()
    {
        return currentMana;
    }

    public static int getMaxHealth()
    {
        return maxHealth;
    }

    public static int getMaxMana()
    {
        return maxMana;
    }

    public static int getStr()
    {
        return str;
    }

    public static int getDex()
    {
        return dex;
    }

    public static int getInt()
    {
        return intel;
    }

    public static int getGold()
    {
        return gold;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
        switch(direction) {
            case SOUTH :
            setRotation(90);
            break;
            case EAST :
            setRotation(0);
            break;
            case NORTH :
            setRotation(270);
            break;
            case WEST :
            setRotation(180);
            break;
            default :
            break;
        }
    }

    public void attack(Enemy enemy) {
        System.out.println(Enemy.getHealth());
        System.out.println("Attacked an enemy");
        enemy.setHealth(Enemy.getHealth() - 1);
        System.out.println(Enemy.getHealth());        
    }

    public void die() {
        getWorld().removeObject(this);
    }
}
