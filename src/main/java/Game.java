import java.util.Random;
import java.util.Scanner;

public class Game {


    private Board board;

    public void startBoard() {
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                board.getBoard()[i][j] = new Cell(false, false, false);
            }
        }
    }

    public void displayBoard() {
        System.out.print("   ");
        for (int i = 0; i < board.getDimensions(); i++) {
            System.out.print(" " + i + "  ");
        }

        System.out.println();

        for (int i = 0; i < board.getDimensions(); i++) {
            System.out.print(i + " ");
            if (i < 10) {
                System.out.print(" ");
            }
            for (int j = 0; j < board.getDimensions(); j++) {
                if (!board.getBoard()[i][j].isSelected()) {
                    System.out.print("|X| ");
                    if (j > 9) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(board.getBoard()[i][j].getSymbol() + " ");
                    if (j > 9) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }

    }


    public void addMinesToBoard(int x, int y) {
        int mines = Integer.valueOf(board.getNumberOfMines());
        while (mines != 0) {
            int xRandom = (int) (Math.random() * board.getDimensions());
            int yRandom = (int) (Math.random() * board.getDimensions());
            if (xRandom != x && yRandom != y) {
                board.getBoard()[xRandom][yRandom].setMine(true);
                mines--;
            }
        }
    }

    public void makeFirstChoice(int x, int y) {
        board.getBoard()[x][y].setMine(false);
        board.getBoard()[x][y].setSelected(true);
        board.getBoard()[x][y].setSymbol(Integer.toString(findNumberOfNearByMines(x, y)));
        addMinesToBoard(x, y);
        chooseCoordinate(x, y);
    }

    public int findNumberOfNearByMines(int x, int y) {
        int numberOfMinesFound = 0;
        for (int n = -1; n < 2; n++) {
            for (int m = -1; m < 2; m++) {
                if (!isOutOfBound(x, m) && !isOutOfBound(y, n)) {
                    if (board.getBoard()[x + m][y + n].isMine()) {
                        numberOfMinesFound++;
                    }
                }
            }
        }
        return numberOfMinesFound;
    }

    private boolean isOutOfBound(int x, int y) { //checks if the coordinates are out of array bounds
        return (x + y >= board.getDimensions() || x + y < 0);
    }

    public boolean checkIfMine(int x, int y) {
        if (board.getBoard()[x][y].isMine() == false) {
            return false;
        } else {
            endGame();
            return true;
        }
    }

    public void displayMines() {
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                if (!board.getBoard()[i][j].isSelected() && board.getBoard()[i][j].isMine()) {
                    board.getBoard()[i][j].setSelected(true);
                }
            }
        }
    }

    private void chooseCoordinate(int x, int y) { //allows the player to select coordinates
        board.getBoard()[x][y].setSelected(true);
        board.setSelectedBoxes(1);
        if (checkIfMine(x, y)) {
            return;
        }
        int s = findNumberOfNearByMines(x, y);
        board.getBoard()[x][y].setSymbol(" " + Integer.toString(s) + " ");
        if (s == 0) {
            for (int n = -1; n < 2; n++) {
                for (int m = -1; m < 2; m++) {
                    if (!(isOutOfBound(x, m) || isOutOfBound(y, n))) {
                        if (!board.getBoard()[x + m][y + n].isSelected()) {
                            chooseCoordinate(x + m, y + n);
                        }
                    }
                }
            }
        }
    }



    public void startGame() {
        board = new Board();
        startBoard();
        displayBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give a coordinate number (e.g., [1,2] for the cell in the first row and second column):");
//        int a = scan.nextInt();
//        int b = scan.nextInt();
        String firstInput = scanner.next();
        String[] cellTokens = splitCoordinates(firstInput);
//        String[] cellTokens = firstInput.substring(1, firstInput.length() - 1).split(",");
        int row = Integer.parseInt(cellTokens[0]);
        int col = Integer.parseInt(cellTokens[1]);
        makeFirstChoice(row, col);
        displayBoard();
        do {
            System.out.println("Please enter the coordinates of your next selection in the same format:");
            String input = scanner.next();
//            int x = scanner.nextInt();
//            int y = scanner.nextInt();
            String[] tokens = splitCoordinates(input);
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            if (isSelected(x, y)) {
                System.out.println("This point has already been selected. Please try again");
                continue;
            }
            chooseCoordinate(x, y);
            displayBoard();
            wonGame();
        } while (!board.isEndGame());
    }

    public String[] splitCoordinates(String coordinates){
        String[] coordinateTokens = coordinates.substring(1, coordinates.length() - 1).split(",");
        return coordinateTokens;
    }

    private boolean isSelected(int c, int d) {
        if (!(isOutOfBound(c, 0) || isOutOfBound(d, 0))) {
            if (board.getBoard()[c][d].isSelected()) {
                return true;
            }
        }
        return false;
    }

    public void wonGame() {
        if (board.getSelectedBoxes() == ((board.getDimensions() * board.getDimensions()) - board.getNumberOfMines())) {
            endGame();
        }
    }


    private void endGame() {
        if (board.getSelectedBoxes() != ((board.getDimensions() * board.getDimensions()) - board.getNumberOfMines())) {
            displayMines();
            System.out.println("EEEXXXXPPLLLOSSION!!!!, You stepped on a mine. Game over");
        } else {
            System.out.println("Congratulations!!!, you have won!");
        }
        board.setEndGame(true);
    }

}