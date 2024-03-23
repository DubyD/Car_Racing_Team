

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


        //Used to export which turn it to update
        //a label of which turn it is
    public int getTurns(){
        return this.turns;
    }

        //Edits the ActionListener instructions
    @Override
    public void actionPerformed(ActionEvent e){this.run();}

        //Initiates the movement in the program
    @Override
    public void run(){

            //Everytime the Timer goes off this increments by 1
        this.turns = this.turns + 1;

        this.startGame();

            //Used to check if the timer needs to stop
        boolean gameOver = true;

            //Checks to see if each Car is finished
        for(Car next : this.gotham.getRacers()){

                //Compares every Cars status. if they are all
                //finished gameOver == true
            gameOver = gameOver && next.getFinished();
            if(next.getFinished()){

                    //If next(Car) just finished, this sets how
                    //long it took to finish
                if(next.getTimed() == 0){
                    next.setTime(this.turns);
                }
            }
        }

        this.finished = gameOver;
            //If gameOver == true, the clock stops
        if(this.finished){
            this.clock.stop();
        }

            //Updates the gui
        this.guiUpdater.updateLabels();

    }

        //Exports whether the game is over
        //or not to Activate the end button
    public boolean getFinished(){return this.finished;}

    private void startGame(){

            //New List to populate Gotham
        List<Car> currentRacers = new ArrayList<Car>();

            //iterates through the list to move cars
       for(Car next : this.gotham.getRacers()){

                //Skips finished racers
                //and adds them to the new list
           if(next.getFinished() == true){

               this.gotham.removeRacer(next);
               currentRacers.add(next);
               continue;
           }

                //Checks the speed of each vehicle
                //if it is 0 it startMove, if it's
                //>0 it will keepMove
           if(next.getEngine().getSpeed() == 0){

                    //initiates new Car to move
               Car check = next.startMove();

                    //checks collision
               boolean collided;
               do{
                   collided = false;


               } while(collided);


                    //if somehow finished imidiately it stops
                    //the car from moving
               if(check.gotThere() == true){
                   check.getEngine().stop();
               }
               currentRacers.add(check);



           }
       }
    }

        //used to check collisions
    private boolean collision(Car check){
        for(GamePiece next : this.gotham.getWalls()){
            if(check.getX() == next.getX()){
                if(check.getY() == next.getY()){
                    return true;
                }
            }

        }
        return false;
    }

}
