package price.weston.amazeing;

import com.google.common.base.Strings;
import javafx.scene.control.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Created by wprice on 4/21/17.
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

    public static String formatMaze(final Maze maze, boolean traversal) {

        CellBlock[][] grid = maze.getGrid();
        Deque<CellBlock> path = maze.getPath();
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

                if(path.contains(grid[i][j]) && traversal) {
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

    public static CellBlock fromValues(String values) {
        String[] tokens = values.split(",");
        return fromValues(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1]));
    }

    public static CellBlock fromValues(int rows, int columns) {
        return new CellBlock(rows, columns);
    }

    public static String formatTraversalPath(Maze maze, MazeTraversalFormat mazeTraversalFormat) {

        if(mazeTraversalFormat.equals(MazeTraversalFormat.GRID)) {
            return formatMaze(maze, true);
        } else if(mazeTraversalFormat.equals(MazeTraversalFormat.LINE)) {
            return formatTraversalPath(maze.getPath(), false);
        } else if(mazeTraversalFormat.equals(MazeTraversalFormat.STACK)) {
            return formatTraversalPath(maze.getPath(), true);
        } else {
            return formatMaze(maze, true);
        }

    }
    public static String formatTraversalPath(Deque<CellBlock> path) {
        return formatTraversalPath(path, false);
    }
    public static String formatTraversalPath(Deque<CellBlock> path, boolean indent) {


        StringBuffer pathFormat = new StringBuffer();
        int indentCount = 1;

        Iterator<CellBlock> iter = path.descendingIterator();

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
}
