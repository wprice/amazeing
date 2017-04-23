package price.weston.amazeing;

import com.google.common.base.Strings;
import javafx.scene.control.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Utility class for Maze formatting, etc
 *
 */
public class MazeHelper {

    private static final Logger logger = LoggerFactory.getLogger(MazeHelper.class);

    public static void dumpMaze(final Maze maze) {
        dumpMaze(maze, false);
    }

    public static void dumpMaze(final Maze maze, boolean traversal) {
        System.out.println(formatMaze(maze, traversal));
    }

    public static String formatMaze(final Maze maze) {
        return formatMaze(maze, false);
    }

    public static String formatMaze(final CellBlock[][] grid, final Deque<CellBlock> path, boolean traversal) {

        StringBuffer mazeBuffer = new StringBuffer();

        String edge = "|";
        String body = "   ";
        String bottom = "+";

        StringBuffer header = new StringBuffer("+");

        for(int i = 0; i < grid.length; i++) {
            header.append("---+");
        }
        mazeBuffer.append(header + "\n");

        for (int i = 0; i < grid.length; i++) {

            StringBuffer topBuffer = new StringBuffer("|");
            StringBuffer bottomBuffer = new StringBuffer("+");

            for (int j = 0; j < grid[i].length; j++) {

                if(traversal && path.contains(grid[i][j])) {
                    topBuffer.append(" X ");
                } else {
                    topBuffer.append(body);
                }

                if(grid[i][j].connected(i, j + 1)) {
                    topBuffer.append(" ");
                } else {
                    topBuffer.append(edge);
                }

                if(grid[i][j].connected(i + 1, j)) {
                    bottomBuffer.append(body).append("+");
                }else {
                    bottomBuffer.append("---").append("+");
                }

            }
            mazeBuffer.append(topBuffer + "\n").append(bottomBuffer + "\n");

        }

        return mazeBuffer.toString();
    }
    public static String formatMaze(final Maze maze, boolean traversal) {
       return formatMaze(maze.getGrid(), maze.getPath(), traversal);
    }

    public static CellBlock fromValues(String values) {
        String[] tokens = values.split(",");
        return fromValues(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]));
    }

    public static CellBlock fromValues(int rows, int columns) {
        return new CellBlock(rows, columns);
    }

    public static String formatTraversalPath(final Maze maze, MazeTraversalFormat mazeTraversalFormat, MazeTraversalFormatDirection direction) {
        if(mazeTraversalFormat.equals(MazeTraversalFormat.GRID)) {
            return formatMaze(maze, true);
        } else if(mazeTraversalFormat.equals(MazeTraversalFormat.LINE)) {
            return formatTraversalPath(maze.getPath(), false, direction);
        } else if(mazeTraversalFormat.equals(MazeTraversalFormat.STACK)) {
            return formatTraversalPath(maze.getPath(), true, direction);
        } else {
            return formatMaze(maze, true);
        }
    }

    public static String formatTraversalPath(Maze maze) {
        return formatTraversalPath(maze, MazeTraversalFormat.GRID);
    }

    public static String formatTraversalPath(Maze maze, MazeTraversalFormat mazeTraversalFormat) {

        if(mazeTraversalFormat.equals(MazeTraversalFormat.GRID)) {
            return formatMaze(maze, true);
        } else if(mazeTraversalFormat.equals(MazeTraversalFormat.LINE)) {
            return formatTraversalPath(maze.getPath(), false, MazeTraversalFormatDirection.DESCENDING);
        } else if(mazeTraversalFormat.equals(MazeTraversalFormat.STACK)) {
            return formatTraversalPath(maze.getPath(), true, MazeTraversalFormatDirection.DESCENDING);
        } else {
            return formatMaze(maze, true);
        }

    }
    public static String formatTraversalPath(Deque<CellBlock> path) {
        return formatTraversalPath(path, false, MazeTraversalFormatDirection.DESCENDING);
    }
    public static String formatTraversalPath(Deque<CellBlock> path, boolean indent, MazeTraversalFormatDirection direction) {


        StringBuffer pathFormat = new StringBuffer();
        int indentCount = 1;

        Iterator<CellBlock> iter = (direction.equals(MazeTraversalFormatDirection.DESCENDING)) ? path.descendingIterator() : path.iterator();

        while(iter.hasNext()) {
            CellBlock cellBlock = iter.next();
            pathFormat.append("(" + cellBlock.getRow() + "," + cellBlock.getColumn() + ")");

            if(indent) {

                pathFormat.append("\n");
                pathFormat.append(repeatString("\t", indentCount));

                if(iter.hasNext()) {
                    pathFormat.append("-->");
                }
                indentCount += 1;

            } else {
                if(iter.hasNext()) {
                    pathFormat.append("-->");
                }
            }

        }
        return pathFormat.toString();
    }


    private static String repeatString(String value, int repeater) {
        return Strings.repeat(value, repeater);
    }

    public static CellBlock[][] populateGrid(int rows, int columns) {

        return IntStream.range(0, rows)
                        .mapToObj(x -> IntStream.range(0, columns)
                                .mapToObj(y -> new CellBlock(x, y))
                                .toArray(CellBlock[]::new))
                        .toArray(CellBlock[][]::new);
    }

    public static boolean atBoundary(int row, int column, final CellBlock cellBlock, Compass compass) {

        if(compass.equals(Compass.NORTH)) {
            return cellBlock.getRow() - 1 < 0;
        } else if(compass.equals(Compass.SOUTH)) {
            return cellBlock.getRow() + 1 > row;
        } else if(compass.equals(Compass.EAST)) {
            return cellBlock.getColumn() + 1 > column;
        } else if(compass.equals(Compass.WEST)) {
            return cellBlock.getColumn() - 1 < column;
        }

        return false;
    }

    public static CellBlock[][] copy(final CellBlock[][] grid) {
        return Arrays.stream(grid)
                .map((CellBlock[] row) -> row.clone())
                .toArray(value -> new CellBlock[value][]);
    }
}
