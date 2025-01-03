public class Treasure {
    private String firstTreasure = "Horse Bone";
    private String secondTreasure = "candy";
    private String thirdTreasure = "fish";
    // three types of treasures

    public Treasure(){


    }

    public String treasureChance(){
        double chance = Math.random();

        if(chance > 0.66){
            return firstTreasure
        }
    }
}
