//Author WD

import java.util.Random;

public class Motor {
    private int speed;
    private Tires wheels;

    public Motor(){
        this.speed = 0;
        this.wheels = new wheels();
    }

//-------------Setters-----------------------------------------------------

        //Sets up the previous turns speed
    public void setSpeed(int speed){
        this.speed = speed;
    }

        //Slows down if turning
    public void slowDown(){
        this.speed = 1;
    }

        //Stops at each Destination
    public void stop(){
        this.speed = 0;
    }


//------------------Getters-------------------------------------------------

        //When moving the car may speed up
    public int getSpeed(){

        Random randomizer = new Random();

        if(this.speed == 0){
            this.speed = 1;
        }else if(this.speed == 1){

                //Creates a 20% chance to speed up
            int randomNum = randomizer.nextInt(10) + 1;
            if(randomNum > 8){
                this.speed = 2;
            }

        }else if(this.speed == 2){

                //If the tires have friction the Motor will slowdown
            if(this.wheels.hasFriction()){
                this.speed = 1;
            }
        }

        return this.speed;
    }


}
