import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Random random = new Random();
        Board board = new Board();
        game.startGame();
//        int xCoordinate = Math.abs(random.nextInt(board.getNumberOfMines()));
//        int yCoordinate = Math.abs(random.nextInt(board.getNumberOfMines()));
//        System.out.println(xCoordinate);
//        System.out.println(yCoordinate);
    }
}