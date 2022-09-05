package classes.Piece.Buildings;

import classes.Piece.Piece;

public class Tavern extends Piece{
    String type;
    public Tavern(){
        super.setLetter("T");
        super.setName("Tavern");
        super.setScore(0);
        this.type = "Gathering";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }
}
