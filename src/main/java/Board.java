public class Board {

   private int numberOfCells;


    public Board(int numberOfCells) {
        this.numberOfCells = 0;
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
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
