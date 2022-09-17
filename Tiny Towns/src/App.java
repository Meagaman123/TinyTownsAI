import classes.Ai;
import classes.Player;
import classes.RandomBot;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Declare varibles for number of players and create a array to store each
        // player
        int numOfPlayer = 2;
        int numOfAI = 1;
        int numOfrandbot = 1;
        Player[] players = new Player[numOfPlayer];
        String resource = null;
        // Create a scanner obj to get user input
        Scanner in = new Scanner(System.in);

        // creates a new game with 4 players
        for (int i = 0; i < numOfPlayer; i++) {
            if(numOfAI > 0){
                players[i] = new Ai(i + 1);
                numOfAI--;
            }else if (numOfrandbot > 0){
                players[i] = new RandomBot(i + 1);
                numOfAI--;
            }else{
                players[i] = new Player(i + 1);
            }
            System.out.println("Created player " + players[i].getPlayerNum());

        }

        // Start the game keep looping through each player until no more moves are
        // possible
        while (gamePlayable(players)) {
            for (int i = 0; i < numOfPlayer; i++) {
                if (players[i].playable()) {
                    resource = players[i].choseResource(in);
                    for (int x = 0; x < numOfPlayer; x++) {
                        if ((i + x) > numOfPlayer - 1) {
                            if (players[(x + i) - numOfPlayer].playable()) {
                                players[(x + i) - numOfPlayer].makeMove(resource, in);
                            }

                        } else {
                            if (players[x + i].playable()) {
                                players[x + i].makeMove(resource, in);
                            }

                        }

                    }

                    for (Player player : players) {
                        List<List<int[]>> listoflistsWell = player.getBoard().canBuildWell();
                        List<List<int[]>> listoflistsTheatre = player.getBoard().canBuildTheatre();
                        List<List<int[]>> listoflistsTavern = player.getBoard().canBuildTavern();
                        List<List<int[]>> listoflistsCottage = player.getBoard().canBuildCottage();
                        List<List<int[]>> listoflistsFarm = player.getBoard().canBuildFarm();
                        List<List<int[]>> listoflistsChapel = player.getBoard().canBuildChapel();
                        List<List<int[]>> listoflistsFactory = player.getBoard().canBuildFactory();
                        if (!listoflistsWell.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsWell, "Well", in);
                        }

                        if (!listoflistsTheatre.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsTheatre, "Theatre", in);
                        }

                        if (!listoflistsTavern.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsTavern, "Tavern", in);
                        }

                        if (!listoflistsCottage.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsCottage, "Cottage", in);
                        }

                        if (!listoflistsFarm.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsFarm, "Farm", in);
                        }

                        if (!listoflistsChapel.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsChapel, "Chapel", in);
                        }

                        if (!listoflistsFactory.isEmpty()) {
                            player.calcuateBuildingMove(listoflistsFactory, "Factory", in);
                            player.addResource(player.choseResource(in));
                        }
                    }
                }
            }
        }

        for (Player player : players) {
            
            System.out.println(player.getBoard().toString() + "\n" +"Player: " + player.getPlayerNum() +  "\n" + "you score is " + player.getBoard().calculateScore());

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

}
