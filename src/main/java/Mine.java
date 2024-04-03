import java.util.Random;

public class Mine extends Board{

    private Board board;



    public Mine(Board board) {
         this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void createBomb(){
        Random random =  new Random();
        int xCoordinate = random.nextInt(getNumberOfCells());
        int yCoordinate = random.nextInt(getNumberOfBombs());

        Cell cell = new Cell(xCoordinate, yCoordinate);

        if(!cell.hasMine()){
            cell.setHasMine(true);
            cell.setCovered(false);
        }else {
            createBomb();
        }
    }
}
