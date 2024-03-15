//Author WD


import java.util.Random;

//To justify this class we are just separating actions into their real world counterparts
public class Tires {
    private boolean friction;


    public Tires(){

        this.friction = true;

    }

//----------------Set Friction---------------------------------

        //Sends the Motor if it needs to slowdown
    public boolean hasFriction(){

        Random randomizer = new Random();
        int randomInt = randomizer.nextInt(10) + 1;

            //Has a 20% chance to slowdown Cars moving speed 2
        if(randomInt > 8){
            return this.friction;
        }
        return false;
    }


}
