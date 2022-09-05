package classes.Piece.Buildings;

import classes.Piece.Piece;

public class Factory extends Piece {
    String type;
    public Factory() {
        super.setLetter("F");
        super.setName("Factory");
        super.setScore(0);
        this.type = "Speacial";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }
}
