public class Casino {
    private int wagerGold;
    private Hunter customer;
    private String printMessage;

    public Casino(Hunter hunter){
        customer = hunter;
        printMessage = "";
    }

    public void rollDice(int numberGuessed, int goldWagered ){
        this.wagerGold = goldWagered;
        // 1-12 max = 6 min = 1

        int rollNumber = (int) (Math.random() * (6 - 1 + 1 )) + 1;
        int secondRoll= (int) (Math.random() * (6 - 1 + 1 )) + 1;
        rollNumber = rollNumber + secondRoll;
        
        printMessage = "The Dice is ROLLED! *Drum Roll* \n" +
                "The Dice roll is " + rollNumber + ".";

        if(rollNumber == numberGuessed){

            wagerGold = wagerGold * 2; // 10
            customer.changeGold(wagerGold); // 20
            printMessage += "\nNice you guessed the lucky roll! \nYou get double the gold back!" + "\nGold amount: " + customer.getGold();
        }

        else if (rollNumber - numberGuessed == 1 || rollNumber - numberGuessed == 2 ||
                rollNumber - numberGuessed == -2 || rollNumber - numberGuessed == -1) {
            customer.changeGold(wagerGold);
            printMessage += "\nWithin two away from the roll and so you get all of the gold you wagered back!" +
                    "\nGold: " + customer.getGold();
        }
        else{
            printMessage += "\nUnlucky, you lose " + wagerGold + " gold \nCome back next time!";
        }
    }

    public String getPrintMessage(){
        return printMessage;
    }

}
