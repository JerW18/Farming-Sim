public class Tile {
    private boolean isPlowed;
    private boolean isOccupied;
    private boolean isRock;
    private Crop crop;

    // Constructors
    public Tile() {
        this.isPlowed = false;
        this.isOccupied = false;
        this.isRock = false;
        this.crop = null;
    }

    // Getters
    public boolean isPlowed() {
        return this.isPlowed;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public boolean isRock() {
        return this.isRock;
    }

    public Crop getCrop() {
        return this.crop;
    }
    
    // Setters
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    
    public void setRock(boolean isRock) {
        this.isRock = isRock;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    // Methods
    public void plowTile() {
        this.isPlowed = true;
    }

    public void removeRock() {
        this.isRock = false;
        setOccupied(false);
    }

    public void removeWitheredCrop() {
        this.isOccupied = false;
        crop = null;
    }

}
