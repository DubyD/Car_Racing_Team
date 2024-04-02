

//Author VS
//Compatibility edits by AW
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
        
        //The Start button takes the paramaters chosen by user and 
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

        //Parameterless constructor
    public SceneSwitcher(){
        this.frame = null;
        this.gamePanel = null;
        this.menuScreen = null;
        this.gameScreen = null;
    }

    //This method is called when the StartButton is pressed and adds a function to the EndButton.
    //Which displays the Menu Screen again with the racers and their results.
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

    //Displays the Menu
    private void showMenu() {
        this.frame.setContentPane(this.menuScreen);
        this.frame.revalidate();
        this.menuScreen.displayResults();
    }
    
    @Override
    public String toString(){
        String reply = "This class handles switching between the Main Menu and the game";
        return reply;
    }
}