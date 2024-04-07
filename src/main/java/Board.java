import java.util.Scanner;

public class Board {
    private int dimensions;
    private int numberOfMines;
    private Cell[][] board;
    private int selectedBoxes;
    private boolean endGame;

    private int numberOfFlags;

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setSelectedBoxes(int selectedBoxes) {
        this.selectedBoxes += selectedBoxes;
    }

    public int getSelectedBoxes() {
        return selectedBoxes;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public int getNumberOfFlags() {
        return numberOfFlags;
    }

    public void setNumberOfFlags(int numberOfFlags) {
        this.numberOfFlags = numberOfFlags;
    }

    public Board() {
        Scanner scan = new Scanner(System.in);
        int difficulty ;
        do {
            System.out.println("Enter Difficulty level to begin game");
            System.out.println("1: Beginner – 9 * 9 Board and 10 Mines ");
            System.out.println("2: Intermediate – 16 * 16 Board and 40 Mines ");
            System.out.println("3: Expert – 24 * 24 Board and 99 Mines ");
            while (!scan.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scan.next(); // Consume and discard the invalid input
            }

            difficulty = scan.nextInt();


            if (difficulty < 1 || difficulty > 3) {
                System.out.println("Invalid difficulty level. Please enter a number between 1 and 3.");
            }
        } while (!(difficulty == 1 || difficulty == 2 || difficulty == 3));

        switch (difficulty) {
            case 1: // Beginner – 9 * 9 Board and 10 Mines
                dimensions = 9;
                numberOfMines = 10;
                numberOfFlags = 2;
                break;

            case 2: //  Intermediate – 16 * 16 Board and 40 Mines
                dimensions = 16;
                numberOfMines = 40;
                numberOfFlags = 40;
                break;

            case 3: //   Advanced – 24 * 24 Board and 99 Mines
                dimensions = 24;
                numberOfMines = 99;
                numberOfFlags = 99;
                break;

            default:
                dimensions = 0;
                numberOfMines = 0;
                numberOfFlags = 0;
                break;
        }
        board = new Cell[dimensions][dimensions];
        endGame = false;
    }
}
