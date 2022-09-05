package classes.Piece.Buildings;


import classes.Piece.Piece;

public class Cottage extends Piece{
    String type;
    public Cottage(){
        super.setLetter("CO");
        super.setName("Cottage");
        super.setScore(0);
        this.type = "House";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }
}
