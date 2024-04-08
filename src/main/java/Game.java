import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {


    private Board board;

    public void startBoard() {
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                board.getBoard()[i][j] = new Cell(false, false, false); // creating a cell
            }
        }
    }

    public void displayBoard() { // displays the board
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
                    if (j > 9) { //makes more space for double digit numbers to be aligned on board
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


    public void addMinesToBoard(int x, int y) { // creates randomised mines for baord
        int mines = Integer.valueOf(board.getNumberOfMines());
        while (mines != 0) {
            int xRandom = (int) (Math.random() * board.getDimensions());
            int yRandom = (int) (Math.random() * board.getDimensions());
            if (xRandom != x && yRandom != y) { //makes sure mines not repeated
                board.getBoard()[xRandom][yRandom].setMine(true);
                mines--;
            }
        }
    }


    public void addFlagToBoard(int x, int y) {
        int flagsAvailable = board.getNumberOfFlags(); // Assuming board.getNumberOfFlags() returns an int directly

        if (flagsAvailable > 0 && !board.getBoard()[x][y].hasFlag()) { // Check if flags are available and cell doesn't already have a flag
            board.getBoard()[x][y].setHasFlag(true);
            board.getBoard()[x][y].setSelected(true);
            System.out.println("\tTo remove this flag please enter -F[row, column]");
            board.setNumberOfFlags(board.getNumberOfFlags() - 1); // Decrement the number of available flags
            System.out.println("\tYou have " + board.getNumberOfFlags() + " Flag(s) left.");
        } else {
            System.out.println("you have ran out of flags!");
        }

    }


    public void removeFlagFromBoard(int x, int y) { //removes flags
        board.getBoard()[x][y].setHasFlag(false);
        board.getBoard()[x][y].setSelected(false);
        board.setNumberOfFlags(board.getNumberOfFlags() + 1); //increment number of available flags
        System.out.println("\tYou have " + board.getNumberOfFlags() + " Flag(s) left.");
    }


    public void makeFirstChoice(int x, int y) {
        board.getBoard()[x][y].setMine(false);  //makes sure not to hit mine on first attempt
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

    public boolean isOutOfBound(int x, int y) { //checks if the coordinates are out of array bounds
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

    public void displayMines() { //for when it's the end of game and need to display all mines
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                if (!board.getBoard()[i][j].isSelected() && board.getBoard()[i][j].isMine()) {
                    board.getBoard()[i][j].setSelected(true);
                }
            }
        }
    }

    public void chooseCoordinate(int x, int y) { //allows the player to select coordinates
        board.getBoard()[x][y].setSelected(true);
        board.setSelectedBoxes(1);
        if (checkIfMine(x, y)) {
            return;
        }
        int num = findNumberOfNearByMines(x, y);
        board.getBoard()[x][y].setSymbol(" " + num + " ");
        if (num == 0) {
            for (int n = -1; n < 2; n++) {
                for (int m = -1; m < 2; m++) {
                    if (!(isOutOfBound(x, m) || isOutOfBound(y, n))) {
                        if (!board.getBoard()[x + m][y + n].isSelected()) {
                            chooseCoordinate(x + m, y + n); //spreading the board when 0 mines is selected
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
        while (true) {
            System.out.println("Please give a coordinate number (e.g., [1,2] for the cell in the first row and second column):");
            String firstInput = scanner.next();
            String pattern = "\\[(\\d+),(\\d+)\\]";

            if (firstInput.matches(pattern)) {

                String[] cellTokens = splitCoordinates(firstInput);
                int row = Integer.parseInt(cellTokens[0]);
                int col = Integer.parseInt(cellTokens[1]);
                if (!isOutOfBound(row, col)) {
                    makeFirstChoice(row, col);
                    displayBoard();
                    break;
                } else {
                    System.out.println("Coordinates are out of bounds.");
                }

            } else {
                System.out.println("Invalid input");
            }

        }
        do {
            while (true) {
                System.out.println("Please enter the coordinates of your next selection in the same format");
                System.out.println("Enter F[row, column] if you wish to place a flag in that position");
                String input = scanner.next();
                String patternF = "F\\[(\\d+),(\\d+)\\]";
                String patternNF = "-F\\[(\\d+),(\\d+)\\]";
                if (input.matches(patternF) || input.matches(patternNF)) {

                    if (input.indexOf('F') == 0) {
                        int[] values = extractValues(input);
                        int x = values[0];
                        int y = values[1];
                        addFlagToBoard(x, y);
                        break;

                    } else if (input.contains(String.valueOf('F')) && input.contains(String.valueOf('-'))) {
                        int[] values = extractValues(input);
                        int x = values[0];
                        int y = values[1];
                        removeFlagFromBoard(x, y);
                        break;

                    } else {
                        String[] tokens = splitCoordinates(input);
                        int x = Integer.parseInt(tokens[0]);
                        int y = Integer.parseInt(tokens[1]);

                        if (isSelected(x, y)) {
                            System.out.println("This point has already been selected. Please try again:");
                            continue;
                        }
                        chooseCoordinate(x, y);
                        break;
                    }
                } else {
                    System.out.println("Invalid Input");
                }
            }
            displayBoard();
            wonGame();
        } while (!board.isEndGame());
    }

    public String[] splitCoordinates(String coordinates) {
        String[] coordinateTokens = coordinates.substring(1, coordinates.length() - 1).split(",");
        return coordinateTokens;
    }

    public int[] extractValues(String input) {
        Pattern pattern = Pattern.compile("F\\[(\\d+),(\\d+)\\]");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            return new int[]{x, y};
        } else {
            return null; // Return null if no match found
        }
    }

    private boolean isSelected(int x, int y) {
        if (!(isOutOfBound(x, 0) || isOutOfBound(y, 0))) {
            if (board.getBoard()[x][y].isSelected()) {
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
            displayMines(); //display mines when game has ended
            System.out.println("EEEXXXXPPLLLOSSION!!!!, You stepped on a mine.");
            System.out.println("GAME OVER!!!");
            ;
        } else {
            System.out.println("Congratulations!!!, you have won!");
        }
        board.setEndGame(true);
    }

}