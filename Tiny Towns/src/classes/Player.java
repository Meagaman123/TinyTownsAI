package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import classes.Piece.Piece;
import classes.Piece.Buildings.Chapel;
import classes.Piece.Buildings.Cottage;
import classes.Piece.Buildings.Factory;
import classes.Piece.Buildings.Farm;
import classes.Piece.Buildings.Tavern;
import classes.Piece.Buildings.Theatre;
import classes.Piece.Buildings.Well;
import classes.Board.Board;
import classes.Piece.Resources.*;

public class Player {
    int playerNum;
    Board board;
    boolean playable;
    List<String> factoryResources = new ArrayList<>();
    Random random = new Random();

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

    public void checkBoardPlayable() {
        List<List<int[]>> listoflistsWell = board.canBuildWell();
        List<List<int[]>> listoflistsTheatre = board.canBuildTheatre();
        List<List<int[]>> listoflistsTavern = board.canBuildTavern();
        List<List<int[]>> listoflistsCottage = board.canBuildCottage();
        List<List<int[]>> listoflistsFarm = board.canBuildFarm();
        List<List<int[]>> listoflistsChapel = board.canBuildChapel();
        List<List<int[]>> listoflistsFactory = board.canBuildFactory();
        if (board.boardfull()) {
            if (listoflistsWell.isEmpty() && listoflistsTheatre.isEmpty() && listoflistsTavern.isEmpty() && listoflistsCottage.isEmpty() && listoflistsFarm.isEmpty() && listoflistsFactory.isEmpty() && listoflistsChapel.isEmpty()) {
                this.playable = false;
            } else {
                if(!listoflistsWell.isEmpty()){
                    forcebuild(listoflistsWell, "Well");
                } else if(!listoflistsTheatre.isEmpty()){
                    forcebuild(listoflistsTheatre, "Theatre");
                } else if(!listoflistsTavern.isEmpty()){
                    forcebuild(listoflistsTavern, "Tavern");
                } else if(!listoflistsCottage.isEmpty()){
                    forcebuild(listoflistsCottage, "Cottage");
                } else if(!listoflistsFarm.isEmpty()){
                    forcebuild(listoflistsFarm, "Farm");
                } else if(!listoflistsChapel.isEmpty()){
                    forcebuild(listoflistsChapel, "Chapel");
                } else if(!listoflistsFactory.isEmpty()){
                    forcebuild(listoflistsFactory, "Factory");
                }
                
            }
        }

    }

    private void forcebuild(List<List<int[]>> listoflists, String building) {
        List<int[]> coordinatelist = listoflists.get(random.nextInt(listoflists.size()));
        int[] coordinate = coordinatelist.get( random.nextInt(coordinatelist.size()));
        for (int[] coord : coordinatelist) {
            if (coordinate == coord) {
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

    public String choseResource(Scanner in) {
        String selection = "";
        while (true) {
            resourceMenu();
            selection = in.nextLine();
            switch (selection) {
                case "1":
                    return "Brick";
                case "2":
                    return "Glass";
                case "3":
                    return "Stone";
                case "4":
                    return "Wheat";
                case "5":
                    return "Wood";
            }
        }
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

    public void makeMove(String resource, Scanner in) {
        if (factoryResources.contains(resource)) {
            resource = choseResource(in);
        }
        int[] coordinates = calculateMove(resource, in);
        switch (resource) {
            case "Brick":
                board.setTilebyCoord(coordinates[0], coordinates[1], new Brick());
                break;
            case "Glass":
                board.setTilebyCoord(coordinates[0], coordinates[1], new Glass());
                break;
            case "Wheat":
                board.setTilebyCoord(coordinates[0], coordinates[1], new Wheat());
                break;
            case "Wood":
                board.setTilebyCoord(coordinates[0], coordinates[1], new Wood());
                break;
            case "Stone":
                board.setTilebyCoord(coordinates[0], coordinates[1], new Stone());
                break;
        }
    }

    public Board getBoard() {
        return board;
    }

    public int[] calculateMove(String resource, Scanner in) {
        System.out.println(board.toString());
        System.out.println("Player " + playerNum);

        return getCoordinates(in);
    }

    public int[] getCoordinates(Scanner in) {
        while (true) {
            int[] coordinates = new int[2];
            System.out.println("Which tile you would like to place it. [x,y]");
            String coordinatesString = in.nextLine();
            String[] split = coordinatesString.split(",");
            if (checkCoordinates(split)) {
                for (int i = 0; i < split.length; i++) {
                    coordinates[i] = (Integer.parseInt(split[i]) - 1);
                }
                return coordinates;
            }
        }
    }

    private boolean checkCoordinates(String[] split) {
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
        return board.tileEmpty(split);
    }

    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;

        }
    }

    public void addResource(String resource) {
        if (!factoryResources.contains(resource)) {
            factoryResources.add(resource);
        }
    }

    public List<int[]> choseCordinatetoplace(List<List<int[]>> list, Scanner in) {
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

    public void calcuateBuildingMove(List<List<int[]>> listOfBuilding, String building, Scanner in) {
        List<int[]> buildingCoordinate = choseCordinatetoplace(listOfBuilding, in);
        if (!buildingCoordinate.isEmpty()) {
            int[] coord = getCordinateBuilding(buildingCoordinate, in);
            if (coord.length != 0) {
                for (int[] coordinate : buildingCoordinate) {
                    if (coordinate == coord) {
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
        }
    }

    public int[] getCordinateBuilding(List<int[]> cordinatesList, Scanner in) {
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
            return cordinatesList.get(buildingInt - 1);
        }
        return new int[] {};

    }

}
