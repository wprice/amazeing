package price.weston.amazeing;

import org.junit.Test;

import java.util.Deque;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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

        Maze maze = MazeFactory.createMaze(4, 4);
        assertThat(maze, notNullValue());
        assertThat(maze.getRows(), equalTo(4));
        assertThat(maze.getColumns(), equalTo(4));
    }

    @Test
    public void testTraverseMaze() {
        Maze maze = MazeFactory.createMaze(4, 4);
        maze.traverse(new CellBlock(0, 0), new CellBlock(3, 3));
    }

}
