public class Treasure {
    private String firstTreasure = "Horse Bone";
    private String secondTreasure = "candy";
    private String thirdTreasure = "fish";

    // the three types of treasures

    public Treasure(){

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
