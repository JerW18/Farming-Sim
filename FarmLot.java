/**
 * This class represents a farm lot. A farm lot consists of a number of tiles
 * and a player.
 */
public class FarmLot {
    private Tile[][] tiles;
    private Player player;

    /**
     * Creates a FarmLot object with 50 tiles. It also
     * constructs the appropriate number of tiles and a player.
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

    /**
     * This method returns the list of tiles that are on the farm.
     * 
     * @return list of tiles
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
     * This method returns the info of the player
     * 
     * @return info of the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * This method checks if the player has sufficient amount of coins to use the
     * desired tool and
     * subtracts the amount from the player's wallet. It then performs the desired
     * action of the given
     * tool and adds the appropriate amount of experience to the player's
     * experience.
     * 
     * @param toolIndex  index of the desired tool
     * @param tileIndexI Y index of the tile to perform action on
     * @param tileIndexJ X index of the tile to perform action on
     * @return returns true if the player has successfully used the tool and false
     *         if otherwise
     */
    public boolean useTool(int toolIndex, int tileIndexI, int tileIndexJ) {
        if (this.player.checkCoins(this.player.tools.get(toolIndex).getToolCost())) {
            this.player.subtractCoins(this.player.tools.get(toolIndex).getToolCost());
            this.player.addExperiencePoints(this.player.tools.get(toolIndex).getExpGain());

            switch (this.player.tools.get(toolIndex).getName()) {
                case "Plow":
                    this.tiles[tileIndexI][tileIndexJ].setPlowed(true);
                    break;

                case "Watering Can":
                    this.tiles[tileIndexI][tileIndexJ].getCrop().waterCrop(
                            this.player.farmerType.get(this.player.getCurrFarmerType()).getWaterBonusIncrease());
                    break;

                case "Fertilizer":
                    this.tiles[tileIndexI][tileIndexJ].getCrop().fertilizeCrop(
                            this.player.farmerType.get(this.player.getCurrFarmerType()).getFertilizerBonusIncrease());
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
            return false;
        }

    }

    /**
     * This method will plant the desired crop on the given tile.
     * 
     * @param crop       crop to be planted on the tile
     * @param tileIIndex Y index of the tile to plant the crop on
     * @param tileJIndex X index of the tile to plant the crop on
     */
    public void plantCrop(Crop crop, int tileIIndex, int tileJIndex) {
        this.tiles[tileIIndex][tileJIndex].setCrop(crop);
        this.tiles[tileIIndex][tileJIndex].setOccupied(true);
    }

    /**
     * This method calculates the amount that the player will receive after
     * harvesting the tile and adds the
     * amount to the player's wallet. It also gives the player the appropriate
     * amount of experience that the crop
     * gives when harvested. It will display the amount of crop and money the player
     * obtained to the View.
     * It will finally reset the tile to its default state.
     * 
     * @param tileIIndex Y index of the tile to plant the crop on
     * @param tileJIndex X index of the tile to plant the crop on
     * @param farmView GUI where the amount will be displayed on
     */
    public void harvestCrop(int tileIIndex, int tileJIndex, FarmView farmView) {
        int temp = this.tiles[tileIIndex][tileJIndex].getCrop().harvestAmt();
        int harvestTotal = temp * (this.tiles[tileIIndex][tileJIndex].getCrop().getBaseSellPrice()
                + this.player.farmerType.get(this.player.getCurrFarmerType()).getBonusProduceEarnings());
        int waterBonus = (int) (harvestTotal * 0.2 * (this.tiles[tileIIndex][tileJIndex].getCrop().getWaterAmt() - 1));
        int fertilizerBonus = (int) (harvestTotal * 0.5
                * this.tiles[tileIIndex][tileJIndex].getCrop().getFertilizerAmt());
        int finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

        if (this.tiles[tileIIndex][tileJIndex].getCrop().getName().compareTo("Rose") == 0
                || this.tiles[tileIIndex][tileJIndex].getCrop().getName().compareTo("Tulip") == 0
                || this.tiles[tileIIndex][tileJIndex].getCrop().getName().compareTo("Sunflower") == 0) {
            finalHarvestPrice = (int) (finalHarvestPrice * 1.1);
        }

        farmView.setCropProducedText(
                " Produced: " + Integer.toString(temp) + " " + this.tiles[tileIIndex][tileJIndex].getCrop().getName());
        farmView.setCoinsGainedText(" Gained: " + finalHarvestPrice + " Objectcoins");

        this.player.addCoins(finalHarvestPrice);
        this.player.addExperiencePoints(this.tiles[tileIIndex][tileJIndex].getCrop().getExpGain());

        this.tiles[tileIIndex][tileJIndex].resetTile();
    }

    /** This method checks the surrounding tiles to see if it is vacant.
     * 
     *  @param tileIIndex Y index of the tile to plant the crop on
     *  @param tileJIndex X index of the tile to plant the crop on
     *  @return true if the surrounding tiles are vacant and false if otherwise
     */

    public boolean isVacant(int tileIIndex, int tileJIndex) {
        int count = 0;

        if ((tileIIndex - 1 < 0) || (tileIIndex + 1 > 4) || (tileJIndex - 1 < 0) || (tileJIndex + 1 > 9)) {
            return false;
        } else {
            if (this.tiles[tileIIndex - 1][tileJIndex - 1].isOccupied()
                    || this.tiles[tileIIndex - 1][tileJIndex - 1].isRock())
                count++;
            if (this.tiles[tileIIndex - 1][tileJIndex].isOccupied() || this.tiles[tileIIndex - 1][tileJIndex].isRock())
                count++;
            if (this.tiles[tileIIndex - 1][tileJIndex + 1].isOccupied()
                    || this.tiles[tileIIndex - 1][tileJIndex + 1].isRock())
                count++;
            if (this.tiles[tileIIndex][tileJIndex + 1].isOccupied() || this.tiles[tileIIndex][tileJIndex + 1].isRock())
                count++;
            if (this.tiles[tileIIndex][tileJIndex - 1].isOccupied() || this.tiles[tileIIndex][tileJIndex - 1].isRock())
                count++;
            if (this.tiles[tileIIndex + 1][tileJIndex - 1].isOccupied()
                    || this.tiles[tileIIndex + 1][tileJIndex - 1].isRock())
                count++;
            if (this.tiles[tileIIndex + 1][tileJIndex].isOccupied() || this.tiles[tileIIndex + 1][tileJIndex].isRock())
                count++;
            if (this.tiles[tileIIndex + 1][tileJIndex + 1].isOccupied()
                    || this.tiles[tileIIndex + 1][tileJIndex + 1].isRock())
                count++;

            return (count == 0);
        }
    }

    /** This method checks if the player can continue playing
     * 
     *  @return true if the player cannot continue playing and false if otherwise
     */

    public boolean isGameOver() {
        int witheredCount = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.tiles[i][j].isOccupied() && !this.tiles[i][j].isRock()
                        && !this.tiles[i][j].getCrop().isWithered())
                    return false;

                if (this.player.getWallet() < 7 && (this.tiles[i][j].getCrop().isWithered() || this.tiles[i][j].isRock()
                        || !this.tiles[i][j].isOccupied()))
                    witheredCount++;
            }
        }

        if (this.player
                .getWallet() < (5
                        - this.player.getFarmerType().get(this.player.getCurrFarmerType()).getSeedCostReduction())
                && witheredCount == 50)
            return true;

        return (witheredCount == 50);
    }
}
