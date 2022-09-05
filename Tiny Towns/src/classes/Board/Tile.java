package classes.Board;

import classes.Piece.Piece;

public class Tile {
    Piece piece;
    int x;
    int y;
    public Tile(int x , int y){
        this.piece = new Piece();
        this.x = x;
        this.y = y;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece() {
        return piece;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
