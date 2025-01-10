public class Casino {
    private int wagerGold;
    private Hunter customer;
    private String printMessage;

    public Casino(Hunter hunter){
        customer = hunter;
        printMessage = "";
    }

    public void rollDice(int wagerGold ){

        // 1-12 max = 12 min = 1
        int rollNumber = (int) (Math.random() * (12 - 1 + 1 )) + 1;
        printMessage = "The Dice is ROLLED! *Drum Roll* \n" +
                "The Dice roll is " + rollNumber + ".";

        if(rollNumber == wagerGold){

            wagerGold = wagerGold*2;
            customer.changeGold(wagerGold);
            printMessage += "\nNice you guessed the lucky roll!\n You get double the gold back!" + "\n Gold amount: " + customer.getGold();
        }

        else if (rollNumber - wagerGold == 2 || rollNumber - wagerGold == -2) {
            customer.changeGold(wagerGold);
            printMessage += "\nTwo away from the roll and so you get all of the gold you wagered back!" +
                    "\nGold: " + customer.getGold();

        }
        else{

            printMessage += "\nUnlucky, you lose " + wagerGold + "\nCome back next time!";
        }
    }

    public String getPrintMessage(){
        return printMessage;
    }

}
