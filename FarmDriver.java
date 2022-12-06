
public class FarmDriver {

}


//     // Initialization of crops
    //     ArrayList<Crop> crops = new ArrayList<Crop>();
    //     crops.add(new Crop("Turnip", 1, 2, 0, 1, 2, 1, 2, 6, 5, 5));

    //     // Initialization of the mainFarm
    //     FarmLot mainFarm = new FarmLot(1);

    //     int dayCount = 1;
    //     boolean canContinue = true;

    //     while (canContinue) {
    //         System.out.println("\nDay " + dayCount + "\n");
    //         System.out.println(mainFarm.getPlayer().toString());
    //         System.out.println("");
    //         System.out.println(mainFarm.getTiles().get(0).toString());
    //         System.out.println("");

    //         System.out.println("What would you like to do?");
    //         System.out.println("1) Plow Land");
    //         System.out.println("2) Plant Turnip");
    //         System.out.println("3) Water Plant");
    //         System.out.println("4) Harvest Crop");
    //         System.out.println("5) Proceed to next day");
    //         System.out.println("6) End Game");
    //         System.out.print("Choice: ");
            
    //         Scanner input = new Scanner(System.in);
    //         int choice = input.nextInt();
    //         while (choice < 1 || choice > 6) {
    //             System.out.println("Invalid choice. Please try again.");
    //             System.out.print("Choice: ");
    //             choice = input.nextInt();   
    //         }

    //         System.out.println("");
    //         System.out.println("----------------------------");

    //         switch (choice) {
    //             case 1:
    //                 if (!mainFarm.getTiles().get(0).isPlowed()) {
    //                     mainFarm.useTool(0, 0);
    //                 } else {
    //                     System.out.println("\nTile is already plowed");
    //                 }
    //                 break;
            
    //             case 2:
    //                 if (!mainFarm.getTiles().get(0).isOccupied() && mainFarm.getTiles().get(0).isPlowed()) {
    //                     mainFarm.plantCrop(crops.get(0), 0);
    //                 } else {
    //                     System.out.print((mainFarm.getTiles().get(0).isOccupied() ? "\nTile is already occupied\n" : ""));
    //                     System.out.print((!mainFarm.getTiles().get(0).isPlowed() ? "\nTile is not yet plowed\n" : ""));
    //                 }
    //                 break;

    //             case 3:
    //                 mainFarm.useTool(1, 0);
    //                 break;

    //             case 4:
    //                 if (mainFarm.getTiles().get(0).getCrop().isReady()) {
    //                     mainFarm.harvestCrop(0);
    //                 } else {
    //                     System.out.println("\nCrop is not yet ready to be harvested");
    //                 }
    //                 break;

    //             case 5:
    //                 dayCount++;
    //                 if (mainFarm.getTiles().get(0).getCrop() != null) {
    //                     mainFarm.getTiles().get(0).getCrop().addDay();
    //                 }
    //                 break;  
                
    //             case 6:
    //                 canContinue = false;
    //                 break; 
                
    //             default:
    //                 break;
    //         }

    //         mainFarm.getPlayer().updateLevel();

    //         if (mainFarm.getPlayer().getWallet() == 0 && 
    //             !mainFarm.getTiles().get(0).isOccupied() && 
    //             !mainFarm.getTiles().get(0).isRock()) {
    //             System.out.println("\nYou ran out of money.");
    //             canContinue = false;
    //         }

    //         if (mainFarm.getTiles().get(0).getCrop() != null && 
    //             mainFarm.getTiles().get(0).getCrop().isWithered()) {
    //         System.out.println("\nYour plant withered.");
    //         canContinue = false;
    //         }
    //     }