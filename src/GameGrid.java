
// Author EL
// Contributions by VS in updateLabel and setImageDisplay

import java.awt.*;
import java.net.URL;
import java.util.List;

import javax.swing.*;

public class GameGrid extends JPanel {

    private JLabel[][] labels;

    private JPanel gridPanel;

    private int size;
    private City gotham;
    private Icon carIcon;

    /** 
     * Constructor for GameGrid
     * @param size The size of the grid
     * @param numRacers The number of racers in the game
     */
    public GameGrid(int size , int numRacers) {

        this.size = size;
        this.labels = new JLabel[size][size];
        this.gotham = new City(size, numRacers);
        this.gridPanel = new JPanel(new GridLayout(size, size));
        this.carIcon = null;

        new TurnTaker(this.gotham, this);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JLabel label = new JLabel();

                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(100, 60));

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
        this.gridPanel = null;
    }


    /** 
     * Updates the labels on the grid to reflect the current state of the game.
     * @return void
     */
    public void updateLabels() {
        List<GamePiece> board = this.gotham.getBoard();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                JLabel label = this.labels[i][j];
                label.setText("");
                label.setIcon(null);
                for (GamePiece piece : board) {
                    if (piece.getX() == i && piece.getY() == j) {
                        if(piece.getClass().equals(Car.class)){
                            label.setIcon (setImageDisplay(((Car) piece).getCarNum()));
                        }else {
                            label.setText(label.getText() + ' ' + piece.toString());
                        }
                    }
                }
            }
        }
    }

    /**
     * Sets the image of the car based on the car number
     * @param carNum
     * @return Icon
     */
    public Icon setImageDisplay(String carNum){
        int currCarNum = Integer.parseInt(carNum);
        if(currCarNum == 1) {
            URL car1URL = getClass().getResource("images/car1.png"); //File location
            carIcon = new ImageIcon(car1URL); //New icon using this url
        } else if (currCarNum == 2) {
            URL car2URL = getClass().getResource("images/car2.png"); //File location
            carIcon = new ImageIcon(car2URL); //New icon using this url
        } else if (currCarNum == 3) {
            URL car3URL = getClass().getResource("images/car3.png"); //File location
            carIcon = new ImageIcon(car3URL); //New icon using this url
        }else{
            URL car4URL = getClass().getResource("images/car4.png"); //File location
            carIcon = new ImageIcon(car4URL); //New icon using this url
        }
        return carIcon;
    }

    //-------------------------------Setters-----------------------------------------------------

    public void setGotham(City gotham){
        this.gotham = gotham;
    }

    //-------------------------------Getters-----------------------------------------------------
    
    public boolean getFinished(){
        return this.gotham.getFinished();
    }

    public City getGotham(){ //Added for GameScreenGUI functionality - AW
        return gotham;
    }

        //toString method added to complete the class
    @Override
    public String toString(){
        String reply = "This class handles turning the backend work into a user friendly experience.\n"+
                       "Dealing with the visuals of where the game components are in the game space.";
        return reply;
    }
}