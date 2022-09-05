package classes.Piece.Buildings;

import classes.Piece.Piece;

public class Farm extends Piece{
    String type;
    public Farm(){
        super.setLetter("FM");
        super.setName("Farm");
        super.setScore(0);
        this.type = "Food";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }
}
