import java.util.Scanner;

public class GameRunner {
//    public Game startNewGame(){
//        Game game = new Game();
//        return game;
//    }








    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many tiles would you like to play with?");
        int numberOfCells = scanner.nextInt();
        Board board = new Board(numberOfCells);
        board.makeBoard(numberOfCells);

        System.out.println("Please give an x-coordinate and a y-coordinate? Enter coordinates in the form (row,column):");
        String coordinateInput = scanner.nextLine();
        // Remove parentheses and split by comma
        String[] coordinates = coordinateInput.replaceAll("[()\\s]+", "").split(",");
        int row = Integer.parseInt(coordinates[0]);
        int column = Integer.parseInt(coordinates[1]);
    }




}
