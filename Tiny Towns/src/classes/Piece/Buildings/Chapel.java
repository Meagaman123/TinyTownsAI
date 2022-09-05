package classes.Piece.Buildings;

import classes.Piece.Piece;

public class Chapel extends Piece{
    String type;
    public Chapel () {
        super.setLetter("CH");
        super.setName("Chapel");
        super.setScore(0);
        this.type = "Religeous";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }
}
