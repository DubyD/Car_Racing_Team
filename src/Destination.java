



//Author WD
//EL fixed some grammatical errors

import java.util.Objects;

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

    //-------------------------------Setters-----------------------------------------------------

    public void setToken(String token){
        this.token = token;
    }

    //-------------------------------Getters-----------------------------------------------------

    public String getToken(){
        return this.token;
    }

    @Override
    public String toString() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Destination that = (Destination) o;

        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}