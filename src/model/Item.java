
package model;

import java.io.Serializable;

public class Item implements Serializable
{
    private String name;
    private String description;
    private int goldValue;

    public Item(String name, String description, int goldValue) 
    {
        this.name = name;
        this.description = description;
        this.goldValue = goldValue;
        switch (name.toLowerCase())
        {
            case "gold coins":
                this.description = "lovely money!";
                break;
            case "potion":
                this.description = "a potion that heals you to full health!";
                break;
            case "weapon shard":
                this.description = "a magical shard, it will increase your weapon damage!";
                break;
            case "shroom":
                this.description = "dirty (you probably shouldn't eat this)";
                break;
            case "armor":
                this.description = "able to increase your max HP by 20";
                break;
            case "master key":
                this.description = "mysterious";
        }
    }
    
    public String getName() 
    {
        return name;
    }
    
    public String getDescription() 
    {
        return description;
    }
    
    public int getGoldValue() 
    {
        return goldValue;
    }
    
    /**
     * This will use an item from the player (p's) inventory, if an item is found with such a name
     * @param p Player that the item should be used from.
     * @return Returns the action (if it failed, then an error on why it failed, else what happened based on that action)
     */
    public String use(Player p)
    {
        switch (name.toLowerCase())
        {
            case "potion":
                p.healPlayer(p.getMaxHp());
                p.removeFromInventory(name);
                return "You used the " + name + " and your health has returned to max!" + System.lineSeparator();
            case "weapon shard":
                p.getBestWeapon().increaseDamage(10);
                p.removeFromInventory(name);
                return "You used the " + name + " and your " + p.getBestWeapon().getName() + "'s damage has increased by 10!" + System.lineSeparator();
            case "shroom":
                int shroomDmg = p.getCurrentHp() - 5;
                if (shroomDmg < 5)
                {
                    shroomDmg = 5;
                }
                p.damagePlayer(shroomDmg);
                p.removeFromInventory(name);
                return "You used the " + name + " and lose " + shroomDmg + " health! (What are you doing???)" + System.lineSeparator();
            case "armor":
                p.increaseMaxHp(20);
                p.healPlayer(20);
                p.removeFromInventory(name);
                return "You equipped the " + name + " and max health increased by 20!" + System.lineSeparator();
            case "master key":
                return "You can't use this right now! Maybe it will open a chest?";
            default:
                return "You can't use this item!";
        }
    }
    

}
