public class Position {
    private int row;
    private int column;

    public Position(int r, int c) {
        this.row = r;
        this.column = c;
    }

    public void setRow(int r) {
        this.row = r;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int c) {
        this.column = c;
    }

    public int getColumn() {
        return column;
    }

}
