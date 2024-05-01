
//Author WD
//EL fixed some grammatical errors

import java.util.Objects;

public class GamePiece {

        //Every game piece has coordinates
    protected int x;
    protected int y;

        //Every GamePiece has collidable
    private String display;

        //constructor for the Base Class
    public GamePiece(int x, int y){

        this.x = x;
        this.y = y;
        this.display = "#";

    }

        //Second non-parameter constructor to complete the object
    public GamePiece(){
        this.x = -1;
        this.y = -1;

    }

    //--------Getters----------------------------------------------------

        //Returns X coordinates
    public int getX(){
        return this.x;
    }
        //Returns Y coordinates
    public int getY(){
        return this.y;
    }

    //checks if this object is solid

    //--------Setters----------------------------------------------------

        //Setting the String
    public void setDisplay(String display){
        this.display = display;
    }

    //returning the animation
    @Override
    public String toString(){
        return this.display;
    }
    //Equals Method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePiece gamePiece = (GamePiece) o;

        if (x != gamePiece.x) return false;
        if (y != gamePiece.y) return false;
        return Objects.equals(display, gamePiece.display);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (display != null ? display.hashCode() : 0);
        return result;
    }
}
