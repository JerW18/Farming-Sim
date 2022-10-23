public class Tool {
    private String name;
    private int toolCost;
    private double expGain;
    
    // Constructors
    public Tool(String name, int toolCost, double expGain) {
        this.name = name;
        this.toolCost = toolCost;
        this.expGain = expGain;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getToolCost() {
        return toolCost;
    }

    public double getExpGain() {
        return expGain;
    }

}
