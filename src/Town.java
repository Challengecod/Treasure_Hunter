/**
 * The Town Class is where it all happens.
 * The Town is designed to manage all of the things a Hunter can do in town.
 */
public class Town
{
    //instance variables
    private Hunter hunter;
    private Shop shop;
    private Terrain terrain;
    private String printMessage;
    private boolean toughTown;
    private boolean hardMode;
    private boolean easyMode;
    private boolean cheatMode;

    //Constructor
    /**
     * The Town Constructor takes in a shop and the surrounding terrain, but leaves the hunter as null until one arrives.
     * @param s The town's shoppe.
     * @param t The surrounding terrain.
     */
    public Town(Shop shop, double toughness, String mode)
    {
        this.shop = shop;
        this.terrain = getNewTerrain();

        // the hunter gets set using the hunterArrives method, which
        // gets called from a client class
        hunter = null;

        printMessage = "";

        // higher toughness = more likely to be a tough town
        toughTown = (Math.random() < toughness);

        if(mode.equals("hard")) {
            hardMode = true;
        }

        if(mode.equals("easy")) {
            easyMode = true;
        }

        if(mode.equals("cheat")){
            cheatMode = true;
        }


    }

    public String getLatestNews()
    {
        return printMessage;
    }

    /**
     * Assigns an object to the Hunter in town.
     * @param h The arriving Hunter.
     */
    public void hunterArrives(Hunter hunter)
    {
        this.hunter = hunter;
        printMessage = "Welcome to town, " + hunter.getHunterName() + ".";

        if (toughTown)
        {
            printMessage += "\nIt's pretty rough around here, so watch yourself.";
        }
        else
        {
            printMessage += "\nWe're just a sleepy little town with mild mannered folk.";
        }
    }

    /**
     * Handles the action of the Hunter leaving the town.
     * @return true if the Hunter was able to leave town.
     */
    public boolean leaveTown()
    {
        boolean canLeaveTown = terrain.canCrossTerrain(hunter);
        if (canLeaveTown)
        {
            String item = terrain.getNeededItem();
            printMessage = "You used your " + item + " to cross the " + terrain.getTerrainName() + ".";
            if (checkItemBreak())
            {
                hunter.removeItemFromKit(item);
                printMessage += "\nUnfortunately, your " + item + " broke.";
            }

            return true;
        }

        printMessage = "You can't leave town, " + hunter.getHunterName() + ". You don't have a " + terrain.getNeededItem() + ".";
        return false;
    }

    public void enterShop(String choice)
    {
        shop.enter(hunter, choice);
    }

    /**
     * Gives the hunter a chance to fight for some gold.<p>
     * The chances of finding a fight and winning the gold are based on the toughness of the town.<p>
     * The tougher the town, the easier it is to find a fight, and the harder it is to win one.
     */
    public void lookForTrouble()
    {
        if(cheatMode)
        {
            printMessage = "You want trouble, stranger!  You got it!\nOof! Umph! Ow!\n";
            printMessage += "Okay, stranger! You proved yer mettle. Here, take my gold.";
            printMessage += "\nYou won the brawl and receive 100 gold.";
            hunter.changeGold(100);
        }
        else {

            double noTroubleChance;
            if (toughTown) {
                noTroubleChance = 0.66;
            } else {
                noTroubleChance = 0.33;
            }

            if (Math.random() > noTroubleChance) // greater than 0.66
            {
                printMessage = "You couldn't find any trouble";
            } else {
                printMessage = "You want trouble, stranger!  You got it!\nOof! Umph! Ow!\n";
                int goldDiff = 0;
                if (hardMode) {
                    goldDiff = (int) (Math.random() * 10) + 1;
                }

                if (easyMode) {
                    goldDiff = (int) (Math.random() * 30) + 1;
                }

                if (Math.random() > noTroubleChance) // greater than 0.3
                {
                    printMessage += "Okay, stranger! You proved yer mettle. Here, take my gold.";
                    printMessage += "\nYou won the brawl and receive " + goldDiff + " gold.";
                    hunter.changeGold(goldDiff);
                } else {
                    if (easyMode) {
                        if (goldDiff < 10) {
                            printMessage += "That'll teach you to go lookin' fer trouble in MY town! Now pay up!";
                            printMessage += "\nYou lost the brawl and you pay " + goldDiff + " gold.";
                            hunter.changeGold(-1 * goldDiff);
                        } else {

                            printMessage += "That'll teach you to go lookin' fer trouble in MY town! Now pay up!";
                            printMessage += "\nYou lost the brawl and you pay " + goldDiff + " gold. \n";
                            goldDiff = goldDiff - 10;
                            printMessage += "But your in easy mode so you pay " + goldDiff + " gold.";
                            hunter.changeGold(-1 * goldDiff);
                        }
                    } else {
                        printMessage += "That'll teach you to go lookin' fer trouble in MY town! Now pay up!";
                        printMessage += "\nYou lost the brawl and pay " + goldDiff + " gold.";
                        hunter.changeGold(-1 * goldDiff);
                    }
                }
            }
        }
    }

    public String toString()
    {
        return "This nice little town is surrounded by " + terrain.getTerrainName() + ".";
    }

    /**
     * Determines the surrounding terrain for a town, and the item needed in order to cross that terrain.
     *
     * @return A Terrain object.
     */
    private Terrain getNewTerrain()
    {
        double rnd = Math.random();
        if (rnd < .2)
        {
            return new Terrain("Mountains", "Rope");
        }
        else if (rnd < .4)
        {
            return new Terrain("Ocean", "Boat");
        }
        else if (rnd < .6)
        {
            return new Terrain("Plains", "Horse");
        }
        else if (rnd < .8)
        {
            return new Terrain("Desert", "Water");
        }
        else
        {
            return new Terrain("Jungle", "Machete");
        }
    }

    /**
     * Determines whether or not a used item has broken.
     * @return true if the item broke.
     */
    private boolean checkItemBreak()
    {
        double rand = Math.random();
        return (rand < 0.5);
    }
}
