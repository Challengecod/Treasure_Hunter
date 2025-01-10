public class Casino {
    private int wagerGold;
    private Hunter customer;

    public Casino(Hunter hunter){
        customer = hunter;
    }

    public void rollDice(int wagerGold ){
        // 1-12 max = 12 min = 1
        int rollNumber = (int) (Math.random() * (12 - 1 + 1 )) + 1;
    }

}
