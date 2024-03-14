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

    public void setSpeed(int speed){
        this.speed = speed;
    }

//------------------Getters-------------------------------------------------

    public int getSpeed(){

        Random randomizer = new Random();

        if(this.speed == 0){
            this.speed = 1;
        }else if(this.speed == 1){


        }else if(this.speed == 2){


        }

        return this.speed;
    }


}
