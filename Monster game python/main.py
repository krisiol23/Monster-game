import random
from characters import Hero, Monster, Friend
from Item import Item
from IfDigitException import IfDigitException

def domesticate(character):
    getFriend = random.randint(1,2)
    if(getFriend == 2):
        if(character.getIdxF() != 2):
            print("You've got a friend\n")
            character.takeFriend()
        else:
            print("You don't have enough space")
    else:
        print("Creature is dead\n")

def getRandomItem():
    dictI = {"HealthPotion":1, "BigHealthPotion": 2}
    randomKey = random.choice(list(dictI.keys()))
    randomVal = dictI[randomKey]
    return [randomKey, randomVal]

def displayEq(character):
    print(f"You have {character.getIdx} out of 5")
    for item in character.items:
        if(item!=None):
            print(item.name)

def checkIfNotDigit(str):
    if(str.isdigit()):
        raise IfDigitException("Incorrect data. Try again.\n Only letters with digit, no only digit")
    return str

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    win = True
    print("Name of your character?")

    charName = input()
    charName = checkIfNotDigit(charName)

    print("Choose the attribute of your character(strength, durability )")
    charAttribute = input()
    while (charAttribute != "strength" and charAttribute != "durability"):
        print("Incorrect data. Try again")
        charAttribute = input()

    character = Hero(charAttribute, charName)

    game = [None for i in range(21)]
    quantityM = random.randint(1,13)
    for i in range(quantityM):
        pos = random.randint(0,20)
        if(game[pos] == None):
            game[pos] = Monster()

    quantityF = random.randint(1, 4)
    for i in range(quantityF):
        pos = random.randint(0, 20)
        if (game[pos] == None):
            game[pos] = Friend()

    for monster in game:
        if(monster!= None):
            if(monster.getType() == "monster"):
                print()
                print("You was attacked by a monster")
                if(character.getIdxF() > 0):
                    print("Do you want to use your friend/s to help you? Y/N")
                    ansF = input()
                    while(ansF != "Y" and ansF !="N"):
                        print("Incorrect data. Try again")
                        ansF = input()
                    if(ansF == "Y"):
                        monster.healthL -= character.useFriend()
                        print(f"Monster has: {monster.healthL} hp")

                while(monster.healthL > 0 and character.healthL > 0):
                    print("Do you want to attack? Y/N")
                    ans = input()
                    while (ans != "Y" and ans != "N"):
                        print("Incorrect data. Try again")
                        ans = input()

                    if(ans == "Y"):
                        monster.healthL -= character.attack()

                        if(monster.healthL > 0 and character.healthL > 0):
                            character.healthL -= monster.attack()
                            print(f"Monster has: {monster.healthL} hp")
                            print(f"You have: {character.healthL} hp")
                    else:
                        character.healthL -= monster.attack()
                        if(character.healthL > 0):
                            print(f"You have: {character.healthL} hp")

                if(character.healthL<=0):
                    win = False
                    break

                print("You beat the monster\n")
                print("Do you want to domesticate defeated  monster? Y/N")
                ans = input()
                while (ans != "Y" and ans != "N"):
                    print("Incorrect data. Try again")
                    ans = input()
                if(ans == "Y"):
                    domesticate(character)

                getItem = random.randint(1,2)
                getItem = 2
                if(getItem == 2):
                    if(character.getIdx() != 5):
                        randomItem = getRandomItem()
                        key = randomItem[0]
                        val = randomItem[1]
                        print(f"You got: {key} level: {val}\n")
                        character.addItem(Item(key, val))
                character.levelUp(monster.getExpToGive())

                if(character.getIdx() > 0):
                    print("Do you want to use your items? Y/N")
                    ans = input()
                    while (ans != "Y" and ans != "N"):
                        print("Incorrect data. Try again")
                        ans = input()

                    if(ans == "Y"):
                        print("Which one do you want to use?")
                        displayEq(character)
                        ans2 = input()
                        while(ans2 != "HealthPotion" and ans2 != "BigHealthPotion"):
                            print("Incorrect data. Try again")
                            ans2 = input()
                        character.useItem(ans2)
                print()
                print("Your stats: ")
                print(character.stats())
            elif(monster.getType() == "friend"):
                print("You found a homeless creature")
                print("Do you want to domesticate that creature? Y/N\n")
                ans = input()
                while (ans != "Y" and ans != "N"):
                    print("Incorrect data. Try again")
                    ans = input()
                if(ans == "Y"):
                    domesticate(character)
    if(win):
        print(f"You defeated all the monsters. Congratulation {character.name}!!!")
    else:
        print("Game Over")
