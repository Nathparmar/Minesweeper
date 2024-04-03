import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Cell> cellList;

    public Game() {
        this.cellList = new ArrayList<>();
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }
}
