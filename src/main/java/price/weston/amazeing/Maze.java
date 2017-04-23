package price.weston.amazeing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Implementation of a maze. Current only the Binary Tree algorithm is used for maze generation. Similarly,
 * a simple variation of Tremaux's algorithm is used for traversal.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Maze">Maze</a>
 *
 *
 */
public class Maze {

    private static final Logger logger = LoggerFactory.getLogger(Maze.class);

    private int rows;
    private int columns;
    private CellBlock[][] grid;
    private CellBlock entrance;
    private CellBlock exit;
    private Deque<CellBlock> path = new ArrayDeque<>();

    /**
     * Construct a new maze
     * @param rows the number of rows in the maze
     * @param columns the number of columns in the maze
      */
    public Maze(int rows, int columns) {

        logger.info("Creating maze with {} rows and {} columns", rows, columns);

        if(rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Row and columns must be greater than zero");
        }
        this.rows = rows;
        this.columns = columns;
        this.grid = MazeHelper.populateGrid(rows, columns);
        entrance = new CellBlock(0, 0);
        exit = new CellBlock(rows - 1, columns - 1);

    }

    public Maze(CellBlock[][] grid) {
        this.rows = grid.length;
        this.columns = grid[0].length;
        this.grid = grid;
    }
    public CellBlock getCell(int row, int column) {
        return grid[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }


    public CellBlock getEntrance() {
        return entrance;
    }

    public CellBlock getExit() {
        return exit;
    }

    private CellBlock traverse(CellBlock cellBlock) {

        if(cellBlock.equals(exit)) {
            path.push(cellBlock);
            return cellBlock;
        }

        cellBlock.setVisited(true);
        if(cellBlock.nonVisitedConnectionsCount() != 0) {
            path.push(cellBlock);
        } else {
            while(path.peek().nonVisitedConnectionsCount() == 0) {
                path.pop();
            }
        }

        for (CellBlock connection : cellBlock.getConnections()) {
            if(!connection.isVisited()) {
                traverse(connection);
            }
        }

       return cellBlock;
    }

    public void traverse() {
        traverse(entrance, exit);
    }

    public void traverse(final CellBlock entrance, final CellBlock exit) {
        validateEntrance(entrance);
        validateExit(exit);
        path.clear();
        traverse(grid[getEntrance().getRow()][getEntrance().getColumn()]);
    }


    private void validateEntrance(final CellBlock entrance) {

        if(entrance != null) {
            if(entrance.getRow() < 0) {
                throw new IllegalArgumentException("Maze entrance row must be greater than zero");
            }
            this.entrance = entrance;
        } else {
            logger.warn("No maze entrance provided setting to row {} and column {}", 0, 0);
            this.entrance = new CellBlock(0, 0);
        }
    }

    private void validateExit(final CellBlock exit) {
        if(exit != null) {
            if(entrance.equals(exit)) {
                throw new IllegalStateException("Maze entrance and exit cannot be the same");
            }
            this.exit = exit;
        } else {
            logger.warn("No Maze exit provided setting to row {} and column {}", rows - 1, columns - 1);
            this.exit = new CellBlock(rows - 1, columns - 1);
        }


    }

    public Deque<CellBlock> getPath() {
        return path;
    }

    public CellBlock[][] getGrid() {
        return MazeHelper.copy(grid);
    }
}
