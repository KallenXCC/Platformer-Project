import greenfoot.*;
import java.util.List;
//import java.awt.Color;
//import java.awt.Font;
import java.util.ArrayList;
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

    private boolean facingRight = false;

    private int speed = 10;
    boolean jumping = false;
    private int vSpeed = 0;
    private int jumpStrength = 17;
    private int acceleration = 1;

    public static int str, dex, intel, gold, currentMana, maxMana;
    public static int currentHealth = 100;
    public static int maxHealth = 100;
    private Bar healthBar;

    private GreenfootImage pMoveLeft = new GreenfootImage("char_move_left.png");
    private GreenfootImage pMoveRight = new GreenfootImage("char_move_right.png");

    public List<Weapon> weapons = new ArrayList<Weapon>();

    public static int atkDelay = 14;
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
        Weapon sword = new Weapon ("sword", 10, 0);
        weapons.add(sword);

        maxHealth = maxHP;
        currentHealth = maxHealth;
        healthBar = new Bar("", "", currentHealth, maxHealth);
        healthBar.setShowTextualUnits(false);
        healthBar.setBarWidth(50);
        healthBar.setBarHeight(5);
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, getX(), getY() - 50);
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
            facingRight = true;
            this.setImage(pMoveRight);
            if (!(checkRight()) ||vSpeed < 0 )
            {
                this.moveRight();
            }
        } 
        if(Greenfoot.isKeyDown("left"))
        {
            facingRight = false;
            this.setImage(pMoveLeft);
            if (!(checkLeft()) || vSpeed < 0)
            {
                this.moveLeft();
            }
        } 
        if (Greenfoot.isKeyDown("up") && jumping == false)
        {
            Greenfoot.playSound("Jump1.wav");
            jump();
        }
        checkFall();

        /*if(((ProfileButton)buttons.get(0)).isClicked())
        {
        setDirection(EAST);
        super.move(15);
        world.addObject(display, 199, 118);
        }*/
        if (Greenfoot.isKeyDown("a")  && atkDelay <= 0) {
            attack();
            atkDelay = 14;
        }
        atkDelay--;
        //         if (currentHealth <= 0) {
        //             die();
        //         }
        healthBar.setLocation(getX(), getY() - 50);
        //destroyEnemy();
        takeDamage();
        healthBar.setValue(currentHealth);
        if (healthBar.getValue() == 0) {
            getWorld().removeObject(healthBar);
            getWorld().removeObject(this);
        }
    }

    public void addWeapon(Weapon item)
    {
        weapons.add(item);
    }

    public List getWeapons()
    {
        return weapons;
    }

    public void equip(String wep)
    {
        for(int a=0; a<weapons.size(); ++a)
        {
            if(weapons.get(a).getName()=="wep")
            {
                break;
            }
        }
    }

    public void jump()
    {
        jumping = true;
        vSpeed =- jumpStrength;
        fall();
    }

    public void fall()
    {
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed+acceleration;
    }

    public void moveRight()
    {
        facingRight = true;
        setLocation ( getX() + speed, getY() );
    }

    public void moveLeft()
    {
        facingRight = false;
        setLocation ( getX() - speed, getY() );
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

    public boolean has(String item)
    {
        if(!weapons.isEmpty())
            for(int a =0; a<weapons.size(); ++a)
            {
                if(weapons.get(a).getName()==item)
                    return true;
            }
        return false;
    }

    public void attack() {
        if (facingRight) {
            getWorld().addObject(new Slicer(7, facingRight), getX() + 35, getY());
        }
        else {
            getWorld().addObject(new Slicer(7, facingRight), getX() - 35, getY());
        }            
    }

    public void takeDamage() {
        //         Actor slicer = getOneIntersectingObject(Slicer.class);  
        //         if(slicer != null) {  
        //             enemyHealth--;
        //         }  
        PlayerWorld myWorld = (PlayerWorld)getWorld();
        //         List player = myWorld.getObjects(Player.class);
        //         Player player1 = ((Player)player.get(0));
        //         List enemy = myWorld.getObjects(Enemy.class);
        //Enemy enemy = getOneIntersectingObject(Enemy.class);
        //if(enemy != null) {
        Actor intersector = getOneIntersectingObject(Enemy.class);
        if(intersector != null) {
            Enemy enemy = (Enemy)intersector;
            currentHealth -= enemy.getAtkPow();
        }
    }

    //     public void die() {
    //         getWorld().removeObject(this);
    //     }
}
