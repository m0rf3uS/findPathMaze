import java.util.ArrayList;
import java.util.Scanner;

public class FindPathInputReaderStdIn extends AbstractFindPathInputReader {
    
    public void outputPath() {

        Maze maze = new Maze();
        int currentLine = 0;
        Scanner scanner = new Scanner(System.in);
        String input = "something";
        boolean mazeValidity = true;
        boolean startingPosition = false;
        boolean targetPosition = false;
        
        System.out.println("Please insert your maze.");
        System.out.println("Type --stop-- to finish insertion");
        while (!input.equals("stop")) {
            input = scanner.nextLine();
            ArrayList<Element> rowElements = new ArrayList<Element>();
            for (int i=0; i < input.length(); i++) {
                Position position = new Position(currentLine, i);
                Element element = new Element(String.valueOf(input.charAt(i)), position);
                if (element.getState() == State.START) {
                    maze.setStartingPosition(position);
                    if (startingPosition) {
                        mazeValidity = false;
                        break;
                    }
                    startingPosition = true;
                } else if (element.getState() == State.TARGET) {
                    maze.setTargetPosition(position);
                    if (targetPosition) {
                        mazeValidity = false;
                        break;
                    }
                    targetPosition = true;
                }
                rowElements.add(element);
            }
            maze.getMazeElements().add(rowElements);
            currentLine++;
        }
        scanner.close();

        if (mazeValidity) {
            this.findPath(maze);
        } else {
            System.out.println("Maze is not valid");
        }
    }
}
