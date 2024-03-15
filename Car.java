//Author WD

import java.util.ArrayList;
import java.util.List;

public class Car extends GamePiece implements Cloneable{

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
        this.wheel.setDisplay(carNum);
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

        //Determines how far on the X-Axis the Car is away from it's next goal
    private int getXDiff(){
        int reply = this.getX() - this.map.get(0).getX();
        return reply;
    }
        //Determines how far on the Y-Axis the Car is away from it's next goal
    private int getYDiff(){
        int reply = this.getY() - this.map.get(0).getY();
        return reply;
    }

        //When a car moves, this gives it new coordinates
    private void setNewXY(int x, int y){
        this.x = x;
        this.y = y;
    }


//--------------------------------------Public Methods-------------------------------------------
//--------------------------------------Setters, then Getters---------------------------

    public void setDestination(List<Destination> map){
        this.map.addAll(map);
    }

    public void setPrev(Car past){
        this.prevSpot = past;
    }

    public String setDisplay(){
        this.display(this.wheel.getDisplay());
    }

    public boolean getFinished(){
        return this.finished;
    }

    public String getResults(){
        String reply = "Racer: "+ this.getCarNum() + " finished in " + this.getTurns() + " turns";
        return reply;
    }

    public String getCarNum(){
        return this.carNum;
    }



//------------------------------Exotic getters-----------------------------------------------------

        //Used in TurnTaker in case there is a wall
    public Car getPrev(){
        this.prevSpot;
    }

        //Used in TurnTaker to speed up, slow down, or stop
    public Motor getEngine() {
        return this.engine;
    }

        //Used in TurnTaker to turn and get GuiDisplay
    public Steering getWheel(){
        return this.wheel;
    }

        //Clones an Object to maintain direction and speed
        //Meaning we dont need to adjust the steering and motor
        //Everytime
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


//-------------------------Exotic Public Methods---------------------------------------------------



        //Checks to see if the Car has made it to the Destination
    public boolean gotThere(){
        if(this.getX() == this.map.get(0).getX()){
            if(this.map.get(0).getY() == this.getY()){


                this.getNextDestination();
                return true;
            }
        }
        return false;
    }

        //used if vehicle stops at a Destination and needs to initate movement
        //Also used if the Vehicle needs to turn
    public Car startMove(){

            //Escapes the loop
        if(this.finished){
            return this;
        }

        this.engine.setSpeed();

            //Used to set Coordinates of a new Car
        int nextX = this.getX();
        int nextY = this.getY();

            //Needed a separate variable in case of car avoiding a wall.
        int xDiff = this.getXDiff();
        int yDiff = this.getYDiff();

            //Sets up return obj. with all of the initialized
        Car nextCar = (Car) this.clone();

            //If turning we want to ensure we are
            //moving to a non-blocked Axis
        if(this.wheel.getTurning()){

            int tempData = xDiff;
            xDiff = yDiff;
            yDiff = tempData;

                //Sets speed to 1, turning takes time
            nextCar.getEngine().slowDown();

        }

            //shortens the largest gap
            //If xDiff  > yDiff EW
            //If yDiff => xDiff NS
            //If turning == true then xDiff <-> yDiff (are switched)
        if(Math.abs(xDiff) > Math.abs(yDiff)){

                //Sets the Current axis Direction
            nextCar.getWheel().setDirectionXY('X');

                //Sets the direction from motionless
                //+ or - 1 from the X axis
            if(this.getXDiff() > 0){
                nextX = nextX - 1;
                    //Helps set the display of the CarGui
                nextCar.getWheel().setDirection(-1);

            }else if(this.getXDiff() < 0){
                nextX = nextX + 1;
                    //Helps set the display of the CarGui
                nextCar.getWheel().setDirection(1);

                //If Turning but on the Correct X Axis
                //Automatically turns East
            }else{
                nextX = nextX + 1;
                    //Helps set the display of the CarGui
                nextCar.getWheel().setDirection(1);
            }
        }else{

                //Sets the Current axis Direction
            nextCar.getWheel().setDirectionXY('Y');
                //Sets the direction from motionless
                //+ or - 1 from the Y axis
            if(this.getYDiff() > 0){
                nextY = nextY - 1;
                    //Helps set the display of the CarGui
                nextCar.getWheel().setDirection(-1);

            }else if(this.getYDiff() < 0){
                nextY = nextY + 1;
                    //Helps set the display of the CarGui
                nextCar.getWheel().setDirection(1);

                //If Turning but on the Correct X Axis
                //Automatically turns South
            }else{
                nextY = nextY + 1;
                    //Helps set the display of the CarGui
                nextCar.getWheel().setDirection(1);

            }
        }


            //Adds the new Coordinates
        nextCar.setNewXY(nextX, nextY);
            //Sets a history of the previous turn
        nextCar.setPrev(this);

        return nextCar;
    }


        //used to move a moving Car
        //if moving the Car cannot be finished
    public Car keepMove(){

        int nextX = this.getX();
        int nextY = this.getY();

            //Needed a separate variable in case of car avoiding a wall.
        int xDiff = this.getXDiff();
        int yDiff = this.getYDiff();

            //Sets up return obj. with all of the initialized
        Car nextCar = (Car) this.clone();



            //Adds new coordinates to next iteration of Car
        if(this.wheel.getXY() == 'X'){
            if(this.atDestinationX()){
                return this.startMove();

            }
            nextX = nextX + 1;

        } else if(this.wheel.getXY() == 'X'){
            if(this.atDestinationX()){
                return this.startMove();
            }
            nextX = nextX - 1;

        }else if(this.wheel.getXY() == 'Y'){
            if(this.atDestinationY()){
                return this.startMove();

            }
            nextY = nextY - 1;

        }else if(this.wheel.getXY() == 'Y'){
            if(this.atDestinationY()){

                return this.startMove();

            }
            nextY = nextY + 1;

        }

        nextCar.setPrev(this);

        return nextCar;


    }



}
