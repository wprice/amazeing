package price.weston.amazeing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by wprice on 4/21/17.
 */
public class Maze {

    private int rows;
    private int columns;
    private CellBlock[][] grid;

    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new CellBlock[rows][columns];
        initGrid();
    }

    public CellBlock getCell(int row, int column) {
        return grid[row][column];
    }

    private void initGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new CellBlock(i, j);
            }
        }
    }

    public void dumpMaze() {

        for(int i = 0; i < grid.length; i++) {
           System.out.print("-" + "\t");
        }
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void generateMaze(int startRow, int startColumn) {

        CellBlock startBlock = grid[startRow][startColumn];
        generateMaze(startBlock);

    }

    public void generateMaze(final CellBlock cellBlock) {

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
}
