


//Author WD
//3-23 AW fixed some bugs I found
//EL fixed grammatical errors and added documentation

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //What number car is this
    private String carNum;
        //How long did it take to finish
    private int time;

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

            //Number of turns it took to finish
        this.time = 0;

            //Complex Car Parts
        this.engine = new Motor();
        this.wheel = new Steering();
        this.wheel.setDisplay(carNum);
        this.setDisplay();
    }

    //Parameter free constructor to complete the class
    public Car() {
        super(-1, -1);

        this.prevSpot = null;
        this.map = null;
        this.passport = null;
        this.finished = false;
        this.engine = null;
        this.wheel = null;
        this.carNum = "-1";
        this.time = -1;
    }

//--------------------------------------------Private Methods--------------------------------------

    //-------------------------------Destination Methods-------------------------------------------

    /**
     * Used to get the current destination
     * @return Destination
     */
    private Destination getCurrentDestination(){
        return this.map.get(0);
    }

    /**
     * Used to determine whether to turn or not
     * @return boolean
     */
    private boolean atDestinationY(){
        if((this.getY() - this.getCurrentDestination().getY()) == 0){
            return true;
        }
        return false;
    }

    /**
     * Used to determine whether to turn or not
     * @return boolean
     */
    private boolean atDestinationX(){
        if((this.getX() - this.getCurrentDestination().getX()) == 0){
            return true;
        }
        return false;
    }

        //AW - changed method to public so it could be accessed from TurnTaker() in startGame()
    /**
     * This method is used to check if the car has reached it's destination
     * @return boolean
     */
    public boolean gotThere(){
        if(this.atDestinationX() && this.atDestinationY()){
            this.getNextDestination();
            return true;
        }
        return false;
    }

    /**
     * This method is used to stamp the passport with the destination
     * @return void
     */
    private void getNextDestination(){
        this.passport.add(this.map.get(0).getToken());
        this.map.remove(0);

        //Once the passport is completely stamped the next turn will end this cars race
        if(this.map.size() == 0){
            this.finished = true;
        }
    }
//-----------------------------------Movement Methods--------------------------------------------

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

    public void setDisplay(){
        this.setDisplay(this.wheel.getDisplay());
    }

        //When the Car finishes sets the time it took (Number of Turns)
    public void setTime(int finishTime){this.time = finishTime;}

        //Retrieves the number that of turns it took to finish
    public int getTimed(){return this.time;}

        //returns if this car finished or not
    public boolean getFinished(){
        return this.finished;
    }

    public String getResults(){
        String reply = "Racer: "+ this.getCarNum() + " finished in " + this.getTimed() + " turns";
        return reply;
    }

    public String getCarNum(){
        return this.carNum;
    }



//------------------------------Exotic getters-----------------------------------------------------

        //Used in TurnTaker in case there is a wall
    public Car getPrev(){
        return this.prevSpot; 
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
        //Meaning we don't need to adjust the steering and motor
        //Every time
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


//-------------------------Exotic Public Methods---------------------------------------------------

    /**
     * This method is used to get the car moving
     * @return void
     */
    public void revEngine() {
        this.engine.setSpeed();
    }

    /**
     * This method is used to start the movement of the car
     * Used if vehicle stops at a Destination and needs to initiate movement
     * Also used if the Vehicle needs to turn
     * @return Car
     */
    public Car startMove(){

            //Escapes the loop
        if(this.finished){
            return this;
        }

            //Used to set Coordinates of a new Car
        int nextX = this.getX();
        int nextY = this.getY();

            //Needed a separate variable in case of car avoiding a wall.
        int xDiff = this.getXDiff();
        int yDiff = this.getYDiff();

            //Sets up return obj. with all of the initialized
        Car nextCar = null;
        try {
            nextCar = (Car) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        nextCar.revEngine();

            //Takes a Turn to go toward it's next goal
            //Resets speed and Gui
        if(this.gotThere()){
            nextCar.getEngine().stop();
            nextCar.getWheel().stop();
            nextCar.setDisplay();
            return nextCar;
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
            //Changes the Gui
        nextCar.setDisplay();

        return nextCar;
    }
    
    /**
     * This method is used to keep the car moving
     * If the car is moving it cannot be finished
     * @return Car
     */
    public Car keepMove(){

        int nextX = this.getX();
        int nextY = this.getY();

            //Sets up return obj. with all of the initialized
        Car nextCar = null;
        try {
            nextCar = (Car) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        nextCar.revEngine();

            //Adds new coordinates to next iteration of Car
        if(this.wheel.getXY() == 'X'){

            if(this.atDestinationX()){
                return this.startMove();

            }
            nextX = nextX + this.wheel.getDirection();

        }else if(this.wheel.getXY() == 'Y'){

            if(this.atDestinationY()){
                return this.startMove();

            }
            nextY = nextY + this.wheel.getDirection();

        }

            //Sets up new Coordinates
        nextCar.setNewXY(nextX, nextY);
            //Sets a history of the previous turn
        nextCar.setPrev(this);
            //Changes the Gui
        nextCar.setDisplay();

        return nextCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        if (finished != car.finished) return false;
        if (!Objects.equals(engine, car.engine)) return false;
        if (!Objects.equals(wheel, car.wheel)) return false;
        return Objects.equals(carNum, car.carNum);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (wheel != null ? wheel.hashCode() : 0);
        result = 31 * result + (finished ? 1 : 0);
        result = 31 * result + (carNum != null ? carNum.hashCode() : 0);
        return result;
    }
}
