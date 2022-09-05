package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Board.Board;

public class Player {
    int playerNum;
    public Board board;
    boolean playable;
    List<String> factoryResources = new ArrayList<>();

    public Player(int playerNum) {
        this.playerNum = playerNum;
        this.board = new Board();
        this.playable = true;
    }
    public int getPlayerNum() {
        return playerNum;
    }
    public boolean playable() {
        return this.playable;
    }

    public void checkBoardPlayable(){
        if (board.boardfull()){
            if(board.canBuildChapel().isEmpty() && board.canBuildCottage().isEmpty() && board.canBuildFactory().isEmpty() && board.canBuildFarm().isEmpty() && board.canBuildTavern().isEmpty() && board.canBuildTheatre().isEmpty() && board.canBuildWell().isEmpty()){
                this.playable = false;
            }
        }

    }
    public void choseResource(Scanner in) {
        String selection = in.nextLine();
        switch (selection) {
    }
}


}

