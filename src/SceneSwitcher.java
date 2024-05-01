
//Author VS
//Compatibility edits by AW
//EL added documentation, setter and getter, and fixed grammatical errors

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {

    private JFrame frame;

    private JPanel gamePanel;

    private MenuGui menuScreen;
    private GameScreenGui gameScreen;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        this.menuScreen = new MenuGui(frame);
        this.gameScreen = new GameScreenGui();
        
        //The Start button takes the parameters chosen by user and 
        //creates the gameScreen, then displays it.
        this.menuScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuScreen.getSelectedSizeItem() != null 
                && menuScreen.getSelectedRacingItem() != null) {
                    frame.getContentPane().removeAll();
                    showGame(menuScreen.getSelectedSizeItem(), menuScreen.getSelectedRacingItem());
                }
            }
        });

        //Initiates the menu first
        this.frame.setContentPane(menuScreen);
        this.frame.setVisible(true);
        this.menuScreen.titleScreen();
    }

        //No parameter constructor
    public SceneSwitcher(){
        this.frame = null;
        this.gamePanel = null;
        this.menuScreen = null;
        this.gameScreen = null;
    }

    /**
     * This method is called when the StartButton is pressed and adds a function to the EndButton.
     * Which displays the Menu Screen again with the racers and their results.
     * @param size
     * @param racersNum
     * @return void
     */
    private void showGame(String size, String racersNum) {
        gameScreen = new GameScreenGui(Integer.parseInt(size), Integer.parseInt(racersNum));

        gameScreen.getEndButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameScreen.getFinished()){
                    menuScreen.setResults(gameScreen.getGotham().getResults());
                    showMenu();
                }
            }
        });

        //Sets up the Game Screen
        this.gamePanel = new JPanel();
        this.gamePanel.setLayout(new BorderLayout());
        this.gamePanel.add(this.gameScreen, BorderLayout.CENTER);

        this.frame.setContentPane(gamePanel);
        this.frame.revalidate();
    }

    /**
     * This method is called when the EndButton is pressed and displays the Menu Screen.
     * @return void
     */
    private void showMenu() {
        this.frame.setContentPane(this.menuScreen);
        this.frame.revalidate();
        this.menuScreen.displayResults();
    }

    //-------------------------------Setters-----------------------------------------------------

    public void setFrame(JFrame frame){
        this.frame = frame;
    }

    public void setGamePanel(JPanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setMenuScreen(MenuGui menuScreen){
        this.menuScreen = menuScreen;
    }

    public void setGameScreen(GameScreenGui gameScreen){
        this.gameScreen = gameScreen;
    }

    //-------------------------------Getters-----------------------------------------------------

    public JFrame getFrame(){
        return this.frame;
    }

    public JPanel getGamePanel(){
        return this.gamePanel;
    }

    public MenuGui getMenuScreen(){
        return this.menuScreen;
    }

    public GameScreenGui getGameScreen(){
        return this.gameScreen;
    }
    
    @Override
    public String toString(){
        String reply = "This class handles switching between the Main Menu and the game";
        return reply;
    }
}