/** This class represents a farm lot. A farm lot consists of a number of tiles and a player.
  */
public class FarmLot {
    private Tile[][] tiles;
    private Player player;

    /** Creates a FarmLot object by supplying the number of tiles that will be on the farm. It also
      * constructs the appropriate number of tiles and a player.
      * 
      * @param farmSize number of tiles
      */
    public FarmLot() {
        this.tiles = new Tile[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.tiles[i][j] = new Tile();
            }
        }
        this.player = new Player();
    }

    /** This method returns the list of tiles that are on the farm.
      * 
      * @return list of tiles
      */
    public Tile[][] getTiles() {
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
    public boolean useTool(int toolIndex, int tileIndexI, int tileIndexJ) {
        if (this.player.subtractCoins(this.player.tools.get(toolIndex).getToolCost())) {
            this.player.addExperiencePoints(this.player.tools.get(toolIndex).getExpGain());

            switch (this.player.tools.get(toolIndex).getName()) {
                case "Plow":
                    this.tiles[tileIndexI][tileIndexJ].setPlowed(true);
                    break;
            
                case "Watering Can":
                    this.tiles[tileIndexI][tileIndexJ].getCrop().waterCrop(this.player.farmerType.get(this.player.getCurrFarmerType()).getWaterBonusIncrease());
                    break;

                case "Fertilizer":
                    this.tiles[tileIndexI][tileIndexJ].getCrop().fertilizeCrop(this.player.farmerType.get(this.player.getCurrFarmerType()).getFertilizerBonusIncrease());
                    break;

                case "Pickaxe":
                    this.tiles[tileIndexI][tileIndexJ].removeRock();
                    break;

                case "Shovel":
                    this.tiles[tileIndexI][tileIndexJ].resetTile();
                    break;
                
                default:
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
    public void plantCrop(Crop crop, int tileIIndex, int tileJIndex) {
        if(this.player.subtractCoins(crop.getBaseSellPrice() - this.player.farmerType.get(this.player.getCurrFarmerType()).getSeedCostReduction())) {
            this.tiles[tileIIndex][tileJIndex].setCrop(crop);
            this.tiles[tileIIndex][tileJIndex].setOccupied(true);
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
    public void harvestCrop(int tileIIndex, int tileJIndex) {
        int temp = this.tiles[tileIIndex][tileJIndex].getCrop().harvestAmt();
        int harvestTotal = temp * (this.tiles[tileIIndex][tileJIndex].getCrop().getBaseSellPrice() + this.player.farmerType.get(this.player.getCurrFarmerType()).getBonusProduceEarnings());
        int waterBonus = (int)(harvestTotal * 0.2 * (this.tiles[tileIIndex][tileJIndex].getCrop().getWaterAmt() - 1));
        int fertilizerBonus = (int)(harvestTotal * 0.5 * this.tiles[tileIIndex][tileJIndex].getCrop().getFertilizerAmt());
        int finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        this.player.addCoins(finalHarvestPrice);
        this.player.addExperiencePoints(this.tiles[tileIIndex][tileJIndex].getCrop().getExpGain());
        
        System.out.println("Turnips Produced: " + temp);
        System.out.println("Objectcoins Obtained: " + finalHarvestPrice);

        this.tiles[tileIIndex][tileJIndex].resetTile();
    }
}
