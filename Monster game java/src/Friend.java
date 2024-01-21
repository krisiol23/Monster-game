public class Friend extends Monster{
    private  int power = 5;
    private String type = "friend";

    public Friend()
    {

    }

    @Override
    public int attack()
    {
        return power;
    }

    public String getType() {
        return type;
    }
}
