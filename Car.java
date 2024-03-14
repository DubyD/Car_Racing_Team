import java.util.ArrayList;
import java.util.List;

public class Car extends GamePiece {

    //Creates a linked list of turn history
    private Car prevSpot;
    //Handles all speed functions and variables
    private Motor engine;
    //Handles direction and display of the Car obj.
    private Steering wheel;
    //Handles an shuffled list of where this racer has
    //to go to win the race
    private List<Destination> map;
    //Stamps each racers passport to make sure they
    //hit all of their Destinations
    private List<String> passport;
    //Is this racer finished racing
    private boolean finished;
    //What does this racer look like
    private String display;
    //What number car is this
    private String carNum;


    //Constructor of class
    public Car(int x, int y, String carNum) {

        //Base class
        super(x, y);

        //Sub class capabilities
        this.prevSpot = null;
        this.map = new ArrayList<Destination>();
        this.passport = new ArrayList<String>();

        //For ending the game
        this.finished = false;

        //Saves which racer this is
        this.carNum = carNum;

        //Complex Car Parts
        this.engine = new Motor();
        this.wheel = new Steering();
        this.wheel.saveDisplay("|" + carNum + "|");
    }

    //Parameter free constructor to complete the class
    public Car() {


        super(-1, -1);

        this.prevSpot = null;
        this.map = null;
        this.passport = null;
        this.finished = false;
        this.display = " ";
        this.engine = null;
        this.wheel = null;
        this.carNum = -1;
    }



//--------------------------------------------Private Methods--------------------------------------
        //Used to stamp passport with destinations
    private void getNextDestination(){
        this.passport.add(this.map.get(0).getToken());
        this.map.remove(0);

            //Once the passport is completely stamped the next turn will end this cars race
        if(this.map.size() == 0){
            this.finished = true;
        }
    }

    private Destination getCurrentDestination(){
        return this.map.get(0);
    }

        //Used to determine whether to turn or not
    private boolean atDestinationY(){
        if((this.getY() - this.getCurrentDestination().getY()) == 0){
            return true;
        }
        return false;
    }
        //Used to determine whether to turn or not
    private boolean atDestinationX(){
        if((this.getX() - this.getCurrentDestination().getX()) == 0){
            return true;
        }
        return false;
    }

}
