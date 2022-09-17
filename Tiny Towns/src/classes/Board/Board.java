package classes.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classes.Piece.Piece;
import classes.Piece.Buildings.Chapel;
import classes.Piece.Buildings.Cottage;
import classes.Piece.Buildings.Factory;
import classes.Piece.Buildings.Farm;
import classes.Piece.Buildings.Tavern;
import classes.Piece.Buildings.Theatre;
import classes.Piece.Buildings.Well;
import classes.Piece.Resources.*;

public class Board {
    int score;
    Tile[][] board;

    public Board() {
        this.board = createBoard();
    }

    public Board(Board boards) {
        Tile[][] matrixToCopy = boards.getBoard();
        board = createBoard();
        for (int i = 0; i < matrixToCopy.length; i++) {
            for (int j = 0; j < matrixToCopy.length; j++) {
                board[i][j].piece = new Piece(matrixToCopy[i][j].piece);
            }
        }
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setTilebyCoord(int x, int y, Piece piece) {
        board[x][y].setPiece(piece);
    }

    public Tile getTilebyCoord(int x, int y) {
        return board[x][y];
    }

    // use this function to create an empty board for a new player.
    public Tile[][] createBoard() {
        Tile[][] board = new Tile[4][4];
        // nested for loop to create the board
        for (int h = 0; h < 4; h++) {
            for (int v = 0; v < 4; v++) {
                board[h][v] = new Tile(h, v);
            }
        }
        return board;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  x____________\n");
        for (int y = 0; y < board.length; y++) {
            sb.append(String.format("%2s|%2s|%2s|%2s|%2s|%n", (y + 1), board[0][y].piece.getLetter(),
                    board[1][y].piece.getLetter(), board[2][y].piece.getLetter(), board[3][y].piece.getLetter()));
        }
        sb.append("  -------------Y");

        return sb.toString();
    }

    /*
     * This next section is the board checking for any wells that can be built using
     * a cusotmised pathing algortihm
     */
    public List<List<int[]>> canBuildWell() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("wood")) {
                    List<List<int[]>> singleList = wellPathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> wellPathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = adjecentTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("stone")) {
                List<int[]> singleList = new ArrayList<>();
                singleList.add(start);
                singleList.add(new int[] { coord[0], coord[1] });
                listofCoord.add(singleList);
            }
        }
        return listofCoord;
    }

    /*
     * This next section is the board checking for any theatres that can be built
     * using a cusotmised pathing algortihm
     */
    public List<List<int[]>> canBuildTheatre() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("wood")) {
                    List<List<int[]>> singleList = theatrePathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> theatrePathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = rightUpTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("glass")) {
                if (coord[2] == 0 && inboard(coord[0] + 1)) {
                    if (board[coord[0] + 1][coord[1]].piece.getName().equals("wood")) {
                        if (inboard(coord[1] + 1)) {
                            if (board[coord[0]][coord[1] + 1].piece.getName().equals("stone")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0] + 1, coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] + 1 });
                                listofCoord.add(singleList);
                            }
                        }
                        if (inboard(coord[1] - 1)) {
                            if (board[coord[0]][coord[1] - 1].piece.getName().equals("stone")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0] + 1, coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] - 1 });
                                listofCoord.add(singleList);
                            }
                        }
                    }
                }
                if (coord[2] == 2 && inboard(coord[1] + 1)) {
                    if (board[coord[0]][coord[1] + 1].piece.getName().equals("wood")) {
                        if (inboard(coord[0] + 1)) {
                            if (board[coord[0] + 1][coord[1]].piece.getName().equals("stone")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] + 1 });
                                singleList.add(new int[] { coord[0] + 1, coord[1] });
                                listofCoord.add(singleList);
                            }
                        }
                        if (inboard(coord[0] - 1)) {
                            if (board[coord[0] - 1][coord[1]].piece.getName().equals("stone")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] + 1 });
                                singleList.add(new int[] { coord[0] - 1, coord[1] });
                                listofCoord.add(singleList);
                            }

                        }
                    }

                }
            }

        }
        return listofCoord;
    }

    /*
     * This next section is the board checking for any tavern that can be built
     * using a cusotmised pathing algortihm
     */

    public List<List<int[]>> canBuildTavern() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("brick")) {
                    List<List<int[]>> singleList = tavernPathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> tavernPathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = rightUpTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("brick")) {
                if (coord[2] == -1 && inboard(coord[0] - 1)) {
                    if (board[coord[0] - 1][coord[1]].piece.getName().equals("glass")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] - 1, coord[1] });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 0 && inboard(coord[0] + 1)) {
                    if (board[coord[0] + 1][coord[1]].piece.getName().equals("glass")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] + 1, coord[1] });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 1 && inboard(coord[1] - 1)) {
                    if (board[coord[0]][coord[1] - 1].piece.getName().equals("glass")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] - 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 2 && inboard(coord[1] + 1)) {
                    if (board[coord[0]][coord[1] + 1].piece.getName().equals("glass")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] + 1 });
                        listofCoord.add(singleList);
                    }
                }
            }
        }
        return listofCoord;
    }

    public List<List<int[]>> canBuildCottage() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("brick")) {
                    List<List<int[]>> singleList = cottagePathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> cottagePathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = adjecentTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("glass")) {
                if (coord[2] == -1 && inboard(coord[1] - 1)) {
                    if (board[coord[0]][coord[1] - 1].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] - 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == -1 && inboard(coord[1] + 1)) {
                    if (board[coord[0]][coord[1] + 1].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] + 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 0 && inboard(coord[1] + 1)) {
                    if (board[coord[0]][coord[1] + 1].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] + 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 0 && inboard(coord[1] - 1)) {
                    if (board[coord[0]][coord[1] - 1].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] - 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 1 && inboard(coord[0] - 1)) {
                    if (board[coord[0] - 1][coord[1]].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] - 1, coord[1] });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 1 && inboard(coord[0] + 1)) {
                    if (board[coord[0] + 1][coord[1]].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] + 1, coord[1] });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 2 && inboard(coord[0] + 1)) {
                    if (board[coord[0] + 1][coord[1]].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] + 1, coord[1] });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 2 && inboard(coord[0] - 1)) {
                    if (board[coord[0] - 1][coord[1]].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] - 1, coord[1] });
                        listofCoord.add(singleList);
                    }
                }
            }
        }
        return listofCoord;
    }

    public List<List<int[]>> canBuildFarm() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("wood")) {
                    List<List<int[]>> singleList = farmPathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> farmPathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = rightUpTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("wood")) {
                if (coord[2] == 0 && inboard(coord[1] - 1) && inboard(start[1] - 1)) {
                    if (board[start[0]][start[1] - 1].piece.getName().equals("wheat")
                            && board[coord[0]][coord[1] - 1].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] - 1 });
                        singleList.add(new int[] { start[0], start[1] - 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 0 && inboard(coord[1] + 1) && inboard(start[1] + 1)) {
                    if (board[start[0]][start[1] + 1].piece.getName().equals("wheat")
                            && board[coord[0]][coord[1] + 1].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0], coord[1] + 1 });
                        singleList.add(new int[] { start[0], start[1] + 1 });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 2 && inboard(coord[0] - 1) && inboard(start[0] - 1)) {
                    if (board[start[0] - 1][start[1]].piece.getName().equals("wheat")
                            && board[coord[0] - 1][coord[1]].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] - 1, coord[1] });
                        singleList.add(new int[] { start[0] - 1, start[1] });
                        listofCoord.add(singleList);
                    }
                }
                if (coord[2] == 2 && inboard(coord[0] + 1) && inboard(start[0] + 1)) {
                    if (board[start[0] + 1][start[1]].piece.getName().equals("wheat")
                            && board[coord[0] + 1][coord[1]].piece.getName().equals("wheat")) {
                        List<int[]> singleList = new ArrayList<>();
                        singleList.add(start);
                        singleList.add(new int[] { coord[0], coord[1] });
                        singleList.add(new int[] { coord[0] + 1, coord[1] });
                        singleList.add(new int[] { start[0] + 1, start[1] });
                        listofCoord.add(singleList);
                    }
                }
            }
        }
        return listofCoord;
    }

    public List<List<int[]>> canBuildChapel() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("stone")) {
                    List<List<int[]>> singleList = chapelPathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> chapelPathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = adjecentTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("glass")) {
                if (coord[2] == -1 && inboard(coord[0] - 1)) {
                    if (board[coord[0] - 1][coord[1]].piece.getName().equals("stone")) {
                        if (inboard(coord[1] - 1)) {
                            if (board[coord[0] - 1][coord[1] - 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0] - 1, coord[1] });
                                singleList.add(new int[] { coord[0] - 1, coord[1] - 1 });
                                listofCoord.add(singleList);
                            }
                        }
                        if (inboard(coord[1] + 1)) {
                            if (board[coord[0] - 1][coord[1] + 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0] - 1, coord[1] });
                                singleList.add(new int[] { coord[0] - 1, coord[1] + 1 });
                                listofCoord.add(singleList);
                            }
                        }
                    }
                }
                if (coord[2] == 0 && inboard(coord[0] + 1)) {
                    if (board[coord[0] + 1][coord[1]].piece.getName().equals("stone")) {
                        if (inboard(coord[1] - 1)) {
                            if (board[coord[0] + 1][coord[1] - 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0] + 1, coord[1] });
                                singleList.add(new int[] { coord[0] + 1, coord[1] - 1 });
                                listofCoord.add(singleList);
                            }
                        }
                        if (inboard(coord[1] + 1)) {
                            if (board[coord[0] + 1][coord[1] + 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0] + 1, coord[1] });
                                singleList.add(new int[] { coord[0] + 1, coord[1] + 1 });
                                listofCoord.add(singleList);
                            }
                        }
                    }

                }
                if (coord[2] == 1 && inboard(coord[1] - 1)) {
                    if (board[coord[0]][coord[1] - 1].piece.getName().equals("stone")) {
                        if (inboard(coord[0] - 1)) {
                            if (board[coord[0] - 1][coord[1] - 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] - 1 });
                                singleList.add(new int[] { coord[0] - 1, coord[1] - 1 });
                                listofCoord.add(singleList);
                            }
                        }
                        if (inboard(coord[0] + 1)) {
                            if (board[coord[0] + 1][coord[1] + 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] - 1 });
                                singleList.add(new int[] { coord[0] + 1, coord[1] - 1 });
                                listofCoord.add(singleList);
                            }
                        }
                    }
                }
                if (coord[2] == 2 && inboard(coord[1] + 1)) {
                    if (board[coord[0]][coord[1] + 1].piece.getName().equals("stone")) {
                        if (inboard(coord[0] - 1)) {
                            if (board[coord[0] - 1][coord[1] - 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] + 1 });
                                singleList.add(new int[] { coord[0] - 1, coord[1] + 1 });
                                listofCoord.add(singleList);
                            }
                        }
                        if (inboard(coord[0] + 1)) {
                            if (board[coord[0] + 1][coord[1] + 1].piece.getName().equals("glass")) {
                                List<int[]> singleList = new ArrayList<>();
                                singleList.add(start);
                                singleList.add(new int[] { coord[0], coord[1] });
                                singleList.add(new int[] { coord[0], coord[1] + 1 });
                                singleList.add(new int[] { coord[0] + 1, coord[1] + 1 });
                                listofCoord.add(singleList);
                            }
                        }
                    }
                }
            }
        }
        return listofCoord;
    }

    public List<List<int[]>> canBuildFactory() {
        List<List<int[]>> listoflists = new ArrayList<>();
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.piece.getName().equals("brick")) {
                    List<List<int[]>> singleList = factoryPathAlgorithm(new int[] { tile.x, tile.y });
                    if (!singleList.isEmpty()) {
                        listoflists.addAll(singleList);
                    }
                }
            }
        }
        return listoflists;
    }

    public List<List<int[]>> factoryPathAlgorithm(int[] start) {
        List<int[]> adjecentTiles = adjecentTiles(start);
        List<List<int[]>> listofCoord = new ArrayList<>();
        for (int[] coord : adjecentTiles) {
            if (board[coord[0]][coord[1]].piece.getName().equals("stone")) {
                if (coord[2] == -1 && inboard(coord[0] - 1)) {
                    if (board[coord[0] - 1][coord[1]].piece.getName().equals("stone")) {
                        if (inboard(coord[0] - 2)) {
                            if (board[coord[0] - 2][coord[1]].piece.getName().equals("brick")) {
                                if (inboard(coord[1] + 1)) {
                                    if (board[coord[0] - 2][coord[1] + 1].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0] - 1, coord[1] });
                                        singleList.add(new int[] { coord[0] - 2, coord[1] });
                                        singleList.add(new int[] { coord[0] - 2, coord[1] + 1 });
                                        listofCoord.add(singleList);
                                    }
                                }
                                if (inboard(coord[1] - 1)) {
                                    if (board[coord[0] - 2][coord[1] - 1].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0] - 1, coord[1] });
                                        singleList.add(new int[] { coord[0] - 2, coord[1] });
                                        singleList.add(new int[] { coord[0] - 2, coord[1] - 1 });
                                        listofCoord.add(singleList);
                                    }
                                }
                            }
                        }
                    }
                }
                if (coord[2] == 0 && inboard(coord[0] + 1)) {
                    if (board[coord[0] + 1][coord[1]].piece.getName().equals("stone")) {
                        if (inboard(coord[0] + 2)) {
                            if (board[coord[0] + 2][coord[1]].piece.getName().equals("brick")) {
                                if (inboard(coord[1] + 1)) {
                                    if (board[coord[0] + 2][coord[1] + 1].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0] + 1, coord[1] });
                                        singleList.add(new int[] { coord[0] + 2, coord[1] });
                                        singleList.add(new int[] { coord[0] + 2, coord[1] + 1 });
                                        listofCoord.add(singleList);
                                    }
                                }
                                if (inboard(coord[1] - 1)) {
                                    if (board[coord[0] + 2][coord[1] - 1].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0] + 1, coord[1] });
                                        singleList.add(new int[] { coord[0] + 2, coord[1] });
                                        singleList.add(new int[] { coord[0] + 2, coord[1] - 1 });
                                        listofCoord.add(singleList);
                                    }
                                }
                            }
                        }
                    }
                }
                if (coord[2] == 1 && inboard(coord[1] - 1)) {
                    if (board[coord[0]][coord[1] - 1].piece.getName().equals("stone")) {
                        if (inboard(coord[1] - 2)) {
                            if (board[coord[0]][coord[1] - 2].piece.getName().equals("brick")) {
                                if (inboard(coord[0] + 1)) {
                                    if (board[coord[0] + 1][coord[1] - 2].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0], coord[1] - 1 });
                                        singleList.add(new int[] { coord[0], coord[1] - 2 });
                                        singleList.add(new int[] { coord[0] + 1, coord[1] - 2 });
                                        listofCoord.add(singleList);
                                    }
                                }
                                if (inboard(coord[0] - 1)) {
                                    if (board[coord[0] - 1][coord[1] - 2].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0], coord[1] - 1 });
                                        singleList.add(new int[] { coord[0], coord[1] - 2 });
                                        singleList.add(new int[] { coord[0] - 1, coord[1] - 2 });
                                        listofCoord.add(singleList);
                                    }
                                }
                            }
                        }
                    }
                }
                if (coord[2] == 2 && inboard(coord[1] + 1)) {
                    if (board[coord[0]][coord[1] + 1].piece.getName().equals("stone")) {
                        if (inboard(coord[1] + 2)) {
                            if (board[coord[0]][coord[1] + 2].piece.getName().equals("brick")) {
                                if (inboard(coord[0] + 1)) {
                                    if (board[coord[0] + 1][coord[1] + 2].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0], coord[1] + 1 });
                                        singleList.add(new int[] { coord[0], coord[1] + 2 });
                                        singleList.add(new int[] { coord[0] + 1, coord[1] + 2 });
                                        listofCoord.add(singleList);
                                    }
                                }
                                if (inboard(coord[0] - 1)) {
                                    if (board[coord[0] - 1][coord[1] + 2].piece.getName().equals("wood")) {
                                        List<int[]> singleList = new ArrayList<>();
                                        singleList.add(start);
                                        singleList.add(new int[] { coord[0], coord[1] });
                                        singleList.add(new int[] { coord[0], coord[1] + 1 });
                                        singleList.add(new int[] { coord[0], coord[1] + 2 });
                                        singleList.add(new int[] { coord[0] - 1, coord[1] + 2 });
                                        listofCoord.add(singleList);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return listofCoord;
    }

    // pathing algortithms get the tiles in any direction.
    public List<int[]> rightUpTiles(int[] coordinate) {
        List<int[]> leftUpTiles = new ArrayList<>();
        if (inboard(coordinate[0] + 1) && inboard(coordinate[1])) {
            leftUpTiles.add(new int[] { coordinate[0] + 1, coordinate[1], 0 });
        }
        if (inboard(coordinate[0]) && inboard(coordinate[1] + 1)) {
            leftUpTiles.add(new int[] { coordinate[0], coordinate[1] + 1, 2 });
        }
        return leftUpTiles;
    }

    public List<int[]> adjecentTiles(int[] coordinate) {
        List<int[]> adjTilesList = new ArrayList<>();
        if (inboard(coordinate[0] + 1) && inboard(coordinate[1])) {
            adjTilesList.add(new int[] { coordinate[0] + 1, coordinate[1], 0 });
        }
        if (inboard(coordinate[0] - 1) && inboard(coordinate[1])) {
            adjTilesList.add(new int[] { coordinate[0] - 1, coordinate[1], -1 });
        }
        if (inboard(coordinate[0]) && inboard(coordinate[1] + 1)) {
            adjTilesList.add(new int[] { coordinate[0], coordinate[1] + 1, 2 });
        }
        if (inboard(coordinate[0]) && inboard(coordinate[1] - 1)) {
            adjTilesList.add(new int[] { coordinate[0], coordinate[1] - 1, 1 });
        }
        return adjTilesList;
    }

    private boolean inboard(int i) {
        return (i < 4 && i > -1);
    }

    public boolean boardfull() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[x][y].piece.getName().equalsIgnoreCase("empty")) {
                    return false;
                }
            }
        }
        return true;
    }

    public int calculateScore() {
        int score = 0;
        int numberofCottages = 0;
        int numberofChapels = 0;
        int numberofTavern = 0;
        int food = 0;
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (Character.isLowerCase(tile.piece.getName().charAt(0))) {
                    score--;
                } else if (tile.piece.getName().equals("Cottage")) {
                    numberofCottages++;
                } else if (tile.piece.getName().equals("Farm")) {
                    food = food + 4;
                } else if (tile.piece.getName().equals("Chapel")) {
                    numberofChapels++;
                } else if (tile.piece.getName().equals("Well")) {
                    List<int[]> adjecenTiles = adjecentTiles(new int[] { tile.x, tile.y });
                    for (int[] coordinates : adjecenTiles) {
                        if (board[coordinates[0]][coordinates[1]].getPiece().getName().equals("Cottage")) {
                            score++;
                        }
                    }
                } else if (tile.piece.getName().equals("Theatre")) {
                    List<String> types = new ArrayList<>();
                    for (int i = 0; i < tiles.length; i++) {
                        if (types.contains(board[tile.x][i].piece.getType())
                                && board[tile.x][i].piece.getType() != null) {
                            score++;
                            types.add(board[tile.x][i].piece.getType());
                        }
                        if (types.contains(board[i][tile.y].piece.getType())
                                && board[i][tile.y].piece.getType() != null) {
                            score++;
                            types.add(board[i][tile.y].piece.getType());
                        }
                    }
                } else if (tile.piece.getName().equals("Tavern") && numberofTavern != 5) {
                    numberofTavern++;
                    score += (numberofTavern + 1);
                }
            }
        }
        int fedcottages = numberofCottages - food;
        if (fedcottages < 0) {
            fedcottages = numberofCottages;
        } else {
            fedcottages = food;
        }
        score += (fedcottages * 3);
        score += (numberofChapels * fedcottages);

        return score;
    }

    public boolean tileEmpty(String[] split) {
        return board[Integer.parseInt(split[0]) - 1][Integer.parseInt(split[1]) - 1].getPiece().getName()
                .equalsIgnoreCase("empty");
    }

    public ArrayList<int[]> generateMoves() {
        ArrayList<int[]> moves = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < board.length; y++) {
                if (tileEmpty(x, y)) {
                    moves.add(new int[] { x, y });
                }
            }
        }
        return moves;
    }

    public boolean tileEmpty(int x, int y) {
        return board[x][y].getPiece().getName().equalsIgnoreCase("empty");
    }

    public void placeResource(int i, int j, String resource) {
        switch (resource) {
            case "Brick":
                board[i][j].setPiece(new Brick());
                break;
            case "Glass":
                board[i][j].setPiece(new Glass());
                break;
            case "Wheat":
                board[i][j].setPiece(new Wheat());
                break;
            case "Wood":
                board[i][j].setPiece(new Wood());
                break;
            case "Stone":
                board[i][j].setPiece(new Stone());
                break;
        }
    }

    public void placeBuilding(int x, int y, List<int[]> list, String building) {
        for (int[] is : list) {
            if (x == is[0] && y == is[1]) {
                switch (building) {
                    case "Chapel":
                        board[x][y].setPiece(new Chapel());
                        break;
                    case "Cottage":
                        board[x][y].setPiece(new Cottage());
                        break;
                    case "Factory":
                        board[x][y].setPiece(new Factory());
                        break;
                    case "Farm":
                        board[x][y].setPiece(new Farm());
                        break;
                    case "Tavern":
                        board[x][y].setPiece(new Tavern());
                        break;
                    case "Theatre":
                        board[x][y].setPiece(new Theatre());
                        break;
                    case "Well":
                        board[x][y].setPiece(new Well());
                        break;
                }
            }else {
                board[x][y].setPiece(new Piece());
            }
        }
}
}
