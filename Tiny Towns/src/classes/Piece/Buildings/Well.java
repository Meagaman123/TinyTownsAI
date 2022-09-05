package classes.Piece.Buildings;




import classes.Piece.Piece;

public class Well extends Piece{
    String type;
    public Well() {
        super.setLetter("W");
        super.setName("Well");
        super.setScore(0);
        this.type = "Small";
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return type;
    }
}