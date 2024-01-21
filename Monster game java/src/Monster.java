public class Monster extends Character {
    private int expToGive = 5;
    private  int power = 4;
    private String type = "monster";

    public Monster()
    {

    }

    @Override
    public int attack()
    {
        return power;
    }

    public int getExpToGive() {
        return expToGive;
    }

    public String getType() {
        return type;
    }
}
