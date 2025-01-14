/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 *
 */
import java.sql.SQLOutput;
import java.util.Scanner;

public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Hunter hunter;
    private boolean hardMode;
    private boolean easyMode;
    private boolean cheatMode;
    private Treasure treasure;
    private Casino casino;
    private int newTown = 0;
    private String gameEnds = "";

    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        hunter = null;
        hardMode = false;
        easyMode = false;
        cheatMode = false;
    }

    // starts the game; this is the only public method
    public void play ()
    {
        welcomePlayer();
        enterTown();
        showMenu();
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TREASURE HUNTER!");
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();


        String hard;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.print("Hard mode? (y/n): ");
            hard = scanner.nextLine();
            if (hard.equals("y") || hard.equals("Y")) {
                System.out.println("You chose hard mode");
                hunter = new Hunter(name, 10);
                hardMode = true;
                validChoice = true;
            }else if(hard.equals("n") || hard.equals("N")){
                System.out.println("You chose easy mode");
                hunter = new Hunter(name, 90);
                easyMode = true;
                validChoice = true;
            } else if (hard.equals("C") || hard.equals("c")) {
                System.out.println("Your in cheat mode!!");
                hunter = new Hunter(name, 100000);
                cheatMode = true;
                validChoice = true;
            }
            else {
                System.out.println("Choose again");
            }
        }
    }

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        newTown = 0;
        double markdown = 0.25;
        double toughness = 0.4;
        if (hardMode)
        {
            // in hard mode, you get less money back when you sell items
            markdown = 0.5;

            // and the town is "tougher"
            toughness = 0.75;

        }
        if (easyMode)
        {
            markdown = 1;

            toughness = 0.40;
        }

        if(cheatMode)
        {
            markdown = 1.5;
        }


        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown, cheatMode);



        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        if (hardMode) {
            currentTown = new Town(shop, toughness, "hard");
        }

        if (easyMode) {
            currentTown = new Town(shop, toughness, "easy");
        }

        if(cheatMode){
            currentTown = new Town(shop, toughness, "cheat");
        }

        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);

        // treasure
        treasure = new Treasure(hunter);

        // casino
        casino = new Casino(hunter);
    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!(choice.equals("X") || choice.equals("x") || gameEnds.equals("x")))
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            System.out.println("***");
            System.out.println(hunter);
            System.out.println(hunter.getTreasureInventory());
            System.out.println(currentTown);
            System.out.println("(B)uy something at the shop.");
            System.out.println("(S)ell something at the shop.");
            System.out.println("(M)ove on to a different town.");
            System.out.println("(L)ook for trouble!");
            System.out.println("(F)ind treasure!");
            System.out.println("(D)iscard a treasure.");
            System.out.println("(C)asino Time to Gamble!");
            System.out.println("Give up the hunt and e(X)it.");
            System.out.println();
            System.out.print("What's your next move? ");
            choice = scanner.nextLine();
            choice = choice.toUpperCase();
            processChoice(choice);
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        if (choice.equals("B") || choice.equals("b") || choice.equals("S") || choice.equals("s"))
        {
            currentTown.enterShop(choice);
        }
        else if (choice.equals("M") || choice.equals("m"))
        {
            if (currentTown.leaveTown())
            {
                boolean newTown = true;
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equals("L") || choice.equals("l"))
        {
            currentTown.lookForTrouble();
        }

        else if (choice.equals("F") || choice.equals("f")){

            if(newTown == 0) {
                System.out.println("Finding treasure");
                treasure.findTreasure();
                System.out.println(treasure.getPrintMessage());
                if(treasure.getPrintMessage().equals("Game Ends!")){
                    gameEnds = "x";
                }
            }
            else{
                System.out.println("Finding treasure may only be used once a town");
                System.out.println("Move to the next town to search again");
            }

            newTown = newTown + 1;
        }

        else if (choice.equals("D") || choice.equals("d")){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type the treasure you want to discard: ");
            String toDiscard = scanner.nextLine();
            treasure.discardTreasure(toDiscard);
        }

        else if (choice.equals("C") || choice.equals("c")){
            if(hunter.getGold() > 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("You've entered a Casino!");
                System.out.print("Type how much you want to wager: ");
                String wager = scanner.nextLine();
                int wagerNumber = Integer.parseInt(wager); // 5
                if(wagerNumber <= hunter.getGold()) {
                    System.out.println("You've Wagered: " + wagerNumber + " gold!");
                    hunter.changeGold(wagerNumber*-1);
                    System.out.println("What is your guess (1-12)?");
                    System.out.print("Your guess: ");
                    String guess = scanner.nextLine();
                    int guessNumber = Integer.parseInt(guess);
                    casino.rollDice(guessNumber, wagerNumber); // 5
                    System.out.println(casino.getPrintMessage());
                }
                else{
                    System.out.println("You don't own that many gold.");
                }

            }
            else{
                System.out.println("You don't have enough gold to play at the Casino.");
                System.out.println("Come Back once you have more gold!");
            }
        }
        else if (choice.equals("X") || choice.equals("x") || gameEnds.equals("x"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }
}
