/** This class represents a farmer type, which has a name, 
  * levelRequirement which stores the level requirement needed to upgrade to this farmer type, 
  * bonusProduceEarnings which stores the bonus amount that can be earned,
  * seedCostReduction which stores the cost reduction to buy seeds,
  * waterBonusIncrease which stores the limit increase of water that can be received by the crop,
  * fertilizerBonusIncrease which stores the the limit increase of fertilizer that can be received by the crop, and
  * registrationFee which stores the amount needed to register for the farmer tier.
  */
public class FarmerType {
    private String name;
    private int levelRequirement;
    private int bonusProduceEarnings;
    private int seedCostReduction;
    private int waterBonusIncrease;
    private int fertilizerBonusIncrease;
    private int registrationFee;
    
    /** Creates a FarmerType object by supplying the name, levelRequirement, bonusProduceEarnings, seedCostReduction,
      * waterBonusIncrease, fertilizerBonusIncrease, and registrationFee.
      * 
      * @param name                     name of the farmer type
      * @param levelRequirement         level requirement to obtain this farmer type
      * @param bonusProduceEarnings     bonus earnings of this farmer type
      * @param seedCostReduction        cost reduction of this farmer type
      * @param waterBonusIncrease       water limit increase of this farmer type
      * @param fertilizerBonusIncrease  fertilizer limit increase of this farmer type
      * @param registrationFee          fee to upgrade to this farmer type
      */
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

    
    
    /** This method returns the name of the farmer type.
      * 
      * @return name of the farmer type
      */
    public String getName() {
        return name;
    }

    
    /** This method returns the level requirement of this farmer type.
      * @return level requirement
      */
    public int getLevelRequirement() {
        return levelRequirement;
    }

    
    /** This method returns the bonus earnings of this farmer type.
      * 
      * @return bonus earnings
      */
    public int getBonusProduceEarnings() {
        return bonusProduceEarnings;
    }

    
    /** This method returns the seed cost reduction of this farmer type.
      * 
      * @return seed cost reduction
      */
    public int getSeedCostReduction() {
        return seedCostReduction;
    }

    
    /** This method returns the water bonus limit increase of this farmer type.
      * 
      * @return water bonus limit increase 
      */
    public int getWaterBonusIncrease() {
        return waterBonusIncrease;
    }

    
    /** This method returns the fertilizer bonus limit increase of this farmer type.
      * 
      * @return fertilizer bonus limit increase
      */
    public int getFertilizerBonusIncrease() {
        return fertilizerBonusIncrease;
    }

    
    /** This method returns the registration fee for this farmer type
      * @return registration fee
      */
    public int getRegistrationFee() {
        return registrationFee;
    }
}
