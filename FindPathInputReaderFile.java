import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FindPathInputReaderFile extends AbstractFindPathInputReader {




    public void outputPath(String pathToFile) {

        Maze maze = new Maze();
        int currentLine = 0;
        boolean mazeValidity = true;
        boolean startingPosition = false;
        boolean targetPosition = false;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF8"));
        
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Element> rowElements = new ArrayList<Element>();
            for (int i = 0; i < line.length(); i++) {
                Position position = new Position(currentLine, i);
                Element element = new Element(String.valueOf(line.charAt(i)), position);
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
            br.close();
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mazeValidity) {
            this.findPath(maze);
        } else {
            System.out.println("Maze is not valid");
        }
    }
}


