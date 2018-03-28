import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * Maze - A class that is used to read a maze file and store the relevant data about the starting point and maze.
 *
 * <pre>
 *
 * Assignment:     #1
 * Course:         ADEV-3001
 * Date Created:   February 2nd 2018
 *
 * Revision Log
 * Who       When          Reason
 * --------- ------------- ---------------------------------
 *
 * </pre>
 *
 * @author Christian Wenham
 * @version 1.0
 *
 */
public class Maze {

    private Point startPoint;
    private int rowLength;
    private int columnLength;
    private char[][] charMaze;
    private  Stack<Point> pathToFollow;

    /**
     * Constructor, reads a specified file while populating maze details.
     */
    public Maze(String mazeFileName) {

        try {
            Scanner reader = new Scanner(new FileInputStream(mazeFileName));
            String read;
            int count = 0;

            while (reader.hasNextLine()) {
                read = reader.nextLine();
                if(count == 0) {
                    String[] split = read.split(" ");
                    this.rowLength = Integer.parseInt(split[0]);
                    this.columnLength = Integer.parseInt(split[1]);
                    this.charMaze = new char[rowLength][columnLength];
                } else if (count == 1) {
                    String[] split = read.split(" ");
                    this.startPoint = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else if(count > 1) {
                    char[] line = read.toCharArray();
                    for(int i = 0; i < line.length; i++){
                        charMaze[count-2][i] = line[i];
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor, assigns the starting point based on the passed Row/Column values. Assigns internal charMaze variable to the passed existingMaze value.
     * @param startingRow
     * @param startingColumn
     * @param existingMaze
     */
    public Maze(int startingRow, int startingColumn, char[][] existingMaze) {
        if(startingRow < 1 || startingColumn < 1 || startingRow > existingMaze.length - 1 || startingColumn > existingMaze[0].length - 1) {
            throw  new IndexOutOfBoundsException();
        }

        this.rowLength = existingMaze.length;
        this.columnLength = existingMaze[0].length;
        this.charMaze = new char[rowLength][columnLength];

        this.startPoint = new Point(startingRow, startingColumn);
        for(int i = 0; i < existingMaze.length; i++) {
            for(int j = 0; j < existingMaze[i].length; j++) {
                this.charMaze[i][j] = existingMaze[i][j];
            }
        }
    }

    /**
     * @return the starting point.
     */
    public Point getStartingPoint() {
        return startPoint;
    }

    /**
     * @return the number of rows in the maze.
     */
    public int getRowLength() {
        return rowLength;
    }

    /**
     * @return the number of columns in the maze.
     */
    public int getColumnLength() {
        return columnLength;
    }

    /**
     * @return the charMaze array.
     */
    public char[][] getMaze() {
        return charMaze;
    }

    /**
     * Print the maze from the charArray private variable.
     * @return
     */
    public String printMaze() {

        String mazeOutput = "";

        if(pathToFollow != null) {
            if (pathToFollow.isEmpty()) {
                mazeOutput = "No exit found in maze!\n\n";
            } else if (!pathToFollow.isEmpty()) {
                int count = pathToFollow.getSize();
                Node<Point> pointPath = pathToFollow.getHead();
                for (int i = 0; i < count; i++) {
                    mazeOutput =  pointPath.getElement().toString() + "\n" + mazeOutput;
                    pointPath = pointPath.getPrevious();
                }
                mazeOutput = "Path to follow from Start " + startPoint.toString() + " to Exit " + pathToFollow.top().toString() + " - " + pathToFollow.getSize() + " steps:\n" + mazeOutput;
            }
        }


        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < columnLength; j++) {
                mazeOutput += charMaze[i][j];
            }
            if(i < rowLength - 1)
                mazeOutput += "\n";
        }
        return mazeOutput;
    }

    /**
     * Performs a depth first search using the starting point values obtained in the constructor.
     * @return
     */
    public String depthFirstSearch() {
        pathToFollow = new Stack<Point>();

        recursiveDFS(startPoint.getRow(), startPoint.getColumn());

        if(!pathToFollow.isEmpty()) {
            int count = pathToFollow.getSize();
            Node<Point> point = pathToFollow.getHead();
            point = point.getPrevious();
            for (int i = 0; i < count - 1; i++) {
                charMaze[point.getElement().getRow()][point.getElement().getColumn()] = '.';
                point = point.getPrevious();
            }
        }

        return printMaze();
    }

    /**
     * Performs a breadth first search using the starting point values obtained in the constructor.
     * @return
     */
    public String breadthFirstSearch() {
        pathToFollow = new Stack<Point>();
        Stack<Point> path = new Stack<Point>();
        Point cur, end, north, east, west, south, parentPoint;
        Queue<Point> queue = new Queue<Point>();

        queue.enqueue(startPoint);

        while (!queue.isEmpty()) {

            cur = queue.dequeue();
            if(charMaze[cur.getRow()][cur.getColumn()] == 'E') {
                end = cur;
                path.push(end);
                parentPoint = end.getParentPoint();
                while (path.top() != startPoint) {
                    path.push(parentPoint);
                    parentPoint = parentPoint.getParentPoint();
                }
                break;
            }

            charMaze[cur.getRow()][cur.getColumn()] = 'V';
            south = new Point(cur.getRow() + 1, cur.getColumn());
            if(charMaze[south.getRow()][south.getColumn()] != 'W' && charMaze[south.getRow()][south.getColumn()] != 'V') {
                queue.enqueue(south);
                south.setParentPoint(cur);
            }

            east = new Point(cur.getRow(), cur.getColumn() + 1);
            if(charMaze[east.getRow()][east.getColumn()] != 'W' && charMaze[east.getRow()][east.getColumn()] != 'V') {
                queue.enqueue(east);
                east.setParentPoint(cur);
            }

            west = new Point(cur.getRow(), cur.getColumn() - 1);
            if(charMaze[west.getRow()][west.getColumn()] != 'W' && charMaze[west.getRow()][west.getColumn()] != 'V') {
                queue.enqueue(west);
                west.setParentPoint(cur);
            }

            north = new Point(cur.getRow() - 1, cur.getColumn());
            if(charMaze[north.getRow()][north.getColumn()] != 'W' && charMaze[north.getRow()][north.getColumn()] != 'V') {
                queue.enqueue(north);
                north.setParentPoint(cur);
            }
        }

        while(!path.isEmpty()) {
            if(charMaze[path.top().getRow()][path.top().getColumn()] != 'E'){
                charMaze[path.top().getRow()][path.top().getColumn()] = '.';
            }
            pathToFollow.push(path.pop());
        }

        return printMaze();
    }

    /**
     * @return the stack created during the search.
     */
    public Stack<Point> getPathToFollow() {
        Stack<Point> path = new Stack<Point>();

        if (pathToFollow == null) {
            throw new UnsupportedOperationException();
        }

        while (!pathToFollow.isEmpty()) {
            path.push(pathToFollow.pop());
        }

        return path;
    }

    /**
     * Recursive call for depth first search.
     * @param row
     * @param col
     * @return
     */
    public boolean recursiveDFS(int row, int col) {

        if(charMaze[row][col] == 'E') {
            this.pathToFollow.push(new Point(row, col));
            return true;
        }
        if(charMaze[row][col] == 'W' || charMaze[row][col] == 'V') {
            return false;
        }

        this.pathToFollow.push(new Point(row, col));
        charMaze[row][col] = 'V';

        if (recursiveDFS(row + 1, col) || recursiveDFS(row, col + 1) || recursiveDFS(row, col - 1) || recursiveDFS(row - 1, col)) {
            return true;
        }

        pathToFollow.pop();
        return false;
    }
}
