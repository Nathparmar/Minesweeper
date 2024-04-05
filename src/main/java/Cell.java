public class Cell {

    private boolean isMine;

    private boolean isSelected;

    private boolean hasFlag;

    private String symbol;

    public Cell(boolean isMine, boolean isSelected, boolean hasFlag) {
        this.isMine = isMine;
        this.isSelected = isSelected;
        this.hasFlag = hasFlag;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
        if (isMine){
            setSymbol(" ! ");
        }
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean hasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean flag) {
        hasFlag = flag;
        if (hasFlag){
            setSymbol(" F ");
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}