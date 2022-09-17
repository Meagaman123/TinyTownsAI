package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Board.Board;
import classes.Piece.Piece;
import classes.Piece.Buildings.Chapel;
import classes.Piece.Buildings.Cottage;
import classes.Piece.Buildings.Factory;
import classes.Piece.Buildings.Farm;
import classes.Piece.Buildings.Tavern;
import classes.Piece.Buildings.Theatre;
import classes.Piece.Buildings.Well;
import classes.Piece.Resources.Brick;

public class Ai extends Player {
    int maxDepth = 3;
    String[] resources = { "Brick", "Glass", "Stone", "Wheat", "Wood" };

    public Ai(int playerNum) {
        super(playerNum);
    }

    @Override
    public int[] calculateMove(String resource, Scanner in) {
        System.out.println(board.toString());
        System.out.println("Player " + playerNum);
        // TODO Auto-generated method stub
        Object[] bestMove = miniMaxGetCoordinate(maxDepth, super.board, resource);
        int[] move = { (Integer) bestMove[1], (Integer) bestMove[2] };
        return move;
    }

    private Object[] miniMaxGetCoordinate(int depth, Board boards, String resource) {
        if (depth == 0) {
            Object[] x = { boards.calculateScore(), null, null };
            return x;
        }

        ArrayList<int[]> allpossibleMoves = boards.generateMoves();
        if (allpossibleMoves.isEmpty()) {
            Object[] x = { boards.calculateScore(), null, null };
            return x;
        }
        Object[] bestMove = new Object[3];
        bestMove[0] = -17;
        for (int[] move : allpossibleMoves) {
            Board dummyBoard = new Board(boards);
            dummyBoard.placeResource(move[0], move[1], resource);
            Object[] tempMove = miniMax(depth - 1, dummyBoard);
            if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                bestMove = tempMove;
                bestMove[1] = move[0];
                bestMove[2] = move[1];
            }
        }
        return bestMove;
    }

    private Object[] miniMax(int depth, Board boards) {
        // check if this is the terminal node
        if (depth == 0) {
            Object[] x = { boards.calculateScore(), null, null, null };
            return x;
        }

        ArrayList<int[]> allpossibleMoves = boards.generateMoves();
        if (allpossibleMoves.isEmpty()) {
            Object[] x = { boards.calculateScore(), null, null, null };
            return x;
        }

        Object[] bestMove = new Object[3];
        bestMove[0] = -17;

        for (int[] move : allpossibleMoves) {
            for (int i = 0; i < resources.length; i++) {
                Board dummyBoard = new Board(boards);
                dummyBoard.placeResource(move[0], move[1], resources[i]);
                Object[] tempMove = miniMax(depth - 1, dummyBoard);

                if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                    bestMove = tempMove;
                    bestMove[1] = move[0];
                    bestMove[2] = move[1];
                    bestMove[3] = resources[i];
                }

                List<List<int[]>> listoflistsWell = dummyBoard.canBuildWell();
                List<List<int[]>> listoflistsTheatre = dummyBoard.canBuildTheatre();
                List<List<int[]>> listoflistsTavern = dummyBoard.canBuildTavern();
                List<List<int[]>> listoflistsCottage = dummyBoard.canBuildCottage();
                List<List<int[]>> listoflistsFarm = dummyBoard.canBuildFarm();
                List<List<int[]>> listoflistsChapel = dummyBoard.canBuildChapel();
                List<List<int[]>> listoflistsFactory = dummyBoard.canBuildFactory();
                for (List<int[]> list : listoflistsWell) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Well");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
                for (List<int[]> list : listoflistsTheatre) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Theatre");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
                for (List<int[]> list : listoflistsTavern) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Tavern");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
                for (List<int[]> list : listoflistsFactory) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Factory");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
                for (List<int[]> list : listoflistsFarm) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Farm");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
                for (List<int[]> list : listoflistsChapel) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Chapel");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
                for (List<int[]> list : listoflistsCottage) {
                    for (int[] coord : list) {
                        dummyBoard.placeBuilding(coord[0], coord[1], list, "Cottage");
                        tempMove = miniMax(depth - 1, dummyBoard);
                        if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                            bestMove = tempMove;
                            bestMove[1] = move[0];
                            bestMove[2] = move[1];
                            bestMove[3] = resources[i];
                        }
                    }
                }
            }
        }
        return bestMove;
    }

    @Override
    public void calcuateBuildingMove(List<List<int[]>> listOfBuilding, String building, Scanner in) {
        Object[] bestMove = new Object[3];
        bestMove[0] = -17;
        List<int[]> returnList = new ArrayList<>();

        for (List<int[]> list : listOfBuilding) {
            for (int[] array : list) {
                Board dummyBoard = new Board(super.board);
                dummyBoard.placeBuilding(array[0], array[1], list, building);
                Object[] tempMove = miniMax(maxDepth, dummyBoard);
                if ((Integer) tempMove[0] > (Integer) bestMove[0]) {
                    bestMove = tempMove;
                    bestMove[1] = array[0];
                    bestMove[2] = array[1];
                    returnList = list;
                }
            }
        }
        for (int[] coordinate : returnList) {
            if (coordinate[0] == (Integer)bestMove[1] && coordinate[1] == (Integer)bestMove[2]) {
                switch (building) {
                    case "Chapel":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Chapel());
                    break;
                    case "Cottage":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Cottage());
                    break;
                    case "Factory":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Factory());
                    break;
                    case "Farm":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Farm());
                    break;
                    case "Tavern":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Tavern());
                    break;
                    case "Theatre":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Theatre());
                    break;
                    case "Well":
                    board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Well());
                    break;
                }
            } else {
                board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
            }
        }

        
    }

   
    @Override
    public String choseResource(Scanner in) {
        Object[] bestmove = miniMax(maxDepth, super.board);
        return bestmove[3].toString();
    }

}
