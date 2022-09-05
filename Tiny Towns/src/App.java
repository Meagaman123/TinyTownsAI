import classes.Player;
import classes.Board.Board;
import classes.Board.Tile;
import classes.Piece.Piece;
import classes.Piece.Buildings.Chapel;
import classes.Piece.Buildings.Cottage;
import classes.Piece.Buildings.Factory;
import classes.Piece.Buildings.Farm;
import classes.Piece.Buildings.Tavern;
import classes.Piece.Buildings.Theatre;
import classes.Piece.Buildings.Well;
import classes.Piece.Resources.Brick;
import classes.Piece.Resources.Glass;
import classes.Piece.Resources.Stone;
import classes.Piece.Resources.Wheat;
import classes.Piece.Resources.Wood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Declare varibles for number of players and create a array to store each
        // player
        String[] buildingCoordinates = new String[32];
        int numOfPlayer = 1;
        Player[] players = new Player[numOfPlayer];
        int playable = 0;
        int[] coordinates = new int[2];
        String selection;
        int rounds = 0;
        // Create a scanner obj to get user input
        Scanner in = new Scanner(System.in);

        // creates a new game with 4 players
        for (int i = 0; i < numOfPlayer; i++) {
            players[i] = new Player(i+1);
            System.out.println("Created player " + players[i].getPlayerNum());
        }

        // Start the game keep looping through each player until no more moves are
        // possible
        while (gamePlayable(players)) {
            for (int i = 0; i < numOfPlayer; i++) {
                int currentround = rounds;
                if (players[i].playable()) {
                    do {
                        resourceMenu();
                        players[i].choseResource(in);
                        selection = in.nextLine();
                        switch (selection) {
                            case "1":
                                if (confirm("Brick", in)) {
                                    for (int x = 0; x < numOfPlayer; x++) {
                                        if ((i + x) > numOfPlayer - 1) {

                                            if (players[(x + i) - numOfPlayer].playable()) {
                                                System.out.println(players[(x + i) - numOfPlayer].board.toString());
                                                System.out.println("Player " + (((x + i) - numOfPlayer) + 1));
                                                coordinates = getCoordinates(in, players[(x + i) - numOfPlayer]);
                                                players[(x + i) - numOfPlayer].board.setTilebyCoord(coordinates[0],
                                                        coordinates[1], new Brick());
                                            }

                                        } else {

                                            if (players[x + i].playable()) {
                                                System.out.println(players[x + i].board.toString());
                                                System.out.println("Player " + (x + i + 1));
                                                coordinates = getCoordinates(in, players[x + i]);
                                                players[x + i].board.setTilebyCoord(coordinates[0], coordinates[1],
                                                        new Brick());
                                            }
                                        }

                                    }
                                    rounds++;
                                }
                                break;
                            case "2":
                                if (confirm("Glass", in)) {
                                    for (int x = 0; x < numOfPlayer; x++) {
                                        if ((i + x) > numOfPlayer - 1) {

                                            if (players[(x + i) - numOfPlayer].playable()) {
                                                System.out.println(players[(x + i) - numOfPlayer].board.toString());
                                                System.out.println("Player " + (((x + i) - numOfPlayer) + 1));
                                                coordinates = getCoordinates(in, players[(x + i) - numOfPlayer]);
                                                players[(x + i) - numOfPlayer].board.setTilebyCoord(coordinates[0],
                                                        coordinates[1], new Glass());
                                            }

                                        } else {

                                            if (players[x + i].playable()) {
                                                System.out.println(players[x + i].board.toString());
                                                System.out.println("Player " + (x + i + 1));
                                                coordinates = getCoordinates(in, players[x + i]);
                                                players[x + i].board.setTilebyCoord(coordinates[0], coordinates[1],
                                                        new Glass());
                                            }
                                        }

                                    }
                                    rounds++;
                                }
                                break;
                            case "3":
                                if (confirm("Stone", in)) {
                                    for (int x = 0; x < numOfPlayer; x++) {
                                        if ((i + x) > numOfPlayer - 1) {

                                            if (players[(x + i) - numOfPlayer].playable()) {
                                                System.out.println(players[(x + i) - numOfPlayer].board.toString());
                                                System.out.println("Player " + (((x + i) - numOfPlayer) + 1));
                                                coordinates = getCoordinates(in, players[(x + i) - numOfPlayer]);
                                                players[(x + i) - numOfPlayer].board.setTilebyCoord(coordinates[0],
                                                        coordinates[1], new Stone());
                                            }

                                        } else {

                                            if (players[x + i].playable()) {
                                                System.out.println(players[x + i].board.toString());
                                                System.out.println("Player " + (x + i + 1));
                                                coordinates = getCoordinates(in, players[x + i]);
                                                players[x + i].board.setTilebyCoord(coordinates[0], coordinates[1],
                                                        new Stone());
                                            }
                                        }

                                    }
                                    rounds++;
                                }
                                break;
                            case "4":
                                if (confirm("Wheat", in)) {
                                    for (int x = 0; x < numOfPlayer; x++) {

                                        if ((i + x) > numOfPlayer - 1) {
                                            if (players[(x + i) - numOfPlayer].playable()) {
                                                System.out.println(players[(x + i) - numOfPlayer].board.toString());
                                                System.out.println("Player " + (((x + i) - numOfPlayer) + 1));
                                                coordinates = getCoordinates(in, players[(x + i) - numOfPlayer]);
                                                players[(x + i) - numOfPlayer].board.setTilebyCoord(coordinates[0],
                                                        coordinates[1], new Wheat());
                                            }

                                        } else {
                                            if (players[x + i].playable()) {
                                                System.out.println(players[x + i].board.toString());
                                                System.out.println("Player " + (x + i + 1));
                                                coordinates = getCoordinates(in, players[x + i]);
                                                players[x + i].board.setTilebyCoord(coordinates[0], coordinates[1],
                                                        new Wheat());
                                            }
                                        }

                                    }
                                    rounds++;
                                }
                                break;
                            case "5":
                                if (confirm("Wood", in)) {
                                    for (int x = 0; x < numOfPlayer; x++) {

                                        if ((i + x) > numOfPlayer - 1) {
                                            if (players[(x + i) - numOfPlayer].playable()) {
                                                System.out.println(players[(x + i) - numOfPlayer].board.toString());
                                                System.out.println("Player " + (((x + i) - numOfPlayer) + 1));
                                                coordinates = getCoordinates(in, players[(x + i) - numOfPlayer]);
                                                players[(x + i) - numOfPlayer].board.setTilebyCoord(coordinates[0],
                                                        coordinates[1], new Wood());
                                            }

                                        } else {
                                            if (players[x + i].playable()) {
                                                System.out.println(players[x + i].board.toString());
                                                System.out.println("Player " + (x + i + 1));
                                                coordinates = getCoordinates(in, players[x + i]);
                                                players[x + i].board.setTilebyCoord(coordinates[0], coordinates[1],
                                                        new Wood());
                                            }
                                        }

                                    }
                                    rounds++;
                                }
                                break;
                        }
                    } while (currentround == rounds);

                    for (Player player : players) {
                        List<List<int[]>> listoflistsWell = player.board.canBuildWell();
                        List<List<int[]>> listoflistsTheatre = player.board.canBuildTheatre();
                        List<List<int[]>> listoflistsTavern = player.board.canBuildTavern();
                        List<List<int[]>> listoflistsCottage = player.board.canBuildCottage();
                        List<List<int[]>> listoflistsFarm = player.board.canBuildFarm();
                        List<List<int[]>> listoflistsChapel = player.board.canBuildChapel();
                        List<List<int[]>> listoflistsFactory = player.board.canBuildFactory();

                        if (!listoflistsWell.isEmpty()) {
                            List<int[]> wellCoordinate = choseCordinatetoplace(listoflistsWell, in);
                            if (!wellCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(wellCoordinate, in);
                             if (coord.length != 0) {
                                for (int[] coordinate : wellCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Well());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }
                        }

                        if (!listoflistsTheatre.isEmpty()) {
                            List<int[]> theatreCoordinate = choseCordinatetoplace(listoflistsTheatre, in);
                            if (!theatreCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(theatreCoordinate, in);
                            if (coord.length != 0) {
                                for (int[] coordinate : theatreCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1])
                                                .setPiece(new Theatre());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }
                        }
                        if (!listoflistsTavern.isEmpty()) {
                            List<int[]> buildingCoordinate = choseCordinatetoplace(listoflistsTavern, in);
                            if (!buildingCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(buildingCoordinate, in);
                            if (coord.length != 0) {
                                for (int[] coordinate : buildingCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1])
                                                .setPiece(new Tavern());
                                        System.out.println(player.board.getTilebyCoord(coordinate[0], coordinate[1])
                                                .getPiece().getType());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }
                        }
                        if (!listoflistsCottage.isEmpty()) {
                            List<int[]> buildingCoordinate = choseCordinatetoplace(listoflistsCottage, in);
                            if (!buildingCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(buildingCoordinate, in);
                            if (coord.length != 0) {
                                for (int[] coordinate : buildingCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1])
                                                .setPiece(new Cottage());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }

                        }
                        if (!listoflistsFarm.isEmpty()) {
                            List<int[]> buildingCoordinate = choseCordinatetoplace(listoflistsFarm, in);
                            if (!buildingCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(buildingCoordinate, in);
                            if (coord.length != 0) {
                                for (int[] coordinate : buildingCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Farm());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }
                        }
                        if (!listoflistsChapel.isEmpty()) {
                            List<int[]> buildingCoordinate = choseCordinatetoplace(listoflistsChapel, in);
                            if (!buildingCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(buildingCoordinate, in);
                            if (coord.length != 0) {
                                for (int[] coordinate : buildingCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1])
                                                .setPiece(new Chapel());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }

                        }
                        if (!listoflistsFactory.isEmpty()) {
                            List<int[]> buildingCoordinate = choseCordinatetoplace(listoflistsFactory, in);
                            if (!buildingCoordinate.isEmpty()){
                            int[] coord = getCordinateBuilding(buildingCoordinate, in);
                            if (coord.length != 0) {
                                for (int[] coordinate : buildingCoordinate) {
                                    if (coordinate == coord) {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1])
                                                .setPiece(new Factory());
                                    } else {
                                        player.board.getTilebyCoord(coordinate[0], coordinate[1]).setPiece(new Piece());
                                    }

                                }
                            }
                        }
                    }

                    }
                }
            }
        }

        for (Player player : players) {
            System.out.println("you score is " + player.board.calculateScore());
        }

    }

    private static boolean gamePlayable(Player[] players) {
        for (Player player : players) {
            player.checkBoardPlayable();
            if (player.playable()) {
                return true;
            }
        }
        return false;
    }

    private static void resourceMenu() {
        System.out.println("\n--------------------");
        System.out.println("Choose a Resource");
        System.out.println("--------------------");
        System.out.println("[1] Brick");
        System.out.println("[2] Glass");
        System.out.println("[3] Stone");
        System.out.println("[4] Wheat");
        System.out.println("[5] Wood");
        System.out.println();
    }

    // This checks the coordinates entered to see if they are a possible coordinate
    // IE within the grid and if the tile is empty
    private static boolean checkCoordinates(String[] split, Tile[][] board) {
        if (split.length != 2) {
            return false;
        }
        for (String xory : split) {
            if (!isInt(xory)) {
                return false;
            }
            int xoryint = Integer.parseInt(xory);
            if (xoryint > 4 || (Integer.parseInt(xory) < 0)) {
                return false;
            }
        }
        return board[Integer.parseInt(split[0])-1][Integer.parseInt(split[1])-1].getPiece().getName().equals("empty");
    }

    // makes the user confirm that what they have selected is what they want to
    // select.
    private static boolean confirm(String selection, Scanner in) {
        System.out.println("Confirm you selected " + selection + ". [Y/N]");
        return in.nextLine().equalsIgnoreCase("y");

    }

    private static int[] getCoordinates(Scanner in, Player player) {
        while (true) {
            int[] coordinates = new int[2];
            System.out.println("Which tile you would like to place it. [x,y]");
            String coordinatesString = in.nextLine();
            String[] split = coordinatesString.split(",");
            if (checkCoordinates(split, player.board.getBoard()) && confirm(coordinatesString, in)) {
                for (int i = 0; i < split.length; i++) {
                    coordinates[i] = (Integer.parseInt(split[i]) - 1);

                }
                return coordinates;
            }
        }
    }

    // checks if the user input is an int
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;

        }
    }

    private static List<int[]> choseCordinatetoplace(List<List<int[]>> list, Scanner in) {
        int i = 1;
        for (List<int[]> innerList : list) {
            System.out.print(i + ": ");
            for (int[] array : innerList) {
                System.out.print("[" + (array[0] + 1) + ", " + (array[1] + 1) + "]");
            }
            System.out.println();
            i++;

        }
        String well = "";
        System.out.println("Type quit to exit");
        while (!isInt(well) && !well.equalsIgnoreCase("quit")) {
            System.out.println("Enter the number you would like to build a well on.");
            well = in.nextLine();
        }
        if (isInt(well)) {
            int wellInt = Integer.parseInt(well);
            return list.get(wellInt - 1);
        }
        return new ArrayList<>();

    }

    private static int[] getCordinateBuilding(List<int[]> cordinatesList, Scanner in) {
        String building = "";
        int i = 1;
        for (int[] array : cordinatesList) {
            System.out.println(i++ + ": [" + (array[0] + 1) + ", " + (array[1] + 1) + "]");
        }
        System.out.println("Type quit to exit");
        while (!isInt(building) && !building.equalsIgnoreCase("quit")) {
            System.out.println(
                    "Enter the number for the coordinate you want to build the Factory on");
            building = in.nextLine();
        }
        if (isInt(building)) {
            int buildingInt = Integer.parseInt(building);
            int[] coord = cordinatesList.get(buildingInt - 1);
            return coord;
        }
        return new int[] {};

    }
}
