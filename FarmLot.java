import java.util.ArrayList;

public class FarmLot {
    private ArrayList<Tile> tiles;
    private Player player;

    // Constructors
    public FarmLot(int farmSize) {
        this.tiles = new ArrayList<Tile>();
        for (int i = 0; i < farmSize; i++) {
            this.tiles.add(new Tile());
        }
        this.player = new Player();
    }

    // Getters
    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    public Player getPlayer() {
        return this.player;
    } 

    // Methods
    public boolean useTool(int toolIndex, int tileIndex) {
        if (this.player.subtractCoins(player.tools.get(toolIndex).getToolCost())) {
            this.player.addExperiencePoints(player.tools.get(toolIndex).getExpGain());

            switch (this.player.tools.get(toolIndex).getName()) {
                case "Plow":
                    this.tiles.get(tileIndex).plowTile();
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
                    this.tiles.get(tileIndex).removeWitheredCrop();
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

    public void plantCrop(Crop crop, int tileIndex) {
        if(this.player.subtractCoins(crop.getBaseSellPrice() - this.player.farmerType.get(this.player.getCurrFarmerType()).getSeedCostReduction())) {
            this.tiles.get(tileIndex).setCrop(crop);
            this.tiles.get(tileIndex).setOccupied(true);
            System.out.println("Successfully planted " + crop.getName() + " on current tile.");
        } else {
            System.out.println("Insufficient Objectcoins to plant" + crop.getName());
        }
    }

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

        this.tiles.get(tileIndex).getCrop().setWaterAmt(0);
        this.tiles.get(tileIndex).getCrop().setFertilizerAmt(0);
        this.tiles.get(tileIndex).getCrop().setDaysPlanted(0);
        this.tiles.get(tileIndex).setPlowed(false);
        this.tiles.get(tileIndex).setOccupied(false);
        this.tiles.get(tileIndex).setCrop(null);

    }

    public void harvestAll() {
        for (int i = 0; i < this.tiles.size(); i++) {
            harvestCrop(i);
        }
    }

}
