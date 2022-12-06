import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.Scanner; 


public class FarmController {

    private ImageIcon unplowedLand = new ImageIcon("UnplowedLand.png");
    private ImageIcon plowedLand = new ImageIcon("PlowedLand.png");
    private ImageIcon wateredLand = new ImageIcon("WateredLand.png");
    private ImageIcon rockLand = new ImageIcon("RockLand.png");

    private FarmLot mainFarm;
    private FarmView farmView;
    private int days = 1;
    private int currentI;
    private int currentJ;

    public FarmController() {
        this.mainFarm = new FarmLot();
        this.farmView = new FarmView();

        new RockGenerator().generateFile();

        try {
            File rockFile = new File("RockFile.txt");
            Scanner myReader = new Scanner(rockFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int count = 0;

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 10; j++) {
                        this.mainFarm.getTiles()[i][j].setRock((data.charAt(count) == '1'));
                        count++;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        initializeMainGame();
    }
    
    public static void main(String[] args) {
        new FarmController();
    }
    
    public void initializeMainGame() {

        // TODO: Not Done
        farmView.initializeFarmTileImage(mainFarm, unplowedLand, rockLand);

        ActionListener[][] farmActionListener = new ActionListener[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) { 
                final int final_i = i;
                final int final_j = j;
                farmActionListener[i][j] = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        farmView.getFarmBtn()[currentI][currentJ].setBackground(null);
                        
                        currentI = final_i;
                        currentJ = final_j;

                        farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);

                        farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ]);

                        farmView.setNextDayBtnVisibility(false);
                        farmView.setPlantCropBtnVisibility(false);
                        farmView.setPlowBtnVisibility(false); 
                        farmView.setWateringCanBtnVisibility(false);
                        farmView.setFertilizerBtnVisibility(false);
                        farmView.setPickaxeBtnVisibility(false);
                        farmView.setShovelBtnVisibility(false);
                        farmView.setBackBtnVisibility(false);

                        if (!mainFarm.getTiles()[currentI][currentJ].isOccupied() && !mainFarm.getTiles()[currentI][currentJ].isRock() && mainFarm.getTiles()[currentI][currentJ].isPlowed())
                            farmView.setPlantCropBtnVisibility(true);

                        if (!mainFarm.getTiles()[currentI][currentJ].isPlowed() && !mainFarm.getTiles()[currentI][currentJ].isRock())
                            farmView.setPlowBtnVisibility(true); 
                        
                        if (mainFarm.getTiles()[currentI][currentJ].isOccupied() && !mainFarm.getTiles()[currentI][currentJ].isRock()) {
                            farmView.setWateringCanBtnVisibility(true);
                            farmView.setFertilizerBtnVisibility(true);
                        }

                        if (mainFarm.getTiles()[currentI][currentJ].isRock())
                            farmView.setPickaxeBtnVisibility(true);

                        if (mainFarm.getTiles()[currentI][currentJ].getCrop().isWithered())
                            farmView.setShovelBtnVisibility(true);
                            
                        farmView.setBackBtnVisibility(true);
                        
                        Action plowActionListener = new AbstractAction("Plow - Free") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(0, currentI, currentJ);

                                farmView.setPlantCropBtnVisibility(true);
                                farmView.setPlowBtnVisibility(false);

                                farmView.updateFarmTileImage(mainFarm.getTiles()[currentI][currentJ], unplowedLand, plowedLand, wateredLand, currentI, currentJ);
                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(), 
                                            mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(), 
                                            mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        // TODO: Not Done
                        Action wateringCanActionListener = new AbstractAction("Watering Can - Free") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(1, currentI, currentJ);

                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(), 
                                            mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(), 
                                            mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        // TODO: Not Done
                        Action fertilizerActionListener = new AbstractAction("Fertilizer - 10 Objectcoins") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(2, currentI, currentJ);

                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(), 
                                            mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(), 
                                            mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        // TODO: Not Done
                        Action pickaxeActionListener = new AbstractAction("Pickaxe - 50 Objectcoins") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(3, currentI, currentJ);

                                farmView.setPickaxeBtnVisibility(false);
                                farmView.setPlowBtnVisibility(true); 

                                farmView.updateFarmTileImage(mainFarm.getTiles()[currentI][currentJ], unplowedLand, plowedLand, wateredLand, currentI, currentJ);
                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(), 
                                            mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(), 
                                            mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        // TODO: Not Done
                        Action shovelActionListener = new AbstractAction("Shovel - 7 Objectcoins") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(4, currentI, currentJ);
                                
                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(), 
                                            mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(), 
                                            mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        // TODO: Not Done
                        Action backActionListener = new AbstractAction("Back") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                farmView.setTileInfoVisibility(false);
                                farmView.setNextDayBtnVisibility(true);   
                                farmView.setPlantCropBtnVisibility(false);
                                farmView.setPlowBtnVisibility(false); 
                                farmView.setWateringCanBtnVisibility(false);
                                farmView.setFertilizerBtnVisibility(false);
                                farmView.setPickaxeBtnVisibility(false);
                                farmView.setShovelBtnVisibility(false);
                                farmView.setBackBtnVisibility(false);
                                farmView.getFarmBtn()[currentI][currentJ].setBackground(null);
                            }
                        };

                        farmView.setActionsActionListeners(plowActionListener, wateringCanActionListener, fertilizerActionListener, pickaxeActionListener, shovelActionListener, backActionListener);
                    }
                }; 
            }
        }
            

        // TODO: Not Done
        ActionListener nextDayActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                days++;
                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(), 
                                            mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(), 
                                            mainFarm.getPlayer().getFarmerType(), days);
            }
        };

        
        this.farmView.setActionListeners(farmActionListener, nextDayActionListener);
    }
}