import java.util.Random;

public class Mine extends Board{

    private Board board;



    public Mine() {

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void createMine(){
        Random random =  new Random();
        int xCoordinate = random.nextInt(getNumberOfCells());
        int yCoordinate = random.nextInt(getNumberOfCells());

        Cell cell = new Cell(xCoordinate, yCoordinate);

        if(!cell.hasMine()){
            cell.setHasMine(true);
            cell.setCovered(false);
        }else {
            createMine();
        }
    }

    public void produceMines(int numOfMines){
        int numberOfBombs = numOfMines;
        int count = 0;
        do {
            createMine();
            count++;
        }while (count < numberOfBombs);
    }




}
