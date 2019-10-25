import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
/**
 * A world where wombats live.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class PlayerWorld extends World
{
    int level = 0;
    private Player player;
    //private Enemy1 enemy1;
    private EnemyBoss enemyBoss;

    public PlayerWorld() 
    {
        super(750,800, 1);
        //dungeon1();
        town();
        //training();
        initWorld();
        setPaintOrder(Bar.class, Player.class, Enemy.class, Slicer.class, Door.class, Portal.class, ProfileButton.class, Weapon.class, StatsPage.class, Inventory.class, MessageDisplay.class);
    }

    public void initWorld()
    {
        player = new Player(5, 3, 5, 100, 10);
        addObject(player, 348, 450);
        //enemy1 = new Enemy1();
        //addObject(enemy1, 398, 450);
                 
        //addObject(new MessageDisplay("AAFDAFAF"), 375, 651);
    }

    public void act()
    {

    }

    public int getLevel()
    {
        return level;
    }

    public void enterBattle(Enemy enemy)
    {

    }

    public void dung()
    {
        removeButtons();
        setBackground("Dung_Main.png");
        Portal dungToTown = new Portal("dungToTown", "unlocked");
        DungeonDoor door = new DungeonDoor("Dungeon1");
        
        addObject(door, 373, 458);
        addObject(dungToTown, 30, 495);
        addButtons();
    }

    public void train()
    {
        removeButtons();
        setBackground("Train_Main.png");
        DungeonDoor door = new DungeonDoor("train");
        Portal trainToTown = new Portal("trainToTown", "unlocked");

        addObject(door, 373, 458);
        addObject(trainToTown, 725, 495);
        addButtons();
    }
    
    public void dungeon1()
    {
        setBackground("Dungeon_1.png");
        
        for (int i=750; i > 490; i-=50)
        {
            addObject(new Block(), i, 360);
        }
        for (int i=0; i < 290; i+= 50)
        {
            addObject(new Block(), i, 360);
        }
        for (int i=25; i < 750; i+= 50)
        {
            addObject(new Block(), i, 775);
        }
        addObject(new Block(), 375, 360);
        
        addObject(new Block(), 375, 240);
        addObject(new Block(), 425, 240);
        addObject(new Block(), 325, 240);
        addObject(new Block(), 25, 775);
        addObject(new Block(), 25, 775);
        
        enemyBoss = new EnemyBoss();
        addObject(enemyBoss, 550, 400);
    }

    public void training()
    {
        removeButtons();
        setBackground("Train_Map.png");
        LeftPlatform leftPlat = new LeftPlatform();
        RightPlatform rightPlat = new RightPlatform();
        Portal trainPortal = new Portal("trainToTrainMain", "locked");

        addObject(trainPortal, 725, 495);
        addObject(leftPlat, 200, 350);
        addObject(rightPlat, 600, 400);

        addButtons();
    }

    public void town()
    {
        removeButtons();
        setBackground("towm_dem2.png");
        Portal portalToDung = new Portal("townToDung", "unlocked");
        Portal portalToTrain = new Portal("townToTrain", "unlocked");
        Door barDoor = new Door("townToBar");
        Door shopDoor = new Door("townToShop");
        addObject(portalToDung, 725, 495);
        addObject(portalToTrain, 30, 495);
        addObject(barDoor, 120, 427);
        addObject(shopDoor, 397, 427);

        for (int i=25; i < 750; i+= 50)
        {
            addObject(new Block(), i, 525);
        }
        
        addButtons();
    }

    public void shop()
    {
        removeButtons();
        Door door = new Door("shopToTown");
        setBackground("Shop_Main.png");
        addObject(new msword(), 245, 473);
        addObject(door, 550, 427);
        addButtons();
    }

    public void bar()
    {
        removeButtons();
        Door door = new Door("barToTown");
        ProfileButton buttClenchButton = new ProfileButton("Butt_Clench.png", "buttClench");
        setBackground("Bar_Inside.png");

        addObject(buttClenchButton, 225, 100);
        addObject(door, 593, 427);
        addButtons();
    }

    public void addButtons()
    {

        ProfileButton moveButton = new ProfileButton("MoveButton.png", "move");
        ProfileButton inventoryButton = new ProfileButton("InventoryButton.png", "inventory");
        ProfileButton profButton = new ProfileButton("ProfileButton.png", "profile");

        addObject(moveButton, 160, 595);
        addObject(inventoryButton, 482, 583);
        addObject(profButton, 162, 715);
    }

    public void profilePage()
    {
        removeButtons();
        StatsPage stats = new StatsPage(player);
        addObject(stats, 375, 651);
        ProfileButton backButton = new ProfileButton("BackButton.png", "back");
        addObject(backButton, 60, 575);
    }

    public void inventoryPage()
    {
        removeButtons();
        Inventory inv = new Inventory(player.getWeapons());
        addObject(inv, 375, 651);
        ProfileButton backButton = new ProfileButton("BackButton.png", "back");
        addObject(backButton, 60, 575);
        if(player.has("msword"))
            addObject(new ProfileButton("swordMenu.png", "sword"), 200, 625);
    }

    public void homeMenu()
    {
        removeButtons();
        List statsPage = getObjects(StatsPage.class);
        if (!statsPage.isEmpty())
            removeObject((Actor)statsPage.get(0));
        List inv = getObjects(Inventory.class);
        if (!inv.isEmpty())
            removeObject((Actor)inv.get(0));
        addButtons();
    }

    public void removeButtons()
    {
        List buttons = getObjects(ProfileButton.class);
        if(!buttons.isEmpty())
        {
            for(int a=0; a<buttons.size(); ++a)
            {
                if (((ProfileButton)(buttons.get(a))).getType() != "buttClench")    
                {
                    removeObject((Actor)(buttons.get(a)));
                }
            }
        }
    }
}