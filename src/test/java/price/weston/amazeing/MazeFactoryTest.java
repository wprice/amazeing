package price.weston.amazeing;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wprice on 4/21/17.
 */
public class MazeFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(MazeFactoryTest.class);

    @Before
    public void setUp() {
        logger.info("init");
    }
    @Test
    public void testMazeFactory() {
        Maze maze = MazeFactory.createMaze();
    }
}
