package price.weston.amazeing;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wprice on 4/21/17.
 */
public class MazeTest {

    @Test
    public void testMazeInit() {
        Maze m = new Maze(10, 10);
        Assert.assertTrue(m.getRows() == 10 && m.getColumns() == 10);
    }

    @Test
    public void tesDumpMaze() {
        Maze m = new Maze(10, 10);
        m.dumpMaze();
    }

    @Test
    public void testCreateMaze() {
        Maze m = new Maze(4, 4);
        m.generateMaze(0, 0);
        m.dumpMaze();
    }
}
