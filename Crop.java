import java.util.concurrent.ThreadLocalRandom;

/** This class represents a crop, that has a name, 
  * waterAmt where it stores the current amount of water, 
  * minWater where it stores the minimum amount of water needs for crop to survive, 
  * maxWater where it stores the maximum amount of water the crop can receive, 
  * fertilizerAmt where it stores the current amount of fertilizer,
  * minFertilizer where it stores the minimum amount fertilizer needs for the crop to survive, 
  * maxFertilizer where it stores the maximum amount of fertilizer the crop can receive, 
  * daysPlanted where it stores the number of days the crop is currently planted,
  * daysUntilHarvest where it stores the number of days the crop needs to be ready to harvest,
  * minProduct where it stores the minimum amount of product the crop can produce, 
  * maxProduct where it stores the maximum amount of product the crop can produce,
  * baseSellPrice where it stores the crop's base selling price,
  * expGain where it stores the amount of experience that the player can gain harvesting the crop,
  * cropCost where it stores the cost to plant the crop.
  */
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

    /** Creates a Crop object by initializing all variables to 0.
      */
    public Crop() {
        this.name = "";
        this.waterAmt = 0;
        this.minWater = 0;
        this.maxWater = 0;
        this.fertilizerAmt = 0;
        this.minFertilizer = 0;
        this.maxFertilizer = 0;
        this.daysPlanted = 0;
        this.daysUntilHarvest = 0;
        this.minProduct = 0;
        this.maxProduct = 0;
        this.baseSellPrice = 0;
        this.expGain = 0;
        this.cropCost = 0;
    }
    
    /** Creates a Crop object by supplying the name, minWater, maxWater, minFertilizer, maxFertilizer,
      * daysUntilHarvest, minProduct, maxProduct, baseSellPrice, expGain, and cropCost. This constructor
      * also initializes the waterAmt, fertilizerAmt, and daysPlanted to 0.
      * 
      * @param name             name of the crop
      * @param minWater         minimum amount of water for the crop to survive
      * @param maxWater         maximum amount of water the crop can receive
      * @param minFertilizer    minimum amount of fertilizer for the crop to survive
      * @param maxFertilizer    maximum amount of fertilizer the crop can receive
      * @param daysUntilHarvest number of days until the crop is ready to be harvested
      * @param minProduct       minimum amount of product the crop can produce
      * @param maxProduct       maximum amount of product the crop can produce
      * @param baseSellPrice    base sell price of the crop
      * @param expGain          amount of experience that will be gained by harvesting the crop
      * @param cropCost         cost to plant the crop
      */
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

    /** This method returns the name of the crop
      * 
      * @return name of the crop
      */
    public String getName() {
        return this.name;
    }

    /** This method returns the current amount of water the crop has
      * 
      * @return amount of water
      */
    public int getWaterAmt() {
        return this.waterAmt;
    }

    /** This method returns the minimum amount of water the crop needs
      * 
      * @return minimum amount of water
      */
    public int getMinWater() {
        return this.minWater;
    }

    /** This method returns the maximum amount of water the crop needs
      * 
      * @return maximum amount of water
      */
    public int getMaxWater() {
        return this.maxWater;
    }

    /** This method returns the current amount of fertilizer the crop has
      * 
      * @return amount of fertilizer
      */
    public int getFertilizerAmt() {
        return this.fertilizerAmt;
    }

    /** This method returns the minimum amount of fertilizer the crop needs
      * 
      * @return minimum amount of fertilizer
      */
    public int getMinFertilizer() {
        return this.minFertilizer;
    }

    /** This method returns the maximum amount of fertilizer the crop needs
      * 
      * @return maximum amount of fertilizer
      */
    public int getMaxFertilizer() {
        return this.maxFertilizer;
    }

    /** This method returns the current number of days the crop is planted
      * 
      * @return number of days
      */
    public int getDaysPlanted() {
        return this.daysPlanted;
    }

    /** This method returns the number of days until the crop is ready to be harvested
      * 
      * @return number of days until the crop is ready to be harvested
      */
    public int getDaysUntilHarvest() {
        return this.daysUntilHarvest;
    }

    /** This method returns the minimum amount of product the crop can produce
      * 
      * @return minimum amount of product
      */
    public int getMinProduct() {
        return this.minProduct;
    }

    /** This method returns the maximum amount of product the crop can produce
      * 
      * @return maximum amount of product
      */
    public int getMaxProduct() {
        return this.maxProduct;
    }
    
    /** This method returns the base sell price of the crop
      * 
      * @return base sell price of the crop  
      */
    public int getBaseSellPrice() {
        return this.baseSellPrice;
    }

    /** This method returns the experience to be gained from harvesting the crop
      * 
      * @return experience to be gained
      */
    public double getExpGain() {
        return this.expGain;
    }

    /** This method returns the cost to plant the crop
      * 
      * @return cost of the crop
      */
    public int getCropCost() {
        return this.cropCost;
    }

    /** This method adds 1 to the amount of water the crop currently has.
      * It won't add more water if it has reach the max amount of water the crop can take.
      * 
      * @param waterBonusIncrease bonus amount of water the crop can take based on farmer type 
      */ 
    public void waterCrop(int waterBonusIncrease) {
        if (this.waterAmt + 1 <= maxWater + waterBonusIncrease) {
            this.waterAmt += 1; 
        }
    }

    /** This method adds 1 to the amount of fertilizer the crop currently has.
      * It won't add more fertilizer if it has reach the max amount of fertilizer the crop can take.
      * 
      * @param fertilizerBonusIncrease bonus amount of fertilizer the crop can take based on farmer type
      */
    public void fertilizeCrop(int fertilizerBonusIncrease) {
        if (this.fertilizerAmt + 1 <= maxFertilizer + fertilizerBonusIncrease) {
            this.fertilizerAmt += 1; 
        }
    }

    /** This method adds 1 day to the current amount of days the crop is planted for.
      */
    public void addDay() {
        this.daysPlanted += 1; 
    }

    /** This method randomly generates a harvest amount for when the player harvests the crop
      * based on the minimum amount of product and maximum amount of product the crop can produce.
      * 
      * @return random harvest amount
      */
    public int harvestAmt() {
        return ThreadLocalRandom.current().nextInt(this.minProduct, this.maxProduct + 1);
    }

    /** This method checks if the crop has achieved it requirements to be ready to be harvested
      * 
      * @return true if the crop is ready to be harvested and false if otherwise
      */
    public boolean isReady() {
        return (this.daysPlanted == this.daysUntilHarvest && 
                this.waterAmt >= this.minWater && 
                this.fertilizerAmt >= this.minFertilizer);
    }

    /** This method checks if the crop has withered or not
      * 
      * @return true if the plant has withered and false if otherwise
      */
    public boolean isWithered() {
        return (this.daysPlanted == this.daysUntilHarvest && this.waterAmt <= this.minWater && this.fertilizerAmt <= this.minFertilizer && this.name.compareTo("") != 0);
    }

    /** This method copies the crop info into current crop object.
      * 
      * @param crop info of which will be copied
      */
    public void copyCrop(Crop crop) {
        this.name = crop.getName();
        this.waterAmt = crop.getWaterAmt();
        this.minWater = crop.getMinWater();
        this.maxWater = crop.getMaxWater();
        this.fertilizerAmt = crop.getFertilizerAmt();
        this.minFertilizer = crop.getMinFertilizer();
        this.maxFertilizer = crop.getMaxFertilizer();
        this.daysPlanted = crop.getDaysPlanted();
        this.daysUntilHarvest = crop.getDaysUntilHarvest();
        this.minProduct = crop.getMinProduct();
        this.maxProduct = crop.getMaxProduct();
        this.baseSellPrice = crop.getBaseSellPrice();
        this.expGain = crop.getExpGain();
        this.cropCost = crop.getCropCost();
    }
}