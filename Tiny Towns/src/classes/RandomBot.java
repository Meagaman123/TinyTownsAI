package classes;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomBot extends Player {
    Random random = new Random();
    public RandomBot(int playerNum) {
        super(playerNum);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public int[] getCoordinates(Scanner in) {
        int upperBound = 4;
        while(true){
            int[] coordinates = new int[]{random.nextInt(upperBound), random.nextInt(upperBound)};
            if(board.tileEmpty(coordinates[0], coordinates[1]));
            return coordinates;
        }
        
    }
    
    @Override
    public int[] getCordinateBuilding(List<int[]> cordinatesList, Scanner in) {
        // TODO Auto-generated method stub
        return cordinatesList.get(random.nextInt(cordinatesList.size()));
    }

    @Override
    public List<int[]> choseCordinatetoplace(List<List<int[]>> list, Scanner in) {
        // TODO Auto-generated method stub
        return list.get( random.nextInt(list.size()));
    }
    @Override
    public String choseResource(Scanner in) {
        String [] array = {"Brick","Glass","Stone","Wheat","Wood"};
        return array[random.nextInt(5)];
    }
}
