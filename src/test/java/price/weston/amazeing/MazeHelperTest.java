package price.weston.amazeing;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by wprice on 4/23/17.
 */
public class MazeHelperTest {


    @Test
    public void testBoundary() {

        CellBlock cellBlock = new CellBlock(0, 0);
        Assert.assertTrue(MazeHelper.atBoundary(0, 0, cellBlock, Compass.NORTH));

        cellBlock = new CellBlock(3, 2);
        Assert.assertFalse(MazeHelper.atBoundary(2, 2, cellBlock, Compass.NORTH));

    }

    @Test
    public void testPopulateGrid() {
        int rows = 3;
        int columns = 3;
        CellBlock[][] grid = MazeHelper.populateGrid(rows, columns);
        assertThat(grid, notNullValue());
        assertThat(grid.length, equalTo(rows));
        assertThat(grid[0].length, equalTo(columns));

    }

    @Test
    public void testPathFormat() {
        Maze maze = MazeFactory.createMaze(4, 4);
        maze.traverse(new CellBlock(0, 0), new CellBlock(3, 3));
        Deque<CellBlock> path = maze.getPath();
        assertThat(path, notNullValue());

        //TODO we should really be using the logs here, but there is an issue with the formatting
        System.out.println(MazeHelper.formatTraversalPath(path));
        System.out.println(MazeHelper.formatTraversalPath(path, false, MazeTraversalFormatDirection.DESCENDING));
        System.out.println(MazeHelper.formatTraversalPath(path, true, MazeTraversalFormatDirection.ASCENDING));


    }

    @Test
    public void testGridCopy() {
        CellBlock[][] grid = MazeHelper.populateGrid(3, 3);
        assertThat(grid, equalTo(MazeHelper.copy(grid)));
    }
}
