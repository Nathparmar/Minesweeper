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
        int numberOfBombs = numberOfCells;
        Board board = new Board(numberOfCells,numberOfBombs);
        board.makeBoard(numberOfCells);
        System.out.println("Please give a cell number (e.g., [1,2] for the cell in the first row and second column):");
       String cellInput = scanner.next();



        String[] cellTokens = cellInput.substring(1, cellInput.length() - 1).split(","); // remove the square brackets and split the string by comma
        int row = Integer.parseInt(cellTokens[0]);
        int col = Integer.parseInt(cellTokens[1]);
        System.out.println(row);
        System.out.println(col);









    }




}
