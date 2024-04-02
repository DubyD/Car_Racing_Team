



//Author WD
//EL fixed some grammatical errors

public class Destination extends GamePiece{

    private String token;


    public Destination(int x, int y, String display){

        //Setting up base class coordinates,
        //toString, isSolid boolean
        super(x,y);
        this.setDisplay(display);

        this.token = display;

    }

    public Destination(){
        super(-1,-1);
        this.token = " ";
    }


    public String getToken(){
        return this.token;
    }

}