public class Treasure {
    private String firstTreasure = "Horse Bone";
    private String secondTreasure = "candy";
    private String thirdTreasure = "fish";
    private boolean ifTreasureFound = false;
    // three types of treasures
    // secondTreasure in the town
    // firstTreausre

    // search secondtreasure or nothing
    // first
    //boolean ifTreasureFound  = false



    public Treasure(){


    }

    //first Town
    public void hunterArrives(Hunter hunter) {

    }


    public String treasureChance(){
        double chance = Math.random();

        if(chance > 0.66){
            return firstTreasure;
        }

        if(chance > 0.33){
            return secondTreasure;
        }

        if(chance > 0){
            return thirdTreasure;
        }

        return "tresuare";
    }

    public String findTreasure(){
        double chance = Math.random();
        if (!ifTreasureFound){

        }

        if(chance > 0.5){
            ifTreasureFound = true;
            return treasureChance() + "found";
        }

        else {
            return "Nothing found";
        }
    }

}
