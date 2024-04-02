

//Author VS

import javax.swing.*;
import java.awt.*;

//Makes a neat display of the game
public class GameScreenGui extends JPanel {

    private GameGrid gameGrid;
    private JButton endGameButton;

    public GameScreenGui(int size, int racers) {

        this.setLayout(new BorderLayout());

        // Create GameGrid
        this.gameGrid = new GameGrid(size, racers);
        this.add(this.gameGrid, BorderLayout.CENTER);

        // Create JButton
        this.endGameButton = new JButton("End Game");

        // Creates a space for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(endGameButton);

        // Add buttonPanel to the bottom of the frame
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

        //Non-parameter constructor to complete this class
    public GameScreenGui(){
        this.gameGrid = null;
        this.endGameButton = null;
    }

    public JButton getEndButton(){
        return this.endGameButton;
    }

    public GameScreenGui(){
        this.gameGrid = null;
    }


    public City getGotham(){
        return this.gameGrid.getGotham();
    }

    public boolean getFinished(){
        return this.gameGrid.getFinished();
    }

        //Added to complete the class
    @Override
    public String toString(){
        String reply = "This acts like an outer shell holding the game";
        return reply;
    }
}
