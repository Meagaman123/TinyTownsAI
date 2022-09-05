package classes.Piece;

import classes.Board.Board;
import classes.Board.Tile;

public class Piece {
    String letter = "";
    String name = "empty";
    int score = -1;
    public Piece(){
        setLetter(letter);
        setScore(score);
        setName(name);

    }
    public String getLetter() {
        return letter;
    }
    public void setLetter(String letter) {
        this.letter = letter;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return null;
    }
}
