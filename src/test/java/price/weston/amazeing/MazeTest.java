package price.weston.amazeing;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by wprice on 4/21/17.
 */
public class MazeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testMazeInvalidRows() {
        new Maze(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMazeInvalidColumns() {
        new Maze(10, 0);

    }

    @Test
    public void testMazeInit() {
        Maze maze = new Maze(10, 10);

        assertThat(maze, notNullValue());
        assertThat(maze.getEntrance(), notNullValue());
        assertThat(maze.getExit(), notNullValue());
        assertThat(maze.getRows(), equalTo(10));
        assertThat(maze.getColumns(), equalTo(10));
    }

    @Test
    public void testCreateMaze() {
        Maze maze = new Maze(4, 4);
        maze.generateMaze();
        MazeHelper.dumpMaze(maze);
    }

    @Test
    public void testTraverseMaze() {
        Maze maze = new Maze(4, 4);
        maze.generateMaze();
        maze.traverse(new CellBlock(0, 0), new CellBlock(3, 3));
        MazeHelper.dumpMaze(maze, true);
    }

    @Test
    public void testGridCopy() {
        Maze m = new Maze(4, 4);
        CellBlock[][] grid = m.getGrid();
        assertThat(grid, notNullValue());
        System.out.println(Arrays.deepToString(grid));
    }


}
