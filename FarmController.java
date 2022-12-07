import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FarmController {

    private ImageIcon unplowedLand = new ImageIcon("UnplowedLand.png");
    private ImageIcon plowedLand = new ImageIcon("PlowedLand.png");
    private ImageIcon rockLand = new ImageIcon("RockLand.png");
    private ImageIcon witheredCropLand = new ImageIcon("WitheredCropLand.png");
    private ImageIcon plantedCropLand = new ImageIcon("PlantedCropLand.png");
    private ImageIcon plantedTreeLand = new ImageIcon("PlantedTreeLand.png");
    private ImageIcon turnipReadyLand = new ImageIcon("TurnipReadyLand.png");
    private ImageIcon carrotReadyLand = new ImageIcon("CarrotReadyLand.png");
    private ImageIcon potatoReadyLand = new ImageIcon("PotatoReadyLand.png");
    private ImageIcon roseReadyLand = new ImageIcon("RoseReadyLand.png");
    private ImageIcon tulipReadyLand = new ImageIcon("TulipReadyLand.png");
    private ImageIcon sunflowerReadyLand = new ImageIcon("SunflowerReadyLand.png");
    private ImageIcon mangoReadyLand = new ImageIcon("MangoReadyLand.png");
    private ImageIcon appleReadyLand = new ImageIcon("AppleReadyLand.png");

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

        ArrayList<Crop> crops = new ArrayList<Crop>();
        crops.add(new Crop("Turnip", 1, 2, 0, 1, 2, 1, 2, 6, 5, 5));
        crops.add(new Crop("Carrot", 1, 2, 0, 1, 3, 1, 2, 9, 7.5, 10));
        crops.add(new Crop("Potato", 3, 4, 1, 2, 5, 1, 10, 3, 12.5, 20));
        crops.add(new Crop("Rose", 1, 2, 0, 1, 1, 1, 1, 5, 2.5, 5));
        crops.add(new Crop("Tulip", 2, 3, 0, 1, 2, 1, 1, 9, 5, 10));
        crops.add(new Crop("Sunflower", 2, 3, 1, 2, 3, 1, 1, 19, 7.5, 20));
        crops.add(new Crop("Mango", 7, 7, 4, 4, 10, 5, 15, 8, 25, 100));
        crops.add(new Crop("Apple", 7, 7, 5, 5, 10, 10, 15, 5, 25, 200));

        farmView.initializeFarmTileImage(mainFarm, unplowedLand, rockLand);

        Action nextDayActionListener = new AbstractAction("Next Day") {
            @Override
            public void actionPerformed(ActionEvent e) {
                days++;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (mainFarm.getTiles()[i][j].isOccupied() && !mainFarm.getTiles()[i][j].isRock()) {
                            mainFarm.getTiles()[i][j].getCrop().addDay();
                        }
                    }
                }

                mainFarm.getPlayer().updateLevel();

                farmView.updateNextDayFarmTileImage(mainFarm, witheredCropLand, turnipReadyLand, carrotReadyLand,
                        potatoReadyLand, roseReadyLand, tulipReadyLand, sunflowerReadyLand, mangoReadyLand,
                        appleReadyLand);

                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(), mainFarm.getPlayer().getLevel(),
                        mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(),
                        mainFarm.getPlayer().getFarmerType(), days);

                if (mainFarm.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "You have lost!", "Game Over", JOptionPane.OK_OPTION);
                    
                    hideButtons();

                    farmView.setExitBtnVisibility(true);
                }
            }
        };

        StringBuilder registerFarmerBtnString = new StringBuilder();
        registerFarmerBtnString.append("Register Farmer - ");
        registerFarmerBtnString.append(Integer.toString(mainFarm.getPlayer().getFarmerType()
                .get(mainFarm.getPlayer().getCurrFarmerType() + 1).getRegistrationFee()));
        Action registerFarmerActionListener = new AbstractAction(registerFarmerBtnString.toString()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFarm.getPlayer().registerFarmerType()) {

                    farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                            mainFarm.getPlayer().getLevel(),
                            mainFarm.getPlayer().getExperience(),
                            mainFarm.getPlayer().getCurrFarmerType(),
                            mainFarm.getPlayer().getFarmerType(), days);
                } else {
                    
                    farmView.setRegisterFarmerBtnColor(Color.RED);

                    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                    executorService.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            farmView.setRegisterFarmerBtnColor(null);
                        }
                    }, 1, 2, TimeUnit.SECONDS);

                }

            }
        };

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

                        farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);

                        hideButtons();
                        showButtons();

                        Action plantCropActionListener = new AbstractAction("Select Crop") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                farmView.setPlantCropBtnVisibility(false);
                                farmView.setBackBtnVisibility(false);
                                farmView.setTurnipBtnVisibility(true);
                                farmView.setCarrotBtnVisibility(true);
                                farmView.setPotatoBtnVisibility(true);
                                farmView.setRoseBtnVisibility(true);
                                farmView.setTulipBtnVisibility(true);
                                farmView.setSunflowerBtnVisibility(true);
                                farmView.setMangoBtnVisibility(true);
                                farmView.setAppleBtnVisibility(true);
                                farmView.setReturnBtnVisibility(true);

                                StringBuilder turnipBtnString = new StringBuilder();
                                turnipBtnString.append("Turnip - ");
                                turnipBtnString.append(Integer.toString((crops.get(0).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action turnipActionListener = new AbstractAction(turnipBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(0).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(0).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(0), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(0).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder carrotBtnString = new StringBuilder();
                                carrotBtnString.append("Carrot - ");
                                carrotBtnString.append(Integer.toString((crops.get(1).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action carrotActionListener = new AbstractAction(carrotBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(1).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(1).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(1), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(1).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder potatoBtnString = new StringBuilder();
                                potatoBtnString.append("Potato - ");
                                potatoBtnString.append(Integer.toString((crops.get(2).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action potatoActionListener = new AbstractAction(potatoBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(2).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(2).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(2), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(2).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder roseBtnString = new StringBuilder();
                                roseBtnString.append("Rose - ");
                                roseBtnString.append(Integer.toString((crops.get(3).getCropCost() - mainFarm.getPlayer()
                                        .getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action roseActionListener = new AbstractAction(roseBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(3).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(3).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(3), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(3).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder tulipBtnString = new StringBuilder();
                                tulipBtnString.append("Tulip - ");
                                tulipBtnString.append(Integer.toString((crops.get(4).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action tulipActionListener = new AbstractAction(tulipBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(4).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(4).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(4), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(4).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder sunflowerBtnString = new StringBuilder();
                                sunflowerBtnString.append("Sunflower - ");
                                sunflowerBtnString.append(Integer.toString((crops.get(5).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action sunflowerActionListener = new AbstractAction(sunflowerBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(5).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(5).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(5), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(5).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder mangoBtnString = new StringBuilder();
                                mangoBtnString.append("Mango - ");
                                mangoBtnString.append(Integer.toString((crops.get(6).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action mangoActionListener = new AbstractAction(mangoBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(6).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())
                                                || !mainFarm.isVacant(currentI, currentJ)) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(6).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(6), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(6).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                StringBuilder appleBtnString = new StringBuilder();
                                appleBtnString.append("Apple - ");
                                appleBtnString.append(Integer.toString((crops.get(7).getCropCost() - mainFarm
                                        .getPlayer().getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getSeedCostReduction())));

                                Action appleActionListener = new AbstractAction(appleBtnString.toString()) {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!mainFarm.getPlayer()
                                                .checkCoins(crops.get(7).getCropCost() - mainFarm.getPlayer().farmerType
                                                        .get(mainFarm.getPlayer().getCurrFarmerType())
                                                        .getSeedCostReduction())
                                                || !mainFarm.isVacant(currentI, currentJ)) {
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                        } else {
                                            mainFarm.getPlayer().subtractCoins(
                                                    crops.get(7).getCropCost() - mainFarm.getPlayer().farmerType
                                                            .get(mainFarm.getPlayer().getCurrFarmerType())
                                                            .getSeedCostReduction());
                                            mainFarm.plantCrop(crops.get(7), currentI, currentJ);
                                            farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                            hideButtons();
                                            showButtons();
                                            farmView.updateGrowingCropFarmTileImage(crops.get(7).getName(),
                                                    plantedCropLand, plantedTreeLand, currentI, currentJ);
                                            farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                            farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                                    mainFarm.getPlayer().getLevel(),
                                                    mainFarm.getPlayer().getExperience(),
                                                    mainFarm.getPlayer().getCurrFarmerType(),
                                                    mainFarm.getPlayer().getFarmerType(), days);
                                        }
                                    }
                                };

                                Action returnActionListener = new AbstractAction("Return") {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                        hideButtons();
                                        showButtons();
                                    }
                                };

                                farmView.setCropActionListeners(turnipActionListener, carrotActionListener,
                                        potatoActionListener, roseActionListener, tulipActionListener,
                                        sunflowerActionListener, mangoActionListener, appleActionListener,
                                        returnActionListener);
                            }
                        };

                        Action plowActionListener = new AbstractAction("Plow - Free") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(0, currentI, currentJ);

                                farmView.setPlantCropBtnVisibility(true);
                                farmView.setPlowBtnVisibility(false);

                                mainFarm.getPlayer().updateLevel();
                                farmView.updateFarmTileImage(mainFarm.getTiles()[currentI][currentJ], unplowedLand,
                                        plowedLand, currentI, currentJ);
                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                        mainFarm.getPlayer().getLevel(),
                                        mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(),
                                        mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        Action wateringCanActionListener = new AbstractAction("Watering Can - Free") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.useTool(1, currentI, currentJ);

                                hideButtons();
                                showButtons();
                                mainFarm.getPlayer().updateLevel();
                                farmView.updateNextDayFarmTileImage(mainFarm, witheredCropLand, turnipReadyLand,
                                        carrotReadyLand,
                                        potatoReadyLand, roseReadyLand, tulipReadyLand, sunflowerReadyLand,
                                        mangoReadyLand,
                                        appleReadyLand);
                                farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                        mainFarm.getPlayer().getLevel(),
                                        mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(),
                                        mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        Action fertilizerActionListener = new AbstractAction("Fertilizer - 10 Objectcoins") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!mainFarm.useTool(2, currentI, currentJ)) {
                                    farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                } else {
                                    farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                    hideButtons();
                                    showButtons();
                                    mainFarm.getPlayer().updateLevel();
                                    farmView.updateNextDayFarmTileImage(mainFarm, witheredCropLand, turnipReadyLand,
                                            carrotReadyLand, potatoReadyLand, roseReadyLand, tulipReadyLand,
                                            sunflowerReadyLand,
                                            mangoReadyLand, appleReadyLand);
                                    farmView.updateTileInfo(mainFarm.getTiles()[currentI][currentJ], mainFarm);
                                    farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                            mainFarm.getPlayer().getLevel(),
                                            mainFarm.getPlayer().getExperience(),
                                            mainFarm.getPlayer().getCurrFarmerType(),
                                            mainFarm.getPlayer().getFarmerType(), days);
                                }
                            }
                        };

                        // Done
                        Action pickaxeActionListener = new AbstractAction("Pickaxe - 50 Objectcoins") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!mainFarm.useTool(3, currentI, currentJ)) {
                                    farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                } else {
                                    farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                    farmView.setPickaxeBtnVisibility(false);
                                    farmView.setPlowBtnVisibility(true);

                                    mainFarm.getPlayer().updateLevel();
                                    farmView.updateFarmTileImage(mainFarm.getTiles()[currentI][currentJ], unplowedLand,
                                            plowedLand, currentI, currentJ);
                                    farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                            mainFarm.getPlayer().getLevel(),
                                            mainFarm.getPlayer().getExperience(),
                                            mainFarm.getPlayer().getCurrFarmerType(),
                                            mainFarm.getPlayer().getFarmerType(), days);
                                }
                            }
                        };

                        // Done
                        Action shovelActionListener = new AbstractAction("Shovel - 7 Objectcoins") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!mainFarm.useTool(4, currentI, currentJ)) {
                                    farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.RED);
                                } else {
                                    farmView.getFarmBtn()[currentI][currentJ].setBackground(Color.DARK_GRAY);
                                    hideButtons();
                                    showButtons();
                                    mainFarm.getPlayer().updateLevel();
                                    farmView.updateFarmTileImage(mainFarm.getTiles()[currentI][currentJ], unplowedLand,
                                            plowedLand, currentI, currentJ);
                                    farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                            mainFarm.getPlayer().getLevel(),
                                            mainFarm.getPlayer().getExperience(),
                                            mainFarm.getPlayer().getCurrFarmerType(),
                                            mainFarm.getPlayer().getFarmerType(), days);
                                }
                            }
                        };

                        Action harvestActionListener = new AbstractAction("Harvest Crop") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainFarm.harvestCrop(currentI, currentJ, farmView);

                                hideButtons();
                                showButtons();
                                
                                farmView.setTextPanelVisibility(true);
                                mainFarm.getPlayer().updateLevel();
                                farmView.updateFarmTileImage(mainFarm.getTiles()[currentI][currentJ], unplowedLand,
                                        plowedLand, currentI, currentJ);
                                farmView.updatePlayerInfo(mainFarm.getPlayer().getWallet(),
                                        mainFarm.getPlayer().getLevel(),
                                        mainFarm.getPlayer().getExperience(), mainFarm.getPlayer().getCurrFarmerType(),
                                        mainFarm.getPlayer().getFarmerType(), days);
                            }
                        };

                        Action backActionListener = new AbstractAction("Back") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                hideButtons();
                                mainFarm.getPlayer().updateLevel();
                                farmView.setNextDayBtnVisibility(true);
                                farmView.setRegisterFarmerBtnVisibility(true);
                                farmView.getFarmBtn()[currentI][currentJ].setBackground(null);
                                farmView.setTileInfoVisibility(false);
                            }
                        };

                        farmView.setActionsActionListeners(plantCropActionListener, plowActionListener,
                                wateringCanActionListener, fertilizerActionListener, pickaxeActionListener,
                                shovelActionListener, harvestActionListener, backActionListener);
                    }
                };
            }
        }

        this.farmView.setActionListeners(farmActionListener, nextDayActionListener, registerFarmerActionListener);
    }

    public void hideButtons() {
        farmView.setTextPanelVisibility(false);

        farmView.setNextDayBtnVisibility(false);
        farmView.setRegisterFarmerBtnVisibility(false);
        farmView.setPlantCropBtnVisibility(false);
        farmView.setPlowBtnVisibility(false);
        farmView.setWateringCanBtnVisibility(false);
        farmView.setFertilizerBtnVisibility(false);
        farmView.setPickaxeBtnVisibility(false);
        farmView.setShovelBtnVisibility(false);
        farmView.setHarvestBtnVisibility(false);
        farmView.setBackBtnVisibility(false);

        farmView.setTurnipBtnVisibility(false);
        farmView.setCarrotBtnVisibility(false);
        farmView.setPotatoBtnVisibility(false);
        farmView.setRoseBtnVisibility(false);
        farmView.setTulipBtnVisibility(false);
        farmView.setSunflowerBtnVisibility(false);
        farmView.setAppleBtnVisibility(false);
        farmView.setMangoBtnVisibility(false);
        farmView.setReturnBtnVisibility(false);
    }

    public void showButtons() {
        if (!mainFarm.getTiles()[currentI][currentJ].isOccupied() && !mainFarm.getTiles()[currentI][currentJ].isRock()
                && mainFarm.getTiles()[currentI][currentJ].isPlowed())
            farmView.setPlantCropBtnVisibility(true);

        if (!mainFarm.getTiles()[currentI][currentJ].isPlowed() && !mainFarm.getTiles()[currentI][currentJ].isRock())
            farmView.setPlowBtnVisibility(true);

        if (mainFarm.getTiles()[currentI][currentJ].isOccupied() && !mainFarm.getTiles()[currentI][currentJ].isRock()
                && (mainFarm.getTiles()[currentI][currentJ].getCrop()
                        .getWaterAmt() < (mainFarm.getTiles()[currentI][currentJ].getCrop().getMaxWater()
                                + mainFarm.getPlayer().farmerType.get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getWaterBonusIncrease()))
                && !mainFarm.getTiles()[currentI][currentJ].getCrop().isWithered()
                && !mainFarm.getTiles()[currentI][currentJ].getCrop().isReady())
            farmView.setWateringCanBtnVisibility(true);

        if (mainFarm.getTiles()[currentI][currentJ].isOccupied() && !mainFarm.getTiles()[currentI][currentJ].isRock()
                && (mainFarm.getTiles()[currentI][currentJ].getCrop()
                        .getFertilizerAmt() < (mainFarm.getTiles()[currentI][currentJ].getCrop().getMaxFertilizer()
                                + mainFarm.getPlayer().farmerType.get(mainFarm.getPlayer().getCurrFarmerType())
                                        .getFertilizerBonusIncrease()))
                && !mainFarm.getTiles()[currentI][currentJ].getCrop().isWithered()
                && !mainFarm.getTiles()[currentI][currentJ].getCrop().isReady())
            farmView.setFertilizerBtnVisibility(true);

        if (mainFarm.getTiles()[currentI][currentJ].isRock())
            farmView.setPickaxeBtnVisibility(true);

        if (mainFarm.getTiles()[currentI][currentJ].isOccupied() && !mainFarm.getTiles()[currentI][currentJ].isRock())
            farmView.setShovelBtnVisibility(true);

        if (mainFarm.getTiles()[currentI][currentJ].isOccupied()
                && mainFarm.getTiles()[currentI][currentJ].getCrop().isReady())
            farmView.setHarvestBtnVisibility(true);

        farmView.setBackBtnVisibility(true);
    }

}