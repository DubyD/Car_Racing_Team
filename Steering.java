

//Author WD
//GW fixed some bugs I found
public class Steering {

    private char directionXY;
    private int direction;
    private boolean turning;
    private String display;

    private String carNum;

    public Steering(){
        this.display = "";
        this.turning = false;
        this.direction = 0;
        this.directionXY = ' ';
        this.carNum = "0";
    }
//-------------------------------Setters-----------------------------------------------------

        //Sets the initial look of a Car obj.
    public void setDisplay(String display){
        this.carNum = display;
        this.display = "|" + carNum + "|";
    }

        //Sets whether this instance had to turn around an object
    public void setTurning() {
        this.turning = true;
    }

    public void stopTurning(){
        this.turning = false;
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
    //-------------------------------Getters-------------------------------------------------

        //Used to get around objects
    public boolean getTurning(){
        return this.turning;
    }

        //gets which axis it's moving on
    public char getXY(){
        return this.directionXY;
    }

    public void stop(){
        this.directionXY = ' ';
        this.direction = 0;
    }

    public int getDirection(){
        return this.direction;
    }


        //Sets up the String to simulate Movement or lack of movement
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
}
