
//Author WD
//GW fixed some bugs I found
//EL fixed grammatical errors, added toString() method, and documentation

public class Steering {

    private char directionXY;

    private int direction;

    private String display;

    private String carNum;

    public Steering(){
        this.display = "";
        this.direction = 0;
        this.directionXY = ' ';
        this.carNum = "0";
    }

    /**
     * This method returns the display of the car with the direction it is moving in
     * @return String
     */
    public String getDisplay(){

        String reply = this.display;
        if(this.directionXY == 'Y'){
            if(this.direction > 0){
                reply = reply + ">";
            }else {
                reply = "<" + reply;
            }
        }else if(this.directionXY == 'X'){
            if(this.direction > 0){
                reply = reply + "\nv";
            }else {
                reply = "^\n" + reply;
            }
        }
        return reply;
    }

    /**
     * This method resets the direction of the car to 0 and the directionXY to ' '
     * @return void
     */
    public void stop(){
        this.directionXY = ' ';
        this.direction = 0;
    }

//-------------------------------Setters-----------------------------------------------------

        //Sets the initial look of a Car obj.
    public void setDisplay(String display){
        this.carNum = display;
        this.display = "|" + carNum + "|";
    }

        //Sets which Axis this car is moving on.
        //if not in motion this.directionXY == ' '
    public void setDirectionXY(char xy){
        this.directionXY = xy;
    }

        //Sets which direction on the Axis the car is moving in
        //negative = up or left, positive = down or right
    public void setDirection(int num){
        this.direction = num;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    //-------------------------------Getters-------------------------------------------------

        //gets which axis it's moving on
    public char getXY(){
        return this.directionXY;
    }

    public char getDirectionXY() {
        return this.directionXY;
    }


    public String getCarNum() {
        return this.carNum;
    }

    public int getDirection(){
        return this.direction;
    }

        //Added to complete this as a java class
    @Override
    public String toString(){
        String reply = "Handles all of the Directional methods/attributes of a car class"+
                        "also handling the display of the car as well";
        return reply;
    }
}
