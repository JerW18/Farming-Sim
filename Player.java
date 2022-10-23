import java.util.ArrayList;

public class Player {
    private int wallet;
    private double experience;
    private int level;
    private int currFarmerType;
    ArrayList<Tool> tools;
    ArrayList<FarmerType> farmerType;

    // Constructors
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
        farmerType.add(new FarmerType("Distinguished Farmer", 10, 2, 1, 1, 0, 300));
        farmerType.add(new FarmerType("Legendary Farmer", 15, 4, 2, 2, 1, 400));
    }

    // Getters
    public int getWallet() {
        return this.wallet;
    }

    public double getExperience() {
        return this.experience;
    }

    public int getLevel() {
        return this.level;
    }
    
    public int getCurrFarmerType() {
        return currFarmerType;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public ArrayList<FarmerType> getFarmerType() {
        return farmerType;
    }

    // Methods
    public boolean subtractCoins(int coins) {
        if (this.wallet >= coins) {
            this.wallet -= coins;
            System.out.println("Deducted " + coins + " Objectcoins from your wallet");
            return true;
        }

        return false;
    }

    public void addCoins(int coins) {
        this.wallet += coins;
    }

    public void addExperiencePoints(double experience) {
        this.experience += experience;
    }

    public void updateLevel() {
        this.level = (int)(this.experience / 100);
    }

    public boolean registerFarmerType() {
        if (this.wallet >= this.farmerType.get(currFarmerType + 1).getRegistrationFee() && 
        this.level >= this.farmerType.get(currFarmerType + 1).getLevelRequirement()) {
            this.wallet -= this.farmerType.get(currFarmerType + 1).getRegistrationFee();
            this.currFarmerType += 1;
            return true;
        }

        return false;
    }
}
