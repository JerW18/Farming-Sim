import java.util.concurrent.ThreadLocalRandom;

public class Crop {
    private String name;
    private int waterAmt;
    private int minWater;
    private int maxWater;
    private int fertilizerAmt;
    private int minFertilizer;
    private int maxFertilizer;
    private int daysPlanted;
    private int daysUntilHarvest;
    private int minProduct;
    private int maxProduct;
    private int baseSellPrice;
    private double expGain;
    private int cropCost;
    
    // Constructors
    public Crop(String name, int minWater, int maxWater, int minFertilizer, int maxFertilizer, 
            int daysUntilHarvest, int minProduct, int maxProduct, int baseSellPrice, double expGain, int cropCost) {
        this.name = name;
        this.waterAmt = 0;
        this.minWater = minWater;
        this.maxWater = maxWater;
        this.fertilizerAmt = 0;
        this.minFertilizer = minFertilizer;
        this.maxFertilizer = maxFertilizer;
        this.daysPlanted = 0;
        this.daysUntilHarvest = daysUntilHarvest;
        this.minProduct = minProduct;
        this.maxProduct = maxProduct;
        this.baseSellPrice = baseSellPrice;
        this.expGain = expGain;
        this.cropCost = cropCost;
    }

    public Crop(String name, int minWater, int maxWater, int minFertilizer, int maxFertilizer, 
            int daysUntilHarvest, int maxProduct, int baseSellPrice, double expGain, int cropCost) {
        this.name = name;
        this.waterAmt = 0;
        this.minWater = minWater;
        this.maxWater = maxWater;
        this.fertilizerAmt = 0;
        this.minFertilizer = minFertilizer;
        this.maxFertilizer = maxFertilizer;
        this.daysPlanted = 0;
        this.daysUntilHarvest = daysUntilHarvest;
        this.minProduct = maxProduct;
        this.maxProduct = maxProduct;
        this.baseSellPrice = baseSellPrice;
        this.expGain = expGain;
        this.cropCost = cropCost;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getWaterAmt() {
        return this.waterAmt;
    }

    public int getMinWater() {
        return this.minWater;
    }

    public int getMaxWater() {
        return this.maxWater;
    }

    public int getFertilizerAmt() {
        return this.fertilizerAmt;
    }

    public int getMinFertilizer() {
        return this.minFertilizer;
    }

    public int getMaxFertilizer() {
        return this.maxFertilizer;
    }

    public int getDaysPlanted() {
        return this.daysPlanted;
    }

    public int getDaysUntilHarvest() {
        return this.daysUntilHarvest;
    }

    public int getMinProduct() {
        return this.minProduct;
    }

    public int getMaxProduct() {
        return this.maxProduct;
    }
    
    public int getBaseSellPrice() {
        return this.baseSellPrice;
    }

    public double getExpGain() {
        return this.expGain;
    }

    public int getCropCost() {
        return this.cropCost;
    }

    // Methods    
    public void waterCrop(int waterBonusIncrease) {
        if (this.waterAmt + 1 <= maxWater + waterBonusIncrease) {
            this.waterAmt += 1; 
        }
    }

    public void fertilizeCrop(int fertilizerBonusIncrease) {
        if (this.fertilizerAmt + 1 <= maxFertilizer + fertilizerBonusIncrease) {
            this.fertilizerAmt += 1; 
        }
    }

    public void addDay() {
        this.daysPlanted += 1; 
    }

    // https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    public int harvestAmt() {
        return ThreadLocalRandom.current().nextInt(this.minProduct, this.maxProduct + 1);
    }

    public boolean isReady() {
        return (this.daysPlanted == this.daysUntilHarvest && this.waterAmt > this.minWater && this.fertilizerAmt >= this.minFertilizer);
    }

    public boolean isWithered() {
        return (this.daysPlanted == this.daysUntilHarvest + 1);
    }

}