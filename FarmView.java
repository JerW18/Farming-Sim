import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
    private JPanel tileInfoTempPanel = new JPanel();
    private JLabel cropInfo;
    private JLabel cropInfoName;
    private JLabel cropInfoDaysPlanted;
    private JLabel cropInfoWaterAmt;
    private JLabel cropInfoFertilizerAmt;

    private JPanel textPanel = new JPanel();

    private JPanel actionsPanel = new JPanel();
    private JButton nextDayBtn;
    private JButton plantCropBtn;
    private JButton plowBtn;
    private JButton wateringCanBtn;
    private JButton fertilizerBtn;
    private JButton pickaxeBtn;
    private JButton shovelBtn;
    private JButton backBtn;


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
        this.playerInfoWallet.setFont(new Font("Arial", Font.PLAIN, 15));

        this.playerInfoLevel = new JLabel("  Level: 0");
        this.playerInfoLevel.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoLevel.setFont(new Font("Arial", Font.PLAIN, 15));

        this.playerInfoExp = new JLabel("  Experience: 0.0/100");
        this.playerInfoExp.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoExp.setFont(new Font("Arial", Font.PLAIN, 15));

        this.playerInfoFarmerType = new JLabel("  Farmer Type: Farmer");
        this.playerInfoFarmerType.setAlignmentX(LEFT_ALIGNMENT);
        this.playerInfoFarmerType.setFont(new Font("Arial", Font.PLAIN, 15));

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

        this.tileInfoTempPanel.setBounds(895, 0, 195, 250);
        this.tileInfoTempPanel.setBorder(border);
        
        this.cropInfo = new JLabel("a");
        this.cropInfo.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfo.setFont(new Font("Arial", Font.PLAIN, 20));

        this.cropInfoName = new JLabel("a");
        this.cropInfoName.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoName.setFont(new Font("Arial", Font.PLAIN, 15));

        this.cropInfoDaysPlanted = new JLabel("a");
        this.cropInfoDaysPlanted.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoDaysPlanted.setFont(new Font("Arial", Font.PLAIN, 15));
        
        this.cropInfoWaterAmt = new JLabel("a");
        this.cropInfoWaterAmt.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoWaterAmt.setFont(new Font("Arial", Font.PLAIN, 15));

        this.cropInfoFertilizerAmt = new JLabel("a");
        this.cropInfoFertilizerAmt.setAlignmentX(LEFT_ALIGNMENT);
        this.cropInfoFertilizerAmt.setFont(new Font("Arial", Font.PLAIN, 15));

        this.tileInfoPanel.add(this.cropInfo);
        this.tileInfoPanel.add(this.cropInfoName);
        this.tileInfoPanel.add(this.cropInfoDaysPlanted);
        this.tileInfoPanel.add(this.cropInfoWaterAmt);
        this.tileInfoPanel.add(this.cropInfoFertilizerAmt);

        this.mainFrame.add(this.tileInfoPanel);
        this.mainFrame.add(this.tileInfoTempPanel);
    }
    
    private void initializeTextPanel() {
        this.textPanel.setBounds(700, 250, 390, 120);
        this.textPanel.setBorder(border);

        this.mainFrame.add(this.textPanel);
    }

    private void initializeActionsPanel() {
        this.actionsPanel.setBounds(700, 370, 390, 240);
        this.actionsPanel.setBorder(border);
        
        this.nextDayBtn = new JButton("Next Day");
        this.nextDayBtn.setPreferredSize(new Dimension(200, 50));

        this.actionsPanel.add(this.nextDayBtn);

        initializeToolButtons();

        this.mainFrame.add(this.actionsPanel);
    }

    private void initializeToolButtons() {
        this.plantCropBtn = new JButton("Select Crop");
        this.plantCropBtn.setPreferredSize(new Dimension(180, 50));
        this.plantCropBtn.setVisible(false);

        this.plowBtn = new JButton();
        this.plowBtn.setPreferredSize(new Dimension(180, 50));
        this.plowBtn.setVisible(false);

        this.wateringCanBtn = new JButton();
        this.wateringCanBtn.setPreferredSize(new Dimension(180, 50));
        this.wateringCanBtn.setVisible(false);

        this.fertilizerBtn = new JButton();
        this.fertilizerBtn.setPreferredSize(new Dimension(180, 50));
        this.fertilizerBtn.setVisible(false);

        this.pickaxeBtn = new JButton();
        this.pickaxeBtn.setPreferredSize(new Dimension(180, 50));
        this.pickaxeBtn.setVisible(false);

        this.shovelBtn = new JButton();
        this.shovelBtn.setPreferredSize(new Dimension(180, 50));
        this.shovelBtn.setVisible(false);

        this.backBtn = new JButton();
        this.backBtn.setPreferredSize(new Dimension(180, 50));
        this.backBtn.setVisible(false);

        this.actionsPanel.add(plantCropBtn);
        this.actionsPanel.add(plowBtn);
        this.actionsPanel.add(wateringCanBtn);
        this.actionsPanel.add(fertilizerBtn);
        this.actionsPanel.add(pickaxeBtn);
        this.actionsPanel.add(shovelBtn);
        this.actionsPanel.add(backBtn);
    }

    public void updatePlayerInfo(int wallet, int level, double experience, int currFarmerType, ArrayList<FarmerType> farmerType, int day) {
        this.playerInfoDay.setText(" Day " + day);
        this.playerInfo.setText(" Player Info -");
        this.playerInfoWallet.setText("  Wallet: " + wallet);
        this.playerInfoLevel.setText("  Level: " + level);
        this.playerInfoExp.setText("  Experience: " + experience + "/" + (100 + 100 * level));
        this.playerInfoFarmerType.setText("  Farmer Type: " + farmerType.get(currFarmerType).getName());
    }

    public void updateTileInfo(Tile tile) {
        this.tileInfoPanel.setVisible(true);
        this.cropInfo.setText(" Crop Info -");
        this.cropInfoName.setText("  Name: " + tile.getCrop().getName());
        this.cropInfoDaysPlanted.setText("  Days Planted: " + tile.getCrop().getDaysPlanted());
        this.cropInfoWaterAmt.setText("  Water Amount: " + tile.getCrop().getWaterAmt());
        this.cropInfoFertilizerAmt.setText("  Fertilizer Amount: " + tile.getCrop().getFertilizerAmt());
    }

    public void setTileInfoVisibility(boolean tileInfoVisibility) {
        this.tileInfoPanel.setVisible(tileInfoVisibility);
    }

    public void setNextDayBtnVisibility(boolean nextDayBtnVisibility) {
        this.nextDayBtn.setVisible(nextDayBtnVisibility);
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

    public void setBackBtnVisibility(boolean backBtnVisibility) {
        this.backBtn.setVisible(backBtnVisibility);
    }

    public void setActionListeners(ActionListener[][] farmActionListeners, ActionListener nextDayActionListener) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                this.farmBtn[i][j].addActionListener(farmActionListeners[i][j]);
            }
        }

        this.nextDayBtn.addActionListener(nextDayActionListener);
    }

    public void setActionsActionListeners(Action plowActionListener, Action wateringCanActionListener, Action fertilizerActionListener, Action pickaxeActionListener, Action shovelActionListener, Action backActionListener) {
        this.plowBtn.setAction(plowActionListener);
        this.wateringCanBtn.setAction(wateringCanActionListener);
        this.fertilizerBtn.setAction(fertilizerActionListener);
        this.pickaxeBtn.setAction(pickaxeActionListener);
        this.shovelBtn.setAction(shovelActionListener);
        this.backBtn.setAction(backActionListener);
    }

    public JButton[][] getFarmBtn() {
        return farmBtn;
    }

    public void initializeFarmTileImage(FarmLot mainFarm, ImageIcon unplowedLand, ImageIcon rockLand) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (!mainFarm.getTiles()[i][j].isPlowed()) {
                    this.farmBtn[i][j].setIcon(new ImageIcon(unplowedLand.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
                }

                if (mainFarm.getTiles()[i][j].isRock()) {
                    this.farmBtn[i][j].setIcon(new ImageIcon(rockLand.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
                }
            }
        }
    }

    public void updateFarmTileImage(Tile tile, ImageIcon unplowedLand, ImageIcon plowedLand, ImageIcon wateredLand, int i, int j) {
        if (!tile.isPlowed() && !tile.isRock()) {
            this.farmBtn[i][j].setIcon(new ImageIcon(unplowedLand.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        }

        if (tile.isPlowed() && !tile.isRock()) {
            this.farmBtn[i][j].setIcon(new ImageIcon(plowedLand.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
        }
    }
}
