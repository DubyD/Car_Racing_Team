//Author WD

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

    private int getXDiff(){
        int reply = this.getX() - this.map.get(0).getX();
        return reply;
    }

    private int getYDiff(){
        int reply = this.getY() - this.map.get(0).getY();
        return reply;
    }



//--------------------------------------Public Methods-------------------------------------------
//--------------------------------------Setters, then Getters---------------------------

    public void setDestination(List<Destination> map){
        this.map.addAll(map);
    }

    public void setPrev(Car past){
        this.prevSpot = past;
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

//-------------------------Exotic Public Methods---------------------------------------------

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
    public Car startMove(){

            //Escapes the loop
        if(this.finished){
            return this;
        }

        int nextX = this.getX();
        int nextY = this.getY();
        char whichAxis;


            //shortens the largest gap
            //If xDiff  > yDiff EW
            //If yDiff => xDiff NS
        if(Math.abs(this.getXDiff()) > Math.abs(this.getYDiff())){
            //Sets the direction from motionless
            //+ or - 1 from the X axis
            if(this.getXDiff() > 0){
                nextX = nextX - 1;

            }else{
                nextX = nextX + 1;

            }
        }else{
            //Sets the direction from motionless
            //+ or - 1 from the Y axis
            if(this.getYDiff() > 0){
                nextY = nextY - 1;

            }else{
                nextY = nextY + 1;

            }
        }


        Car nextCar = new Car(nextX, nextY, this.getCarNum());
        nextCar.setSpeed(this.speed);
        nextCar.setPrev(this);
        nextCar.setDestination(this.map);

        return nextCar;
    }


    //used to move the Car
    public Car keepMove(){


        int nextX = this.getX();
        int nextY = this.getY();

        /*
            //Adds new coordinates to next iteration of Car
        if(this.direction == 'X'){
            if(this.atDestinationX()){
                return this.startMove();

            }
            nextX = nextX + 1;

        } else if(this.direction == 'X'){
            if(this.atDestinationX()){
                return this.startMove();
            }
            nextX = nextX - 1;

        }else if(this.direction == 'Y'){
            if(this.atDestinationY()){
                return this.startMove();

            }
            nextY = nextY - 1;

        }else if(this.direction == 'Y'){
            if(this.atDestinationY()){

                return this.startMove();

            }
            nextY = nextY + 1;

        }

        Car next = new Car(nextX, nextY, this.getCarNum());
        next.setPrev(this);
        next.setSpeed(this.speed);
        next.setDestination(this.map);

        return next;
        */

    }



}
