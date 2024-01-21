class Character:
    healthL = 30
    def __int__(self):
        pass

class Hero(Character):
    level = 1
    __idxF = 0
    power = 10
    exp = 0
    __idx = 0
    maxHealth = 0
    items = [None for i in range(5)]
    friends = [None for i in range(2)]

    def __init__(self, attribute, name):
        self.attribute = attribute
        self.name = name
        self.maxHealth = super().healthL
        if (attribute == "strength"):
            self.power = 14
        elif(attribute == "durability"):
            self.healthL = 40
            self.maxHealth = self.healthL

    def addItem(self, item):
        self.items[self.__idx] = item
        self.__idx+=1

    def useItem(self, idx):
        for i in range(len(self.items)):
            if(self.items[i]!= None):
                if(self.items[i].level <= self.level):
                    if(self.items[i].name == "HealthPotion" and idx == "HealthPotion"):
                        if(self.healthL < self.maxHealth):
                            if(self.healthL + 10 <= self.maxHealth):
                                self.healthL += 10
                                self.items[i] = None
                                break
                            else:
                                self.healthL = self.maxHealth
                                self.items[i] = None
                                break
                        else:
                            print("You cannot use potion because you have full hp")
                    elif(self.items[i].name == "BigHealthPotion" and idx == "BigHealthPotion"):
                        if(self.healthL < self.maxHealth):
                            self.healthL = self.maxHealth
                            self.items[i] = None
                            break
                        else:
                            print("You cannot use potion because you have full hp")
                else:
                    print("Your level is too low")
                    break
        temp = [None for i in range(5)]
        j = 0
        for i in range(len(self.items)):
            if(self.items[i] != None):
                temp[j] = self.items[i]
                j+=1
            self.__idx = j
        temp[len(self.items)-1] = None
        self.items = temp


    def attack(self):
        return self.power

    def levelUp(self, exp):
        self.exp+=exp
        if(self.exp == 15):
            self.exp = 0
            self.maxHealth+=10
            self.power += 5
            self.level +=1
            print(f"Next level: {self.level}")
            print(f"Your stats:")
            print(self)

    def takeFriend(self):
        self.friends[self.__idxF] = Friend()
        self.__idxF += 1

    def useFriend(self):
        count = 0
        for friend in self.friends:
            if(friend != None):
                count+=1
        return count * self.friends[0].attack()

    def stats(self):
        return f"level: {self.level} Health: {self.healthL} power: {self.power}"

    def __str__(self):
        return f"level: {self.level} maxHealth: {self.maxHealth} power: {self.power}"

    def getIdx(self):
        return self.__idx
    def getIdxF(self):
        return self.__idxF


class Monster(Character):
    __power = 4
    expToGive = 5
    __type = "monster"
    def attack(self):
        return self.__power
    def getExpToGive(self):
        return self.expToGive
    def getType(self):
        return self.__type

class Friend(Monster):
    __power = 5
    __type = "friend"
    def attack(self):
        return self.__power
    def getType(self):
        return self.__type