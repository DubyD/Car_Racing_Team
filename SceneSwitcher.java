//Author VS

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {

    private JFrame frame;

    private JPanel menuPanel;
    private JPanel gamePanel;

    private MenuGui menuScreen;
    private GameScreenGui gameScreen;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;
        this.menuScreen = new MenuGui();
        this.gameScreen = new GameScreenGui();

        //The Start button takes the paramaters chosen by user and 
        //creates the gameScreen, then displays it.
        this.menuScreen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuScreen.getSelectedSizeItem() != null 
                && menuScreen.getSelectedRacingItem() != null) {
                    showGame(menuScreen.getSelectedSizeItem(), menuScreen.getSelectedRacingItem());
                }
            }
        });

        //Sets up the Menu Screen
        this.menuPanel = new JPanel();
        this.menuPanel.add(menuScreen, BorderLayout.CENTER);

        //Initiates the menu first
        this.frame.setContentPane(menuPanel);
        this.frame.setVisible(true);
    }

    //This method is called when the StartButton is pressed and adds a function to the EndButton.
    //Which displays the Menu Screen again with the racers and their results.
    private void showGame(String size, String racersNum) {
        GameScreenGui gameScreen = new GameScreenGui(Integer.parseInt(size), Integer.parseInt(racersNum));

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
        this.gamePanel.add(gameScreen, BorderLayout.CENTER);

        this.frame.setContentPane(gamePanel);
        this.frame.revalidate();
    }

    //Displays the Menu
    private void showMenu() {
        this.frame.setContentPane(this.menuPanel);
        this.frame.revalidate();
    }
}