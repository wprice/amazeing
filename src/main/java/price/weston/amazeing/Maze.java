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
        this.grid = new CellBlock[rows][columns];


        init();

    }

    public CellBlock getCell(int row, int column) {
        return grid[row][column];
    }

    private void init() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new CellBlock(i, j);
            }
        }

        entrance = new CellBlock(0, 0);
        exit = new CellBlock(rows - 1, columns - 1);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void generateMaze() {

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                CellBlock cb = grid[i][j];
                CellBlock connection = getAdjoiningBlock(cb);
                if(connection != null) {
                    cb.addConnection(connection);
                }

            }
        }
    }

    /**
     * Note, this really provides the core of the Binary Tree logic in that we need to decide whether or not
     * to go South or East or do nothing at all.
     *
     * @param cellBlock
     * @return
     */
    private CellBlock getAdjoiningBlock(final CellBlock cellBlock) {
        List<CellBlock> cellBlocks = new ArrayList<>();

        if(!(cellBlock.getRow() + 1 > grid.length - 1)) {
            cellBlocks.add(grid[cellBlock.getRow() + 1][cellBlock.getColumn()]);
        }

        if(!(cellBlock.getColumn() + 1 > grid[cellBlock.getRow()].length - 1)) {
            cellBlocks.add(grid[cellBlock.getRow()][cellBlock.getColumn() + 1]);
        }

        return (!cellBlocks.isEmpty()) ? cellBlocks.get(new Random().nextInt(cellBlocks.size())) : null;
    }

    public CellBlock getEntrance() {
        return entrance;
    }

    public CellBlock getExit() {
        return exit;
    }

    private CellBlock traverse(CellBlock cellBlock) {

        System.out.println(cellBlock.getRow() + "   " + cellBlock.getColumn());

        if(cellBlock.equals(exit)) {
            System.out.println("Maze finished at " + cellBlock);
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
            this.exit = new CellBlock(rows - 1, columns - 1);
        }


    }

    public CellBlock[][] getGrid() {
        return Arrays.stream(grid)
                .map((CellBlock[] row) -> row.clone())
                .toArray(value -> new CellBlock[value][]);
    }

    public Deque<CellBlock> getPath() {
        return path;
    }
}
