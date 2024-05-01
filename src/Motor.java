
//Author WD
//EL added documentation and fixed grammatical errors

import java.util.Random;

public class Motor{
    
    private int speed;

    public Motor(){
        this.speed = 0;
    }

    //-------------Setters-----------------------------------------------------

        //When moving the car may speed up
    public void setSpeed(){

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
            if(this.hasFriction()){
                this.speed = 1;
            }
        }
    }

    //-------------Getters-----------------------------------------------------

        //Returns the speed of the Car
    public int getSpeed(){
        return this.speed;
    }

    //------------------exotic-------------------------------------------------

    /**
     * This method is used to determine if the Car has friction
     * @return boolean
     */
    public boolean hasFriction(){

        //When moving fast the car may slowdown
        Random randomizer = new Random();
        int randomInt = randomizer.nextInt(10) + 1;

        //Has a 20% chance to slowdown Cars moving speed 2
        if(randomInt > 8){
            return true;
        }
        return false;
    }

    /**
     * This method is used to reset the speed of the Car to 0
     * @return void
     */
    public void stop(){
        //Stops at each Destination
        this.speed = 0;
    }

        //Added this to complete this as a java class
    @Override
    public String toString(){
        String reply = "This handles all of the speed functionality of the Car obj.";
        return reply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Motor motor = (Motor) o;

        return speed == motor.speed;
    }

    @Override
    public int hashCode() {
        return speed;
    }
}
