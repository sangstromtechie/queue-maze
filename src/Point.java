/**
 * Point - A class representing a point in a maze with a row and column.
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
public class Point {

    private int row, column;

    /**
     * Constructor, initializes private row and column fields using parameter values.
     * @param row
     * @param column
     */
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return private row field value.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return private column field value.
     */
    public int getColumn() {
        return column;
    }

    /**
     * @return [row, column]
     */
    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }
}
