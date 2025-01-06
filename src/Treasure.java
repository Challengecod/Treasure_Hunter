import java.sql.SQLOutput;

public class Treasure {
    private String firstTreasure = "Horse Bone";
    private String secondTreasure = "candy";
    private String thirdTreasure = "fish";
    private Hunter hunter;
    // the three types of treasures

    public Treasure(Hunter hunter){
        this.hunter = hunter;
    }

    public void findTreasure(){
        System.out.println("Method works");
        String treasureFound = treasureChance();

        if(!treasureFound.equals("Nothing")) {
            if (hunter.addTreasure(treasureFound) == false) {
                System.out.println("You have already found the treasure.");
            }

            else {
                System.out.println("Congrats you have found " + treasureFound + "!");

            }
        }

        else{
            System.out.println("You didn't find anything! \uD83D\uDE2D ");
        }

    }


    // 25 percent chance to find the treasure
    public String treasureChance(){
        double chance = Math.random();

        if(chance > 0.75){
            return firstTreasure;
        }

        else if(chance > 0.5){
            return secondTreasure;
        }

        else if(chance > 0.25){
            return thirdTreasure;
        }

        return "Nothing found";
    }

    public void addTreasureKit(){
        treasureChance();
    }





}
