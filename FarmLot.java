import java.util.ArrayList;

/** This class represents a farm lot. A farm lot consists of a number of tiles and a player.
  */
public class FarmLot {
    private ArrayList<Tile> tiles;
    private Player player;

    /** Creates a FarmLot object by supplying the number of tiles that will be on the farm. It also
      * constructs the appropriate number of tiles and a player.
      * 
      * @param farmSize number of tiles
      */
    public FarmLot(int farmSize) {
        this.tiles = new ArrayList<Tile>();
        for (int i = 0; i < farmSize; i++) {
            this.tiles.add(new Tile());
        }
        this.player = new Player();
    }

    /** This method returns the list of tiles that are on the farm.
      * 
      * @return list of tiles
      */
    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    /** This method returns the info of the player
      * 
      * @return info of the player
      */
    public Player getPlayer() {
        return this.player;
    } 

    /** This method checks if the player has sufficient amount of coins to use the desired tool and
      * subtracts the amount from the player's wallet. It then performs the desired action of the given
      * tool and adds the appropriate amount of experience to the player's experience.
      * 
      * @param toolIndex    index of the desired tool
      * @param tileIndex    index of the tile to perform action on
      * @return returns true if the player has sufficient amount of coins to use tool and false if otherwise
      */
    public boolean useTool(int toolIndex, int tileIndex) {
        if (this.player.subtractCoins(player.tools.get(toolIndex).getToolCost())) {
            this.player.addExperiencePoints(player.tools.get(toolIndex).getExpGain());

            switch (this.player.tools.get(toolIndex).getName()) {
                case "Plow":
                    this.tiles.get(tileIndex).setPlowed(true);
                    System.out.println("Successfully plowed the tile.");
                    break;
            
                case "Watering Can":
                    this.tiles.get(tileIndex).getCrop().waterCrop(this.player.farmerType.get(this.player.getCurrFarmerType()).getWaterBonusIncrease());
                    System.out.println("Successfully watered the crop.");
                    break;

                case "Fertilizer":
                    this.tiles.get(tileIndex).getCrop().fertilizeCrop(this.player.farmerType.get(this.player.getCurrFarmerType()).getFertilizerBonusIncrease());
                    System.out.println("Successfully fertilized the crop.");
                    break;

                case "Pickaxe":
                    this.tiles.get(tileIndex).removeRock();
                    System.out.println("Successfully removed the rock.");
                    break;

                case "Shovel":
                    this.tiles.get(tileIndex).resetTile();
                    System.out.println("Successfully removed the withered crop.");
                    break;
                
                default:
                    System.out.println("Error occured in useTool.");
                    break;
                
            }

            return true;
        } else {
            System.out.println("Insufficient Objectcoins to use" + player.tools.get(toolIndex).getName());
            return false;
        }

    }

    /** This method checks if the player has sufficient amount fo coins to plant the desired crop and will
      * plant the crop on the given tile.
      * 
      * @param crop         crop to be planted on the tile
      * @param tileIndex    index of the tile to plant the crop on
      */
    public void plantCrop(Crop crop, int tileIndex) {
        if(this.player.subtractCoins(crop.getBaseSellPrice() - this.player.farmerType.get(this.player.getCurrFarmerType()).getSeedCostReduction())) {
            this.tiles.get(tileIndex).setCrop(crop);
            this.tiles.get(tileIndex).setOccupied(true);
            System.out.println("Successfully planted " + crop.getName() + " on current tile.");
        } else {
            System.out.println("Insufficient Objectcoins to plant" + crop.getName());
        }
    }

    /** This method calculates the amount that the player will receive after harvesting the tile and adds the
      * amount to the player's wallet. It also gives the player the appropriate amount of experience that the crop
      * gives when harvested. It will finally reset the tile to its default state.
      * 
      * @param tileIndex    index of the tile where the crop is to be harvested
      */
    public void harvestCrop(int tileIndex) {
        int temp = this.tiles.get(tileIndex).getCrop().harvestAmt();
        int harvestTotal = temp * (this.tiles.get(tileIndex).getCrop().getBaseSellPrice() + this.player.farmerType.get(this.player.getCurrFarmerType()).getBonusProduceEarnings());
        int waterBonus = (int)(harvestTotal * 0.2 * (this.tiles.get(tileIndex).getCrop().getWaterAmt() - 1));
        int fertilizerBonus = (int)(harvestTotal * 0.5 * this.tiles.get(tileIndex).getCrop().getFertilizerAmt());
        int finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        this.player.addCoins(finalHarvestPrice);
        this.player.addExperiencePoints(this.tiles.get(tileIndex).getCrop().getExpGain());
        
        System.out.println("Turnips Produced: " + temp);
        System.out.println("Objectcoins Obtained: " + finalHarvestPrice);

        this.tiles.get(tileIndex).resetTile();
    }

    /** This method harvests all the crops that are ready to be harvested in the farm.
      */
    public void harvestAll() {
        for (int i = 0; i < this.tiles.size(); i++) {
            if (this.tiles.get(i).getCrop().isReady()) {
                harvestCrop(i);
            }
        }
    }

}
