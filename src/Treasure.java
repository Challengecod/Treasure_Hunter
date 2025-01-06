import java.sql.SQLOutput;

public class Treasure {
    private String firstTreasure = "Horse Bone";
    private String secondTreasure = "candy";
    private String thirdTreasure = "fish";
    private Hunter hunter;
    // the three types of treasures

    public Treasure(){
        this.hunter = null;
    }

    public void enter(Hunter hunter){
        this.hunter = hunter;
        String treasureFound = treasureChance();

        if(hunter.addTreasure(treasureFound) == false){
            System.out.println("You have already found the treasure.");
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
