import java.util.ArrayList;

public class Maze {
    
    private ArrayList<ArrayList<Element>> mazeElements = new ArrayList<ArrayList<Element>>();
    private Position startingPosition = null;
    private Position targetPosition = null;

    public Maze() {

    }

    public ArrayList<ArrayList<Element>> getMazeElements() {
        return mazeElements;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Element getElement(int row, int column) {
        return mazeElements.get(row).get(column);
    }

    public Element getElement(Position position) {
        return mazeElements.get(position.getRow()).get(position.getColumn());
    }

}
