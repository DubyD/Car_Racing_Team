//Author WD


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.util.TimerTask;


public class TurnTaker extends TimerTask implements ActionListener{

        //Connecting the back end
    private City gotham;

        //Connecting the front end
    private GameGrid guiUpdater;

        //Tells the game to stop moving
    private boolean finished;

        //Counts how many turns have passed
    private int turns;

        //Creates intervals for the game to move
    private Timer clock;

        //Constructor used to create the movement in the race
    public TurnTaker(City gotham, GameGrid guiUpdater){

        this.gotham = gotham;
        this.finished = false;
        this.turns = 0;
        this.clock = new Timer(400, this);
        this.guiUpdater = guiUpdater;
        this.clock.start();
    }

        //Second Constructor to complete this class
    public TurnTaker(){
        this.gotham = null;
        this.finished = false;
        this.turns = -1;
        this.clock = null;
        this.guiUpdater = null;
    }


    //----------------------------Deals with number of Turns---------------------------

    private setTurns(){
        this.turns = this.turns + 1;
    }

    public getTurns(){
        return this.turns;
    }

        //Edits the ActionListener instructions
    @Override
    public void actionPerformed(ActionEvent e){this.run();}

        //Initiates the movement in the program
    @Override
    public void run(){
        
    }


}
