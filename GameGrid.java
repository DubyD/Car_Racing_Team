// Author EL

import java.awt.*;
import java.util.List;

import javax.swing.*;

public class GameGrid extends JPanel {

    private JLabel[][] labels;

    private JPanel gridPanel;

    private int size;
    private City gotham;
    private TurnTaker movement;

    /** 
     * Constructor for GameGrid
     * @param size The size of the grid
     * @param numRacers The number of racers in the game
     */
    public GameGrid(int size , int numRacers) {

        this.size = size;
        this.labels = new JLabel[size][size];
        this.gotham = new City(size, numRacers);
        this.movement = new TurnTaker(this.gotham, this);
        this.gridPanel = new JPanel(new GridLayout(size, size));


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel label = new JLabel();

                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(60, 20));

                this.labels[i][j] = label;
                gridPanel.add(label);
            }
        }

        this.add(gridPanel, BorderLayout.CENTER);
        this.updateLabels();
    }

        //Zero parameter constructor to complete class
    public GameGrid(){
        this.size = -1;
        this.gotham = null;
        this.labels = null;
        this.movement = null;
        this.gridPanel = null;
    }


    /** 
     * Updates the labels on the grid to reflect the current state of the game. 
     */
    public void updateLabels() {
        List<GamePiece> board = this.gotham.getBoard();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                JLabel label = this.labels[i][j];
                label.setText("");
                for (GamePiece piece : board) {
                    if (piece.getX() == i && piece.getY() == j) {
                        label.setText(label.getText() + ',' + piece.toString());
                    }
                }
            }
        }
    }

    public boolean getFinished(){
        return this.gotham.getFinished();
    }

    public String[] getResults(){
        return this.gotham.getResults();
    }

        //toString method added to complete the class
    @Override
    public String toString(){
        String reply = "This class handles turning the backend work into a user friendly experience.\n"+
                       "Dealing with the visuals of where the game components are in the game space.";
        return reply;
    }
}