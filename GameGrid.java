// Author EL

import java.util.List;

import javax.swing.JLabel;

public class GameGrid {

    private JLabel[][] labels;

    private int size;
    private City gotham;

    /** 
     * Constructor for GameGrid
     * @param size The size of the grid
     * @param numRacers The number of racers in the game
     */
    public GameGrid(int size , int numRacers) {
        this.size = size;
        this.labels = new JLabel[size][size];
        this.gotham = new City(size, numRacers);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel label = new JLabel();

                // Code for Label properties
                // ...

                this.labels[i][j] = label;
            }
        }

        updateLabels();
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
                        label.setText(piece.toString());
                    }
                }
            }
        }
    }

    public JLabel[][] getLabels() {
        return this.labels;
    }
}
