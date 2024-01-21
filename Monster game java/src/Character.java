public class Character {
     String attribute;
     String name;
    private int level = 1;
    int healthL = 30;
    Item[] items = new Item[5];
    Friend[] friends = new Friend[2];
    private int idxF = 0;
    private int power = 10;
    private int exp = 0;
    private int maxHealth;
    private int idx = 0;
    private static Character instance;
    public Character()
    {

    }

    private Character(String attribute, String name)
    {
        this.attribute = attribute;
        this.name = name;
        maxHealth = healthL;
        if(attribute.equals("strength"))
            this.power = 14;

        else if (attribute.equals("durability")) {
            healthL = 40;
            maxHealth = healthL;
        }
    }

    public static Character getInstance(String attribute, String name)
    {
        if(instance == null)
            instance = new Character(attribute, name);
        return  instance;
    }

    public void addItem(Item item)
    {
        items[idx] = item;
        ++idx;
    }
    public void useItem(String idx)
    {
        for(int i = 0; i< items.length; i++){
            if(items[i] != null) {
                if (items[i].level <= level) {
                    if (items[i].name.equals("HealthPotion")  && idx.equals("HealthPotion")) {
                        if (healthL < maxHealth) {
                            if (healthL + 10 <= maxHealth) {
                                healthL += 10;
                                items[i] = null;
                                break;
                            }
                            else {
                                healthL = maxHealth;
                                items[i] = null;
                                break;
                            }
                        }
                        else
                            System.out.println("You cannot use potion because you have full hp");
                    }
                    else if (items[i].name.equals("BigHealthPotion") && idx.equals("BigHealthPotion")) {
                        if(healthL < maxHealth) {
                            healthL = maxHealth;
                            items[i] = null;
                            break;
                        }
                        else
                            System.out.println("You cannot use potion because you have full hp");
                    }
                }
                else {
                    System.out.println("Your level is too low");
                    break;
                }
            }
        }
        Item[] temp = new Item[5];
        for(int i = 0, j = 0; i< items.length; i++)
        {
            if(items[i] != null)
            {
                temp[j] = items[i];
                ++j;
            }
            this.idx = j;
        }
        temp[items.length-1] = null;
        items = temp;
    }

    public int attack()
    {
        return power;
    }

    public void levelUp(int exp)
    {
        this.exp+=exp;
        if(this.exp == 15) {
            this.exp = 0;
            maxHealth+=10;
            power+= 5;
            level+=1;
            System.out.println("Next level: " + level);
            System.out.println("Your stats: ");
            System.out.println(this);
        }
    }

    public void takeFriend()
    {
        friends[idxF] = new Friend();
        ++idxF;
    }
    public int useFriend()
    {
        int count = 0;
        for (Friend friend : friends) {
            if (friend != null)
                ++count;
        }

        return count * friends[0].attack();
    }

    public String stats()
    {
        return "level: "+level + " Health: " + healthL + " power: " +  power+"\n";
    }
    public String toString()
    {
        return "level: "+level + " maxHealth: "+maxHealth  + " power: " +  power+"\n";
    }

    public int getIdx() {
        return idx;
    }

    public int getIdxF() {
        return idxF;
    }

}
