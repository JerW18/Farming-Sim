/** This class represents a tool, which contains a name, toolCost
  * where its cost will be stored, and expGain which will store
  * the amount of experience the player will gain.
  */
public class Tool {
    private String name;
    private int toolCost;
    private double expGain;
    
    /** Creates a Tool object by supplying the name, the cost, 
      * and the experience gain of the tool.
      * 
      * @param name         name of the tool
      * @param toolCost     cost of the tool
      * @param expGain      experience gain of the tool
     */
    public Tool(String name, int toolCost, double expGain) {
        this.name = name;
        this.toolCost = toolCost;
        this.expGain = expGain;
    }

    /** A method that returns the name of the tool.
      * 
      * @return the name of the tool
      */
    public String getName() {
        return name;
    }

    /** A method that returns the cost of the tool.
      * 
      * @return the cost of the tool
      */
    public int getToolCost() {
        return toolCost;
    }

    /** A method that returns the experience gain of the tool.
      * 
      * @return the experience gain of the tool
      */
    public double getExpGain() {
        return expGain;
    }

}
