package classes.Piece.Buildings;

import classes.Piece.Piece;

public class Theatre extends Piece {
    String type;
    public Theatre() {
        super.setLetter("TH");
        super.setName("Theatre");
        super.setScore(0);
        this.type = "Shops";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }

}
