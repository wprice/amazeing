package price.weston.amazeing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        assertThat(m, notNullValue());
    }


    @Test
    public void testCreateMaze() {
        Maze m = new Maze(3, 3);
        m.generateMaze(2, 0);
        m.dumpMaze();
    }
}
