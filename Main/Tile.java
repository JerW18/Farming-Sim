package Main;
/** This class represents a tile, that has isPlowed to determine whether the tile is plowed or not,
  * isOccupied to determine whether the tile has a crop/rock on it or not, isRock to determine whether
  * the tile has a rock on it or not, and crop to determine what crop is planted on the tile.
  */
public class Tile {
    private boolean isPlowed;
    private boolean isOccupied;
    private boolean isRock;
    private Crop crop;

    /** Creates a Tile object. The tile will start off unplowed, and unoccupied.
      */
    public Tile() {
        this.isPlowed = false;
        this.isOccupied = false;
        this.isRock = false;
        this.crop = null;
    }

    /** This method returns whether the tile is plowed or not
      * 
      * @return plow status of the tile
      */
    public boolean isPlowed() {
        return this.isPlowed;
    }

    /** This method returns whether the tile is occupied or not
      * 
      * @return occupation status of the tile
      */
    public boolean isOccupied() {
        return this.isOccupied;
    }

    /** This method returns whether a rock is occupying the tile or not
      * 
      * @return rock status of the tile
      */
    public boolean isRock() {
        return this.isRock;
    }

    /** This method returns the info of the crop currently planted at the tile
      * 
      * @return info of the crop
      */
    public Crop getCrop() {
        return this.crop;
    }
    
    /** This method sets the plow status of the tile
      * 
      * @param isPlowed current plow status
      */
    public void setPlowed(boolean isPlowed) {
        this.isPlowed = isPlowed;
    }
    
    /** This method sets the occupation status of the tile
      * 
      * @param isOccupied current occupation status
      */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
    /** This method sets the rock status of the tile
      *
      * @param isRock current rock status
      */
    public void setRock(boolean isRock) {
        this.isRock = isRock;
    }

    /** This method sets the info of the crop currently planted at the tile
      *
      * @param crop info of the crop
      */
    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    /** This method removes the rock from the tile
      */
    public void removeRock() {
        this.isRock = false;
        this.isOccupied = false;
    }
    
    /** This method resets the tile to its default state
      */
    public void resetTile() {
        this.crop.setWaterAmt(0);
        this.crop.setFertilizerAmt(0);
        this.crop.setDaysPlanted(0);
        this.isPlowed = false;
        this.isOccupied = false;
        this.crop = null;
    }

    public String toString() {
        return  "Tile Status: " + (this.isPlowed ? "Plowed\n" : "Unplowed\n") + 
                "Current Crop: " + (this.crop != null ? crop.getName() + "\n" : "None\n") +
                "Crop Status - " + (this.crop != null ? 
                                    "\nDays Planted: " + this.crop.getDaysPlanted() + 
                                    "\nWater: " + this.crop.getWaterAmt() + 
                                    "\nFertilizer: " + this.crop.getFertilizerAmt() : "N/A");
    }

}
