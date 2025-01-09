import java.sql.SQLOutput;

public class Treasure {
    private String firstTreasure = "Horse Bone";
    private String secondTreasure = "Candy";
    private String thirdTreasure = "Fish";
    private boolean first = false;
    private boolean second = false;
    private boolean third = false;
    private String printMessage = "";

    private Hunter hunter;
    // the three types of treasures

    public Treasure(Hunter hunter){
        this.hunter = hunter;
    }

    public void discardTreasure(String treasure){
        hunter.removeTreasureFromKit(treasure);
        printMessage = hunter.getTreasureInventory();

    }

    public String getPrintMessage(){
        return printMessage;
    }



    public void findTreasure() {
        if (!allTreasureFound()) {
            String treasureFound = treasureChance();

            if (!treasureFound.equals("Nothing found")) {

                if (hunter.addTreasure(treasureFound)) {
                    printMessage = "Congrats you have found " + treasureFound + "! \n" +
                                    "You have already found " + treasureFound + " before. \n" +
                                    hunter.getTreasureInventory() +  "\n" +
                                    "You should discard the treasure";

                }

                else {
                    printMessage = "Congrats you have found " + treasureFound + "! \n" +
                                    hunter.getTreasureInventory();
                }
            }

            else {
                printMessage = "You didn't find anything! \uD83D\uDE2D ";
            }
        }

        else{
            printMessage = "Game Ends! ";
        }
    }


    // 25 percent chance to find the treasure
    public String treasureChance(){
        double chance = Math.random();

        if(chance > 0.75){
            first = true;
            return firstTreasure;
        }

        else if(chance > 0.5){
            second = true;
            return secondTreasure;
        }

        else if(chance > 0.25){
            third = true;
            return thirdTreasure;
        }

        return "Nothing found";
    }

    public boolean allTreasureFound(){
       if(first && second && third){
           return true;
       }


           return false;

    }





}
