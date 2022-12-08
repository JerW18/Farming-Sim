import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class FarmView extends JFrame {

    private Border border = BorderFactory.createLineBorder(Color.black, 1);

    private JFrame mainFrame;
    private JButton[][] farmBtn = new JButton[5][10];
    private JPanel farmPanel = new JPanel();

    private JPanel playerInfoPanel = new JPanel();
    private JLabel playerInfoDay;
    private JLabel playerInfo;
    private JLabel playerInfoWallet;
    private JLabel playerInfoLevel;
    private JLabel playerInfoExp;
    private JLabel playerInfoFarmerType;

    private JPanel tileInfoPanel = new JPanel();
    private JPanel tileInfoBorderPanel = new JPanel();
    private JLabel cropInfo;
    private JLabel cropInfoName;
    private JLabel cropInfoDaysPlanted;
    private JLabel cropInfoWaterAmt;
    private JLabel cropInfoFertilizerAmt;

    private JPanel textPanel = new JPanel();
    private JLabel cropProduced;
    private JLabel coinsGained;

    private JPanel actionsPanel = new JPanel();
    private JButton nextDayBtn;
    private JButton registerFarmerBtn;
    private JButton plantCropBtn;
    private JButton plowBtn;
    private JButton wateringCanBtn;
    private JButton fertilizerBtn;
    private JButton pickaxeBtn;
    private JButton shovelBtn;
    private JButton harvestBtn;
    private JButton backBtn;
    private JButton exitBtn;
    private JButton returnBtn;

    private JButton turnipBtn;
    private JButton carrotBtn;
    private JButton potatoBtn;
    private JButton roseBtn;
    private JButton tulipBtn;
    private JButton sunflowerBtn;
    private JButton mangoBtn;
    private JButton appleBtn;

    public FarmView() {
        this.mainFrame = new JFrame("My Farm");

        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setSize(1105, 650);
        this.mainFrame.setLayout(null);
        this.mainFrame.setResizable(false);

        initializeFarmPanel();
        initializePlayerInfoPanel();
        initializeTileInfoPanel();
        initializeTextPanel();
        initializeActionsPanel();

        this.mainFrame.setVisible(true);
    }

    private void initializeFarmPanel() {
        this.farmPanel.setBounds(0, 0, 700, 610);
        this.farmPanel.setLayout(new GridLayout(5, 10));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.farmBtn[i][j] = new JButton();
                this.farmBtn[i][j].setFocusable(false);
                this.farmPanel.add(farmBtn[i][j]);
            }
        }

        this.mainFrame.add(this.farmPanel);
    }

    private void initializePlayerInfoPanel() {
        this.playerInfoPanel.setBounds(700, 0, 195, 250);
        this.playerInfoPanel.setLayout(new GridLayout(8, 1));
        this.playerInfoPanel.setBorder(border);

        this.playerInfoDay = new JLabel(" Day 1");
        this.playerInfoDay.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoDay.setFont(new Font("Arial", Font.PLAIN, 20));

        this.playerInfo = new JLabel(" Player Info -");
        this.playerInfo.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfo.setFont(new Font("Arial", Font.PLAIN, 20));

        this.playerInfoWallet = new JLabel("  Wallet: 100");
        this.playerInfoWallet.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoWallet.setFont(new Font("Arial", Font.PLAIN, 13));

        this.playerInfoLevel = new JLabel("  Level: 0");
        this.playerInfoLevel.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoLevel.setFont(new Font("Arial", Font.PLAIN, 13));

        this.playerInfoExp = new JLabel("  Experience: 0.0/100");
        this.playerInfoExp.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoExp.setFont(new Font("Arial", Font.PLAIN, 13));

        this.playerInfoFarmerType = new JLabel("  Farmer Type: Farmer");
        this.playerInfoFarmerType.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoFarmerType.setFont(new Font("Arial", Font.PLAIN, 13));

        this.playerInfoPanel.add(this.playerInfoDay);
        this.playerInfoPanel.add(this.playerInfo);
        this.playerInfoPanel.add(this.playerInfoWallet);
        this.playerInfoPanel.add(this.playerInfoLevel);
        this.playerInfoPanel.add(this.playerInfoExp);
        this.playerInfoPanel.add(this.playerInfoFarmerType);

        this.mainFrame.add(this.playerInfoPanel);
    }

    private void initializeTileInfoPanel() {
        this.tileInfoPanel.setBounds(896, 1, 193, 248);
        this.tileInfoPanel.setLayout(new GridLayout(8, 1));
        this.tileInfoPanel.setVisible(false);

        this.tileInfoBorderPanel.setBounds(895, 0, 195, 250);
        this.tileInfoBorderPanel.setBorder(border);

        this.cropInfo = new JLabel();
        this.cropInfo.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfo.setFont(new Font("Arial", Font.PLAIN, 20));

        this.cropInfoName = new JLabel();
        this.cropInfoName.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoName.setFont(new Font("Arial", Font.PLAIN, 15));

        this.cropInfoDaysPlanted = new JLabel();
        this.cropInfoDaysPlanted.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoDaysPlanted.setFont(new Font("Arial", Font.PLAIN, 15));

        this.cropInfoWaterAmt = new JLabel();
        this.cropInfoWaterAmt.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoWaterAmt.setFont(new Font("Arial", Font.PLAIN, 15));

        this.cropInfoFertilizerAmt = new JLabel();
        this.cropInfoFertilizerAmt.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoFertilizerAmt.setFont(new Font("Arial", Font.PLAIN, 15));

        this.tileInfoPanel.add(this.cropInfo);
        this.tileInfoPanel.add(this.cropInfoName);
        this.tileInfoPanel.add(this.cropInfoDaysPlanted);
        this.tileInfoPanel.add(this.cropInfoWaterAmt);
        this.tileInfoPanel.add(this.cropInfoFertilizerAmt);

        this.mainFrame.add(this.tileInfoPanel);
        this.mainFrame.add(this.tileInfoBorderPanel);
    }

    private void initializeTextPanel() {
        this.textPanel.setBounds(700, 250, 390, 120);
        this.textPanel.setLayout(new GridLayout(4, 1));
        this.tileInfoPanel.setVisible(false);
        this.textPanel.setBorder(border);

        this.cropProduced = new JLabel();
        this.cropProduced.setAlignmentX(LEFT_ALIGNMENT);
        this.cropProduced.setFont(new Font("Arial", Font.PLAIN, 15));

        this.coinsGained = new JLabel();
        this.coinsGained.setAlignmentX(LEFT_ALIGNMENT);
        this.coinsGained.setFont(new Font("Arial", Font.PLAIN, 15));

        this.textPanel.add(this.cropProduced);
        this.textPanel.add(this.coinsGained);

        this.mainFrame.add(this.textPanel);
    }

    private void initializeActionsPanel() {
        this.actionsPanel.setBounds(700, 370, 390, 240);
        this.actionsPanel.setBorder(border);

        this.nextDayBtn = new JButton();
        this.nextDayBtn.setPreferredSize(new Dimension(200, 50));
        this.nextDayBtn.setFocusable(false);

        this.registerFarmerBtn = new JButton();
        this.registerFarmerBtn.setPreferredSize(new Dimension(200, 50));
        this.registerFarmerBtn.setFocusable(false);

        this.actionsPanel.add(this.nextDayBtn);
        this.actionsPanel.add(this.registerFarmerBtn);

        initializeToolButtons();
        initializeCropButtons();

        this.mainFrame.add(this.actionsPanel);
    }

    private void initializeToolButtons() {
        this.plantCropBtn = new JButton("Select Crop");
        this.plantCropBtn.setPreferredSize(new Dimension(180, 50));
        this.plantCropBtn.setFocusable(false);
        this.plantCropBtn.setVisible(false);

        this.plowBtn = new JButton("Plow - Free");
        this.plowBtn.setPreferredSize(new Dimension(180, 50));
        this.plowBtn.setFocusable(false);
        this.plowBtn.setVisible(false);

        this.wateringCanBtn = new JButton("Watering Can - Free");
        this.wateringCanBtn.setPreferredSize(new Dimension(180, 50));
        this.wateringCanBtn.setFocusable(false);
        this.wateringCanBtn.setVisible(false);

        this.fertilizerBtn = new JButton("Fertilizer - 10 Objectcoins");
        this.fertilizerBtn.setPreferredSize(new Dimension(180, 50));
        this.fertilizerBtn.setFocusable(false);
        this.fertilizerBtn.setVisible(false);

        this.pickaxeBtn = new JButton("Pickaxe - 50 Objectcoins");
        this.pickaxeBtn.setPreferredSize(new Dimension(180, 50));
        this.pickaxeBtn.setFocusable(false);
        this.pickaxeBtn.setVisible(false);

        this.shovelBtn = new JButton("Shovel - 7 Objectcoins");
        this.shovelBtn.setPreferredSize(new Dimension(180, 50));
        this.shovelBtn.setFocusable(false);
        this.shovelBtn.setVisible(false);

        this.harvestBtn = new JButton("Harvest Crop");
        this.harvestBtn.setPreferredSize(new Dimension(180, 50));
        this.harvestBtn.setFocusable(false);
        this.harvestBtn.setVisible(false);

        this.backBtn = new JButton();
        this.backBtn.setPreferredSize(new Dimension(180, 50));
        this.backBtn.setFocusable(false);
        this.backBtn.setVisible(false);

        this.exitBtn = new JButton("Return");
        this.exitBtn.setPreferredSize(new Dimension(120, 60));
        this.exitBtn.setFocusable(false);
        this.exitBtn.setVisible(false);
        this.exitBtn.setAction(new AbstractAction("Exit Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.actionsPanel.add(harvestBtn);
        this.actionsPanel.add(plantCropBtn);
        this.actionsPanel.add(plowBtn);
        this.actionsPanel.add(wateringCanBtn);
        this.actionsPanel.add(fertilizerBtn);
        this.actionsPanel.add(pickaxeBtn);
        this.actionsPanel.add(shovelBtn);
        this.actionsPanel.add(backBtn);
        this.actionsPanel.add(exitBtn);
    }

    private void initializeCropButtons() {
        this.turnipBtn = new JButton();
        this.turnipBtn.setPreferredSize(new Dimension(120, 60));
        this.turnipBtn.setFocusable(false);
        this.turnipBtn.setVisible(false);

        this.carrotBtn = new JButton();
        this.carrotBtn.setPreferredSize(new Dimension(120, 60));
        this.carrotBtn.setFocusable(false);
        this.carrotBtn.setVisible(false);

        this.potatoBtn = new JButton();
        this.potatoBtn.setPreferredSize(new Dimension(120, 60));
        this.potatoBtn.setFocusable(false);
        this.potatoBtn.setVisible(false);

        this.roseBtn = new JButton();
        this.roseBtn.setPreferredSize(new Dimension(120, 60));
        this.roseBtn.setFocusable(false);
        this.roseBtn.setVisible(false);

        this.tulipBtn = new JButton();
        this.tulipBtn.setPreferredSize(new Dimension(120, 60));
        this.tulipBtn.setFocusable(false);
        this.tulipBtn.setVisible(false);

        this.sunflowerBtn = new JButton();
        this.sunflowerBtn.setPreferredSize(new Dimension(120, 60));
        this.sunflowerBtn.setFocusable(false);
        this.sunflowerBtn.setVisible(false);

        this.mangoBtn = new JButton();
        this.mangoBtn.setPreferredSize(new Dimension(120, 60));
        this.mangoBtn.setFocusable(false);
        this.mangoBtn.setVisible(false);

        this.appleBtn = new JButton();
        this.appleBtn.setPreferredSize(new Dimension(120, 60));
        this.appleBtn.setFocusable(false);
        this.appleBtn.setVisible(false);

        this.returnBtn = new JButton("Return");
        this.returnBtn.setPreferredSize(new Dimension(120, 60));
        this.returnBtn.setFocusable(false);
        this.returnBtn.setVisible(false);

        this.actionsPanel.add(turnipBtn);
        this.actionsPanel.add(carrotBtn);
        this.actionsPanel.add(potatoBtn);
        this.actionsPanel.add(roseBtn);
        this.actionsPanel.add(tulipBtn);
        this.actionsPanel.add(sunflowerBtn);
        this.actionsPanel.add(mangoBtn);
        this.actionsPanel.add(appleBtn);
        this.actionsPanel.add(returnBtn); 
    }

    public void setTileInfoVisibility(boolean tileInfoVisibility) {
        this.tileInfoPanel.setVisible(tileInfoVisibility);
    }

    public void setTextPanelVisibility(boolean textPanelVisibility) {
        this.textPanel.setVisible(textPanelVisibility);
    }

    public void setNextDayBtnVisibility(boolean nextDayBtnVisibility) {
        this.nextDayBtn.setVisible(nextDayBtnVisibility);
    }

    public void setRegisterFarmerBtnVisibility(boolean registerFarmerBtnVisibility) {
        this.registerFarmerBtn.setVisible(registerFarmerBtnVisibility);
    }

    public void setRegisterFarmerBtnColor(Color color) {
        this.registerFarmerBtn.setBackground(color);
    }

    public JButton GetRegisterFarmerBtn() {
        return this.registerFarmerBtn;
    }

    public void setPlantCropBtnVisibility(boolean plantCropBtnVisibility) {
        this.plantCropBtn.setVisible(plantCropBtnVisibility);
    }

    public void setPlowBtnVisibility(boolean plowBtnVisibility) {
        this.plowBtn.setVisible(plowBtnVisibility);
    }

    public void setWateringCanBtnVisibility(boolean wateringCanBtnVisibility) {
        this.wateringCanBtn.setVisible(wateringCanBtnVisibility);
    }

    public void setFertilizerBtnVisibility(boolean fertilizerBtnVisibility) {
        this.fertilizerBtn.setVisible(fertilizerBtnVisibility);
    }

    public void setPickaxeBtnVisibility(boolean pickaxeBtnVisibility) {
        this.pickaxeBtn.setVisible(pickaxeBtnVisibility);
    }

    public void setShovelBtnVisibility(boolean shovelBtnVisibility) {
        this.shovelBtn.setVisible(shovelBtnVisibility);
    }

    public void setHarvestBtnVisibility(boolean harvestBtnVisibility) {
        this.harvestBtn.setVisible(harvestBtnVisibility);
    }

    public void setBackBtnVisibility(boolean backBtnVisibility) {
        this.backBtn.setVisible(backBtnVisibility);
    }

    public void setExitBtnVisibility(boolean exitBtnVisibility) {
        this.exitBtn.setVisible(exitBtnVisibility);
    }

    public void setReturnBtnVisibility(boolean returnBtnVisibility) {
        this.returnBtn.setVisible(returnBtnVisibility);
    }

    public void setTurnipBtnVisibility(boolean turnipBtnVisibility) {
        this.turnipBtn.setVisible(turnipBtnVisibility);
    }

    public void setCarrotBtnVisibility(boolean carrotBtnVisibility) {
        this.carrotBtn.setVisible(carrotBtnVisibility);
    }

    public void setPotatoBtnVisibility(boolean potatoBtnVisibility) {
        this.potatoBtn.setVisible(potatoBtnVisibility);
    }

    public void setRoseBtnVisibility(boolean roseBtnVisibility) {
        this.roseBtn.setVisible(roseBtnVisibility);
    }

    public void setTulipBtnVisibility(boolean tulipBtnVisibility) {
        this.tulipBtn.setVisible(tulipBtnVisibility);
    }

    public void setSunflowerBtnVisibility(boolean sunflowerBtnVisibility) {
        this.sunflowerBtn.setVisible(sunflowerBtnVisibility);
    }

    public void setMangoBtnVisibility(boolean mangoBtnVisibility) {
        this.mangoBtn.setVisible(mangoBtnVisibility);
    }

    public void setAppleBtnVisibility(boolean appleBtnVisibility) {
        this.appleBtn.setVisible(appleBtnVisibility);
    }

    public void setCropProducedText(String string) {
        this.cropProduced.setText(string);
    }

    public void setCoinsGainedText(String string) {
        this.coinsGained.setText(string);
    }

    public void setActionListeners(ActionListener[][] farmActionListeners, Action nextDayActionListener, Action registerFarmerActionListener) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.farmBtn[i][j].addActionListener(farmActionListeners[i][j]);
            }
        }

        this.nextDayBtn.setAction(nextDayActionListener);
        this.registerFarmerBtn.setAction(registerFarmerActionListener);
    }

    public void setActionsActionListeners(Action plantCropActionListener, Action plowActionListener,
            Action wateringCanActionListener, Action fertilizerActionListener, Action pickaxeActionListener,
            Action shovelActionListener, Action harvestActionListener, Action backActionListener) {
        this.plantCropBtn.setAction(plantCropActionListener);
        this.plowBtn.setAction(plowActionListener);
        this.wateringCanBtn.setAction(wateringCanActionListener);
        this.fertilizerBtn.setAction(fertilizerActionListener);
        this.pickaxeBtn.setAction(pickaxeActionListener);
        this.shovelBtn.setAction(shovelActionListener);
        this.harvestBtn.setAction(harvestActionListener);
        this.backBtn.setAction(backActionListener);
    }

    public void setCropActionListeners(Action turnipActionListener, Action carrotActionListener,
            Action potatoActionListener, Action roseActionListener, Action tulipActionListener,
            Action sunflowerActionListener, Action mangoActionListener, Action appleActionListener,
            Action returnActionListener) {
        this.turnipBtn.setAction(turnipActionListener);
        this.carrotBtn.setAction(carrotActionListener);
        this.potatoBtn.setAction(potatoActionListener);
        this.roseBtn.setAction(roseActionListener);
        this.tulipBtn.setAction(tulipActionListener);
        this.sunflowerBtn.setAction(sunflowerActionListener);
        this.mangoBtn.setAction(mangoActionListener);
        this.appleBtn.setAction(appleActionListener);
        this.returnBtn.setAction(returnActionListener);
    }

    public JButton[][] getFarmBtn() {
        return farmBtn;
    }

    public void initializeFarmTileImage(FarmLot mainFarm, ImageIcon unplowedLand, ImageIcon rockLand) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (!mainFarm.getTiles()[i][j].isPlowed()) {
                    this.farmBtn[i][j].setIcon(
                            new ImageIcon(unplowedLand.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
                }

                if (mainFarm.getTiles()[i][j].isRock()) {
                    this.farmBtn[i][j]
                            .setIcon(new ImageIcon(rockLand.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
                }
            }
        }
    }

    public void updateFarmTileImage(Tile tile, ImageIcon unplowedLand, ImageIcon plowedLand, int i, int j) {
        if (!tile.isPlowed() && !tile.isRock()) {
            this.farmBtn[i][j]
                    .setIcon(new ImageIcon(unplowedLand.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        }

        if (tile.isPlowed() && !tile.isRock()) {
            this.farmBtn[i][j]
                    .setIcon(new ImageIcon(plowedLand.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        }
    }

    public void updateNextDayFarmTileImage(FarmLot mainFarm, ImageIcon witheredCropLand, ImageIcon turnipReadyLand, ImageIcon carrotReadyLand, ImageIcon potatoReadyLand, ImageIcon roseReadyLand, ImageIcon tulipReadyLand, ImageIcon sunflowerReadyLand, ImageIcon mangoReadyLand, ImageIcon appleReadyLand ) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (mainFarm.getTiles()[i][j].getCrop().isWithered()) {
                    this.farmBtn[i][j].setIcon(
                            new ImageIcon(witheredCropLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                }

                if (mainFarm.getTiles()[i][j].getCrop().isReady()) {
                    switch (mainFarm.getTiles()[i][j].getCrop().getName()) {
                        case "Turnip":
                            this.farmBtn[i][j].setIcon(new ImageIcon(turnipReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;

                        case "Carrot":
                            this.farmBtn[i][j].setIcon(new ImageIcon(carrotReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;

                        case "Potato":
                            this.farmBtn[i][j].setIcon(new ImageIcon(potatoReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;

                        case "Rose":
                            this.farmBtn[i][j].setIcon(new ImageIcon(roseReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;

                        case "Tulip":
                            this.farmBtn[i][j].setIcon(new ImageIcon(tulipReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;

                        case "Sunflower":
                            this.farmBtn[i][j].setIcon(new ImageIcon(sunflowerReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;
                    
                        case "Mango":
                            this.farmBtn[i][j].setIcon(new ImageIcon(mangoReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;

                        case "Apple":
                            this.farmBtn[i][j].setIcon(new ImageIcon(appleReadyLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                            break;
            
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void updateGrowingCropFarmTileImage(String cropName, ImageIcon plantedCropLand, ImageIcon plantedTreeLand, int i, int j) {
        switch (cropName) {
            case "Turnip":
            case "Carrot":
            case "Potato":
            case "Rose":
            case "Tulip":
            case "Sunflower":
                this.farmBtn[i][j].setIcon(new ImageIcon(plantedCropLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                break;
        
            case "Mango":
            case "Apple":
                this.farmBtn[i][j].setIcon(new ImageIcon(plantedTreeLand.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
                break;

            default:
                break;
        }
    }

    public void updatePlayerInfo(double wallet, int level, double experience, int currFarmerType,
            ArrayList<FarmerType> farmerType, int day) {
        this.playerInfoDay.setText(" Day " + day);
        this.playerInfo.setText(" Player Info -");
        this.playerInfoWallet.setText("  Wallet: " + wallet);
        this.playerInfoLevel.setText("  Level: " + level);
        this.playerInfoExp.setText("  Experience: " + experience + "/" + (100 + 100 * level));
        this.playerInfoFarmerType.setText("  Farmer Type: " + farmerType.get(currFarmerType).getName());
    }

    public void updateTileInfo(Tile tile, FarmLot mainFarm) {
        this.tileInfoPanel.setVisible(true);
        this.cropInfo.setText(" Crop Info -");
        this.cropInfoName.setText("  Name: " + tile.getCrop().getName());
        this.cropInfoDaysPlanted.setText(
                "  Days Planted: " + tile.getCrop().getDaysPlanted() + "/" + tile.getCrop().getDaysUntilHarvest());
        this.cropInfoWaterAmt.setText("  Water Amount: " + tile.getCrop().getWaterAmt() + "/"
                + tile.getCrop().getMinWater() + "(" + (tile.getCrop().getMaxWater() + mainFarm.getPlayer()
                        .getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType()).getWaterBonusIncrease())
                + ")");
        this.cropInfoFertilizerAmt.setText("  Fertilizer Amount: " + tile.getCrop().getFertilizerAmt() + "/"
                + tile.getCrop().getMinFertilizer() + "(" + (tile.getCrop().getMaxFertilizer() + mainFarm.getPlayer()
                        .getFarmerType().get(mainFarm.getPlayer().getCurrFarmerType()).getFertilizerBonusIncrease())
                + ")");
    }
}
