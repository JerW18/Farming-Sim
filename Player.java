import java.util.ArrayList;

/**
 * This class represents a player, who has a wallet to store their
 * Objectcoins, experience to store the amount of experience while
 * doing actions, level to show what the current level of the player is,
 * currFarmerType to show what type of farmer the player is currently,
 * a list of tools that the player can use, and a list of farmerType that
 * the player can upgrade to.
 */
public class Player {
    private double wallet;
    private double experience;
    private int level;
    private int currFarmerType;
    ArrayList<Tool> tools;
    ArrayList<FarmerType> farmerType;

    /**
     * Creates a Player object. This constructor also initializes wallet,
     * experience,
     * level, and currFarmerType. It also initializes tools that the player can use
     * and farmerType
     * upgrades that the player can upgrade to.
     */
    public Player() {
        this.wallet = 100;
        this.experience = 0;
        this.level = 0;
        this.currFarmerType = 0;
        this.tools = new ArrayList<Tool>();
        tools.add(new Tool("Plow", 0, 0.5));
        tools.add(new Tool("Watering Can", 0, 0.5));
        tools.add(new Tool("Fertilizer", 10, 4));
        tools.add(new Tool("Pickaxe", 50, 15));
        tools.add(new Tool("Shovel", 7, 2));
        this.farmerType = new ArrayList<FarmerType>();
        farmerType.add(new FarmerType("Farmer", 0, 0, 0, 0, 0, 0));
        farmerType.add(new FarmerType("Registered Farmer", 5, 1, 1, 0, 0, 200));
        farmerType.add(new FarmerType("Distinguished Farmer", 10, 2, 2, 1, 0, 300));
        farmerType.add(new FarmerType("Legendary Farmer", 15, 4, 3, 2, 1, 400));
    }

    /**
     * A method that returns the amount of coins in the wallet.
     * 
     * @return amount of coins in the wallet
     */
    public double getWallet() {
        return this.wallet;
    }

    /**
     * A method that returns the amount of experience of the player.
     * 
     * @return amount of experience of the player
     */
    public double getExperience() {
        return this.experience;
    }

    /**
     * A method that returns the level of the player.
     * 
     * @return level of the player
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * A method that returns the current farmer type of the player.
     * 
     * @return currFarmerType of the player
     */
    public int getCurrFarmerType() {
        return currFarmerType;
    }

    /**
     * A method that returns the list of tools that the player can use.
     * 
     * @return list of tools that the player can use
     */
    public ArrayList<Tool> getTools() {
        return tools;
    }

    /**
     * A method that returns the list of farmer types that the player can upgrade
     * to.
     * 
     * @return list of farmer types that the player can upgrade to
     */
    public ArrayList<FarmerType> getFarmerType() {
        return farmerType;
    }

    /**
     * This method subtracts an amount of coins from the player's wallet 
     * 
     * @param coins amount of coins to be subtracted
     */
    public void subtractCoins(int coins) {
        this.wallet -= coins;
    }

    /**
     * This method adds an amount of coins to the player's wallet.
     * 
     * @param coins amount of coins to be added
     */
    public void addCoins(double coins) {
        this.wallet += coins;
    }

    /**
     * This method adds an amount of experience to the player's experience.
     * 
     * @param experience amount of experience to be added
     */
    public void addExperiencePoints(double experience) {
        this.experience += experience;
    }

    /**
     * This method updates the player's level based on the player's amount of
     * experience
     */
    public void updateLevel() {
        this.level = (int) (this.experience / 100);
    }

    /**
     * This method checks if the player has enough experience and coins to register
     * into the next farmer type. If the player has enough, coins will be deducted
     * and the player will be upgraded to the next tier.
     * 
     * @return true if player successfully registered for the next tier of farmer
     *         type and false if otherwise
     */
    public boolean registerFarmerType() {
        if (checkCoins(this.farmerType.get(currFarmerType + 1).getRegistrationFee())
                && this.level >= this.farmerType.get(currFarmerType + 1).getLevelRequirement()) {
            subtractCoins(this.farmerType.get(currFarmerType + 1).getRegistrationFee());
            this.currFarmerType += 1;
            return true;
        }

        return false;
    }

    /**
     * This method checks if the player has enough coins to perform the specific
     * task.
     * 
     * @param cost cost of the item/task
     * @return true if player has sufficient coins and flase if otherwise
     */
    public boolean checkCoins(int cost) {
        return (this.wallet >= cost);
    }
}
