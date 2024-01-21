import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean win = true;
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.println("Name of your character?");
            String charName = scanner.next();
            charName = checkIfNotDigit(charName);

            System.out.println("Choose the attribute of your character(strength, durability )");
            String charAttribute = scanner.next();
            while(!charAttribute.equals("strength") && !charAttribute.equals("durability"))
            {
                System.out.println("Incorrect data. Try again");
                charAttribute = scanner.next();
            }

            Character character = Character.getInstance(charAttribute, charName);

            Monster[] game = new Monster[20];

            int quantityM = (int)(Math.random() * (13-1)+1);
            for(int i = 0; i<quantityM; i++)
            {
                int pos = (int)(Math.random() * (20-1)+1);
                if(game[pos] == null)
                    game[pos] = new Monster();
            }

            int quantityF = (int)(Math.random() * (4-1)+1);
            for(int i = 0; i<quantityF; i++)
            {
                int pos = (int)(Math.random() * (20-2)+2);
                if(game[pos] == null)
                    game[pos] = new Friend();
            }

            for (Monster monster: game) {
                if(monster!= null) {
                    if (monster.getType().equals("monster")) {
                        System.out.println();
                        System.out.println("You was attacked by a monster");


                        if (character.getIdxF() > 0) {
                            System.out.println("Do you want to use your friend/s to help you? Y/N");
                            String ansF = scanner.next();
                            while (!ansF.equals("Y") && !ansF.equals("N")) {
                                System.out.println("Incorrect data. Try again");
                                ansF = scanner.next();
                            }
                            if (ansF.equals("Y")) {
                                monster.healthL -= character.useFriend();
                                System.out.println("Monster has " + monster.healthL + " hp" + "\n");
                            }
                        }

                        while (monster.healthL > 0 && character.healthL > 0) {
                            System.out.println("Do you want to attack? Y/N");
                            String att = scanner.next();
                            while (!att.equals("Y") && !att.equals("N")) {
                                System.out.println("Incorrect data. Try again");
                                att = scanner.next();
                            }

                            if (att.equals("Y")) {

                                monster.healthL -= character.attack();

                                if (monster.healthL > 0 && character.healthL > 0) {
                                    character.healthL -= monster.attack();
                                    System.out.println("Monster has " + monster.healthL + " hp");
                                    System.out.println("You have " + character.healthL + " hp\n");
                                }
                            } else {
                                character.healthL -= monster.attack();
                                if (character.healthL > 0) {
                                    System.out.println("You have " + character.healthL + " hp\n");
                                }
                            }
                        }
                        if (character.healthL <= 0) {
                            win = false;
                            break;
                        }

                        System.out.println("You beat the monster\n");
                        System.out.println("Do you want to domesticate defeated  monster? Y/N");
                        String ans5 = scanner.next();
                        while (!ans5.equals("Y") && !ans5.equals("N")) {
                            System.out.println("Incorrect data. Try again");
                            ans5 = scanner.next();
                        }
                        if (ans5.equals("Y")) {
                            domesticate(character);
                        }

                        int getItem = (int) (Math.random() * (3 - 1) + 1);
                        if (getItem == 2) {
                            if (character.getIdx() != 5) {
                                HashMap<String, Integer> randomItem = getRandomItem();
                                String key = randomItem.keySet().toArray()[0].toString();
                                Integer value = randomItem.get(key);
                                System.out.println("You got: " + key + " level: " + value + "\n");
                                character.addItem(new Item(key, value));
                            }
                        }

                        character.levelUp(monster.getExpToGive());

                        if (character.getIdx() > 0) {
                            System.out.println("Do you want to use your items? Y/N");
                            String ans = scanner.next();
                            while (!ans.equals("Y") && !ans.equals("N")) {
                                System.out.println("Incorrect data. Try again");
                                ans = scanner.next();
                            }
                            if (ans.equals("Y")) {
                                System.out.println("Which one do you want to use?");
                                displayEq(character);
                                String ans2 = scanner.next();
                                while (!ans2.equals("HealthPotion") && !ans2.equals("BigHealthPotion")) {
                                    System.out.println("Incorrect data. Try again");
                                    ans2 = scanner.next();
                                }
                                character.useItem(ans2);
                            }
                        }
                        System.out.println();
                        System.out.println("Your stats: ");
                        System.out.println(character.stats());
                    }
                    else if (monster.getType().equals("friend")) {
                        System.out.println("You found a homeless creature");

                        System.out.println("Do you want to domesticate that creature? Y/N\n");
                        String ans = scanner.next();
                        while (!ans.equals("Y") && !ans.equals("N")) {
                            System.out.println("Incorrect data. Try again");
                            ans = scanner.next();
                        }
                        if (ans.equals("Y")) {
                            domesticate(character);
                        }
                    }
                }
            }
            if(win)
                System.out.println("You defeated all the monsters. Congratulation " + character.name + "!!!");
            else
                System.out.println("Game Over");
            }
        catch (IfDigitException e)
        {
            e.printStackTrace();
        }
    }

    private static void domesticate(Character character) {
            int getFriend = (int) (Math.random() * (3 - 1) + 1);
            if (getFriend== 2) {
                if (character.getIdxF() != 2) {
                    System.out.println("You've got a friend\n");
                    character.takeFriend();
                }
                else
                    System.out.println("You don't have enough space to take him\n");
            }
            else
                System.out.println("Creature is dead\n");
    }

    private static HashMap<String, Integer> getRandomItem()
    {
        HashMap<String, Integer> itemsName = new HashMap<>();
        itemsName.put("HealthPotion",1);
        itemsName.put("BigHealthPotion",2);

        Set<String> keySet = itemsName.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomKey = keyList.get(randIdx);
        Integer randomValue = itemsName.get(randomKey);

        HashMap<String, Integer> randomItem = new HashMap<>();
        randomItem.put(randomKey, randomValue);
        return randomItem;
    }

    private static void displayEq(Character character)
    {
        System.out.println("You have " + character.getIdx() + " out of 5");
        for (Item item:character.items ) {
            if(item != null)
                System.out.println(item.name);
        }
    }

    private static String checkIfNotDigit(String str) throws IfDigitException
    {
        if(str.matches("\\d+"))
            throw new IfDigitException("Incorrect data. Try again.\n Only letters with digit, no only digit");
        return str;
    }
}