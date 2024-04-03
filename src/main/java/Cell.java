public class Cell extends Board{

    private int row;

    private int column;

    private boolean isCovered;

    private boolean hasMine;

    private int numberOfMinesAround;

    private boolean hasFlag;


    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.isCovered = true;
        this.hasMine = false;
        this.hasFlag = false;
        this.numberOfMinesAround = numberOfMinesAround;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public int getNumberOfMinesAround() {
        return numberOfMinesAround;
    }

    public void setNumberOfMinesAround(int numberOfMinesAround) {
        this.numberOfMinesAround = numberOfMinesAround;
    }

    public void cellChosen(int row, int column){
        Cell chosenCell = new Cell(row, column);
        chosenCell.setCovered(false);
        if (chosenCell.hasMine()){
            Game currentGame = new Game();
            currentGame.setGameStarted(false);
            System.out.println("EEEXXXPPLOSION!, you stepped on a mine!");
        }

    }
}
