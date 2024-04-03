public class Board {

   private int numberOfCells;

   private int numberOfBombs;


    public Board(int numberOfCells, int numberOfBombs) {
        this.numberOfCells = numberOfCells;
        this.numberOfBombs = numberOfBombs;
    }

    public Board() {
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public void makeBoard(int numberOfCells) {
        for (int i = 0; i < numberOfCells; i++) {
            System.out.print("|");
            for (int j = 0; j < numberOfCells; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print(" X |");
                } else {
                    System.out.print(" X |");
                }
            }
            System.out.println();
        }
    }


}
