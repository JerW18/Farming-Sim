public class FarmerType {
    private String name;
    private int levelRequirement;
    private int bonusProduceEarnings;
    private int seedCostReduction;
    private int waterBonusIncrease;
    private int fertilizerBonusIncrease;
    private int registrationFee;
    
    // Constructors
    public FarmerType(String name, int levelRequirement, int bonusProduceEarnings, int seedCostReduction,
            int waterBonusIncrease, int fertilizerBonusIncrease, int registrationFee) {
        this.name = name;
        this.levelRequirement = levelRequirement;
        this.bonusProduceEarnings = bonusProduceEarnings;
        this.seedCostReduction = seedCostReduction;
        this.waterBonusIncrease = waterBonusIncrease;
        this.fertilizerBonusIncrease = fertilizerBonusIncrease;
        this.registrationFee = registrationFee;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getBonusProduceEarnings() {
        return bonusProduceEarnings;
    }

    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    public int getWaterBonusIncrease() {
        return waterBonusIncrease;
    }

    public int getFertilizerBonusIncrease() {
        return fertilizerBonusIncrease;
    }

    public int getRegistrationFee() {
        return registrationFee;
    }
}
