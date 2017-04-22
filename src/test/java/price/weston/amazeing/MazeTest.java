package price.weston.amazeing;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
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
        Maze m = new Maze(10, 10);
        assertThat(m, notNullValue());
        assertThat(m.getEntrance(), notNullValue());
        assertThat(m.getExit(), notNullValue());
    }

    @Test
    public void testCreateMaze() {
        Maze m = new Maze(4, 4);
        m.generateMaze();
        m.dumpMaze();
    }

    @Test
    public void testTraverseMaze() {
        Maze m = new Maze(4, 4);
        m.generateMaze();
        m.traverse(new CellBlock(0, 0), new CellBlock(3, 3));
        m.dumpMaze();
    }

}
