
public class Element {

    private State state;
    private Position position;

    public Element(String state, Position position) {
        this.state = getStateFromString(state);
        this.position = position;
    }

    public Element(State state, Position position) {
        this.state = state;
        this.position = position;
    }

    public void setState(String state) {
        this.state = getStateFromString(state);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    private State getStateFromString(String character) {
        switch (character) {
            case "#":
                return State.BLOCKED;
            case "S":
                return State.START;
            case "X":
                return State.TARGET;
            case ".":
            default:
                return State.FREE;
        }
    }


    
}
