package price.weston.amazeing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Deque;
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
//        logger.info(formatMaze(maze, traversal));
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
}
